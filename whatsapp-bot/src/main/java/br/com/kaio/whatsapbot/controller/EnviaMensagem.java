package br.com.kaio.whatsapbot.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnviaMensagem {

	public static void enviarMensagens() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver-win64\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://web.whatsapp.com/");

		WebDriverWait wait = new WebDriverWait(driver, 60);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pane-side")));
			Thread.sleep(5000); 

			List<WebElement> conversas = driver.findElements(By.cssSelector("#pane-side span[dir='auto'][title]"));

			for (int i = 0; i < conversas.size(); i++) {
				try {
					List<WebElement> listaAtualizada = driver
							.findElements(By.cssSelector("#pane-side span[dir='auto'][title]"));
					WebElement conversa = listaAtualizada.get(i);

					System.out.println("Enviando para: " + conversa.getAttribute("title"));

					conversa.click();
					Thread.sleep(2000);

					String xPathCaixaText = "//*[@id='main']/footer//*[@role='textbox']";
					WebElement caixamensagem = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathCaixaText)));

					caixamensagem.sendKeys("Olá! Mensagem automática.");
					caixamensagem.sendKeys(Keys.RETURN);

					Thread.sleep(1000); 

				} catch (Exception e) {
					System.out.println("Pulei um item devido a erro: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Erro no carregamento principal.");
		}
	}

	public static void enviarMensagem() throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver-win64\\\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://web.whatsapp.com/");

		List<String> contato = Arrays.asList("Kaio", "Ivanilson", "Family 💙");

		WebDriverWait wait = new WebDriverWait(driver, 30);

		try {
			for (String nome : contato) {

				String cssSelector = "span[title='" + nome + "']";
				WebElement elementoContato = wait
						.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));

				elementoContato.click();

				Thread.sleep(2000);

				String mensagem = "Olá! Esta mensagem foi enviada por Java. ";

				String xPathCaixaText = "//*[@id='main']/footer//*[@role='textbox']";

				var caixamensagem = driver.findElement(By.xpath(xPathCaixaText));

				caixamensagem.sendKeys(mensagem);

				caixamensagem.sendKeys(Keys.RETURN);

				System.out.println("Mensagem enviada com sucesso!");
			}

		} catch (Exception e) {
			System.out.println(
					"O contato '" + contato + "' não foi encontrado a tempo. Verifique se o QR Code foi lido.");
		}

	}
}