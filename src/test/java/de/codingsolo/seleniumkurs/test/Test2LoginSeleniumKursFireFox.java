package de.codingsolo.seleniumkurs.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.seleniumkurs.pages.SeleniumKursHomePage;
import de.codingsolo.seleniumkurs.pages.SeleniumKursLoginPage;

public class Test2LoginSeleniumKursFireFox {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen- ich räume");
		driver.close();
	}

	@Test
	public void testLogin() {
		System.out.println("Starte Test Login Erfolgreich");

		// Arrange

		SeleniumKursLoginPage loginPage = new SeleniumKursLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium101", "codingsolo");

		// Act
		loginPage.loginButtonAnklicken();

		// Assert

		SeleniumKursHomePage homePage = new SeleniumKursHomePage(driver);
		String erfolgsMeldung = homePage.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains("Willkommen"));
	}

}
