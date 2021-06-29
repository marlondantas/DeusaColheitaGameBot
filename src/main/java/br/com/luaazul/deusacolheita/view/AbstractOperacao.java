package br.com.luaazul.deusacolheita.view;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public abstract class AbstractOperacao implements MessageCreateListener{
	
	@Override
    public void onMessageCreate(MessageCreateEvent event) {
		
		execute(event);
		
    }
	
	public void execute(MessageCreateEvent event) {
		//DO something
	}
	
}
