
package cl.mtt.sgprt.util.factory;

public abstract class AbstractCertificado {
    
    public static final String CERT_ANALISISGASES_TIPOA1 = "CertificadoAnalisisGasesTipoA1";
    public static final String CERT_ANALISISGASES_TIPOA2 = "CertificadoAnalisisGasesTipoA2";
    public static final String CERT_ANALISISGASES_TIPOB = "CertificadoAnalisisGasesTipoB";
    public static final String CERT_REVTECNICA_TIPOA1 = "CertificadoRevisionTecnicaTipoA1";
    public static final String CERT_REVTECNICA_TIPOA2 = "CertificadoRevisionTecnicaTipoA2";
    public static final String CERT_REVTECNICA_TIPOB = "CertificadoRevisionTecnicaTipoB";
    public static final String CERT_RESUMEN = "CertificadoResumen";
    
    String getTagApertura(String tagName) {        
        return "<" + tagName + ">";
    }

    String getTagCierre(String tagName) {
        return "</" + tagName + ">";
    }

    String getTag(String tagName, String tagVal) {
        return "<" + tagName + ">" + tagVal + "</" + tagName + ">";
    }

    public String replaceComunes(String code, String certificadoXML){
            
        //ResumenRevision
        if(code.equalsIgnoreCase("[NUM_CERTIFICADO]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroCertificado", code), "");
        else if(code.equalsIgnoreCase("[CANTIDAD_RECHAZOS]")) certificadoXML = certificadoXML.replace(getTag("aem:CantidadRechazos", code), "");
        else if(code.equalsIgnoreCase("[COD_MOTIVO_RECHAZO]")) certificadoXML = certificadoXML.replace(getTag("aem:CodigoRechazo", code), "");
        else if(code.equalsIgnoreCase("[NUM_CERTIFICADO_GASES]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroCertificadoGases", code), "");
        else if(code.equalsIgnoreCase("[RESULTADO_CRT_GASES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRevisionGases", code), "");
        else if(code.equalsIgnoreCase("[FEC_VENCIMIENTO_GASES]")) certificadoXML = certificadoXML.replace(getTag("aem:FechaVencimientoGases", code), "");
        else if(code.equalsIgnoreCase("[NUM_DISTINTIVO]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroDistintivo", code), "");

        //Vehiculo/PropietarioDelVehiculo/domicilio
        else if(code.equalsIgnoreCase("[NUMERO_DOMICILIO]")) certificadoXML = certificadoXML.replace(getTag("aem:Numero", code), "");
        else if(code.equalsIgnoreCase("[NUMERO_DEPARTAMENTOCASA_DOMICILIO]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroDepartamentoCasa", code), "");
        else if(code.equalsIgnoreCase("[NUMERO_TORRE_DOMICILIO]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroTorre", code), "");
        else if(code.equalsIgnoreCase("[INFORMACION_ADICIONAL]")) certificadoXML = certificadoXML.replace(getTag("aem:InformacionAdicional", code), "");
        
        //Vehiculo/PropietarioDelVehiculo/nombres
        else if(code.equalsIgnoreCase("[APEL_MATERNO]")) certificadoXML = certificadoXML.replace(getTag("aem:apellidoMaterno", code), "");
        
        //Vehiculo/CaracteristicasDelVehiculo
        else if(code.equalsIgnoreCase("[APEL_MATERNO]")) certificadoXML = certificadoXML.replace(getTag("aem:apellidoMaterno", code), "");
        else if(code.equalsIgnoreCase("[TARA]")) certificadoXML = certificadoXML.replace(getTag("aem:Tara", code), "");
        else if(code.equalsIgnoreCase("[POTENCIA]")) certificadoXML = certificadoXML.replace(getTag("aem:Potencia", code), "");
        else if(code.equalsIgnoreCase("[CAP_KG]")) certificadoXML = certificadoXML.replace(getTag("aem:CapacidadKG", code), "");
        else if(code.equalsIgnoreCase("[NUM_SERVICIO]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroServicio", code), "");
        else if(code.equalsIgnoreCase("[COD_TIPO_CERTIFICACION]")) certificadoXML = certificadoXML.replace(getTag("aem:CodigoTipoCertificacion", code), "");
        else if(code.equalsIgnoreCase("[COD_TIPO_MOTOR]")) certificadoXML = certificadoXML.replace(getTag("aem:CodigoTipoMotor", code), "");
        else if(code.equalsIgnoreCase("[CANT_EJES]")) certificadoXML = certificadoXML.replace(getTag("aem:CantidadEjes", code), "");
        else if(code.equalsIgnoreCase("[NUM_ASIENTOS]")) certificadoXML = certificadoXML.replace(getTag("aem:NumeroAsientos", code), "");
        else if(code.equalsIgnoreCase("[COD_TIPO_CARROCERIA]")) certificadoXML = certificadoXML.replace(getTag("aem:CodigoTipoCarroceria", code), "");
        else if(code.equalsIgnoreCase("[MARCA_CARROCERIA]")) certificadoXML = certificadoXML.replace(getTag("aem:MarcaCarroceria", code), "");
        else if(code.equalsIgnoreCase("[CAP_TONELADAS]")) certificadoXML = certificadoXML.replace(getTag("aem:CapacidadToneladas", code), "");
        else if(code.equalsIgnoreCase("[CAP_M3]")) certificadoXML = certificadoXML.replace(getTag("aem:CapacidadM3", code), "");
        else if(code.equalsIgnoreCase("[PESO_BRUTO_VEHICULAR]")) certificadoXML = certificadoXML.replace(getTag("aem:PesoBrutoVehicular", code), "");
        else if(code.equalsIgnoreCase("[TRACCION]")) certificadoXML = certificadoXML.replace(getTag("aem:Traccion", code), "");
        else if(code.equalsIgnoreCase("[COLOR1]")) certificadoXML = certificadoXML.replace(getTag("aem:Color1", code), "");
        else if(code.equalsIgnoreCase("[COLOR2]")) certificadoXML = certificadoXML.replace(getTag("aem:Color2", code), "");
        else if(code.equalsIgnoreCase("[EJE1]")) certificadoXML = certificadoXML.replace(getTag("aem:Eje1", code), "");
        else if(code.equalsIgnoreCase("[EJE2]")) certificadoXML = certificadoXML.replace(getTag("aem:Eje2", code), "");

        return certificadoXML;
    }
    
    public abstract String replaceCertificado(String code, String certificadoXML);

}
