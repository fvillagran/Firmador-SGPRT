package cl.mtt.sgprt.sign;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class ArchivoSalida
{
  public File getFile(File parent, String child)
    throws IOException
  {
    File f = new File(parent, child);
    short i = 1;
    DecimalFormat df = new DecimalFormat("000");

    int posPunto = child.indexOf('.');
    String ext = child.substring(posPunto + 1);
    String nombre = child.substring(0, posPunto);

    while ((f.isFile()) && (i < 1000)) {
      String hijo = nombre + "_" + df.format(i) + "." + ext;
      f = new File(parent, hijo);
      i = (short)(i + 1);
    }
    return f;
  }
}
