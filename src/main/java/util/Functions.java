package util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpizarro on 10/3/2018.
 */
public class Functions {
    private JsonUtil jsonUtil = new JsonUtil();
    private String text;
    private Actions actionBuilder;
    private WebElement element;

    public WebDriver functionLogin(String username, String password, WebDriver webDriver) throws Exception {
        clickElementCSS(webDriver, "login.json", "element_focus_username");
        enterElementByCss(webDriver, "login.json", "element_username", username);
        clickElementCSS(webDriver, "login.json", "element_focus_password");
        enterElementByCss(webDriver, "login.json", "element_password", password);
        clickElementCSS(webDriver, "login.json", "element_button");
        return webDriver;
    }

    public String returnElementCssSelectorText(WebDriver webDriver, String jsonName, String elementName) {
        text = webDriver.findElement(By.cssSelector((String) jsonUtil.returnJson(jsonName).get(elementName))).getText();
        return text;
    }

    public String returnElementIDText(WebDriver webDriver, String jsonName, String elementName) {
        text = webDriver.findElement(By.id((String) jsonUtil.returnJson(jsonName).get(elementName))).getText();
        return text;
    }

    public void enterElementByCss(WebDriver driver, String jsonName, String elementName, String valueName) {
        driver.findElement(By.cssSelector((String) jsonUtil.returnJson(jsonName).get(elementName))).sendKeys((String) jsonUtil.returnJson(jsonName).get(valueName));
    }

    public void enterElementByID(WebDriver driver, String jsonName, String elementName, String valueName) {
        driver.findElement(By.id((String) jsonUtil.returnJson(jsonName).get(elementName))).sendKeys((String) jsonUtil.returnJson(jsonName).get(valueName));
    }

    public void clickElementCSS(WebDriver driver, String jsonName, String elementName) {
        driver.findElement(By.cssSelector((String) jsonUtil.returnJson(jsonName).get(elementName))).click();
    }

    public void clickElementByID(WebDriver driver, String jsonName, String elementName) {
        driver.findElement(By.id((String) jsonUtil.returnJson(jsonName).get(elementName))).click();
    }

    public void clickMouseHover(WebDriver driver, String json, String elementName) {
        WebElement svgObj = driver.findElement(By.cssSelector((String) jsonUtil.returnJson(json).get(elementName)));
        actionBuilder = new Actions(driver);
        actionBuilder.moveToElement(svgObj).click().build().perform();
    }

    public WebElement returnElementCSSSelectorDriver(WebDriver webDriver, String jsonName, String elementName) {
        element = webDriver
                .findElement(By.cssSelector((String) jsonUtil.returnJson(jsonName).get(elementName)));
        return element;
    }

    public void verticalScroll(WebDriver driver, String json, String elementName) {
        element = driver.findElement(By.cssSelector((String) jsonUtil.returnJson(json).get(elementName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public String returnElementIdTextAttribute(WebDriver webDriver, String jsonName, String elementName, String
            attribute) {
        text = webDriver.findElement(By.id((String) jsonUtil.returnJson(jsonName).get(elementName)))
                .getAttribute(attribute);
        return text;
    }


    public String returnElement (WebElement lista, String elementName){
        WebElement listaElementosTr = lista;
        List<WebElement> elementosTh = listaElementosTr.findElements(By.tagName("th"));
        for (WebElement element : elementosTh) {
            if (element.getText().equalsIgnoreCase(elementName)){
                text = "Elemento corresponde con el filtro";
                break;
            } else {
                text = "El elemento encontrado no pertenece a los filtrados";
            }
        }
        return text;
    }

    public String returnContainElement (WebElement lista, String elementName){
        WebElement listaElementosTr = lista;
        List<WebElement> elementosTh = listaElementosTr.findElements(By.tagName("th"));
        for (WebElement element : elementosTh) {
            System.out.println(element.getText());
            int valor = element.getText().indexOf(elementName);
            if (valor != -1){
                text = "Elemento corresponde con el filtro";
                break;
            } else {
                text = "El elemento encontrado no pertenece a los filtrados";
            }
        }
        return text;
    }

    public String searchElement(WebDriver webDriver, String elementName, String listUl, String json){
        String elementResult = null;
        String mensaje = null;
        WebElement matchesUL = webDriver.findElement(By.cssSelector((String) jsonUtil.returnJson(json).get(listUl)));
        List<WebElement> matchesLI = matchesUL.findElements(By.tagName("tr"));
        for (WebElement element : matchesLI) {
            elementResult = returnElement(element, (String) jsonUtil.returnJson(json).get(elementName));
            if (elementResult.equals("Elemento corresponde con el filtro")) {
                mensaje = "Todos los elementos encontrados corresponden con el filtro";
            } else {
                mensaje = "Un elemento encontrado no corresponde con el filtro";
                break;
            }
        }
        return mensaje;
    }

    public String searchContainElement(WebDriver webDriver, String elementName, String listUl, String json){
        String elementResult = null;
        String mensaje = null;
        WebElement matchesUL = webDriver.findElement(By.cssSelector((String) jsonUtil.returnJson(json).get(listUl)));
        List<WebElement> matchesLI = matchesUL.findElements(By.tagName("tr"));
        for (WebElement element : matchesLI) {
            elementResult = returnContainElement(element, (String) jsonUtil.returnJson(json).get(elementName));
            if (elementResult.equals("Elemento corresponde con el filtro")) {
                mensaje = "Todos los elementos encontrados corresponden con el filtro";
            } else {
                mensaje = "Un elemento encontrado no corresponde con el filtro";
                break;
            }
        }
        return mensaje;
    }

}


