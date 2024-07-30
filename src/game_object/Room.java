package game_object;

/**
 * the purpose of this class is to generate a room when its called by the main
 * method.
 * 
 * @author Moe
 *
 */
public class Room extends GameObject {
	private int north, south, west, east; // int private variables for the directions
	Inventory room = new Inventory(); // creating inventory for the room
	private Enemy enemy; // adding Enemy variable in the room

	/**
	 * this method create new Room
	 * 
	 * @param aName        Room name
	 * @param aDescription Room Description
	 * @param aNorth       Direction north
	 * @param aSouth       Direction south
	 * @param aWest        Direction west
	 * @param aEast        Direction east
	 * @param aroom        Add Room inventory
	 * @param aenemy       Add Room enemy
	 */
	public Room(String aName, String aDescription, int aNorth, int aSouth, int aWest, int aEast, Inventory aroom,
			Enemy aenemy) {
		super(aName, aDescription); // init superclass
		north = aNorth;
		south = aSouth;
		west = aWest;
		east = aEast;
		room = aroom;
		enemy = aenemy;
	}

	// --- access methods ---
	/**
	 * 
	 * @return get north
	 */
	public int getNorth() {
		return north;
	}

	/**
	 * 
	 * @param aNorth set north
	 */
	public void setNorth(int aNorth) {
		north = aNorth;
	}

	/**
	 * 
	 * @return get south
	 */
	public int getSouth() {
		return south;
	}

	/**
	 * 
	 * @param aSouth set south
	 */
	public void setSouth(int aSouth) {
		south = aSouth;
	}

	/**
	 * 
	 * @return get east
	 */
	public int getEast() {
		return east;
	}

	/**
	 * 
	 * @param aEast set east
	 */
	public void setEast(int aEast) {
		east = aEast;
	}

	/**
	 * 
	 * @return get west
	 */
	public int getWest() {
		return west;
	}

	/**
	 * 
	 * @param aWest set west
	 */
	public void setWest(int aWest) {
		west = aWest;
	}

	/**
	 * 
	 * @param aroom set inventory
	 */
	public void setInventory(Inventory aroom) {
		room = aroom;
	}

	/**
	 * 
	 * @return get inventory
	 */
	public Inventory getInventory() {
		return this.room;
	}

	/**
	 * 
	 * @param aenemy set enemy
	 */
	public void setEnemy(Enemy aenemy) {
		enemy = aenemy;
	}

	/**
	 * 
	 * @return get enemy
	 */
	public Enemy getEnemy() {
		return this.enemy;
	}
}
