package main.java.com.viselkedes.lesson10.memento;

public class NoteApp {
	private String note;

	public void setNote(String note) {
		this.note = note;
	}
	public String getNote() {
		return note;
	}

	public Memento createMemento() {
		return new Memento(note);
	}

	public void restoreMemento(Memento memento) {
		this.note = memento.getSavedNote();
	}
}
