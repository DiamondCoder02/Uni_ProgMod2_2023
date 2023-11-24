package com.viselkedes.lesson10;

import main.java.com.viselkedes.lesson10.iterator.Inventory;
import main.java.com.viselkedes.lesson10.iterator.Item;
import main.java.com.viselkedes.lesson10.mediator.ChatRoom;
import main.java.com.viselkedes.lesson10.mediator.ChatUser;
import main.java.com.viselkedes.lesson10.memento.NoteApp;
import main.java.com.viselkedes.lesson10.memento.NoteCaretaker;

public class Lesson10Application {
	private static void testMediator(){
		ChatRoom chatRoom = new ChatRoom();

		ChatUser user1 = new ChatUser("User1", chatRoom);
		ChatUser user2 = new ChatUser("User2", chatRoom);
		ChatUser user3 = new ChatUser("User3", chatRoom);

		chatRoom.addUser(user1);
		chatRoom.addUser(user2);
		chatRoom.addUser(user3);

		user1.sendMessage("Hello!");
		user2.sendMessage("Hi!");
		user3.sendMessage("Hello, guys!");

		user1.receiveMessage("User1 Final Message");
		user2.receiveMessage("User2 Final Message");
		user3.receiveMessage("User3 Final Message");
	}

	public static void testMemento() {
		NoteApp noteApp = new NoteApp();
		NoteCaretaker caretaker = new NoteCaretaker();

		noteApp.setNote("Note 1");
		caretaker.addMemento(noteApp.createMemento());
		noteApp.setNote("Note 2");
		caretaker.addMemento(noteApp.createMemento());
		noteApp.setNote("Note 3");
		caretaker.addMemento(noteApp.createMemento());

		try {
			Memento previousMemento = caretaker.getMemento(0);
			noteApp.restoreMemento(previousMemento);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void testIterator() {
		Inventory inventory = new Inventory();
		inventory.addItem(new Item("Item 1"));
		inventory.addItem(new Item("Item 2"));
		inventory.addItem(new Item("Item 3"));

		try {
			for (Item item : inventory) {
				System.out.println(item.getName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		testMediator();
		System.out.println("--------------------------");
		testMemento();
	}
}
