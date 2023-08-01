package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class N11_HomePage {
    public N11_HomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "a[title='Giriş Yap']")
    public WebElement login;

    @FindBy(css = "a[title='Hesabım']")
    public WebElement account;

    @FindBy(css = "a[title='Siparişlerim']")
    public WebElement orders;

    @FindBy(id="searchData")
    public WebElement searchBar;

    @FindBy(xpath = "//div[@class= 'resultText ']/h1")
    public WebElement resultText;

    @FindBy(xpath = "//ul[@id='listingUl']/li[last()]")
    public WebElement lastProduct;

    @FindBy(xpath = "//div[@class = 'pagination']/a[2]")
    public WebElement page2;


    //Burada yapıyı dinamikleştirmek için "id, name, page number vs" methodlar yardımıyla lacate ettim ve kullandım.
    ////Here I locate and use "id, name etc" methods to dynamize the structure.
    public WebElement addFavorite(String listNumber){
        try {
            return Driver.getDriver().findElement(By.xpath("//ul[@id='listingUl']/li["+listNumber+"]/div/div/a/div/span"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public WebElement pageNumber(String pageNumber){
        try {
            return Driver.getDriver().findElement(By.xpath("//div[@class = 'pagination']/a["+pageNumber+"]"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getProductId(String listNumber){
        try {
            WebElement id = Driver.getDriver().findElement(By.xpath("//div/ul/li["+listNumber+"]/div"));
            return id.getAttribute("id");
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
