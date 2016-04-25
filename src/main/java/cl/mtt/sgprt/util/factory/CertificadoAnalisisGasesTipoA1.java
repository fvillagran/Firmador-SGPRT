
package cl.mtt.sgprt.util.factory;

public class CertificadoAnalisisGasesTipoA1 extends AbstractCertificado{

    @Override
    public String replaceCertificado(String code, String certificadoXML) {
        
        //EstacionInspeccionGasesA1
        if(code.equalsIgnoreCase("[GASES-V_CO_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCoRalenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_CO_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCoRalenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_HC_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorHcRalenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_HC_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHcRalenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_COCO2_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCoCo2Ralenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_COCO2_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCoCo2Ralenti", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_CO_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCo2500Rpm", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_CO_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCo2500Rpm", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_HC_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorHc2500Rpm", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_HC_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHc2500Rpm", code), ""); 
        else if(code.equalsIgnoreCase("[GASES-V_COCO2_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCo2Co2500Rpm", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_COCO2_2500RPM]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCo2Co2500Rpm", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_HUMO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHumo", code), "");        

        //EstacionInspeccionOpacidadA1
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA1]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad1", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad2", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA3]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad3", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA4]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad4", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA5]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad5", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_VALIDA_OPA]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorMediaOpacidadValida", code), ""); 
        else if(code.equalsIgnoreCase("[OPACIDAD-R_OPA_MEDIDA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoOpacidadMedida", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_CARGA]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidadCarga", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-R_CARGA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoOpacidadCarga", code), "");
        
        
        return certificadoXML;
    }

    
}
