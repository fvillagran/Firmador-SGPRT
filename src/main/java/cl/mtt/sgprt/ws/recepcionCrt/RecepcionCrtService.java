package cl.mtt.sgprt.ws.recepcionCrt;

import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public abstract interface RecepcionCrtService extends Service
{
  public abstract String getRecepcionCrtPortAddress();

  public abstract RecepcionCrtRemote getRecepcionCrtPort()
    throws ServiceException;

  public abstract RecepcionCrtRemote getRecepcionCrtPort(URL paramURL)
    throws ServiceException;
}
