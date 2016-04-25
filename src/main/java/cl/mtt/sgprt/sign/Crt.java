package cl.mtt.sgprt.sign;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Crt
{
  Document doc;
  private static Log log = LogFactory.getLog(Crt.class);

  public Crt(File f, File dir) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      this.doc = db.parse(f);

      log.debug("xmlns Valores:" + this.doc.getElementsByTagName("VALORES").item(0).getAttributes().getNamedItem("xmlns"));
      log.debug("xmlns URI Valores:" + this.doc.getElementsByTagName("VALORES").item(0).getNamespaceURI());
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(-1);
    }
  }

  protected Element getRaizXml() {
    Element raiz = this.doc.getDocumentElement();

    return raiz;
  }

  protected String getId() {
    return this.doc.getElementsByTagName("VALORES").item(0).getAttributes().getNamedItem("ID").getNodeValue();
  }

  protected Document getDoc() {
    return this.doc;
  }
}