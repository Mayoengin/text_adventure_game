package game_object;

/**
 * this class purpose is to create an Enemy when its called by the main method.
 * 
 * @author Moe
 *
 */
public class Enemy extends GameObject {

	private Room location; // the Room where the Person is at present
	private int attack, health;
	boolean alive = true;
	private String reward;

	/**
	 * this method create an Enemy with all of the parameters feed to it
	 * 
	 * @param aName        name of the Enemy
	 * @param aDescription Description of the Enemy
	 * @param aattack      attack that the Enemy deals
	 * @param ahealth      health of the Enemy
	 * @param aRoom        in which room the enemy should be in
	 * @param aalive       boolean to check if the enemy is alive or not
	 * @param areward      string telling what enemy drop after death
	 */
	public Enemy(String aName, String aDescription, int aattack, int ahealth, Room aRoom, boolean aalive,
			String areward) {
		super(aName, aDescription); // init superclass
		this.location = aRoom;
		this.attack = aattack;
		this.health = ahealth;
		this.alive = aalive;
		this.reward = areward;

	}

	/**
	 * 
	 * @param aRoom set where the enemy is spawn
	 */
	public void setLocation(Room aRoom) {
		this.location = aRoom;
	}

	/**
	 * get the loaction of the enemy
	 */
	public Room getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param ahealth set health of enemy
	 */
	public void sethealth(int ahealth) {
		this.health = ahealth;
	}

	/**
	 * 
	 * @return get health of enemy
	 */
	public int gethealth() {
		return this.health;
	}

	/**
	 * 
	 * @param aattack set attack of enemy
	 */
	public void setattack(int aattack) {
		this.attack = aattack;
	}

	/**
	 * 
	 * @return get attack of enemy
	 */
	public int getattack() {
		return this.attack;
	}

	/**
	 * 
	 * @param aalive set the enemy to alive or dead
	 */
	public void setalive(boolean aalive) {
		this.alive = aalive;
	}

	/**
	 * 
	 * @return get whether the enemy is alive or dead
	 */
	public boolean getalive() {
		return this.alive;
	}

	/**
	 * 
	 * @param areward set string what the enemy drop upon her/his death
	 */
	public void setrward(String areward) {
		this.reward = areward;
	}

	/**
	 * 
	 * @return string gives you what the enemy drop upon her/his death
	 */
	public String getreward() {
		return this.reward;
	}

}
