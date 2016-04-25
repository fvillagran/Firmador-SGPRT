package cl.mtt.sgprt.ws.recepcionResumen;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import org.apache.axis.AxisFault;
import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

public class RecepcionResumenRemoteServiceLocator extends Service implements RecepcionResumenRemoteService{
  
  private static final long serialVersionUID = 7963823447386070021L;
  private String RecepcionResumenRemotePort_address = "http://172.25.1.220:8080/SGPRTWebServices/RecepcionResumen";
  private String RecepcionResumenRemotePortWSDDServiceName = "RecepcionResumenRemotePort";
  private HashSet ports = null;
  private URL endpoint;

  public RecepcionResumenRemoteServiceLocator()
  {
  }

  public RecepcionResumenRemoteServiceLocator(EngineConfiguration config)
  {
    super(config);
  }

  public RecepcionResumenRemoteServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
    super(wsdlLoc, sName);
  }

  public String getRecepcionResumenRemotePortAddress()
  {
    return this.RecepcionResumenRemotePort_address;
  }

  public String getRecepcionResumenRemotePortWSDDServiceName()
  {
    return this.RecepcionResumenRemotePortWSDDServiceName;
  }

  public void setRecepcionResumenRemotePortWSDDServiceName(String name) {
    this.RecepcionResumenRemotePortWSDDServiceName = name;
  }

  public RecepcionResumenRemote getRecepcionResumenRemotePort() throws ServiceException
  {
    try {
      endpoint = new URL(this.RecepcionResumenRemotePort_address);
    }
    catch (MalformedURLException e)
    {
      throw new ServiceException(e);
    }
    return getRecepcionResumenRemotePort(endpoint);
  }

  public RecepcionResumenRemote getRecepcionResumenRemotePort(URL portAddress) throws ServiceException {
    try {
      RecepcionResumenRemoteBindingStub _stub = new RecepcionResumenRemoteBindingStub(portAddress, this);
      _stub.setPortName(getRecepcionResumenRemotePortWSDDServiceName());
      return _stub;
    } catch (AxisFault e) {
    }
    return null;
  }

  public void setRecepcionResumenRemotePortEndpointAddress(String address)
  {
    this.RecepcionResumenRemotePort_address = address;
  }

  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (RecepcionResumenRemote.class.isAssignableFrom(serviceEndpointInterface)) {
        RecepcionResumenRemoteBindingStub _stub = new RecepcionResumenRemoteBindingStub(new URL(this.RecepcionResumenRemotePort_address), this);
        _stub.setPortName(getRecepcionResumenRemotePortWSDDServiceName());
        return _stub;
      }
    }
    catch (Throwable t) {
      throw new ServiceException(t);
    }
    throw new ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
  }

  public Remote getPort(QName portName, Class serviceEndpointInterface)
    throws ServiceException
  {
    if (portName == null) {
      return getPort(serviceEndpointInterface);
    }
    String inputPortName = portName.getLocalPart();
    if ("RecepcionResumenRemotePort".equals(inputPortName)) {
      return getRecepcionResumenRemotePort();
    }

    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }

  public QName getServiceName()
  {
    return new QName("http://recepcionResumen.ws.sgprt.mtt.cl/jaws", "RecepcionResumenRemoteService");
  }

  public Iterator getPorts()
  {
    if (this.ports == null) {
      this.ports = new HashSet();
      this.ports.add(new QName("http://recepcionResumen.ws.sgprt.mtt.cl/jaws", "RecepcionResumenRemotePort"));
    }
    return this.ports.iterator();
  }

  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("RecepcionResumenRemotePort".equals(portName)) {
      setRecepcionResumenRemotePortEndpointAddress(address);
    }
    else
    {
      throw new ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
    }
  }

  public void setEndpointAddress(QName portName, String address)
    throws ServiceException
  {
    setEndpointAddress(portName.getLocalPart(), address);
  }
}