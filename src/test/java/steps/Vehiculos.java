package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.Browser;
import util.Functions;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class Vehiculos {
    private Browser util = new Browser();
    private Functions function = new Functions();
    private JsonUtil jsonUtil = new JsonUtil();
    private ArrayList<WebDriver> drivers = new ArrayList<>();
    private String varibleAux = null;

    @After
    public void driverClose() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }

    @Given("^el manager inicia en \"(.*)\" en el (.*)$")
    public void iniciarNavegador(String url, String browsers) throws Exception {
        drivers = util.homePage(url, browsers);
    }

    //Añadir nuevo vehiculo desde el menu de gestion
    @When("^el usuario entra a la seccion nuevo vehiculo$")
    public void seccionNuevoVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            function.functionLogin("usuario_correcto", "contrasenna_correcta", driver);
            Thread.sleep(3000);
            //function.clickElementCSS(driver, "vehiculos.json", "element_menu_gestion");
            function.clickMouseHover(driver, "vehiculos.json", "element_menu_nuevo_vehiculo");
        }
    }

    @And("^añade los datos de un nuevo vehiculo$")
    public void adicionarNuevoVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            //Pestaña datos del Vehiculo
            function.enterElementByID(driver, "vehiculos.json", "element_patente", "text_patente");
            function.enterElementByID(driver, "vehiculos.json", "element_vin", "text_vin");
            function.enterElementByID(driver, "vehiculos.json", "element_annio", "text_annio");
            function.enterElementByID(driver, "vehiculos.json", "element_categoria", "text_categoria");
            function.enterElementByID(driver, "vehiculos.json", "element_marca", "text_marca");
            function.enterElementByID(driver, "vehiculos.json", "element_modelo", "text_modelo");
            function.enterElementByID(driver, "vehiculos.json", "element_version", "text_version");

            //Pestaña Información General
            Thread.sleep(3000);
            function.clickElementCSS(driver, "vehiculos.json", "element_informacion_general");
            function.clickElementCSS(driver, "vehiculos.json", "element_acc");
            function.clickElementCSS(driver, "vehiculos.json", "element_airbag");
            function.clickElementCSS(driver, "vehiculos.json", "element_abs");
            function.clickElementCSS(driver, "vehiculos.json", "element_bas");
            function.clickElementCSS(driver, "vehiculos.json", "element_ebd");
            function.clickElementCSS(driver, "vehiculos.json", "element_cierre_central");
            function.clickElementCSS(driver, "vehiculos.json", "element_alza_vidrios");
            function.enterElementByID(driver, "vehiculos.json", "element_precio_venta", "text_precio_venta");
            function.enterElementByID(driver, "vehiculos.json", "element_carroceria", "text_carrocerria");
            function.enterElementByID(driver, "vehiculos.json", "element_condicion", "text_condicion");
            function.enterElementByID(driver, "vehiculos.json", "element_combustible", "text_combustible");
            function.enterElementByID(driver, "vehiculos.json", "element_traccion", "text_traccion");
            function.enterElementByID(driver, "vehiculos.json", "element_transmision", "text_transmision");
            function.enterElementByID(driver, "vehiculos.json", "element_motor", "text_motor");
            function.enterElementByID(driver, "vehiculos.json", "element_cilindrada", "text_cilindrada");
            function.enterElementByID(driver, "vehiculos.json", "element_kilometraje", "text_kilometraje");
            function.enterElementByID(driver, "vehiculos.json", "element_consumo_ciudad", "text_consumo_ciudad");
            function.enterElementByID(driver, "vehiculos.json", "element_consumo_carretera", "text_consumo_carretera");

            function.verticalScroll(driver, "vehiculos.json", "element_scroll_informacion_general");
            function.enterElementByID(driver, "vehiculos.json", "element_consumo_mixto", "text_consumo_mixto");
            function.enterElementByID(driver, "vehiculos.json", "element_cantidad_pasajeros", "text_cantidad_pasajeros");

            function.verticalScroll(driver, "vehiculos.json", "element_scroll_informacion_general");
            function.enterElementByID(driver, "vehiculos.json", "element_cantidad_puertas", "text_cantidad_puertas");
            function.enterElementByID(driver, "vehiculos.json", "element_color_predominante", "text_color_predominante");

            //Contacto
            function.clickElementCSS(driver, "vehiculos.json", "element_contacto");
            function.enterElementByID(driver, "vehiculos.json", "element_region", "text_region");
            function.enterElementByID(driver, "vehiculos.json", "element_provincia", "text_provincia");
            function.enterElementByID(driver, "vehiculos.json", "element_comuna", "text_comuna");

            //Imágenes
            function.clickElementCSS(driver, "vehiculos.json", "element_imagenes");
        }
    }

    @And("^guarda el vehiculo$")
    public void guardarVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            function.clickElementCSS(driver, "vehiculos.json", "element_buttom_guardar_vehiculo");

        }
    }

    @Then("^el vehiculo se guarda correctamente$")
    public void verificarCambiosGuardados() throws Exception {
        for (WebDriver driver : drivers) {
            //Verificar mensaje de error desaparece muy rapido
            Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_vehiculo_guardado"), function.returnElementCssSelectorText(driver, "mensajes.json", "element_vehiculo_guardado"));
        }

    }

    //Añadir vehiculo desde el boton
    @When("^el usuario hace click en el boton añadir$")
    public void botonNuevoVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            function.functionLogin("usuario_correcto", "contrasenna_correcta", driver);
            function.clickElementCSS(driver, "vehiculos.json", "element_menu_gestion");
            function.clickElementCSS(driver, "vehiculos.json", "element_menu_vehiculos");
            Thread.sleep(2000);
            function.clickElementCSS(driver, "vehiculos.json", "element_buttom_adicionar_vehiculo");
        }
    }

    //Listar vehiculos
    @When("^el usuario entra a la seccion listar vehiculos$")
    public void seccionListarVehiculos() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            function.functionLogin("usuario_correcto", "contrasenna_correcta", driver);
            Thread.sleep(3000);
