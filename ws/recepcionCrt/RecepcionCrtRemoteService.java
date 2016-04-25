package cl.mtt.sgprt.ws.recepcionCrt;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface RecepcionCrtRemoteService extends Service
{
  public abstract String getRecepcionCrtRemotePortAddress();

  public abstract RecepcionCrtRemote getRecepcionCrtRemotePort()
    throws ServiceException;

  public abstract RecepcionCrtRemote getRecepcionCrtRemotePort(URL paramURL)
    throws ServiceException;
}
