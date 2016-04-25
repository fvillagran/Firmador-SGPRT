
package cl.mtt.sgprt.binding;

import cl.mtt.sgprt.util.XMLUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class ReadWithScannerJUnitTest {
    
    public ReadWithScannerJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void regularExpresion() {
        
        String certificado = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<aem:CertificadoAnalisisGasesTipoA1 xmlns:aem=\"http://valida.aem.gob.cl\" xmlns=\"http://www.w3.org/2000/09/xmldsig#\"" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "xsi:schemaLocation=\"http://valida.aem.gob.cl CertificadoAnalisisGasesTipoA1.xsd http://www.w3.org/2000/09/xmldsig# " +
                "http://www.w3.org/TR/2002/REC-xmldsig-core-20020212/xmldsig-core-schema.xsd\">" +
                "	<aem:ResumenRevision>" +
                "		<aem:RutFuncionario>" +
                "			<aem:numero>[RUT_FUNCIONARIO]</aem:numero>" +
                "			<aem:dv>[DV_FUNCIONARIO]</aem:dv>" +
                "		</aem:RutFuncionario>" +
                "		<aem:RutMecanico>" +
                "			<aem:numero>[RUT_MECANICO]</aem:numero>" +
                "			<aem:dv>[DV_MECANICO]</aem:dv>" +
                "		</aem:RutMecanico>" +
                "		<aem:FechaCreacion>" +
                "			<aem:fechaValida>[FEC_CREACION]</aem:fechaValida>" +
                "		</aem:FechaCreacion>" +
                "		<aem:FechaRevision>" +
                "			<aem:fechaValida>[FEC_REVISION]</aem:fechaValida>" +
                "		</aem:FechaRevision>" +
                "		<aem:FechaVencimiento>" +
                "			<aem:fechaValida>[FEC_VENCIMIENTO]</aem:fechaValida>" +
                "		</aem:FechaVencimiento>" +
                "		<aem:TipoCertificado>[TIPODOC]</aem:TipoCertificado>" +
                "		<aem:URI>[URI]</aem:URI>" +
                "		<aem:IdCertificado>[ID_CRT_PRT]</aem:IdCertificado>" +
                "		<aem:CodigoPlantaRevisora>[COD_PRT]</aem:CodigoPlantaRevisora>" +
                "		<aem:NumeroCertificado>[NUM_CERTIFICADO]</aem:NumeroCertificado>" +
                "		<aem:NumeroDistintivo>[NUM_DISTINTIVO]</aem:NumeroDistintivo>" +
                "		<aem:HoraInicioRevision>[HORA_INICIO_REVISION]</aem:HoraInicioRevision>" +
                "		<aem:HoraTerminoRevision>[HORA_TERMINO_REVISION]</aem:HoraTerminoRevision>" +
                "		<aem:ResultadoRevisionTecnica>[RESULTADO_CRT]</aem:ResultadoRevisionTecnica>" +
                "		<aem:CantidadRechazos>[CANTIDAD_RECHAZOS]</aem:CantidadRechazos>" +
                "		<aem:CodigoRechazo>[COD_MOTIVO_RECHAZO]</aem:CodigoRechazo>" +
                "	</aem:ResumenRevision>" +
                "	<aem:Vehiculo>" +
                "		<aem:PropietarioDelVehiculo>" +
                "			<aem:run>" +
                "				<aem:numero>[RUT_PROPIETARIO]</aem:numero>" +
                "				<aem:dv>[DV_PROPIETARIO]</aem:dv>" +
                "			</aem:run>" +
                "			<aem:nombres>" +
                "				<aem:nombres>[NOMBRES]</aem:nombres>" +
                "				<aem:apellidoPaterno>[APEL_PATERNO]</aem:apellidoPaterno>" +
                "				<aem:apellidoMaterno>[APEL_MATERNO]</aem:apellidoMaterno>" +
                "			</aem:nombres>" +
                "			<aem:domicilio>" +
                "				<aem:Nombre>[NOMBRE_DOMICILIO]</aem:Nombre>" +
                "				<aem:Numero>[NUMERO_DOMICILIO]</aem:Numero>"+
                "				<aem:NumeroDepartamentoCasa>[NUMERO_DEPARTAMENTOCASA_DOMICILIO]</aem:NumeroDepartamentoCasa>" +
                "				<aem:NumeroTorre>[NUMERO_TORRE_DOMICILIO]</aem:NumeroTorre>" +
                "				<aem:Comuna>" +
                "					<aem:CodigoComuna>[COD_COMUNA]</aem:CodigoComuna>" +
                "					<aem:DescripcionComuna>[DESCRIPCION_COMUNA]</aem:DescripcionComuna>" +
                "				</aem:Comuna>" +
                "				<aem:Provincia>" +
                "					<aem:CodigoProvincia>[COD_PROVINCIA]</aem:CodigoProvincia>" +
                "					<aem:DescripcionProvincia>[DESCRIPCION_PROVINCIA]</aem:DescripcionProvincia>" +
                "				</aem:Provincia>" +
                "				<aem:Ciudad>[CIUDAD]</aem:Ciudad>" +
                "				<aem:Region>" +
                "					<aem:CodigoRegion>[COD_REGION]</aem:CodigoRegion>" +
                "					<aem:DescripcionRegion>[DESCRIPCION_REGION]</aem:DescripcionRegion>" +
                "				</aem:Region>" +
                "				<aem:Pais>[PAIS]</aem:Pais>" +
                "				<aem:CodigoPostal>[CODIGO_POSTAL]</aem:CodigoPostal>" +
                "				<aem:Tipo>[TIPO]</aem:Tipo>" +
                "				<aem:InformacionAdicional>[INFORMACION_ADICIONAL]</aem:InformacionAdicional>" +
                "			</aem:domicilio>" +
                "		</aem:PropietarioDelVehiculo>" +
                "		<aem:CaracteristicasDelVehiculo>" +
                "			<aem:patente>" +
                "				<aem:patente>[PPU]</aem:patente>" +
                "				<aem:dv>[PPU_DV]</aem:dv>" +
                "			</aem:patente>" +
                "			<aem:CodigoMunicipalidad>[COD_MUNICIPALIDAD]</aem:CodigoMunicipalidad>" +
                "			<aem:Tara>[TARA]</aem:Tara>" +
                "			<aem:Potencia>[POTENCIA]</aem:Potencia>" +
                "			<aem:CapacidadKG>[CAP_KG]</aem:CapacidadKG>" +
                "			<aem:NumeroMotor>[NUM_MOTOR]</aem:NumeroMotor>" +
                "			<aem:NumeroChasis>[NUM_CHASIS]</aem:NumeroChasis>" +
                "			<aem:VIN>[VIN]</aem:VIN>" +
                "			<aem:CodigoTipoServicio>[COD_TIPO_SERVICIO]</aem:CodigoTipoServicio>" +
                "			<aem:AnoFabricacion>[ANO_FABRICACION]</aem:AnoFabricacion>" +
                "			<aem:CodigoTipoVehiculo>[COD_TIPO_VEHICULO]</aem:CodigoTipoVehiculo>" +
                "			<aem:CodigoTipoCombustible>[COD_TIPO_COMBUSTIBLE]</aem:CodigoTipoCombustible>" +
                "			<aem:NumeroServicio>[NUM_SERVICIO]</aem:NumeroServicio>" +
                "			<aem:CodigoTipoCertificacion>[COD_TIPO_CERTIFICACION]</aem:CodigoTipoCertificacion>" +
                "			<aem:CodigoTipoMotor>[COD_TIPO_MOTOR]</aem:CodigoTipoMotor>" +
                "			<aem:CantidadEjes>[CANT_EJES]</aem:CantidadEjes>" +
                "			<aem:NumeroAsientos>[NUM_ASIENTOS]</aem:NumeroAsientos>" +
                "			<aem:CodigoTipoCarroceria>[COD_TIPO_CARROCERIA]</aem:CodigoTipoCarroceria>" +
                "			<aem:MarcaCarroceria>[MARCA_CARROCERIA]</aem:MarcaCarroceria>" +
                "			<aem:CapacidadToneladas>[CAP_TONELADAS]</aem:CapacidadToneladas>" +
                "			<aem:CapacidadM3>[CAP_M3]</aem:CapacidadM3>" +
                "		</aem:CaracteristicasDelVehiculo>" +
                "		<aem:MarcaDelVehiculo>" +
                "			<aem:CodigoMarca>[COD_MARCA]</aem:CodigoMarca>" +
                "			<aem:NombreMarca>[NOM_MARCA]</aem:NombreMarca>" +
                "			<aem:NombreModelo>[NOM_MODELO]</aem:NombreModelo>" +
                "		</aem:MarcaDelVehiculo>" +
                "	</aem:Vehiculo>" +
                "	<aem:EstacionesDeInspeccion>" +
                "		<aem:EstacionInspeccionGasesA1>" +
                "			<aem:ValorCoRalenti>[GASES-V_CO_RALENTI]</aem:ValorCoRalenti>" +
                "			<aem:ResultadoCoRalenti>[GASES-R_CO_RALENTI]</aem:ResultadoCoRalenti>" +
                "			<aem:ValorHcRalenti>[GASES-V_HC_RALENTI]</aem:ValorHcRalenti>" +
                "			<aem:ResultadoHcRalenti>[GASES-R_HC_RALENTI]</aem:ResultadoHcRalenti>" +
                "			<aem:ValorCoCo2Ralenti>[GASES-V_COCO2_RALENTI]</aem:ValorCoCo2Ralenti>" +
                "			<aem:ResultadoCoCo2Ralenti>[GASES-R_COCO2_RALENTI]</aem:ResultadoCoCo2Ralenti>" +
                "			<aem:ValorCo2500Rpm>[GASES-V_CO_2500RPM]</aem:ValorCo2500Rpm>" +
                "			<aem:ResultadoCo2500Rpm>[GASES-R_CO_2500RPM]</aem:ResultadoCo2500Rpm>" +
                "			<aem:ValorHc2500Rpm>[GASES-V_HC_2500RPM]</aem:ValorHc2500Rpm>" +
                "			<aem:ResultadoHc2500Rpm>[GASES-R_HC_2500RPM]</aem:ResultadoHc2500Rpm>" +
                "			<aem:ValorCo2Co2500Rpm>[GASES-V_COCO2_2500RPM]</aem:ValorCo2Co2500Rpm>" +
                "			<aem:ResultadoCo2Co2500Rpm>[GASES-R_COCO2_2500RPM]</aem:ResultadoCo2Co2500Rpm>" +
                "			<aem:ResultadoHumo>[GASES-R_HUMO]</aem:ResultadoHumo>" +
                "		</aem:EstacionInspeccionGasesA1>" +
                "		<aem:EstacionInspeccionOpacidadA1>" +
                "			<aem:ValorOpacidad1>[OPACIDAD-V_OPA1] </aem:ValorOpacidad1>" +
                "			<aem:ValorOpacidad2>[OPACIDAD-V_OPA2]</aem:ValorOpacidad2>" +
                "			<aem:ValorOpacidad3>[OPACIDAD-V_OPA3]</aem:ValorOpacidad3>" +
                "			<aem:ValorOpacidad4>[OPACIDAD-V_OPA4]</aem:ValorOpacidad4>" +
                "			<aem:ValorOpacidad5>[OPACIDAD-V_OPA5]</aem:ValorOpacidad5>" +
                "			<aem:ValorMediaOpacidadValida>[OPACIDAD-V_VALIDA_OPA]</aem:ValorMediaOpacidadValida>" +
                "			<aem:ResultadoOpacidadMedida>[OPACIDAD-R_OPA_MEDIDA]</aem:ResultadoOpacidadMedida>" +
                "			<aem:ValorOpacidadCarga>[OPACIDAD-V_CARGA]</aem:ValorOpacidadCarga>" +
                "			<aem:ResultadoOpacidadCarga>[OPACIDAD-R_CARGA]</aem:ResultadoOpacidadCarga>" +
                "		</aem:EstacionInspeccionOpacidadA1>" +
                "	</aem:EstacionesDeInspeccion>" +
                "</aem:CertificadoAnalisisGasesTipoA1>";
        
    
        //certificado = certificado.replace("<.*[ID_CRT_PRT].*>", "");      
        //certificado = certificado.replace("[ID_CRT_PRT]", "");
        
        String certificado2 = "<aem:IdCertificado>[ID_CRT_PRT]</aem:IdCertificado>" +
                "<aem:CodigoPlantaRevisora>[COD_PRT]</aem:CodigoPlantaRevisora>";
        
        //"this #?anystring; is  #?anystring2jk; test"        
        //"this is test"
        //myString.replaceAll("#\?.*?;", "");
        
        //certificado2 = certificado2.replace("<(*)[ID_CRT_PRT](*)>", "");
        //certificado2 = certificado2.replaceAll("ID.*PRT", "");
        //certificado2 = certificado2.replaceFirst("<.*[ID_CRT_PRT].*>", "");
        
        //String patternString = "<.*[ID_CRT_PRT].*>";
        //String patternString = "<+.*+[ID_CRT_PRT]+.*+>";
        String patternString = "<.*[ID_CRT_PRT]";
        Pattern p = Pattern.compile(patternString);        
        Matcher m = p.matcher(certificado2);
        
        boolean matches = m.matches();
        System.out.println("yyyyyyyyyyyy : " + matches);
        
        while (m.find()){
            System.out.println("zzzzzzzzzzz : " + m.start());
        }
        
        
        
        
        //System.out.println("resultado string : " + certificado2.matches("<.*[ID_CRT_PRT].*>"));
        //System.out.println("resultado string : " + certificado2.replaceFirst("(<.*[ID_CRT_PRT].*>)", ""));
        
    }
}
