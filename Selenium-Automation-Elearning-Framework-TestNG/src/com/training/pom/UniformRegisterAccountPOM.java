package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UniformRegisterAccountPOM {
	private WebDriver driver;

	public UniformRegisterAccountPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[contains(text(),'Register Account')]")
	private WebElement pageHeader;
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastname;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-address-1")
	private WebElement address1;

	@FindBy(id = "input-postcode")
	private WebElement postcode;

	@FindBy(id = "input-zone")
	private WebElement zone;

	@FindBy(id = "input-country")
	private WebElement country;

	@FindBy(id = "input-city")
	private WebElement city;
	

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement confirmPwd;

	@FindBy(xpath = "//label[@class='radio-inline']//input[@value='1']")
	private WebElement rbnewsSubscribeYes;

	@FindBy(xpath = "//label[@class='radio-inline']//input[@value='0']")
	private WebElement rbnewsSubscribeNo;

	@FindBy(name = "agree")
	private WebElement cbPrivPolicy;

	@FindBy(xpath = "//input[@class='btn btn-primary']")
	private WebElement btnContinue;

	public String UniformRegisterPageHeader() {
		String UniformRegisterPageHeader = this.pageHeader.getText();
		return(UniformRegisterPageHeader);
	}
	public void sendfirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		
	}

	public void sendlastName(String lastname) {
		this.lastname.clear();
		this.lastname.sendKeys(lastname);
	}

	public void sendemail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}

	public void sendtelephone(String telephone) {
		this.telephone.clear();
		this.telephone.sendKeys(telephone);
	}

	public void sendaddress1(String address1) {
		this.address1.clear();
		this.address1.sendKeys(address1);
	}

	public void sendpostcode(String postcode) {
		this.postcode.clear();
		this.postcode.sendKeys(postcode);
	}

	public void selectzone(String zone) {
		this.zone.click();
		Select zoneselected = new Select(this.zone);
		zoneselected.selectByVisibleText(zone);
	}

	public void selectcountry(String country) {
		this.country.click();
		Select countrySelect=new Select(this.country);
		countrySelect.selectByVisibleText(country);
	}

	public void sendcity(String city) {
		this.city.clear();
		this.city.sendKeys(city);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void sendconfirmPwd(String confirmPwd) {
		this.confirmPwd.clear();
		this.confirmPwd.sendKeys(confirmPwd);
	}

	public void checkrbnewsSubscribeYes() {
		this.rbnewsSubscribeYes.click();

	}

	public void checkrbnewsSubscribeNo() {
		this.rbnewsSubscribeNo.click();

	}

	public void checkcbPrivPolicy() {
		this.cbPrivPolicy.click();
	}

	public void clickContinueBtn() {
		this.btnContinue.click();
	}
}
