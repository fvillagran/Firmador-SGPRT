
package cl.mtt.sgprt.util.factory;

public class CertificadoFactory {
    
  public static AbstractCertificado getCertificado(String criteria)
  {
    if ( criteria.equals(AbstractCertificado.CERT_ANALISISGASES_TIPOA1) )
      return new CertificadoAnalisisGasesTipoA1();
    else if ( criteria.equals(AbstractCertificado.CERT_ANALISISGASES_TIPOA2) )
      return new CertificadoAnalisisGasesTipoA2();
    else if ( criteria.equals(AbstractCertificado.CERT_ANALISISGASES_TIPOB) )
      return new CertificadoAnalisisGasesTipoB();
    else if ( criteria.equals(AbstractCertificado.CERT_REVTECNICA_TIPOA1) )
      return new CertificadoRevisionTecnicaTipoA1();
    else if ( criteria.equals(AbstractCertificado.CERT_REVTECNICA_TIPOA2) )
      return new CertificadoRevisionTecnicaTipoA2();
    else if ( criteria.equals(AbstractCertificado.CERT_REVTECNICA_TIPOB) )
      return new CertificadoRevisionTecnicaTipoB();
 
    return null;
  }    
    
}
