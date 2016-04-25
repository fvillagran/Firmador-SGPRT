package cl.mtt.sgprt.sign;

import cl.mtt.sgprt.cliente.EnviadorSGPRT;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Envio
{
  private static Log log = LogFactory.getLog(Envio.class);
  private EnviadorSGPRT enviador = EnviadorSGPRT.getInstancia();

  public String leeXML(String nombreArchivoXML) throws Exception {
    byte[] buff = new byte[1024];
    InputStream is = new FileInputStream(nombreArchivoXML);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    int n;
    while ((n = is.read(buff)) != -1)
    {
      os.write(buff, 0, n);
    }return new String(os.toByteArray(), "ISO-8859-1");
  }

  public String envio(DocumentoEnvio documentoEnvio) throws Exception {
    log.info("-------------------------------");
	log.info("Enviando Documento Electrónico");
	log.info("-------------------------------");
    String nombreArchivo = documentoEnvio.getArchivoSalida();
    log.info("Archivo a enviar: " + nombreArchivo);
    String archivoPrueba = leeXML(nombreArchivo);
    log.debug("Enviando:\n" + archivoPrueba);
    String respuestaEnLinea = "";
    if (documentoEnvio.getEsCrt())
      respuestaEnLinea = this.enviador.enviarCRT(archivoPrueba);
    else if (documentoEnvio.getEsResumen()) {
      respuestaEnLinea = this.enviador.enviarResumen(archivoPrueba);
    }
    log.debug("Resultado:\n" + respuestaEnLinea);
    return respuestaEnLinea;
  }
}
