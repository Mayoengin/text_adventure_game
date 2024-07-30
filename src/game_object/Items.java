package game_object;

/**
 * this class Generate Item when its called by the main method
 * 
 * @author Moe
 *
 */
public class Items extends GameObject {

	private int value;// int used for give a number for the item. used for heal,damage,armor....

	/**
	 * this method creates an item.
	 * 
	 * @param aName        name of the item
	 * @param aDescription Description of the item
	 * @param value        int used for give a number for the item. used for
	 *                     heal,damage,armor....
	 */
	public Items(String aName, String aDescription, int value) {
		super(aName, aDescription); // init superclass
		this.value = value;

	}

	/**
	 * 
	 * @param value int used for give a number for the item. used for
	 *              heal,damage,armor....
	 */
	public void setvalue(int value) {
		this.value = value;
	}

	/**
	 * 
	 * @return get value
	 */
	public int getvalue() {
		return this.value;
	}

	/**
	 * this method let player drop object where she/he stands in.
	 * 
	 * @param object the item chosen to drop
	 * @param player used to get the player location
	 * @return String print if which the item is in the inventory or is dropped.
	 */
	public static String dropObject(String object, Player player) {
		String s = "";
		Room r = player.getLocation();
		Items item = new Items(null, null, 0);
		if (player.getInventory().contains(object)) {
			item = player.getInventory().remove(object);
			r.getInventory().add(item);
			s = object + "is dropped";
		} else {
			s = "the Item is not with you";
		}
		return s;
	}

	/**
	 * his method let player take object where she/he stands in.
	 * 
	 * @param object the item chosen to take
	 * @param player used to get the player location
	 * @return String print if which the item is in the inventory or is not there.
	 */
	public static String takeObject(String object, Player player) {
		String s = "";
		Room r = player.getLocation();
		Items item = new Items(null, null, 0);
		if (r.getInventory().contains(object)) {
			item = r.getInventory().remove(object);
			player.getInventory().add(item);
			s = object + " is added to your Inventory";
		} else {
			s = "the Item is not here";
		}
		return s;
	}

}
