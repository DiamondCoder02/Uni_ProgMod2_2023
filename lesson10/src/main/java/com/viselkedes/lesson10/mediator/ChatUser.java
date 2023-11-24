package main.java.com.viselkedes.lesson10.mediator;

public class ChatUser {
	private final String name;
	private final ChatMediator chatMediator;

	public ChatUser(String name, ChatMediator chatMediator) {
		this.name = name;
		this.chatMediator = chatMediator;
	}

	public void sendMessage(String message) {
		chatMediator.sendMessage(message, this);
	}

	public void receiveMessage(String message) {
		System.out.println(name + " received message: " + message);
	}
}
