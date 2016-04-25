package cl.mtt.sgprt.ws.recepcionResumen;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

public class RecepcionResumenRemoteProxy
  implements RecepcionResumenRemote
{
  private String _endpoint = null;
  private Integer _timeout = null;
  private RecepcionResumenRemote recepcionResumenRemote = null;

  public RecepcionResumenRemoteProxy() {
    _initRecepcionResumenRemoteProxy();
  }

  private void _initRecepcionResumenRemoteProxy() {
    try {
      this.recepcionResumenRemote = new RecepcionResumenRemoteServiceLocator().getRecepcionResumenRemotePort();
      if (this.recepcionResumenRemote != null)
        if (this._endpoint != null)
          ((Stub)this.recepcionResumenRemote)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
        else
          this._endpoint = ((String)((Stub)this.recepcionResumenRemote)._getProperty("javax.xml.rpc.service.endpoint.address"));
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
    if (this.recepcionResumenRemote != null)
      ((Stub)this.recepcionResumenRemote)._setProperty("javax.xml.rpc.service.endpoint.address", this._endpoint);
  }

  public void setTimeout(Integer timeout)
  {
    this._timeout = timeout;
    if (this.recepcionResumenRemote != null)
      ((Stub)this.recepcionResumenRemote)._setProperty("axis.connection.timeout", this._timeout);
  }

  public RecepcionResumenRemote getRecepcionResumenRemote()
  {
    if (this.recepcionResumenRemote == null)
      _initRecepcionResumenRemoteProxy();
    return this.recepcionResumenRemote;
  }

  public String recepcionResumen(String string_1) throws RemoteException {
    if (this.recepcionResumenRemote == null)
      _initRecepcionResumenRemoteProxy();
    return this.recepcionResumenRemote.recepcionResumen(string_1);
  }
}
