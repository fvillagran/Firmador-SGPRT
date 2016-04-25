package cl.mtt.sgprt.cliente;

import cl.mtt.sgprt.binding.ReadWithScanner;
import cl.mtt.sgprt.sign.ArchivoSalida;
import cl.mtt.sgprt.sign.DocumentoEnvio;
import cl.mtt.sgprt.sign.Envio;
import cl.mtt.sgprt.util.XMLUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SoftwarePRT
{
  static final String ENCODING = "ISO-8859-1";

  public static void _main(String[] args)
  {
    String path = "C:/Users/Gabriel/Desktop/it proyects/development/SGPRTCertificados/Files";

    String strDirIn = path + "/XMLinput";
    File dirIn = new File(strDirIn);
    if (!dirIn.isDirectory()) {
      System.out.println("No existe directorio entrada: " + strDirIn);
      System.exit(-1);
    }

    String strDirErrores = path + "/XMLerrores";
    File dirOutErrs = new File(strDirErrores);
    if (!dirOutErrs.isDirectory()) {
      System.out.println("No existe directorio para escribir errores: " + strDirErrores);
      System.exit(-1);
    }

    String strDirInFE = path + "/LMXinput";
    File dirInFrontEnd = new File(strDirInFE);
    try
    {
      File[] file = dirInFrontEnd.listFiles(new FilenameFilter() {
        public boolean accept(File arg0, String arg1) {
          if (arg1.toLowerCase().endsWith(".lmx")) {
            System.out.println("archivo en directorio de entrada front end : " + arg1);
            return true;
          }
          return false;
        }
      });
      System.out.println("cantidad de files : " + file.length);

      int i = 0;
      Envio env = new Envio();
      while (i < file.length)
      {
        try {
          String URI_Path_template = "";

          if (file[i].getName().split("_").length > 1) {
            URI_Path_template = path + "/LMXinput/template" + "/" + file[i].getName().split("_")[0] + ".xml";
            System.out.println("URI_Path_template : " + URI_Path_template);
            System.out.println("*** 1");
          }
          else {
            URI_Path_template = path + "/LMXinput/template" + "/" + file[i].getName().replaceAll("(?i)lmx", "xml");
            System.out.println("URI_Path_template : " + URI_Path_template);
            System.out.println("*** 2");
          }

          System.out.println("*** 3 " + URI_Path_template);

          String template = XMLUtil.leeXML(URI_Path_template);

          Document documentTemplate = XMLUtil.string2DOM(template);
          ReadWithScanner parser = new ReadWithScanner(file[i].getPath(), template);

          if ("RESUMEN".equals(documentTemplate.getDocumentElement().getNodeName())) {
            System.out.println("RESUMEN");
            parser.processLineByLineResumen();
          }
          else {
            System.out.println("CERTIFICADO");
            parser.processLineByLine();
          }

          FileOutputStream os = new FileOutputStream(new ArchivoSalida().getFile(dirIn, parser.getFile().getName().replaceAll("(?i)lmx", "xml")));
          os.write(parser.getXML().getBytes("ISO-8859-1"));
          os.close();

          System.out.println(file[i].getName() + " binding Ok.");
          file[i].delete();
        }
        catch (Exception e) {
          file[i].renameTo(new ArchivoSalida().getFile(dirOutErrs, file[i].getName()));
          System.out.println("Error al procesar archivo LMX: " + file[i].getName());
        }

        i++;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");

    String path = "C:/Users/Gabriel/Desktop/it proyects/development/SGPRTCertificados/Files";

    String strDirInFE = path + "/XMLinput";
    File dirIn = new File(strDirInFE);

    File[] file = dirIn.listFiles(new FilenameFilter() {
      public boolean accept(File arg0, String arg1) {
        if (arg1.toLowerCase().endsWith(".xml")) {
          System.out.println("archivo en directorio de entrada in : " + arg1);
          return true;
        }
        return false;
      }
    });
    int i = 0;
    Envio env = new Envio();

    Date start = new Date();

    while (i < file.length)
    {
      String response = "";
      try {
        String URI_Path_xml = strDirInFE + "/" + file[i].getName();
        String documentoXML = XMLUtil.leeXML(URI_Path_xml);

        DocumentoEnvio de = new DocumentoEnvio(file[i], "13255217", "7");
        //System.out.println("XML antes de firmar : " + XMLUtil.DOM2String(de.getDoc()));

        EnviadorSGPRT enviador = EnviadorSGPRT.getInstancia();
        if ("XML".equalsIgnoreCase(file[i].getName().substring(file[i].getName().length() - 3)))
          response = enviador.enviarResumen(documentoXML);
        else
          response = enviador.enviarCRT(documentoXML);
      }
      catch (Exception e)
      {
        response = "error al procesar certificado";
      }

      System.out.println("respuesta:" + response);
      i++;
    }

    Date stop = new Date();

    System.out.println("###########################################################");
    System.out.println("REPORT ####################################################");
    System.out.println("###########################################################");

    Interval interval = new Interval(start.getTime(), stop.getTime());
    Period period = interval.toPeriod();

    System.out.println("Hora de Inicio : " + sdf.format(start));
    System.out.println("Hora de Termino : " + sdf.format(stop));
    System.out.printf(
      "%d años, %d meses, %d dias, %d horas, %d minutos, %d segundos%n", new Object[] { 
      Integer.valueOf(period.getYears()), Integer.valueOf(period.getMonths()), Integer.valueOf(period.getDays()), 
      Integer.valueOf(period.getHours()), Integer.valueOf(period.getMinutes()), Integer.valueOf(period.getSeconds()) });
  }
}