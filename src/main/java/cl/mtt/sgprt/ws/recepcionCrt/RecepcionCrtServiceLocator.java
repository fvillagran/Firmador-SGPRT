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

public class RecepcionCrtServiceLocator extends Service
  implements RecepcionCrtService
{
  private static final long serialVersionUID = -6486771882991355883L;
  private String RecepcionCrtPort_address = "http://172.25.1.220:8080/SGPRTWebServices/RecepcionCrt";
  private String RecepcionCrtPortWSDDServiceName = "RecepcionCrtPort";
  private HashSet ports = null;
  private URL endpoint;
  
  public RecepcionCrtServiceLocator()
  {
  }

  public RecepcionCrtServiceLocator(EngineConfiguration config)
  {
    super(config);
  }

  public RecepcionCrtServiceLocator(String wsdlLoc, QName sName) throws ServiceException {
    super(wsdlLoc, sName);
  }

  public String getRecepcionCrtPortAddress()
  {
    return this.RecepcionCrtPort_address;
  }

  public String getRecepcionCrtPortWSDDServiceName()
  {
    return this.RecepcionCrtPortWSDDServiceName;
  }

  public void setRecepcionCrtPortWSDDServiceName(String name) {
    this.RecepcionCrtPortWSDDServiceName = name;
  }

  public RecepcionCrtRemote getRecepcionCrtPort() throws ServiceException
  {
    try {
      endpoint = new URL(this.RecepcionCrtPort_address);
    }
    catch (MalformedURLException e)
    {
      throw new ServiceException(e);
    }
    return getRecepcionCrtPort(endpoint);
  }

  public RecepcionCrtRemote getRecepcionCrtPort(URL portAddress) throws ServiceException {
    try {
      RecepcionCrtPortBindingStub _stub = new RecepcionCrtPortBindingStub(portAddress, this);
      _stub.setPortName(getRecepcionCrtPortWSDDServiceName());
      return _stub;
    } catch (AxisFault e) {
    }
    return null;
  }

  public void setRecepcionCrtPortEndpointAddress(String address)
  {
    this.RecepcionCrtPort_address = address;
  }

  public Remote getPort(Class serviceEndpointInterface)
    throws ServiceException
  {
    try
    {
      if (RecepcionCrtRemote.class.isAssignableFrom(serviceEndpointInterface)) {
        RecepcionCrtPortBindingStub _stub = new RecepcionCrtPortBindingStub(new URL(this.RecepcionCrtPort_address), this);
        _stub.setPortName(getRecepcionCrtPortWSDDServiceName());
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
    if ("RecepcionCrtPort".equals(inputPortName)) {
      return getRecepcionCrtPort();
    }

    Remote _stub = getPort(serviceEndpointInterface);
    ((Stub)_stub).setPortName(portName);
    return _stub;
  }

  public QName getServiceName()
  {
    return new QName("http://recepcionCrt.ws.sgprt.mtt.cl/", "RecepcionCrtService");
  }

  public Iterator getPorts()
  {
    if (this.ports == null) {
      this.ports = new HashSet();
      this.ports.add(new QName("http://recepcionCrt.ws.sgprt.mtt.cl/", "RecepcionCrtPort"));
    }
    return this.ports.iterator();
  }

  public void setEndpointAddress(String portName, String address)
    throws ServiceException
  {
    if ("RecepcionCrtPort".equals(portName)) {
      setRecepcionCrtPortEndpointAddress(address);
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