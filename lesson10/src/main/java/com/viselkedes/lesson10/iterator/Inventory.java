package main.java.com.viselkedes.lesson10.iterator;

import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Inventory {
	private final List<Item> items;

	public Inventory() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	@Override
	public Iterator<Item> iterator() {
		if (items.isEmpty()) {
			throw new NoSuchElementException("No items in inventory");
		}
		return items.iterator();
	}
}
