package de.codingsolo.seleniumkurs.test;


import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import de.codingsolo.seleniumkurs.pages.SeleniumKursHomePage;
import de.codingsolo.seleniumkurs.pages.SeleniumKursLoginPage;
import de.codingsolo.seleniumkurs.pages.SeleniumKursTestApplikationenPage;
import de.codingsolo.seleniumkurs.pages.SeleniumKursTestForm1Page;

public class Test3NavigationSeleniumKursChrome {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen- ich räume");
		driver.quit();
	}

	@Test
	public void testNavigation() {
		System.out.println("Starte Test Navigation zum TestForm1");
		
		//Arrange
		
		SeleniumKursLoginPage loginPage = new SeleniumKursLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium101", "codingsolo");
		loginPage.loginButtonAnklicken();
		
		//Act

		SeleniumKursHomePage homePage = new SeleniumKursHomePage(driver);
		homePage.menuAusklappen();
		homePage.seleniumTestAppLinkAnklicken();

		SeleniumKursTestApplikationenPage testAppPage = new SeleniumKursTestApplikationenPage(driver);
		testAppPage.testForm1anklicken();

		//Assert
		
		SeleniumKursTestForm1Page testForm1Page = new SeleniumKursTestForm1Page(driver);
		
		String erfolgsMeldung = testForm1Page.ueberschriftAuslesen();
		assertEquals(erfolgsMeldung, "Selenium Test Form1");
	}

}
