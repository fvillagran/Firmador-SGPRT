package cl.mtt.sgprt.ws.recepcionCrt;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class RecepcionCrtRemoteProxy
  implements RecepcionCrtRemote
{
  private String _endpoint = null;
  private Integer _timeout = null;
  private RecepcionCrtRemote recepcionCrtRemote = null;

  public RecepcionCrtRemoteProxy() {
    _initRecepcionCrtRemoteProxy();
  }

  private void _initRecepcionCrtRemoteProxy() {
    try {
      this.recepcionCrtRemote = new RecepcionCrtRemoteServiceLocator().getRecepcionCrtRemotePort();
      if (this.recepcionCrtRemote != null)
        if (this._endpoint != null)
          ((Stub)this.recepcionCrtRemote)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
        else
          this._endpoint = ((String)((Stub)this.recepcionCrtRemote)._getProperty("javax.xml.rpc.service.endpoint.address"));
    }
    catch (ServiceException localServiceException)
    {
    }
  }

  public String getEndpoint() {
    return this._endpoint;
  }

  public void setEndpoint(String endpoint) {
    this._endpoint = endpoint;
    if (this.recepcionCrtRemote != null)
      ((Stub)this.recepcionCrtRemote)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
  }

  public void setTimeout(Integer timeout)
  {
    this._timeout = timeout;
    if (this.recepcionCrtRemote != null)
      ((Stub)this.recepcionCrtRemote)._setProperty("axis.connection.timeout", this._timeout);
  }

  public RecepcionCrtRemote getRecepcionCrtRemote()
  {
    if (this.recepcionCrtRemote == null)
      _initRecepcionCrtRemoteProxy();
    return this.recepcionCrtRemote;
  }

  public String recepcionCRT(String string_1) throws RemoteException {
    if (this.recepcionCrtRemote == null)
      _initRecepcionCrtRemoteProxy();
    return this.recepcionCrtRemote.recepcionCRT(string_1);
  }
}
