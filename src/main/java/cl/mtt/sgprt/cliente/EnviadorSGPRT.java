package cl.mtt.sgprt.cliente;

import cl.mtt.sgprt.ws.recepcionCrt.RecepcionCrtRemoteProxy;
import cl.mtt.sgprt.ws.recepcionResumen.RecepcionResumenRemoteProxy;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class EnviadorSGPRT
{
  private static Logger log = Logger.getLogger(EnviadorSGPRT.class);
  private static EnviadorSGPRT instancia;
  private ResourceBundle configuracion;

  private URL urlEndpoint;  
  
  private EnviadorSGPRT()
  {
    this.configuracion = ResourceBundle.getBundle("ConfiguracionCliente");
  }

  public static synchronized EnviadorSGPRT getInstancia()
  {
    if (instancia == null) {
      instancia = new EnviadorSGPRT();
    }
    return instancia;
  }

  public String enviarCRT(String documentoXML)
  {

    RecepcionCrtRemoteProxy wsProxy = new RecepcionCrtRemoteProxy();      
      
    try
    {
      urlEndpoint = new URL(this.configuracion.getString("URLWebServiceCRT"));
    }
    catch (Exception e)
    {
      String error = "Direccion del Web Service invalida.";
      throw new SGPRTException("AC11", error, e);
    }
    
    int timeoutConexion = Integer.parseInt(this.configuracion.getString("TimeoutConexion"));
    try
    {
      wsProxy.setEndpoint(urlEndpoint.toString());
      wsProxy.setTimeout(new Integer(1000 * timeoutConexion));
    }
    catch (Exception e)
    {
      String error = "No se pudo inicializar correctamente el Modulo Enviador.";
      throw new SGPRTException("AC12", error, e);
    }

    String respuestaXML = null;
    try
    {
      respuestaXML = wsProxy.recepcionCRT(documentoXML);
    } catch (RemoteException rmEx) {
      String error = "Servidor no responde.";
      throw new SGPRTException("AC13", error, rmEx);
    }

    return respuestaXML;
  }

  public String enviarResumen(String documentoXML)
  {
      
    RecepcionResumenRemoteProxy wsProxy = new RecepcionResumenRemoteProxy();      
      
    try
    {
      urlEndpoint = new URL(this.configuracion.getString("URLWebServiceResumen"));
    }
    catch (Exception e)
    {
      String error = "Direccion del Web Service invalida.";
      throw new SGPRTException("AC111", error, e);
    }

    int timeoutConexion = Integer.parseInt(this.configuracion.getString("TimeoutConexion"));
    try
    {
      wsProxy.setEndpoint(urlEndpoint.toString());
      wsProxy.setTimeout(new Integer(1000 * timeoutConexion));
    }
    catch (Exception e)
    {
      String error = "No se pudo inicializar correctamente el Modulo Enviador.";
      throw new SGPRTException("AC112", error, e);
    }

    String respuestaXML = null;
    try
    {
      log.debug("certificado a enviar : " + documentoXML);
      respuestaXML = wsProxy.recepcionResumen(documentoXML);
    } catch (RemoteException rmEx) {
      String error = "Servidor no responde.";
      throw new SGPRTException("AC113", error, rmEx);
    }

    return respuestaXML;
  }
}