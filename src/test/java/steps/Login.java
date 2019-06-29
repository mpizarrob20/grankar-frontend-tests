package steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import util.Browser;
import util.Functions;
import util.JsonUtil;

import java.util.ArrayList;


/**
 * Created by mpizarro on 7/3/2018.
 */
public class Login {
    private Browser util = new Browser();
    private Functions function = new Functions();
    private JsonUtil jsonUtil = new JsonUtil();
    private ArrayList<WebDriver> drivers = new ArrayList<>();
    private String resultuser;
    private String resultpassword;

    @Given("^el usuario inicia en \"(.*)\" en el (.*)$")
    public void iniciarNavegador(String url, String browsers) throws Exception {
        drivers = util.homePage(url, browsers);
    }

    //Verificar login correcto
    @When("^el usuario entra (.*) y (.*)$")
    public void iniciarSession(String usuario, String contrasenna) throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            function.functionLogin(usuario, contrasenna, driver);
            resultuser = usuario;
            resultpassword = contrasenna;

        }
    }

    @Then("^el usuario visualiza su perfil$")
    public void verificarInicioSession() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(3000);
            Assert.assertEquals(jsonUtil.returnJson("login.json").get("text_verificar_usuario_login"), function
                    .returnElementCssSelectorText(driver, "login.json", "element_verificar_usuario_login"));
        }
    }

    //Verificar login incorrecto
    @Then("^se verifica el mensaje de error$")
    public void verificarMensajeError() throws Exception {
        for (WebDriver driver : drivers) {
            if (resultuser.equals("usuario_correcto") && resultpassword.equals("contrasenna_incorrecta")) {
                Thread.sleep(3000);
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_error_usuario_contrasenna"), function
                        .returnElementCssSelectorText(driver, "login.json", "element_error_contrasenna_login"));
            } else if (resultuser.equals("usuario_incorrecto") && resultpassword.equals("contrasenna_correcta")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_error_usuario_login"), function
                        .returnElementCssSelectorText(driver, "login.json", "element_error_usuario_login"));
            } else if (resultuser.equals("usuario_correcto") && resultpassword.equals("texto_tres_elementos")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_error_cant_caract_min"), function
                        .returnElementCssSelectorText(driver, "login.json", "element_error_contrasenna_login"));
            } else if (resultuser.equals("texto_tres_elementos") && resultpassword.equals("contrasenna_correcta")) {
                Assert.assertEquals(jsonUtil.returnJson("mensajes.json").get("text_error_cant_caract_min"), function
                        .returnElementCssSelectorText(driver, "login.json", "element_error_usuario_login"));
            }
            else {
                System.out.print("----------------------------------No hay test para este caso");
            }
        }
    }

    //Verificar largo minimo de la contraseña
    @When("^el usuario ingresa (.*) o (.*) con cacracteres minimos$")
    public void verificarContrasennaCorta(String usuario, String contrasenna) throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            function.clickElementCSS(driver, "login.json", "element_focus_username");
            function.enterElementByCss(driver, "login.json", "element_username", usuario);
            function.clickElementCSS(driver, "login.json", "element_focus_password");
            function.enterElementByCss(driver, "login.json", "element_password", contrasenna);
            resultuser = usuario;
            resultpassword = contrasenna;
        }
    }

    //Verificar boton entrar deshabilitado
    @When("^el usuario solo ingresa (.*)$")
    public void ingresarUsuario(String usuario) throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(2000);
            function.clickElementCSS(driver, "login.json", "element_focus_username");
            function.enterElementByCss(driver, "login.json", "element_username", usuario);
        }
    }

    @Then("^se verifica que el boton de entrar este deshabilitado$")
    public void verificarBotonDeshabilitado() {
        for (WebDriver driver : drivers) {
            Assert.assertFalse(function.returnElementCSSSelectorDriver(driver, "login.json", "element_button").isEnabled());
        }
    }

    //Verificar cierre de la sesion
    @And("^una vez logueado presiona cerrar su sesion$")
    public void cerrarSession() throws Exception {
        for (WebDriver driver : drivers) {
            Thread.sleep(4000);
            function.clickElementCSS(driver, "login.json", "element_menu_derecho_usuario");
            Thread.sleep(3000);
            function.clickElementCSS(driver, "login.json", "element_cerrar_sesion");
        }
    }

    @Then("^la sesion de cierra$")
    public void verificarCierreSesion() {
        for (WebDriver driver : drivers) {
            Assert.assertTrue(function.returnElementCSSSelectorDriver(driver, "login.json", "element_username").isDisplayed());
            Assert.assertTrue(function.returnElementCSSSelectorDriver(driver, "login.json", "element_password").isDisplayed());
        }
    }

    @After
    public void driverClose() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }

}
