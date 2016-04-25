package cl.mtt.sgprt.binding;

import cl.mtt.sgprt.util.factory.AbstractCertificado;
import cl.mtt.sgprt.util.factory.CertificadoFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReadWithScanner{
    
  private static final Log log = LogFactory.getLog(ReadWithScanner.class);
  
  private final String tagCrtResumen = "[ID_CRT_PRT]";
  private final File fFile;
  private final AbstractCertificado certificado;
  private String crtResumen = "";
  private String xml;

  public ReadWithScanner(String aFileName){
    this.fFile = new File(aFileName);
    
    // factory de certificados
    if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOA1))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOA1);
    else if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOA2))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOA2);
    else if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOB))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOB);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOA1))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOA1);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOA2))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOA2);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOB))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOB);  
    else
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_RESUMEN);
  }

  public ReadWithScanner(String aFileName, String xmlTemplate){
    this.fFile = new File(aFileName);
    this.xml = xmlTemplate;
    
    // factory de certificados
    if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOA1))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOA1);
    else if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOA2))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOA2);
    else if(aFileName.contains(AbstractCertificado.CERT_ANALISISGASES_TIPOB))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_ANALISISGASES_TIPOB);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOA1))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOA1);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOA2))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOA2);
    else if(aFileName.contains(AbstractCertificado.CERT_REVTECNICA_TIPOB))
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_REVTECNICA_TIPOB);  
    else
        certificado = CertificadoFactory.getCertificado(AbstractCertificado.CERT_RESUMEN);
  }

  /**
   * procesa scanner linea por linea
   */  
  public void processLineByLine(){
    try {
      Scanner scanner = new Scanner(this.fFile);
      while (scanner.hasNextLine())
        parseLine(scanner.nextLine());
      scanner.close();
    }
    catch (FileNotFoundException e) {
        log.error("Error al procesar scanner de linea", e);
    }
  }

  /**
   * procesa scanner de resumen linea por linea
   */
  public void processLineByLineResumen(){
    try {
        Scanner scanner = new Scanner(this.fFile);
        while (scanner.hasNextLine())
          parseLineResumen(scanner.nextLine());      
        scanner.close();
        this.xml = this.xml.replace(this.tagCrtResumen, this.crtResumen);        
    }
    catch (FileNotFoundException e) {
        log.error("Error al procesar scanner de linea resumen", e);
    }    
  }

  /**
   * parsea linea resumen
   * @param line 
   */  
  private void parseLineResumen(String line){
    Scanner lineScanner = new Scanner(line);
    lineScanner.useDelimiter("=");
    String name = lineScanner.next();
    String value = "";

    if (lineScanner.hasNext()) {
      value = lineScanner.next();
    }

    if (this.tagCrtResumen.equals(name.trim())) {
      if (!"".equals(value.trim()))
        this.crtResumen = this.crtResumen.concat("<CRT ID_CRT_PRT=\"" + value + "\"" + " />");
    }
    else
      this.xml = this.xml.replace(name.trim(), value.trim());
  }

  /**
   * parsea linea
   * @param line 
   */  
  private void parseLine(String line){
    Scanner lineScanner = new Scanner(line);
    lineScanner.useDelimiter("=");
    String name = lineScanner.next();
    String value = "";
    if (lineScanner.hasNext()) {
      value = lineScanner.next();
      //this.xml = this.xml.replace(name.trim(), value.trim());
      this.xml = replaceWithMinOccurs(name.trim(), value.trim(), this.xml);
    }
    else {
      //this.xml = this.xml.replace(name.trim(), value);
      this.xml = replaceWithMinOccurs(name.trim(), value, this.xml);
    }
  }
  
  /**
   * Reemplazo de valores que permite minOccurs="0"
   * @param name
   * @param value
   * @param xml
   * @return 
   */
  private String replaceWithMinOccurs(String name, String value, String xml){
    if(null != value && !"".equals(value.trim())){
        xml = xml.replace(name, value);
    }
    else{
      xml = certificado.replaceComunes(name, xml);    
      xml = certificado.replaceCertificado(name, xml);  
    }      
    return xml;  
  }

  public String getXML(){
    return this.xml;
  }

  public File getFile(){
    return this.fFile;
  }
}
