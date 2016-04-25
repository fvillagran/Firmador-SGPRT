package cl.mtt.sgprt.sign;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class DocumentoEnvio
{
  private static Log log = LogFactory.getLog(DocumentoEnvio.class);
  private Document doc;
  private File archivoSalida;
  private boolean esCrt;
  private boolean esResumen;

  public DocumentoEnvio(File f, String rut, String dv)
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();

      this.doc = db.parse(f);
      esCrt();
      this.esResumen = "RESUMEN".equals(this.doc.getDocumentElement().getNodeName());

      System.out.println("************************");

      addFuncionario(rut, dv);

      updateFechas();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      System.exit(-1);
    }
  }

  protected Element getRaizXml() {
    return this.doc.getDocumentElement();
  }

  @Deprecated
  protected String getId() {
    return this.doc.getElementsByTagName("VALORES")
      .item(0)
      .getAttributes()
      .getNamedItem("ID")
      .getNodeValue();
  }

  public Document getDoc() {
    return this.doc;
  }

  protected void serialize2File(File dir, String nombreArchivo, String encoding) throws IOException, ClassCastException, ClassNotFoundException, InstantiationException, IllegalAccessException
  {
    OutputStream salida = null;
    try {
      this.archivoSalida = new ArchivoSalida().getFile(dir, nombreArchivo);
      salida = new FileOutputStream(this.archivoSalida);
      DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
      DOMImplementationLS impl = (DOMImplementationLS)registry.getDOMImplementation("LS");
      LSSerializer writer = impl.createLSSerializer();

      LSOutput output = impl.createLSOutput();
      output.setEncoding(encoding);
      output.setByteStream(salida);
      writer.write(this.doc, output);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    }
    finally {
      salida.close();
    }
  }

  public String getArchivoSalida() {
    return this.archivoSalida.getAbsolutePath();
  }

  protected void esCrt() {
    this.esCrt = 
      (("aem:CertificadoAnalisisGasesTipoA1".equals(this.doc.getDocumentElement().getNodeName())) || 
      ("aem:CertificadoAnalisisGasesTipoA2".equals(this.doc.getDocumentElement().getNodeName())) || 
      ("aem:CertificadoAnalisisGasesTipoB".equals(this.doc.getDocumentElement().getNodeName())) || 
      ("aem:CertificadoRevisionTecnicaTipoA1".equals(this.doc.getDocumentElement().getNodeName())) || 
      ("aem:CertificadoRevisionTecnicaTipoA2".equals(this.doc.getDocumentElement().getNodeName())) || 
      ("aem:CertificadoRevisionTecnicaTipoB".equals(this.doc.getDocumentElement().getNodeName())));
  }

  public boolean getEsCrt() {
    return this.esCrt;
  }

  public boolean getEsResumen() {
    return this.esResumen;
  }

  private void updateFechas()
  {
    if (this.esCrt) {
      Node nodeCreacion = this.doc.getElementsByTagName("aem:FechaCreacion").item(0);
      Node nodeRevision = this.doc.getElementsByTagName("aem:FechaRevision").item(0);

      NodeList nlc = nodeCreacion.getChildNodes();
      NodeList nlr = nodeRevision.getChildNodes();

      String fechaRevision = "";
      for (int i = 0; i < nlr.getLength(); i++) {
        Node nodechildr = nlr.item(i);
        if ("aem:fechaValida".equalsIgnoreCase(nodechildr.getNodeName())) {
          fechaRevision = nodechildr.getTextContent();
        }
      }

      for (int i = 0; i < nlc.getLength(); i++) {
        Node nodechildc = nlc.item(i);
        if ("aem:fechaValida".equalsIgnoreCase(nodechildc.getNodeName()))
          nodechildc.setTextContent(fechaRevision);
      }
    }
  }

  private void addFuncionario(String rut, String dv)
  {
    if (this.esCrt) {
      Node node = this.doc.getElementsByTagName("aem:RutFuncionario").item(0);
      NodeList nl = node.getChildNodes();
      for (int i = 0; i < nl.getLength(); i++) {
        Node nodechild = nl.item(i);
        if ("aem:numero".equalsIgnoreCase(nodechild.getNodeName()))
          nodechild.setTextContent(rut);
        else if ("aem:dv".equalsIgnoreCase(nodechild.getNodeName())) {
          nodechild.setTextContent(dv);
        }
      }
    }
    else
    {
      Node node = this.doc.getElementsByTagName("ENCABEZADO").item(0);
      NamedNodeMap nl = node.getAttributes();
      for (int i = 0; i < nl.getLength(); i++) {
        Node nodeattribute = nl.item(i);
        if ("RUT_FUNCIONARIO".equalsIgnoreCase(nodeattribute.getNodeName())) {
          nodeattribute.setTextContent(rut);
        }
        else if ("DV_FUNCIONARIO".equalsIgnoreCase(nodeattribute.getNodeName()))
          nodeattribute.setTextContent(dv);
      }
    }
  }

  @Deprecated
  private String addFuncionario(File f, String rut, String dv)
  {
    byte[] buffer = new byte[(int)f.length()];
    BufferedInputStream fis = null;
    try {
      fis = new BufferedInputStream(new FileInputStream(f));
      fis.read(buffer);
    } catch (FileNotFoundException e) {
      e.printStackTrace();

      if (fis != null) try { fis.close(); }
        catch (IOException localIOException1)
        {
        }
    }
    catch (IOException e)
    {
      e.printStackTrace();

      if (fis != null) try { fis.close(); } catch (IOException localIOException2) {  }  } finally { if (fis != null) try { fis.close(); } catch (IOException localIOException3)
        {
        }  }
    String string = new String(buffer);
    string = string.replace("[DV_FUNCIONARIO]", dv);
    string = string.replace("[RUT_FUNCIONARIO]", rut);

    return string;
  }
}