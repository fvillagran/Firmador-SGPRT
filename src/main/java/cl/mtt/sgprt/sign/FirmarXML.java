package cl.mtt.sgprt.sign;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.apache.xml.security.exceptions.XMLSecurityException;

public abstract class FirmarXML extends Thread
{
  private Logger log = Logger.getLogger(FirmarXML.class);
  String rut;
  char dv;

  public abstract void init(ResourceBundle paramResourceBundle);

  public abstract void firmar(DocumentoEnvio paramDocumentoEnvio)
    throws XMLSecurityException, IOException;

  public void buscaRut(X509Certificate certificado)
    throws IOException
  {

    String codExtValue = "2.5.29.17";

    String rutCodificado = getDERPrintableString(certificado.getExtensionValue(codExtValue));
    String auxRut = rutCodificado.substring(20, Math.min(32, rutCodificado.length()));
    StringBuffer _rut = new StringBuffer();
    int i;
    for (i=0; i < auxRut.length(); i++){        
      char caracter = auxRut.charAt(i);
      if (!Character.isDigit(caracter)) break;
        _rut.append(caracter);
    }

    this.rut = _rut.toString();
    this.dv = auxRut.charAt(i + 1);

    log.info("------------------------");
    log.info("Rut: " + getRut() + "-" + getDv());
    log.info("------------------------");
  }

  public String getDERPrintableString(byte[] string)
  {
    char[] cs = new char[string.length];

    for (int i = 0; i != cs.length; i++)
    {
      cs[i] = ((char)(string[i] & 0xFF));
    }

    return new String(cs);
  }

  public String getRut() {
    return this.rut;
  }

  public String getDv() {
    return new String(new char[] { this.dv }).toUpperCase();
  }
  
  /*
  public void datosCertificadoX509(X509Certificate cert) {
    this.log.info("------------------------");
    this.log.info("Datos Certificado X.509");
    this.log.info("------------------------");
    this.log.info("version: " + cert.getVersion());
    this.log.info("tipo: " + cert.getType());
    this.log.info("algoritmo de encriptaci�n: " + cert.getSigAlgName());
    this.log.info("autoridad certificadora: " + cert.getIssuerDN().getName());
    this.log.info("titular: " + cert.getSubjectDN());

    Collection extensiones = cert.getCriticalExtensionOIDs();
    int i = 0;
    for (String s : extensiones) {
      this.log.info(++i + " :" + s);
    }
    log.info("------------------------");
  }
  */
}
