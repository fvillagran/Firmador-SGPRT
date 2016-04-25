package cl.mtt.sgprt.sign;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.ProviderException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.security.auth.login.FailedLoginException;
import org.apache.log4j.Logger;
import org.apache.xml.security.Init;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.SignedInfo;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.w3c.dom.Element;
import sun.security.pkcs11.SunPKCS11;

public class FirmarXMLPKCS11 extends FirmarXML
{
  private static Logger log = Logger.getLogger(FirmarXMLPKCS11.class);
  private char[] pin;
  protected X509Certificate certificado;
  protected PrivateKey privateKey;

  FirmarXMLPKCS11(char[] password)
  {
    this.pin = password;
  }

  public void init(ResourceBundle rbInit)
  {
    Init.init();

    String configName = rbInit.getString("archivo_pkcs11");
    log.info("-------------------------");
    log.info("Buscando Driver eToken...");
    Provider p = null;
    try {
      p = new SunPKCS11(configName);
    } catch (ProviderException pe) {
      throw new RuntimeException("Driver eToken no ha sido instalado!", pe);
    }
    Security.addProvider(p);
    if (log.isDebugEnabled()) {
      p.list(System.out);
    }
    log.info("-------------------------");
    log.info("Driver eToken instalado");
    log.info("-------------------------");
    try
    {
      KeyStore ks = null;
      try {
        ks = KeyStore.getInstance("PKCS11");
      }
      catch (KeyStoreException ksEx) {
        throw new RuntimeException("eToken no encontrado!", ksEx);
      }
      log.info("-------------------------");
      log.info("eToken encontrado");
      if (log.isDebugEnabled()) {
        p.list(System.out);
      }
      log.info("-------------------------");
      try
      {
        ks.load(null, this.pin);
      } catch (IOException ioEx) {
        if ((ioEx.getCause() instanceof FailedLoginException)) {
          throw new RuntimeException("Clave secreta incorrecta");
        }

        throw new RuntimeException("Error de lectura de eToken.", ioEx);
      }

      Enumeration e = ks.aliases();
      String alias = null;
      while (e.hasMoreElements()) {
        alias = String.valueOf(e.nextElement());
        log.info("Usuario:" + alias);
      }

      this.privateKey = ((PrivateKey)ks.getKey(alias, this.pin));
      log.debug("Llave privada eToken: " + this.privateKey);
      log.debug("------------------------");

      if (alias == null) {
        throw new RuntimeException("El archivo no contiene ningun certificado ni llave privada");
      }

      if (!ks.isKeyEntry(alias)) {
        throw new RuntimeException("El archivo no contiene una llave privada");
      }

      Certificate[] preCerts = ks.getCertificateChain(alias);
      X509Certificate[] certs = new X509Certificate[preCerts.length];

      for (int i = 0; i < certs.length; i++) {
        certs[i] = ((X509Certificate)preCerts[i]);
        this.certificado = certs[0];
      }

      if (this.certificado == null) {
        throw new RuntimeException("certificado X509 no encontrado");
      }
      //datosCertificadoX509(this.certificado);
      buscaRut(this.certificado);
    }
    catch (RuntimeException runEx) {
      throw runEx;
    }
    catch (Exception e) {
      throw new RuntimeException("Error inesperado", e);
    }
  }

  public void firmar(DocumentoEnvio documentoEnvio) throws XMLSecurityException, IOException
  {
    firmar(documentoEnvio.getRaizXml(), "");
  }

  protected void firmar(Element raiz, String nameSpaceSignature)
    throws XMLSecurityException, IOException
  {
    XMLSignature sig = null;

    if (raiz == null) {
      throw new RuntimeException("Datos incompletos para firmar documento");
    }
    XMLSignature.setDefaultPrefix("http://www.w3.org/2000/09/xmldsig#", nameSpaceSignature);
    sig = new XMLSignature(raiz.getOwnerDocument(), "", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");

    raiz.appendChild(sig.getElement());

    Transforms transforms = new Transforms(raiz.getOwnerDocument());
    transforms.addTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");

    sig.addDocument("", transforms, "http://www.w3.org/2000/09/xmldsig#sha1");

    sig.addKeyInfo(this.certificado.getPublicKey());
    sig.addKeyInfo(this.certificado);

    log.debug("Se procede a firmar documento");
    sig.sign(this.privateKey);
    log.debug("Documento firmado OK.");
  }

  @Deprecated
  protected void firmar(Element raiz, String id, String nameSpaceSignature) throws XMLSecurityException, IOException {
    XMLSignature sig = null;

    if (raiz == null) {
      throw new RuntimeException("Datos incompletos para firmar documento");
    }
    XMLSignature.setDefaultPrefix("http://www.w3.org/2000/09/xmldsig#", nameSpaceSignature);
    sig = new XMLSignature(raiz.getOwnerDocument(), "", "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
    sig.addDocument("#" + id);

    sig.addKeyInfo(this.certificado.getPublicKey());
    sig.addKeyInfo(this.certificado);

    raiz.appendChild(sig.getElement());

    log.debug("Se procede a firmar documento id #" + id);

    sig.sign(this.privateKey);

    log.debug(new String(sig.getSignedInfo().getCanonicalizedOctetStream()));

    log.debug("Documento id: #" + id + " firmado OK.");
  }
}
