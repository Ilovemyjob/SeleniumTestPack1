package de.codingsolo.seleniumkurs.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import de.codingsolo.seleniumkurs.pages.SeleniumKursLoginPage;

public class Test1LoginFehlschlagSeleniumKursIE {

	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen- ich räume");
		driver.quit();
	}

	@Test
	public void testLogin() {
		System.out.println("Starte Test Login mit Fehlschlag");

		// Arrange

		SeleniumKursLoginPage loginPage = new SeleniumKursLoginPage(driver);
		loginPage.zugangsdatenEingeben("Benutzer", "test");

		// Act
		loginPage.loginButtonAnklicken();

		// Assert

		String fehlerMeldung = loginPage.statusMeldungAuslesen();
		assertTrue(fehlerMeldung.contains("Anmeldung fehlgeschlagen"));
	}

}
