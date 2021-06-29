package br.com.luaazul.deusacolheita.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DiscordToken {

	public static Properties TOKEN;

	public static boolean carregarProperties() throws IOException {
		boolean saida = false;

		try (InputStream input = DiscordToken.class.getClassLoader().getResourceAsStream("discordBot.properties")) {

			DiscordToken.TOKEN = new Properties();

			if (input == null) {
				System.out.println("");
				return false;
			}
			// load a properties file from class path, inside static method
			DiscordToken.TOKEN.load(input);

			saida = true;

		} catch (IOException ex) {
			ex.printStackTrace();
			saida = false;
		}

		return saida;

	}

	public static String getTOKEN() {
		if(!DiscordToken.TOKEN.isEmpty()) {
			return DiscordToken.TOKEN.getProperty("discord.bot.token");
		}
		
		return "";			
	}
}
