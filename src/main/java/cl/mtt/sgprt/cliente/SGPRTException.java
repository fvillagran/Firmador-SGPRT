package cl.mtt.sgprt.cliente;

public class SGPRTException extends RuntimeException
{
  private static final long serialVersionUID = 8022953038192667699L;
  private String codigo;

  public SGPRTException()
  {
  }

  public SGPRTException(String cod, String errorMsg)
  {
    super(errorMsg);
    this.codigo = cod;
  }

  public SGPRTException(String cod, Throwable ex) {
    super(ex);
    this.codigo = cod;
  }

  public SGPRTException(String cod, String errorMsg, Throwable ex) {
    super(errorMsg, ex);
    this.codigo = cod;
  }

  public String toString()
  {
    return "[" + this.codigo + "] " + super.toString();
  }
}
