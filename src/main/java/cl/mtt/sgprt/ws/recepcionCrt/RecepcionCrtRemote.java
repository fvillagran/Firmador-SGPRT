package cl.mtt.sgprt.ws.recepcionCrt;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface RecepcionCrtRemote extends Remote
{
  public abstract String recepcionCRT(String paramString)
    throws RemoteException;
}
