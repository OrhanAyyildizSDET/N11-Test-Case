package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.N11_AccountPage;
import pages.N11_HomePage;
import pages.N11_LoginPage;
import utils.ConfigReader;
import utils.Driver;
import utils.Reusable_Methods;

public class n11Favorite_StepDefs {
    WebDriver driver = Driver.getDriver();
    N11_HomePage homePage = new N11_HomePage();
    N11_LoginPage loginPage = new N11_LoginPage();
    N11_AccountPage accountPage = new N11_AccountPage();
    Actions action = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    String productId;
    Scenario scenario;

    @Before
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("user goes to homepage")
    public void userGoesToHomepage() {
        //Bu kısım Hook class kısmında halledildi.
        //As a base_url ı handled this part in Hook class
    }

    @Then("user on the homepage {string}")
    public void userOnTheHomepage(String homepage) {
        Assert.assertEquals("You are not on the homepage", homepage, driver.getCurrentUrl());
        //Here ı used screenshot method from resuable methods and named it
        //Burada resuable metotlardan ekran görüntüsü metodunu kullandım
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "homepage step");
    }

    @When("user click the login button")
    public void userClickTheLoginButton() {
        homePage.login.click();
    }

    @Then("user should see {string}")
    public void userShouldSee(String arg0) {
        Assert.assertEquals("You are not on the login page", arg0, loginPage.girisYapText.getText());
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "loginPage step");
    }

    @When("user enters email and password and click login button")
    public void userEntersEmailAndPasswordAndClickLoginButton() throws InterruptedException {
        loginPage.loginEmail.sendKeys(ConfigReader.getProperty("email"));
        Thread.sleep(2000);
        loginPage.loginPassword.sendKeys(ConfigReader.getProperty("password"));

        //email ve password girildiğinin kolayca görülmesi için Thread.sleep() kullandım.
        //I used Thread.sleep() to make it easy to see that email and password have been entered.
        Thread.sleep(2000);

        //Here ı used js executor to make sure button clicked. Sometimes just click() methods gives me error
        //Burada düğmenin tıklandığından emin olmak için js executor kullandım. Bazen sadece click() metodu hata veriyor
        js.executeScript("arguments[0].click();", loginPage.loginButton);

    }

    @Then("user should see {string} ont the account menu")
    public void userShouldSeeOntTheAccountMenu(String arg0) {
        //For mouse move methods ı used action class
        //Mouse hareketleri için action classını kullandım
        action.moveToElement(homePage.account).build().perform();

        //Here ı used implicitly wait for dont give synchronization error.
        //Burada senkronizasyon hatası alamamak için implicitly wait kullandım.
        Reusable_Methods.waitForVisibility(homePage.orders,5);

        Assert.assertEquals(arg0, homePage.orders.getText());
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "loginPage step");
    }

    @When("user search the  {string} word")
    public void userSearchTheWord(String arg0) {
        action.sendKeys(homePage.searchBar, arg0).sendKeys(Keys.ENTER).build().perform();
    }

    @Then("user should on the {string} result page")
    public void userShouldOnTheResultPage(String arg0) {
        Assert.assertTrue(homePage.resultText.getText().contains(arg0));
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "Iphone Search Page");
    }

    @And("user goes the {string}. page")
    public void userGoesThePage(String arg0) throws InterruptedException {
        /*Here ı goes the last product from <li> locators and wait here to loaded other products and if it is not the last product

        Burada  <li> lacotordan son ürüne gider ve diğer ürünlerin yüklenmesi için burada bekler, eğer son ürün değilse
        */

        WebElement currentProduct = null;
        while (true) {
            WebElement lastProduct = homePage.lastProduct;
            js.executeScript("arguments[0]. scrollIntoView(true);", lastProduct);
            Thread.sleep(3);
            if (lastProduct == currentProduct) {
                break;
            }
            else{
                currentProduct = lastProduct;
            }
        }
        js.executeScript("arguments[0]. scrollIntoView(true);", homePage.pageNumber(arg0));
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", homePage.pageNumber(arg0));

        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "Iphone Second Search Page");
    }

    @And("user add the number {string} product to favorites")
    public void userAddTheNumberProductToFavorites(String arg0) {
        homePage.addFavorite(arg0).click();
        productId = homePage.getProductId(arg0);
    }

    @And("user goes to account page and click the İstek Listem-Favorilerim")
    public void userGoesToAccountPageAndClickTheIstekListemFavorilerim() throws InterruptedException {
        js.executeScript("arguments[0].click();", homePage.account);
        accountPage.favorites.click();
        Thread.sleep(2000);
    }

    @Then("user see the added product in favorites page")
    public void userSeeTheAddedProductInFavoritesPage() {
        Assert.assertTrue(accountPage.checkProduct(productId));
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "My Favorites Page");
    }

    @When("user delete the favorite product from favorites page")
    public void userDeleteTheFavoriteProductFromFavoritesPage() throws InterruptedException {
        accountPage.deleteProduct(productId).click();
        Thread.sleep(8000);
    }

    @Then("user should not see the same favorite product on the favorite page")
    public void userShouldNotSeeTheSameFavoriteProductOnTheFavoritePage() {
        Assert.assertFalse(accountPage.checkProduct(productId));
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "My Favorites Deleted Iphone Page");
    }

    @And("user logs out")
    public void userLogsOut() {
        action.moveToElement(homePage.account).build().perform();
        accountPage.logout.click();
        scenario.attach(Reusable_Methods.getByteScreenshot(), "image/png", "Logout Page");
    }

}
