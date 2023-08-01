package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class N11_LoginPage {
    public N11_LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "div[class='leftBlock'] h2")
    public WebElement girisYapText;

    @FindBy(id = "email")
    public WebElement loginEmail;

    @FindBy(id = "password")
    public WebElement loginPassword;

    @FindBy(id = "loginButton")
    public WebElement loginButton;
}
