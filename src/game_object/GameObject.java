package game_object;

/**
 * this class is a super class which used to give name and description of all
 * objects in this game.
 * 
 * @author Moe
 *
 */
public class GameObject {
	private String name;
	private String description;

	/**
	 * this method construct a name and a Description called by other method.
	 * 
	 * @param aName        name of the object
	 * @param aDescription Description of the object
	 */
	public GameObject(String aName, String aDescription) {
		// constructor
		name = aName;
		description = aDescription;
	}

	/**
	 * 
	 * @return get the name of object
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param aName set name of object
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * 
	 * @return get the Description of object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param aDescription set Description of object
	 */
	public void setDescription(String aDescription) {
		description = aDescription;
	}

}
