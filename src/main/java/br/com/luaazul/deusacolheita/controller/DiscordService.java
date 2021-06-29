package br.com.luaazul.deusacolheita.controller;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;

public class DiscordService {

	private DiscordApi  client = null;
	private DiscordApiBuilder clientBuilder = null; 
	
	
	public DiscordService(String token) {
		this.clientBuilder  = new DiscordApiBuilder().setToken(token);
	}
	
	public void addOperacao(MessageCreateListener  operacao) {
		this.clientBuilder.addListener(operacao);
	}
	
	public DiscordApi  startBot() {
		this.client = this.clientBuilder.login().join();
		return this.client;
	}
		
	public DiscordApi getClient() {
		return this.client;
	}
	
	
}
