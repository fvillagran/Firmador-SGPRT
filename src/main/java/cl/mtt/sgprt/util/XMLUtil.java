package cl.mtt.sgprt.util;

import cl.mtt.sgprt.sign.DocumentoEnvio;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLUtil{
    
  private static final Log log = LogFactory.getLog(DocumentoEnvio.class);

  public static String leeXML(String nombreArchivoXML) throws Exception {
      
    log.debug("leeXML");
      
    byte[] buff = new byte[1024];
    InputStream is = new FileInputStream(nombreArchivoXML);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int n;
    while ((n = is.read(buff)) != -1){
      os.write(buff, 0, n);
    }
    return new String(os.toByteArray(), "UTF-8");
  }

  /**
   * transforma DOM a String
   * @param doc
   * @return 
   */
  public static String DOM2String(Document doc) {
    
    log.debug("DOM2String");  
      
    TransformerFactory transformerFactory = TransformerFactory.newInstance();    
    Transformer transformer;

    Source source = new DOMSource(doc);
    StringWriter writer = new StringWriter();
    Result result = new StreamResult(writer);
    
    try {
      transformer = transformerFactory.newTransformer();
      transformer.transform(source, result);
      return writer.toString();
      
    } catch (TransformerException e) {
      log.error("Error al transformar DOM a String", e);
    }
    return null;
  }

  /**
   * transforma String a DOM
   * @param s
   * @return 
   */
  public static Document string2DOM(String s) {
    
    log.debug("string2DOM");
      
    Document doc;
    DocumentBuilder builder;
            
    try {
      builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      doc = builder.parse(new ByteArrayInputStream(s.getBytes()));
      return doc;
    } catch (SAXException ex) {
        log.debug("Error al transformar String a DOM", ex);
    } catch (IOException ex) {
        log.debug("Error al transformar String a DOM", ex);
    } catch (ParserConfigurationException ex) {
        log.debug("Error al transformar String a DOM", ex);       
    }
    return null;
  }  
}
   
