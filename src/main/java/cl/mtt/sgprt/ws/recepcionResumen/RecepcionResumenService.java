package cl.mtt.sgprt.ws.recepcionResumen;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface RecepcionResumenService extends Service
{
  public abstract String getRecepcionResumenPortAddress();

  public abstract RecepcionResumenRemote getRecepcionResumenPort()
    throws ServiceException;

  public abstract RecepcionResumenRemote getRecepcionResumenPort(URL paramURL)
    throws ServiceException;
}
