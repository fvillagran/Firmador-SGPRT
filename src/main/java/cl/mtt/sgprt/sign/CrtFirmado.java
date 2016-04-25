package cl.mtt.sgprt.sign;

import java.io.FileOutputStream;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class CrtFirmado
{
  FileOutputStream fileOutStream;
  Document documento;

  protected CrtFirmado(FileOutputStream fos, Document doc)
  {
    this.fileOutStream = fos;
    this.documento = doc;
  }

  void serializar() {
    OutputFormat formatoXML = 
      new OutputFormat(this.documento, "ISO-8859-1", false);
    formatoXML.setPreserveSpace(true);

    XMLSerializer serializar = new XMLSerializer(this.fileOutStream, formatoXML);
    try {
      serializar.serialize(this.documento);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
