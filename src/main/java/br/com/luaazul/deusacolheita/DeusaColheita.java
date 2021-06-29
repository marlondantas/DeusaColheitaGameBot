package br.com.luaazul.deusacolheita;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.deusacolheita.controller.DiscordService;
import br.com.luaazul.deusacolheita.controller.DiscordToken;
import br.com.luaazul.deusacolheita.controller.OperacaoService;
import br.com.luaazul.deusacolheita.dao.GeradorManagerFactory;

public class DeusaColheita {

	public static Logger logger = LoggerFactory.getLogger(DeusaColheita.class);

	public static void main(String[] args) throws IOException {
		
		logger.info("Aloha World!");
		logger.info("INCIANDO PROJETO: Deusa da Colehita!");

		// conectar no banco de dados.
		GeradorManagerFactory.iniciarBanco();
		// criar DAO das clases OKAY

		// TODO ler o arquivo de configuracao
		logger.info("INICIANDO LEITURA DE TOKEN DISCORD");
		DiscordToken.carregarProperties();
		
		// TODO Conectar no bot do discord.	
		DiscordService discordService = new DiscordService(DiscordToken.getTOKEN());
		
		// TODO escutar as chamadas.
		discordService.addOperacao(new OperacaoService());
		
		discordService.startBot();
		
		logger.info("Link para acessar o bot {}",discordService.getClient().createBotInvite());
		
		logger.info("Projeto carregado e operante!");
	}
	

}
