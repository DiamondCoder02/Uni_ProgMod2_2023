package main.java.com.viselkedes.lesson10.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
	private final List<ChatUser> users;

	public ChatRoom() {
        users = new ArrayList<>();
    }

	public ChatRoom(List<ChatUser> users) {
		this.users = users;
	}

	public void addUser(ChatUser user) {
		users.add(user);
	}

	@Override
	public void sendMessage(String message, ChatUser chatUser) {
		users.stream()
			.filter(user -> user != chatUser)
			.forEach(user -> user.receiveMessage(message));
	}
}
