package cl.mtt.sgprt.ws.recepcionResumen;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface RecepcionResumenRemoteService extends Service
{
  public abstract String getRecepcionResumenRemotePortAddress();

  public abstract RecepcionResumenRemote getRecepcionResumenRemotePort()
    throws ServiceException;

  public abstract RecepcionResumenRemote getRecepcionResumenRemotePort(URL paramURL)
    throws ServiceException;
}