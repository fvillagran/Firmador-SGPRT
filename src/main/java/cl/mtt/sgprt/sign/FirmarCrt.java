package cl.mtt.sgprt.sign;

import cl.mtt.sgprt.binding.ReadWithScanner;
import cl.mtt.sgprt.util.XMLUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FirmarCrt extends Thread
{
  private static Log log = LogFactory.getLog(FirmarCrt.class);
  static ResourceBundle rb = ResourceBundle.getBundle("sgprtFirmador");
  static final String ENCODING = "ISO-8859-1";
  File dirIn;
  File dirOut;
  File dirOutResp;
  File dirOutErrs;
  File dirOutProcesados;
  File dirInFrontEnd;
  File dirFrontEndTemplate;
  FirmarXML firma;

  public static void main(String[] args)
  {
    if (args.length != 1) {
      log.error("Debe ingresar la clave secreta como parámetro.");
      log.error("Uso: java FirmarCrt <clave>");
      System.exit(-1);
    }
    new FirmarCrt(args[0].toCharArray()).procesar();
  }

  protected FirmarCrt(char[] password) {
    try {
      log.info("-------------------------");
      log.info("Configuracion de directorios - SGPRTCertificados");
      log.info("-------------------------");

      String strDirInFrontEnd = rb.getString("directorio_in_frontend");
      this.dirInFrontEnd = new File(strDirInFrontEnd);
      if (!this.dirInFrontEnd.isDirectory()) {
        log.info("No existe directorio front end: " + this.dirInFrontEnd);
      }

      String strDirFrontEndTemplate = rb.getString("directorio_frontend_template");
      this.dirFrontEndTemplate = new File(strDirFrontEndTemplate);
      if (!this.dirFrontEndTemplate.isDirectory()) {
        log.info("No existe directorio front end template: " + this.dirFrontEndTemplate);
      }

      String strDirIn = rb.getString("directorio_in");
      this.dirIn = new File(strDirIn);
      if (!this.dirIn.isDirectory()) {
        log.error("No existe directorio entrada: " + strDirIn);
        System.exit(-1);
      }

      log.info("Entrada: " + this.dirIn.getAbsolutePath());

      String strDirOut = rb.getString("directorio_out");
      this.dirOut = new File(strDirOut);
      if (!this.dirOut.isDirectory()) {
        log.error("No existe directorio salida: " + strDirOut);
        System.exit(-1);
      }

      log.info("Salida: " + this.dirOut.getAbsolutePath());

      String strDirRespOut = rb.getString("directorio_out_respuestas");
      this.dirOutResp = new File(strDirRespOut);
      if (!this.dirOutResp.isDirectory()) {
        log.error("No existe directorio respuestas de salida: " + strDirRespOut);
        System.exit(-1);
      }

      log.info("Respuestas: " + this.dirOutResp.getAbsolutePath());

      String strDirProcesados = rb.getString("directorio_out_procesados");
      this.dirOutProcesados = new File(strDirProcesados);
      if (!this.dirOutProcesados.isDirectory()) {
        log.error("No existe directorio salida de procesados: " + strDirProcesados);
        System.exit(-1);
      }

      log.info("Procesados: " + this.dirOutProcesados.getAbsolutePath());

      String strDirErrores = rb.getString("directorio_out_errores");
      this.dirOutErrs = new File(strDirErrores);
      if (!this.dirOutErrs.isDirectory()) {
        log.error("No existe directorio para escribir errores: " + strDirErrores);
        System.exit(-1);
      }

      log.info("Errores: " + this.dirOutErrs.getAbsolutePath());

      this.firma = new FirmarXMLPKCS11(password);

      this.firma.init(rb);
      
    } catch (RuntimeException runEx) {
      log.warn("Error al inicializar certificado x509", runEx);
      throw runEx;
    }
  }

  public void run() {
    log.info("-------------------------");
    log.info("Procesando archivos del directorio de entrada");
    log.info("-------------------------");
    try
    {
      while (true) {
        double tiempoInicio = System.currentTimeMillis();
        procesarLMX();
        procesar();
        double tiempoFin = System.currentTimeMillis();
        double tiempo = tiempoFin - tiempoInicio;

        sleep(1000L);
      }
    } catch (InterruptedException e) {
      log.info("Fin proceso", e);
    }
  }

  public void procesarLMX()
  {
    try
    {
      File[] file = this.dirInFrontEnd.listFiles(new FilenameFilter() {
        public boolean accept(File arg0, String arg1) {
          if (arg1.toLowerCase().endsWith(".lmx")) {
            FirmarCrt.log.debug("archivo en directorio de entrada front end:" + arg1);
            return true;
          }
          return false;
        }
      });
      int i = 0;
      Envio env = new Envio();
      while (i < file.length) {
        try {
          String URI_Path_template = "";

          if (file[i].getName().split("_").length > 1) {
            URI_Path_template = rb.getString("directorio_frontend_template") + "/" + file[i].getName().split("_")[0] + ".xml";
            log.info("URI_Path_template : " + URI_Path_template);
          }
          else {
            URI_Path_template = rb.getString("directorio_frontend_template") + "/" + file[i].getName().replaceAll("(?i)lmx", "xml");
            log.info("URI_Path_template : " + URI_Path_template);
          }

          String template = XMLUtil.leeXML(URI_Path_template);

          Document documentTemplate = XMLUtil.string2DOM(template);
          ReadWithScanner parser = new ReadWithScanner(file[i].getPath(), template);

          if ("RESUMEN".equals(documentTemplate.getDocumentElement().getNodeName())) {
            log.info("RESUMEN");
            parser.processLineByLineResumen();
          }
          else {
            log.info("CERTIFICADO");
            parser.processLineByLine();
          }

          FileOutputStream os = new FileOutputStream(new ArchivoSalida().getFile(this.dirIn, parser.getFile().getName().replaceAll("(?i)lmx", "xml")));
          os.write(parser.getXML().getBytes("ISO-8859-1"));
          os.close();

          log.info(file[i].getName() + " binding Ok.");
          file[i].delete();
        }
        catch (Exception e)
        {
          file[i].renameTo(new ArchivoSalida().getFile(this.dirOutErrs, file[i].getName()));
          log.info("Error al procesar archivo LMX: " + file[i].getName(), e);
        }

        i++;
      }
    } catch (Exception e) {
      log.error("Error al generar certificado.");
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public void procesar()
  {
    try
    {
      File[] file = this.dirIn.listFiles(new FilenameFilter() {
        public boolean accept(File arg0, String arg1) {
          if (arg1.toLowerCase().endsWith(".xml")) {
            FirmarCrt.log.debug("archivo en directorio de entrada:" + arg1);
            return true;
          }
          return false;
        }
      });
      int i = 0;
      Envio env = new Envio();
      while (i < file.length)
      {
        try
        {
          DocumentoEnvio documentoEnvio = new DocumentoEnvio(file[i], this.firma.getRut(), this.firma.getDv());

          this.firma.firmar(documentoEnvio);
          log.info("------------------------");
          log.info(file[i].getName() + " firmado Ok.");
          log.info("-------------------------");

          ValidaFirmaUtil valida = new ValidaFirmaUtil(documentoEnvio.getRaizXml());

          documentoEnvio.serialize2File(this.dirOut, file[i].getName(), "ISO-8859-1");

          String respuesta = env.envio(documentoEnvio);

          FileOutputStream os = new FileOutputStream(new ArchivoSalida().getFile(this.dirOutResp, file[i].getName()));

          os.write(respuesta.getBytes("ISO-8859-1"));
          os.close();

          file[i].renameTo(new ArchivoSalida().getFile(this.dirOutProcesados, file[i].getName()));

          log.info(file[i].getName() + " procesado Ok.");
        }
        catch (Exception e)
        {
          file[i].renameTo(new ArchivoSalida().getFile(this.dirOutErrs, file[i].getName()));

          log.info("Error al procesar archivo XML: " + file[i].getName(), e);
        }
        i++;
      }
    }
    catch (Exception e)
    {
      log.error("Error al generar certificado.");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
