package cl.mtt.sgprt.ws.recepcionCrt;

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

public class RecepcionCrtRemoteServiceLocator extends Service implements RecepcionCrtRemoteService{
    
  private static final long serialVersionUID = 8295596560779265316L;
  private String RecepcionCrtRemotePort_address = "http://172.25.1.220:8080/SGPRTWebServices/RecepcionCrt";
  private String RecepcionCrtRemotePortWSDDServiceName = "RecepcionCrtRemotePort";
  private HashSet ports = null;  
  private URL endpoint;

  public RecepcionCrtRemoteServiceLocator()
  {
  }

  public RecepcionCrtRemoteServiceLocator(EngineConfiguration config)
  {
    super(config);
  }

  public RecepcionCrtRemoteServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
    super(wsdlLoc, sName);
  }

  public String getRecepcionCrtRemotePortAddress()
  {
    return this.RecepcionCrtRemotePort_address;
  }

  public String getRecepcionCrtRemotePortWSDDServiceName()
  {
    return this.RecepcionCrtRemotePortWSDDServiceName;
  }

  public void setRecepcionCrtRemotePortWSDDServiceName(String name) {
    this.RecepcionCrtRemotePortWSDDServiceName = name;
  }

  public RecepcionCrtRemote getRecepcionCrtRemotePort() throws ServiceException
  {
    try {
      endpoint = new URL(this.RecepcionCrtRemotePort_address);
    }
    catch (MalformedURLException e)
    {      
      throw new ServiceException(e);
    }
    return getRecepcionCrtRemotePort(endpoint);
  }

  public RecepcionCrtRemote getRecepcionCrtRemotePort(URL portAddress) throws ServiceException {
    try {
      RecepcionCrtRemoteBindingStub _stub = new RecepcionCrtRemoteBindingStub(portAddress, this);
      _stub.setPortName(getRecepcionCrtRemotePortWSDDServiceName());
      return _stub;
    } catch (AxisFault e) {
    }
    return null;
  }

  public void setRecepcionCrtRemotePortEndpointAddress(String address)
  {
    this.RecepcionCrtRemotePort_address = address;
  }

  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (RecepcionCrtRemote.class.isAssignableFrom(serviceEndpointInterface)) {
        RecepcionCrtRemoteBindingStub _stub = new RecepcionCrtRemoteBindingStub(new URL(this.RecepcionCrtRemotePort_address), this);
        _stub.setPortName(getRecepcionCrtRemotePortWSDDServiceName());
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
    if ("RecepcionCrtRemotePort".equals(inputPortName)) {
      return getRecepcionCrtRemotePort();
    }

    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }

  public QName getServiceName()
  {
    return new QName("http://recepcionCrt.ws.sgprt.mtt.cl/jaws", "RecepcionCrtRemoteService");
  }

  public Iterator getPorts()
  {
    if (this.ports == null) {
      this.ports = new HashSet();
      this.ports.add(new QName("http://recepcionCrt.ws.sgprt.mtt.cl/jaws", "RecepcionCrtRemotePort"));
    }
    return this.ports.iterator();
  }

  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("RecepcionCrtRemotePort".equals(portName)) {
      setRecepcionCrtRemotePortEndpointAddress(address);
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
