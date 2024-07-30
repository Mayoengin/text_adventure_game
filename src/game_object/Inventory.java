package game_object;

/**
 * this class create is a vector class which used as an Inventory
 * 
 * @author Moe
 *
 */
public class Inventory {
	private Items[] items;

	public Inventory() {
		items = new Items[0];
	}

	/**
	 * this method add to the vector an object
	 * 
	 * @param item the object added to the vector
	 */
	public void add(Items item) {
		// First
		// to see if there are any free
		// (null) slots
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = item;
				return;
			}
		}

		// Otherwise, expand the array by
		// 1 to accommodate the new item
		// (and add the new item)
		int len = items.length;
		Items[] newItems = new Items[len + 1];
		for (int i = 0; i < len; i++) {
			newItems[i] = items[i];
		}
		newItems[len] = item;
		items = newItems;
	}

	/**
	 * this method remove a object from the vector
	 * 
	 * @param item the object to be removed
	 */
	public void remove(Items item) {
		for (int i = 0; i < items.length; i++) {
			if (item == items[i]) {
				items[i] = null;
				return;
			}
		}
	}

	/**
	 * this method removes an object based on its name
	 * 
	 * @param itemName the name of the object to be removed
	 * @return null
	 */
	public Items remove(String itemName) {
		// If they passed null, exit
		if (itemName == null) {
			return null;
		}

		// For each item in the room
		for (int i = 0; i < items.length; i++) {
			Items item = items[i];
			// If it's null, don't bother checking and
			// move on to the next item in the array
			if (item == null) {
				continue;
			}
			// If it's the same name, remove it
			if (itemName.equalsIgnoreCase(item.getName())) {
				items[i] = null;
				return item;
			}
		}

		return null;
	}

	/**
	 * this method checks if an item is in the vector
	 * 
	 * @param item the item to be checked
	 * @return boolean whether it contained the item or not
	 */
	public boolean contains(Items item) {
		if (item == null) {
			return false;
		}
		for (int i = 0; i < items.length; i++) {
			if (item == items[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method checks if an item is in the vector based on its name
	 * 
	 * @param itemName the item name to be checked
	 * @return boolean whether it contained the item or not
	 */
	public boolean contains(String itemName) {
		// If they passed null, don't bother checking
		if (itemName == null) {
			return false;
		}

		// For each item in the room
		for (int i = 0; i < items.length; i++) {
			Items item = items[i];
			// If it's null, don't bother checking and
			// move on to the next item in the array
			if (item == null) {
				continue;
			}
			// If it's the same name, return true
			if (itemName.equalsIgnoreCase(item.getName())) {
				return true;
			}
		}

		// If we couldn't find the item, return false
		return false;
	}

	/**
	 * this method print the items in the vector
	 */
	public String toString() {
		String vectorString = "";
		for (int i = 0; i < items.length; i++)
			if (items[i] != null)
				vectorString += i + 1 + "." + items[i].getName() + " " + items[i].getDescription() + "\r\n";
		return vectorString;
	}

	/**
	 * this method checks if the vector is empty or not
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * this method use the print method to print the player inventory.
	 * 
	 * @param player the player given to print the inventory
	 * @return a string of the inventory name and description
	 */
	public static String showInventory(Player player) {
		String s = "";
		s = player.getInventory().toString();
		return s;
	}
}