//            function.clickElementCSS(driver, "vehiculos.json", "element_menu_gestion");
            function.clickMouseHover(driver, "vehiculos.json", "element_menu_vehiculos");
        }
    }

    @Then("^muestra la lista con los vehiculos$")
    public void mostrarListaVehiculos() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_patente"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_patente"));
            Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_nombre"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_nombre"));
            Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_vin"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_vin"));

            Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_vistas"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_vistas"));
            //Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_precio"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_precio"));
            Assert.assertEquals(jsonUtil.returnJson("vehiculos.json").get("text_list_vehiculo_estado"), function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_vehiculo_estado"));
        }
    }

    //Detalle del vehiculos desde la lista
    @And("^hace click sobre un vehiculo$")
    public void detalleVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            varibleAux = function.returnElementCssSelectorText(driver, "vehiculos.json", "element_list_patente");
            function.clickElementCSS(driver, "vehiculos.json", "element_first_vehiculo_list");
        }
    }

    @Then("^muestra el detalle de este vehiculo$")
    public void muestraDetalleVehiculo() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            Assert.assertEquals(varibleAux, function.
                    returnElementCssSelectorText(driver, "vehiculos.json", "element_detalle_numero_patente"));
        }
    }

    //Filtrar vehiculos por palabras completas
    @And("^filtra la busqueda por (.*)$")
    public void filtrarBusqueda(String filtro) throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(5000);
            function.enterElementByCss(driver, "vehiculos.json", "element_filtro", filtro);
            varibleAux = filtro;
        }
    }

    @Then("^se muestran los elementos filtrados$")
    public void muestraFiltro() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            if (varibleAux.equals("text_filtro_patente")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_mensaje_filtro_ok"), function.
                        searchElement(driver, "text_filtro_patente", "element_list_filtrados", "vehiculos.json"));
            } else if (varibleAux.equals("text_filtro_nombre")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_mensaje_filtro_ok"), function.
                        searchElement(driver, "text_filtro_nombre", "element_list_filtrados", "vehiculos.json"));
            } else if (varibleAux.equals("text_filtro_vin")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_mensaje_filtro_ok"), function.
                        searchElement(driver, "text_filtro_vin", "element_list_filtrados", "vehiculos.json"));
            } else if (varibleAux.equals("text_filtro_letras")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_mensaje_filtro_ok"), function.
                        searchContainElement(driver, "text_filtro_letras", "element_list_filtrados", "vehiculos.json"));
            } else {
                System.out.print("----------------------------------No hay test para este caso");
            }
        }
    }

    //Desactivar del vehiculos desde la lista
    @And("^selecciona todos los vehiculos$")
    public void seleccionarTodosVehiculos() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(5000);
            function.clickElementCSS(driver, "vehiculos.json", "element_seleccionar_todos_vehiculos");
        }
    }

    @And("^hace click sobre la accion rapida$")
    public void clickAccionRapida(String filtro) throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(5000);
            function.clickElementCSS(driver, "vehiculos.json", "element_acciones_rapidas");
        }
    }
}
