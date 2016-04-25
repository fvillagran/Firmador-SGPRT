/*     */ package cl.mtt.sgprt.sign;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Enumeration;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.xml.security.Init;
/*     */ import org.apache.xml.security.exceptions.XMLSecurityException;
/*     */ import org.apache.xml.security.signature.XMLSignature;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ public class FirmarXMLPKCS12 extends FirmarXML
/*     */ {
/*     */   private static String LLAVE_PRIVADA;
/*  22 */   private static Logger log = Logger.getLogger(FirmarXMLPKCS12.class);
/*     */   private char[] pin;
/*     */   protected X509Certificate certificado;
/*     */   protected PrivateKey privateKey;
/*     */ 
/*     */   FirmarXMLPKCS12(char[] password)
/*     */   {
/*  30 */     this.pin = password;
/*     */   }
/*     */ 
/*     */   public void init(ResourceBundle rbInit) {
/*  34 */     KeyStore pkcs12 = null;
/*  35 */     String preAlias = null;
/*     */ 
/*  37 */     Init.init();
/*  38 */     LLAVE_PRIVADA = rbInit.getString("pkcs12.llavePrivada.archivo");
/*     */     try
/*     */     {
/*  41 */       FileInputStream fileinputstream = new FileInputStream(LLAVE_PRIVADA);
/*  42 */       pkcs12 = KeyStore.getInstance("PKCS12");
/*  43 */       pkcs12.load(fileinputstream, this.pin);
/*  44 */       preAlias = (String)pkcs12.aliases().nextElement();
/*  45 */       if (preAlias == null)
/*  46 */         throw new RuntimeException("El archivo no contiene ningún Certificado ni llave privada");
/*  47 */       if (!pkcs12.isKeyEntry(preAlias)) {
/*  48 */         throw new RuntimeException("El archivo no contiene una llave privada");
/*     */       }
/*  50 */       Certificate[] preCerts = pkcs12.getCertificateChain(preAlias);
/*  51 */       X509Certificate[] certs = new X509Certificate[preCerts.length];
/*     */ 
/*  53 */       for (int i = 0; i < certs.length; i++)
/*  54 */         certs[i] = ((X509Certificate)preCerts[i]);
/*  55 */       this.certificado = certs[0];
/*  56 */       //datosCertificadoX509(this.certificado);
/*  57 */       this.privateKey = ((PrivateKey)pkcs12.getKey(preAlias, this.pin));
/*     */ 
/*  59 */       if (this.certificado == null)
/*  60 */         throw new RuntimeException("X509 certificate not found");
/*  61 */       buscaRut(this.certificado);
/*     */     }
/*     */     catch (Exception e) {
/*  64 */       if (((e instanceof IOException)) && 
/*  65 */         ((e.getCause() instanceof BadPaddingException)))
/*  66 */         throw new RuntimeException("Clave certificado incorrecta!");
/*  67 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void firmar(DocumentoEnvio documentoEnvio) throws XMLSecurityException, IOException {
/*  72 */     firmar(documentoEnvio.getRaizXml(), documentoEnvio.getId(), "");
/*     */   }
/*     */ 
/*     */   protected void firmar(Element raiz, String id, String nameSpaceSignature)
/*     */     throws XMLSecurityException, IOException
/*     */   {
/*  78 */     XMLSignature sig = null;
/*  79 */     if (raiz == null) {
/*  80 */       log.error("Datos incompletos para firmar documento");
/*  81 */       return;
/*     */     }
/*     */     try
/*     */     {
/*  85 */       XMLSignature.setDefaultPrefix("http://www.w3.org/2000/09/xmldsig#", nameSpaceSignature);
/*     */ 
/*  87 */       sig = new XMLSignature(
/*  88 */         raiz.getOwnerDocument(), 
/*  89 */         "", 
/*  90 */         "http://www.w3.org/2000/09/xmldsig#rsa-sha1");
/*     */ 
/*  92 */       sig.addDocument("#" + id);
/*     */ 
/*  94 */       sig.addKeyInfo(this.certificado.getPublicKey());
/*  95 */       sig.addKeyInfo(this.certificado);
/*     */ 
/*  98 */       raiz.appendChild(sig.getElement());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 102 */       log.error("Excepcion antes de firmar:", e);
/* 103 */       throw new RuntimeException(e);
/*     */     }
/*     */ 
/* 106 */     log.debug("Se procede a firmar:" + id);
/* 107 */     sig.sign(this.privateKey);
/* 108 */     log.debug("Firmado: " + id);
/*     */   }
/*     */ }

/* Location:           F:\proyectos\PRT\SgprtClientV3\sgprt-firmador.jar
 * Qualified Name:     cl.mtt.sgprt.sign.FirmarXMLPKCS12
 * JD-Core Version:    0.6.2
 */