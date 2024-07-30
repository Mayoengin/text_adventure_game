package game_object;

/**
 * the purpose of this method is to Generate a player when its called by the
 * main method.
 * 
 * @author Moe
 *
 */
public class Player extends GameObject {

	private Room location; // the Room where the Person is at present
	private int health, damage;// health and damage of player
	private Inventory bag; // inventory of player

	/**
	 * this method create a player
	 * 
	 * @param aName        Name of player
	 * @param aDescription Description of player
	 * @param health       Health of player
	 * @param adamage      Damage of player
	 * @param aRoom        initial Room the player spawn in
	 * @param abag         inventory of player
	 */
	public Player(String aName, String aDescription, int health, int adamage, Room aRoom, Inventory abag) {
		super(aName, aDescription); // init superclass
		this.location = aRoom; // location of player
		this.health = health;
		this.damage = adamage;
		this.bag = abag;
	}

	// --- access methods ---
	/**
	 * 
	 * @param aRoom set location
	 */
	public void setLocation(Room aRoom) {
		this.location = aRoom;
	}

	/**
	 * 
	 * @return get location
	 */
	public Room getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param health set health
	 */
	public void sethealth(int health) {
		this.health = health;
	}

	/**
	 * 
	 * @return get health
	 */
	public int gethealth() {
		return this.health;
	}

	/**
	 * 
	 * @param damage set damage
	 */
	public void setdamage(int damage) {
		this.damage = damage;
	}

	/**
	 * 
	 * @return get damage
	 */
	public int getdamage() {
		return this.damage;
	}

	/**
	 * 
	 * @param abag set inventory
	 */
	public void setInventory(Inventory abag) {
		this.bag = abag;
	}

	/**
	 * 
	 * @return get inventory
	 */
	public Inventory getInventory() {
		return this.bag;
	}

	/**
	 * this method gives player a perspective on his landscape and what he is facing
	 * 
	 * @param player take player variable to get his location
	 * @return return message containing the location Description and the enemy
	 *         around
	 */
	public static String look(Player player) {
		String msg = "";
		Room r = player.getLocation();
		msg = "> " + "You are in " + r.getName() + " " + r.getDescription();
		if (!r.getInventory().isEmpty())
			msg += "There is/are :" + "\r\n" + r.getInventory().toString();
		if (player.getLocation().getEnemy() != null)
			msg += " \r\n" + "you found " + player.getLocation().getEnemy().getDescription();
		return msg;
	}

}
