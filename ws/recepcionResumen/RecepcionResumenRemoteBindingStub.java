package cl.mtt.sgprt.ws.recepcionResumen;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;
import javax.xml.namespace.QName;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

public class RecepcionResumenRemoteBindingStub extends Stub
  implements RecepcionResumenRemote
{
  static OperationDesc[] _operations = new OperationDesc[1];

  static { _initOperationDesc1(); }


  private static void _initOperationDesc1()
  {
    OperationDesc oper = new OperationDesc();
    oper.setName("recepcionResumen");
    ParameterDesc param = new ParameterDesc(new QName("", "String_1"), (byte)1, new QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
    oper.addParameter(param);
    oper.setReturnType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
    oper.setReturnClass(String.class);
    oper.setReturnQName(new QName("", "result"));
    oper.setStyle(Style.RPC);
    oper.setUse(Use.LITERAL);
    _operations[0] = oper;
  }

  public RecepcionResumenRemoteBindingStub() throws AxisFault
  {
    this(null);
  }

  public RecepcionResumenRemoteBindingStub(URL endpointURL, javax.xml.rpc.Service service) throws AxisFault {
    this(service);
    this.cachedEndpoint = endpointURL;
  }

  public RecepcionResumenRemoteBindingStub(javax.xml.rpc.Service service) throws AxisFault {
    if (service == null)
      this.service = new org.apache.axis.client.Service();
    else {
      this.service = service;
    }
    ((org.apache.axis.client.Service)this.service).setTypeMappingVersion("1.2");
  }

  protected Call createCall() throws RemoteException {
    try {
      Call _call = super._createCall();
      if (this.maintainSessionSet) {
        _call.setMaintainSession(this.maintainSession);
      }
      if (this.cachedUsername != null) {
        _call.setUsername(this.cachedUsername);
      }
      if (this.cachedPassword != null) {
        _call.setPassword(this.cachedPassword);
      }
      if (this.cachedEndpoint != null) {
        _call.setTargetEndpointAddress(this.cachedEndpoint);
      }
      if (this.cachedTimeout != null) {
        _call.setTimeout(this.cachedTimeout);
      }
      if (this.cachedPortName != null) {
        _call.setPortName(this.cachedPortName);
      }
      Enumeration keys = this.cachedProperties.keys();
      while (keys.hasMoreElements()) {
        String key = (String)keys.nextElement();
        _call.setProperty(key, this.cachedProperties.get(key));
      }
      return _call;
    }
    catch (Throwable _t) {
      throw new AxisFault("Failure trying to get the Call object", _t);
    }
  }

  public String recepcionResumen(String string_1) throws RemoteException {
    if (this.cachedEndpoint == null) {
      throw new NoEndPointException();
    }
    Call _call = createCall();
    _call.setOperation(_operations[0]);
    _call.setUseSOAPAction(true);
    _call.setSOAPActionURI("");
    _call.setEncodingStyle(null);
    _call.setProperty("sendXsiTypes", Boolean.FALSE);
    _call.setProperty("sendMultiRefs", Boolean.FALSE);
    _call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
    _call.setOperationName(new QName("http://recepcionResumen.ws.sgprt.mtt.cl/jaws", "recepcionResumen"));

    setRequestHeaders(_call);
    setAttachments(_call);
    try { Object _resp = _call.invoke(new Object[] { string_1 });

      if ((_resp instanceof RemoteException)) {
        throw ((RemoteException)_resp);
      }

      extractAttachments(_call);
      try {
        return (String)_resp;
      } catch (Exception _exception) {
        return (String)JavaUtils.convert(_resp, String.class);
      }
    } catch (AxisFault axisFaultException)
    {
      throw axisFaultException;
    }
  }
}
