
package cl.mtt.sgprt.util.factory;

public class CertificadoAnalisisGasesTipoB extends AbstractCertificado{

    @Override
    public String replaceCertificado(String code, String certificadoXML) {
        
        //EstacionInspeccionGasesB
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
        else if(code.equalsIgnoreCase("[GASES-V_HC_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorHcSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_HC_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHcSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_CO_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCoSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_CO_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCoSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_NO_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorNoSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_NO_STDR5015]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoNoSTDR5015", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_HC_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorHcSTDR2525", code), ""); 
        else if(code.equalsIgnoreCase("[GASES-R_HC_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHcSTDR2525", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_CO_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCoSTDR2525", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_CO_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCoSTDR2525", code), "");
        else if(code.equalsIgnoreCase("[GASES-V_NO_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorNoSTDR2525", code), "");
        else if(code.equalsIgnoreCase("[GASES-R_NO_STDR2525]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoNoSTDR2525", code), "");
        else if(code.equalsIgnoreCase("[ASM-INERCIAEQUIVALENTE]")) certificadoXML = certificadoXML.replace(getTag("aem:InerciaEquivalente", code), "");
        else if(code.equalsIgnoreCase("[PRT-LINEADEREVISION]")) certificadoXML = certificadoXML.replace(getTag("aem:LineaDeRevision", code), "");
        else if(code.equalsIgnoreCase("[ASM-VALOR_CO2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCO2", code), "");
        else if(code.equalsIgnoreCase("[ASM-VALOR_O2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorO2", code), "");
        else if(code.equalsIgnoreCase("[ASM-VALORLAMBDA]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorLambda", code), ""); 
        else if(code.equalsIgnoreCase("[ASM-VALORPRESIONBAROMETRICA]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPresionBarometrica", code), "");
        else if(code.equalsIgnoreCase("[ASM-VALORTEMPERATURAAMBIENTE]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorTemperaturaAmbiente", code), "");
        else if(code.equalsIgnoreCase("[ASM-VALORHUMEDADAMBIENTE]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorHumedadAmbiente", code), "");

        //EstacionInspeccionOpacidadB
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA1]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad1", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad2", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA3]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad3", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA4]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad4", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_OPA5]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorOpacidad5", code), "");
        else if(code.equalsIgnoreCase("[OPACIDAD-V_VALIDA_OPA]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorMediaOpacidadValida", code), ""); 
        else if(code.equalsIgnoreCase("[OPACIDAD-R_OPA_MEDIDA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoOpacidadMedida", code), "");                

        return certificadoXML;        
    }
}