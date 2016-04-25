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

public class RecepcionResumenServiceLocator extends Service
  implements RecepcionResumenService
{
  private String RecepcionResumenPort_address = "http://172.25.1.220:8080/SGPRTWebServices/RecepcionResumen";
  private String RecepcionResumenPortWSDDServiceName = "RecepcionResumenPort";
  private HashSet ports = null;
  private URL endpoint;
  
  public RecepcionResumenServiceLocator()
  {
  }

  public RecepcionResumenServiceLocator(EngineConfiguration config)
  {
    super(config);
  }

  public RecepcionResumenServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
    super(wsdlLoc, sName);
  }

  public String getRecepcionResumenPortAddress()
  {
    return this.RecepcionResumenPort_address;
  }

  public String getRecepcionResumenPortWSDDServiceName()
  {
    return this.RecepcionResumenPortWSDDServiceName;
  }

  public void setRecepcionResumenPortWSDDServiceName(String name) {
    this.RecepcionResumenPortWSDDServiceName = name;
  }

  public RecepcionResumenRemote getRecepcionResumenPort() throws ServiceException
  {
    try {
      endpoint = new URL(this.RecepcionResumenPort_address);
    }
    catch (MalformedURLException e)
    {
      throw new ServiceException(e);
    }
    return getRecepcionResumenPort(endpoint);
  }

  public RecepcionResumenRemote getRecepcionResumenPort(URL portAddress) throws ServiceException {
    try {
      RecepcionResumenPortBindingStub _stub = new RecepcionResumenPortBindingStub(portAddress, this);
      _stub.setPortName(getRecepcionResumenPortWSDDServiceName());
      return _stub;
    } catch (AxisFault e) {
    }
    return null;
  }

  public void setRecepcionResumenPortEndpointAddress(String address)
  {
    this.RecepcionResumenPort_address = address;
  }

  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (RecepcionResumenRemote.class.isAssignableFrom(serviceEndpointInterface)) {
        RecepcionResumenPortBindingStub _stub = new RecepcionResumenPortBindingStub(new URL(this.RecepcionResumenPort_address), this);
        _stub.setPortName(getRecepcionResumenPortWSDDServiceName());
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
    if ("RecepcionResumenPort".equals(inputPortName)) {
      return getRecepcionResumenPort();
    }

    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }

  public QName getServiceName()
  {
    return new QName("http://recepcionResumen.ws.sgprt.mtt.cl/", "RecepcionResumenService");
  }

  public Iterator getPorts()
  {
    if (this.ports == null) {
      this.ports = new HashSet();
      this.ports.add(new QName("http://recepcionResumen.ws.sgprt.mtt.cl/", "RecepcionResumenPort"));
    }
    return this.ports.iterator();
  }

  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("RecepcionResumenPort".equals(portName)) {
      setRecepcionResumenPortEndpointAddress(address);
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