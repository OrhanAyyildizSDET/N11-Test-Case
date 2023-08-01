package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;
import utils.Reusable_Methods;

public class N11_AccountPage {
    public N11_AccountPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//li/a[contains(text(),'Favorilerim & Listelerim')]")
    public WebElement favorites;

    @FindBy(css = "a[title='Çıkış Yap']")
    public WebElement logout;


    //Burada yapıyı dinamikleştirmek için "id, name vs" methodlar yardımıyla lacate ettim ve kullandım.
    ////Here I locate and use "id, name etc" methods to dynamize the structure.

    public boolean checkProduct(String productId){
        try {
            WebElement checkProductExist = Reusable_Methods.waitForVisibility(Driver.getDriver().findElement(By.xpath("//div[@id]/ul/li/div[@id='" + productId + "']")), 10000);
            return checkProductExist.isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }

    public WebElement deleteProduct(String productId){
        return Driver.getDriver().findElement(By.xpath("//div[@id]/ul/li/div[@id='"+productId+"']/div/div"));
    }
}
