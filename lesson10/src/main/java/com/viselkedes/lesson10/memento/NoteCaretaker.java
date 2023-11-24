package main.java.com.viselkedes.lesson10.memento;

import java.util.ArrayList;
import java.util.List;

public class NoteCaretaker {
	private final List<Memento> mementos = new ArrayList<>();

	public void addMemento(Memento memento) {
		mementos.add(memento);
	}

	public Memento getMemento(int index) {
		return mementos.get(index);
	}
}
