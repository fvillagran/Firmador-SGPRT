
package cl.mtt.sgprt.util.factory;

public class CertificadoRevisionTecnicaTipoA1 extends AbstractCertificado{

    @Override
    public String replaceCertificado(String code, String certificadoXML) {
        //EstacionesDeInspeccion
        if(code.equalsIgnoreCase("[IDENT-R]")){
          certificadoXML = certificadoXML.replace(getTagApertura("aem:EstacionIdentificacion"), "");                          
          certificadoXML = certificadoXML.replace(getTag("aem:ResultadoIdentificacion", code), "");
          certificadoXML = certificadoXML.replace(getTagCierre("aem:EstacionIdentificacion"), "");
        }

        // EstacionInspeccionVisualA1
        else if(code.equalsIgnoreCase("[VISUAL-R_ID_PLACA]"))certificadoXML = certificadoXML.replace(getTag("aem:ResultadoIdPlaca", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CARROCERIA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCarroceria", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PARACHOQUE]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoParachoques", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VIDRIOS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVentanasVidriosParabrisas", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_LENTE_MICA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoLenteMica", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_RETROVISOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRetrovisores", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_RUEDAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRuedasNeumaticos", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_SENALIZADO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoSenalizadores", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_LIMPIAPARA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoLimpiaParabrisa", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_EMISIONES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoEmisiones", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PUERTA_CAP]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPuertasCapot", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_ESTRUCTURA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoEstructura", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CINTURONES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCinturones", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_ASIENTOS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAsientos", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_EXTINTOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoExtintor", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_BOTIQUIN]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoBotiquin", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_FRANJAS_REFLEC]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoFranjasReflectantes", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_SENALETICA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoSenaletica", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PINTURA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPintura", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_AUTO_ESCUELA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAutoEscuela", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VEH_ESCOLAR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVehiculoEscolar", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VEH_TAXI]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVehiculoTaxi", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VEH_AEROP]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVehiculoAeropuerto", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PAQ_RESOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVisualPaqueteResortes", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_DUCTOS_FREN]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoDuctosFrenos", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_DIRECCION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoDireccion", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_AMORTIG]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVisualAmortiguadores", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_B_TORSION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoVisualBarrasTorsion", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CHASIS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoChasis", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CILIND]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCilindros", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_REGULADOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRegulador", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VAL_ALIVIO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoValvulaAlivio", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_UNID_CONTROL]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoUnidadControl", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_VALVULA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoValvulas", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CONEXION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoConexion", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_TUBERIA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoTuberia", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_DISTINTIVO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoDistintivo", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_APT_CARROCERIA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAptitudCarroceria", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_SIST_TIRO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoSistemaTiro", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_RUEDA]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRueda5", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PERNO_REAL]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPernoReal", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_GAT_APOYO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoGatosApoyo", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_EQU_FRENO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoEquipoFreno", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_INST_ELECT]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoInstalacionElectrica", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_FUNC_LUCES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoFuncionamientoLuces", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_LLANTAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoLLantas", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_PULMONES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPulmones", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_TENSORES]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoTensores", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_CARROC_CHASIS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCarroceriaChasis", code), "");
        else if(code.equalsIgnoreCase("[VISUAL-R_DIM]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoDimension", code), "");

        // EstacionInspeccionLucesA1
        else if(code.equalsIgnoreCase("[LUCES-R_LUCES]"))certificadoXML = certificadoXML.replace(getTag("aem:ResultadoLuces", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_INTENSIDAD_BAJAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorIntensidadBajas", code), "");
        else if(code.equalsIgnoreCase("[LUCES-C_ALTURA_BAJAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoIntensidadBajas", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_PITCH_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_YAW_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_BREAK_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_PITCH_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_YAW_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_BREAK_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-R_ALIN_BAJAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAlineacionBajas", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_ALIN_ALTAS_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorAlineacionAltasDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_SENT_MARCHA_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorSentidoMarchaDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_ALIN_ALTAS_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorAlineacionAltasIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_SENT_MARCHA_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorSentidoMarchaIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-R_ALIN_ALTAS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAlineacionAltas", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_ALTURA_NEBLI]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorAlturaNeblineros", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_PITCH_NEBLI_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_BREAK_NEBLI_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_YAW_NEBLI_DER]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_PITCH_NEBLI_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchNeblinerosIzquierdo", code), ""); 
        else if(code.equalsIgnoreCase("[LUCES-V_BREAK_NEBLI_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakNeblinerosIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-V_YAW_NEBLI_IZQ]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawNeblinerosIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[LUCES-R_ALIN_NEBLI]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAlineacionNeblineros", code), "");
        else if(code.equalsIgnoreCase("[LUCES-R_CENTRO_FOCO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCentroFocos", code), ""); 

        //EstacionInspeccionAlineacionA1      
        else if(code.equalsIgnoreCase("[ALINEACION-V_EJE_DELANTERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorAlineacionEjeDelantero", code), "");
        else if(code.equalsIgnoreCase("[ALINEACION-R_EJE_DELANTERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAlineacionEjeDelantero", code), ""); 

        //EstacionInspeccionFrenosA1
        else if(code.equalsIgnoreCase("[FRENOS-V_DIF_EJE_DELANTERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-R_EJE_DELANTERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-V_EFI_FRENADO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawNeblinerosDerecho", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-R_EFI_FRENADO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPitchNeblinerosIzquierdo", code), ""); 
        else if(code.equalsIgnoreCase("[FRENOS-V_EFI_FRENO_AUX]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorBreakNeblinerosIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-R_EFI_FRENO_AUX]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorYawNeblinerosIzquierdo", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-V_DIF_EJE_TRASERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAlineacionNeblineros", code), "");
        else if(code.equalsIgnoreCase("[FRENOS-R_DIF_EJE_TRASERO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCentroFocos", code), ""); 

        //EstacionInspeccionHolgurasA1
        else if(code.equalsIgnoreCase("[HOLGURAS-R_ESTANQUE]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoEstanque", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_DUC_LIQFRE]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoDuctoLiquidoFreno", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_ESCAPE]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoEscape", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_CONV_CATAL]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoConvertidorCatalitico", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_B_TORSION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHolguraBarrasTorsion", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_TRANSMISIO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoTransmision", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_PAQ_RESORT]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHolguraPaqueteResortes", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_CAJA_DIREC]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoCajaDireccion", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_D_AMORTIGU]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHolguraAmortiguadores", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_B_DIRECCION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoBarraDireccion", code), ""); 
        else if(code.equalsIgnoreCase("[HOLGURAS-R_P_SUSPENCION]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPulmonSuspension", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_CHASIS]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoChasis", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_F_ESTACIONAM]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoHolguraFrenoEstacionamiento", code), "");
        else if(code.equalsIgnoreCase("[HOLGURAS-R_PISO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoPiso", code), ""); 

        //EstacionInspeccionGasesA1
        else if(code.equalsIgnoreCase("[GASES-V_CO_RALENTI]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorCoRalenti", code), "");
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

        //EstacionInspeccionAnguloGiro
        else if(code.equalsIgnoreCase("[ANGULO-R_GIRO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAnguloGiro", code), "");

        //EstacionInspeccionRuidoA1  
        else if(code.equalsIgnoreCase("[RUIDO-V_VIENTO]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorViento", code), "");      
        else if(code.equalsIgnoreCase("[RUIDO-R_VIENTO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoViento", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-INSCRIP_RNVM]")) certificadoXML = certificadoXML.replace(getTag("aem:InscripcionRnvm", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_FONDO_ESC]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoFondoEscape", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_ESC1]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoEscape1", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_ESC2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoEscape2", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_ESC3]")) certificadoXML = certificadoXML.replace(getTag("ValorRuidoEscape3", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_ESC4]")) certificadoXML = certificadoXML.replace(getTag("ValorRuidoEscape4", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_ESC5]")) certificadoXML = certificadoXML.replace(getTag("ValorRuidoEscape5", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_PROM_ESC]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPromedioRuidoEscape", code), ""); 
        else if(code.equalsIgnoreCase("[RUIDO-R_ESC]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRuidoEscape", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_FONDO_MOTOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoFondoMotor", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_MOTOR1]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoMotor1", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_MOTOR2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoMotor2", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_MOTOR3]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoMotor3", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_MOTOR4]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoMotor4", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_MOTOR5]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoMotor5", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_PROM_MOTOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPromedioRuidoMotor", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-R_MOTOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRuidoMotor", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_FONDO_INTERIOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoFondoInterior", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_INTERIOR1]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoInterior1", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_INTERIOR2]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoInterior2", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_INTERIOR3]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoInterior3", code), ""); 
        else if(code.equalsIgnoreCase("[RUIDO-V_INTERIOR4]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoInterior4", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_INTERIOR5]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorRuidoInterior5", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-V_PROM_INTERIOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ValorPromedioRuidoInterior", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-R_INTERIOR]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoRuidoInterior", code), "");
        else if(code.equalsIgnoreCase("[RUIDO-R_APARATO_SONORO]")) certificadoXML = certificadoXML.replace(getTag("aem:ResultadoAparatoSonoro", code), "");

        return certificadoXML;

    }  
}
