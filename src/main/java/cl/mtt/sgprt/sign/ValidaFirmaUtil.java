package cl.mtt.sgprt.sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.security.Init;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class ValidaFirmaUtil
{
  private static Log log = LogFactory.getLog(ValidaFirmaUtil.class);
  private static final int FUNCIONARIO = 0;
  private static final int ENTIDAD_CERTIFICADORA = 1;
  private Element elementoRaiz;
  private List listaErrores = new ArrayList();
  private Properties configuracion;
  private X509Certificate certificadoCRT;
  private String textoMensajes;

  public ValidaFirmaUtil(Element raiz)
  {
    this.elementoRaiz = raiz;
    log.info("-------------------------------");
    log.info("Validación de Firma Digital");
    log.info("-------------------------------");
    log.info("Firma Digital " + (esFirmaValida(new Date()) ? "Válida." : "No Válida."));
    log.info("-------------------------------");
  }

  boolean esFirmaValida(Date fecRecepcion) {
    log.debug("Iniciando Validacion de Firma Digital.");
    try {
      Init.init();
    } catch (Exception e) {
      log.error("Problemas al inicializar el engine de validacion de Firma Digital.", 
        e);
      agregaError("IN2a");
      return false;
    }

    log.debug("esCRTValido() : " + esCRTValido());
    log.debug("esValidaFechaCertificado() : " + esValidaFechaCertificado(fecRecepcion));

    return (esCRTValido()) && (esValidaFechaCertificado(fecRecepcion));
  }

  private boolean esCRTValido() {
    log.debug("Chequeando validez del documento XML firmado.");
    try {
      Element sigElement = (Element)this.elementoRaiz.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", 
        "Signature")
        .item(0);
      XMLSignature signature = new XMLSignature(sigElement, "");
      KeyInfo keyInfo = signature.getKeyInfo();
      if (keyInfo == null) {
        agregaError("2a");
        return false;
      }
      this.certificadoCRT = signature.getKeyInfo().getX509Certificate();
      if (this.certificadoCRT != null) {
        boolean esFirmaValida = signature.checkSignatureValue(this.certificadoCRT);
        if (!esFirmaValida) {
          agregaError("2b");
        }
        return esFirmaValida;
      }
      agregaError("2c");
      return false;
    }
    catch (Exception e)
    {
      agregaError("2d");
    }return false;
  }

  private boolean esValidaFechaCertificado(Date fecRecepcion)
  {
    log.debug("Chequeando validez de la fecha del certificado digital.");
    try {
      this.certificadoCRT.checkValidity(fecRecepcion);
      return true;
    } catch (CertificateExpiredException e) {
      agregaError("2e");
      return false;
    } catch (CertificateNotYetValidException e) {
      agregaError("2f");
    }return false;
  }

  private boolean esValidaEntidadCertificadora()
  {
    log.debug("Chequeando validez del certificado respecto a la Entidad Certicadora.");
    X509Certificate certificadoCA = null;
    try {
      String pathCertificados = this.configuracion.getProperty("PathCertificados");
      Long rutEntidad = buscaRutCertificado(1);
      String filenameCer = pathCertificados + rutEntidad + ".cer";
      log.debug("busco certificado de la Entidad Certicadora " + filenameCer);
      File archivoCer = new File(filenameCer);
      if (!archivoCer.exists()) {
        log.error("No se puede leer el certificado de la Entidad Certificadora en " + archivoCer);
        agregaError("IN2b");
        return false;
      }
      InputStream input = new FileInputStream(archivoCer);
      CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
      certificadoCA = (X509Certificate)certificatefactory.generateCertificate(input);
      input.close();
    } catch (Exception e) {
      log.error("Problemas en la lectura del certificado de la Entidad Certificadora.", 
        e);
      agregaError("IN2b");
      return false;
    }
    try {
      PublicKey publickey = certificadoCA.getPublicKey();
      this.certificadoCRT.verify(publickey);
      return true;
    } catch (Exception e) {
      log.error("No corresponde el certificado de la Entidad Certificadora con el certificado de la firma", e);
      agregaError("2g");
    }return false;
  }

  private boolean esValidoRespectoCRL()
  {
    log.debug("Chequeando que el certificado digital no haya sido revocado.");
    X509CRL listaRevocacionCA = null;
    try {
      Security.addProvider(new BouncyCastleProvider());

      String pathCertificados = this.configuracion.getProperty("PathCertificados");
      Long rutEntidad = buscaRutCertificado(1);
      String filenameCrl = pathCertificados + rutEntidad + ".crl";
      log.debug("busco lista de revocacion de la Entidad Certicadora " + filenameCrl);
      File archivoCrl = new File(filenameCrl);
      if (!archivoCrl.exists()) {
        log.error("No se puede leer Lista de Revocaciones de Certificados en " + archivoCrl);
        return true;
      }
      InputStream input = new FileInputStream(archivoCrl);
      CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509", 
        "BC");
      listaRevocacionCA = (X509CRL)certificatefactory.generateCRL(input);
      input.close();
    } catch (Exception e) {
      log.error("Problemas en la lectura de la Lista de Revocaciones de Certificados.", 
        e);
      agregaError("IN2c");
      return false;
    }
    if (listaRevocacionCA.isRevoked(this.certificadoCRT)) {
      agregaError("2h");
      return false;
    }
    return true;
  }

  Long buscaRutCertificadoFuncionario() {
    return buscaRutCertificado(0);
  }

  private Long buscaRutCertificado(int tipoRut) {
    log.debug("Extrayendo RUT desde el certificado digital");
    try
    {
      String codExtValue;
      if (tipoRut == 0)
        codExtValue = "2.5.29.17";
      else
        codExtValue = "2.5.29.18";
      DERPrintableString rutCodificado = new DERPrintableString(this.certificadoCRT.getExtensionValue(codExtValue));
      String auxRut = rutCodificado.getString().substring(20, 
        Math.min(32, rutCodificado.getString().length()));
      log.debug("Extrayendo RUT desde el certificado digital: " + auxRut);
      StringBuffer rut = new StringBuffer();
      for (int i = 0; i < auxRut.length(); i++)
      {
        char caracter = auxRut.charAt(i);
        if (Character.isDigit(caracter))
          rut.append(caracter);
        else if (caracter == '-')
            break;
      }
      return new Long(rut.toString());
    } catch (Exception e) {
      log.debug("Error al recuperar RUT desde el certificado digital.");
    }return null;
  }

  private void agregaError(String codError)
  {
    log.error(codError);
  }
}
