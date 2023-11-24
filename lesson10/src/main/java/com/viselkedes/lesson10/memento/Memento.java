package main.java.com.viselkedes.lesson10.memento;

public class Memento {
	private final String savedNote;

	public Memento(String savedNote) {
		this.savedNote = savedNote;
	}

	public String getSavedNote() {
		return savedNote;
	}
}
