package cl.mtt.sgprt.ws.recepcionResumen;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface RecepcionResumenRemote extends Remote
{
  public abstract String recepcionResumen(String paramString)
    throws RemoteException;
}
