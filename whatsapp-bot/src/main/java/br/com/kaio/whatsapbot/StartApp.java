package br.com.kaio.whatsapbot;

import java.io.IOException;

import br.com.kaio.whatsapbot.controller.EnviaMensagem;

public class StartApp {

	public static void main(String[] args) throws IOException, InterruptedException {

//		EnviaMensagem.enviarMensagens();
		EnviaMensagem.enviarMensagem();

	}
}
