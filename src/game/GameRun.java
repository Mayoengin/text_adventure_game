package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import globals.Directions;
import game_object.Player;
import game_object.Room;
import game_object.Enemy;
import game_object.Inventory;
import game_object.Items;

/**
 * this is the main class which will implement all other classes for this game.
 * 
 * @author Moe
 *
 */
public class GameRun {
	private ArrayList<Room> map; // the map - an ArrayList of Rooms
	public static List<String> actions = new ArrayList<>(Arrays.asList("take", "drop", "use", "attack", "go", "look",
			"north", "south", "east", "west", "i", "inventory", "m", "map"));// the actions - an ArrayList of the action
																				// allowed by the user
	private static List<String> utilities = new ArrayList<>(
			Arrays.asList("sword", "armor", "heal", "treasure", "key", "giant", "woolf"));// the utilities - an
																							// ArrayList of the
																							// utilities that the user
																							// can use
	private Enemy giant;// giant - declare an object of class enemy giant
	private Enemy woolf; // woolf - declare an object of class enemy woolf
	Player player = new Player(null, null, 0, 0, null, null); // player - declare a player.
	Inventory Home = new Inventory(); // Home - declare a object vector
	Inventory Forest = new Inventory();// Forest - declare a object vector
	Inventory Treasure = new Inventory();// Treasure - declare a object vector
	Inventory cave = new Inventory();// cave - declare a object vector
	boolean dead = false;// check if player is dead;

	/**
	 * this is the consturctor for the main class.
	 */
	public GameRun() {

		this.map = new ArrayList<Room>(); // the map - an ArrayList of Rooms.
		Home.add(new Items("sword", "the Sowrd of Naegling ", 300)); // adding object 'sword' to the vector Home. Item(
																		// name, description, value )
		Home.add(new Items("armor", "the chaos knight Armor ", 300));// adding object 'armor' to the vector Home.Item(
																		// name, description, value )
		Treasure.add(new Items("treasure", "the lost treasure", 0)); // adding object 'treasure' to the vector
																		// Treasure.Item( name, description, value )

		map.add(new Room("Home", ",A Resting village where you live. ", Directions.NOEXIT, Directions.NOEXIT,
				Directions.NOEXIT, 1, Home, null));// Room( name, description, N, S, W, E )
		map.add(new Room("the Forest", ",A Fearsome monsters lives here.", Directions.NOEXIT, 2, 0, Directions.NOEXIT,
				Forest, woolf));// Room( name, description, N, S, W, E )
		map.add(new Room("Cave", ",A dark,cold and smelly place.", 1, Directions.NOEXIT, Directions.NOEXIT, 3, cave,
				giant));// Room( name, description, N, S, W, E )
		map.add(new Room("treasure Room", ",A Room with a hefty treasure box.", Directions.NOEXIT, Directions.NOEXIT, 2,
				Directions.NOEXIT, Treasure, null));// Room( name, description, N, S, W, E )

		giant = new Enemy("Giant", "the giant garding the door", 100, 1000, map.get(2), true,
				"The Door to the cave is know open"); // Enemy( name, description, damage,health,Room,alive?)
		woolf = new Enemy("Woolf", "a big hungry woolf", 30, 10, map.get(1), true, "a Key droped from the woolf mouth");// Enemy(
																														// name,
																														// description,
																														// damage,health,Room,alive?)
		PlayerSetup();// calling method

	}

	private void PlayerSetup() {
		System.out.println("Enter you name");
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		String str = sc.nextLine(); // reads string
		player.setName(str);
		System.out.println("Discribe your champion");
		str = sc.nextLine();
		player.setDescription(str);
		int c = counter(str); // if the user enters more than 5 letter to his description he will get more
								// health and damage
		if (c < 5) {
			player.setdamage(10);
			player.sethealth(10);
		} else {
			player.setdamage(30);
			player.sethealth(30);
		}
		player.setLocation(map.get(0));// player location
		Inventory bag = new Inventory(); // bag - decalre an object vector
		player.setInventory(bag); // assign it to the player
	}

	/**
	 * this method will count the description sentence entered by the player
	 * 
	 * @param sentence input string
	 * @return int of the count
	 */
	private static int counter(String sentence) {
		if (sentence == null || sentence.isEmpty()) {
			return 0;
		}
		StringTokenizer tokens = new StringTokenizer(sentence);
		return tokens.countTokens();
	}

	/**
	 * this method will look if the object Giant is dead and if so let the player
	 * advance to the last room
	 * 
	 * @param check number of the room.
	 */
	private void checkForGiant(int check) {
		if (check == 3 && giant.getalive())// if player is in the room 3 and the giant is alive
		{
			System.out.println("the giant is blocking the door");
		} else {
			player.setLocation(map.get(check)); // set player location to the last room
			switch (check) {
			case 1:
				if (woolf.getalive())
					player.getLocation().setEnemy(woolf);// set player location to the second room
				break;
			case 2:
				if (giant.getalive())
					player.getLocation().setEnemy(giant);// set player location to the third room
				break;
			}
			System.out.println(Player.look(player));// look around for object and print
		}

	}

	/**
	 * this method will be the movement of the player inside the game
	 * 
	 * @param dir is the Direction given by the user to be checked
	 */
	private void movePlayerTo(Directions dir) {
		// if roomNumber = NOEXIT, display a special message, otherwise
		// display text (e.g. name and description of room)
		Room r = player.getLocation();
		int check;
		switch (dir) {
		case NORTH:
			check = r.getNorth();// check north
			break;
		case SOUTH:
			check = r.getSouth();// check south
			break;
		case EAST:
			check = r.getEast();// check east
			break;
		case WEST:
			check = r.getWest();// check west
			break;
		default:
			check = Directions.NOEXIT; // noexit - stay in same room
			break;
		}
		if (check != Directions.NOEXIT) {
			checkForGiant(check);
		} else {
			System.out.println("No exit");
		}
	}

	/**
	 * this method determine the outcome of the fights between the player and the
	 * enemy
	 * 
	 * @param enmy is the enemy object given by the user to battle
	 * @return msg string of the outcome of the fight
	 */
	private String battle(Enemy enmy) {
		String msg = "";// msg- the return string
		Room r = player.getLocation(); // r- define the player location
		if (enmy.getLocation() != player.getLocation()) {
			msg = enmy.getName() + " is not here"; // can't fight what's not there
		} else {
			if (enmy.getalive() == false) {
				msg = "you already killed this Enemy "; // dead enemy
			} else {
				int att = enmy.gethealth() - player.getdamage();// damage calculation for combat
				if (att <= 0) {
					msg = enmy.getName() + " Defeated ";
					msg += enmy.getreward();
					r.setDescription(r.getDescription() + " " + enmy.getreward()); // change room description
					if (enmy.getName() == "Woolf")
						Forest.add(new Items("key", "the lost treasure key", 0)); // add item to the second room
																					// inventory
					enmy.setalive(false);
					player.getLocation().setEnemy(null); // remove enemy

				} else {
					enmy.sethealth(att); // counter attack by the enemy
					msg += enmy.getName() + " health is now " + enmy.gethealth();// showing enemy health
					int counteratt = player.gethealth() - enmy.getattack();// calculating damage.
					if (counteratt <= 0) {
						msg += " " + enmy.getName() + " killed you"; // dead player
						dead = true;
					} else {
						player.sethealth(counteratt);
						msg += " your health is now " + player.gethealth();// your health
					}

				}
			}
		}
		return msg;
	}

	/**
	 * this method is to define which enemy the player is facing.
	 * 
	 * @param object the name of the enemy
	 * @return s the outcome of the battle.
	 */
	private String attack(String object) {
		String s = "";
		switch (object) {
		case "woolf":
			s = battle(woolf);
			break;
		case "giant":
			s = battle(giant);
			break;
		}

		return s;
	}

	/**
	 * this method take the object name from the parameter a and go throw a switch
	 * case
	 * 
	 * @param a string assigned to go throw a word case
	 * @return string the outcome of this parameter
	 */
	private String useItem(String a) {
		String msg = "";
		Items inuse;// item to be used.
		switch (a) {
		case "sword":// if the item was sword
			inuse = player.getInventory().remove(a); // remove item from inventory
			player.setdamage(player.getdamage() + inuse.getvalue());// set damage of player
			msg = "your damage is now " + player.getdamage(); // damage calculation printer.
			break;
		case "armor":
			inuse = player.getInventory().remove(a);// remove item from inventory
			player.sethealth(player.gethealth() + inuse.getvalue());// set health of player
			msg = "your health is now " + player.gethealth();// health calculation printer.
			break;
		case "key":
			if (player.getLocation() == map.get(3)) {
				inuse = player.getInventory().remove(a);// remove item from inventory
				msg = "you have found the lost tresure";// print end game.
			} else {
				msg = "you can only use the key to open the treasure";// print can't end game.
			}
			break;
		}
		return msg;
	}

	/**
	 * this method will show the player where is he on the map
	 * 
	 * @return s string of the map and the location of the player
	 */
	private String map() {
		Room r = player.getLocation();
		String s = "House-->Forest-->cave-->Tresure \r\n" + "and you are in " + r.getName();

		return s;
	}

	/**
	 * this metho will process the command of the user to a switch case throw
	 * multiple action cases
	 * 
	 * @param wordlist the command given by the user
	 * @return String giving feedback of the action
	 */
	private String processVerb(List<String> wordlist) {
		String action;// for user input
		String msg = "";// for return string
		action = wordlist.get(0);// getting the commmand
		if (!actions.contains(action)) {
			msg = action + " is not a known verb! "; // if the action is not in the wordlist
		} else {
			switch (action) {
			case "north":
				movePlayerTo(Directions.NORTH);// move north
				break;
			case "south":
				movePlayerTo(Directions.SOUTH);// move south
				break;
			case "west":
				movePlayerTo(Directions.WEST);// move west
				break;
			case "east":
				movePlayerTo(Directions.EAST);// move east
				break;
			case "look":
				Player.look(player);// look around for objects and give the names
				break;
			case "inventory":
			case "i":
				System.out.println(Inventory.showInventory(player));// print the inventory
				break;
			case "map":
			case "m":
				System.out.println(map());// give location of player
				break;
			default:
				msg = action + " (not yet implemented)";// extra....
				break;
			}
		}
		return msg;// print methods return value
	}

	/**
	 * this method will take the wordlist and process it throw a switch cases on
	 * which it is defined as actions and object, upon which a seperation has been
	 * made
	 * 
	 * @param wordlist wordlist command from user
	 * @return action for the command that the user requested
	 */
	private String processVerbNoun(List<String> wordlist) {
		String object;// for user input
		String action;// for user input
		String msg = "";// for return string
		boolean error = false;
		action = wordlist.get(0);// getting the command
		object = wordlist.get(1);// getting the command
		if (!actions.contains(action)) {
			msg = action + " is not a known verb! "; // if the action is not in the wordlist
			error = true;
		}
		if (!utilities.contains(object)) {
			msg += (object + " is not a known noun!"); // if the object is not in the wordlist
			error = true;
		}
		if (!error) {
			switch (action) {
			case "take":
				msg = Items.takeObject(object, player);// item added to player inventory
				break;
			case "drop":
				msg = Items.dropObject(object, player);// item droped from player inventory
				break;
			case "attack":
				msg = attack(object); // object here should be an Enemy.
				break;
			case "use":
				msg = useItem(object);// object here should be an item.
				break;
			default:
				msg += " (not yet implemented)";// extra...
				break;
			}
		}
		return msg;// print methods return value
	}

	/**
	 * Sample Java file by Huw Collingbourne from "The Little Book Of Adventure Game
	 * Programming in Java" www.bitwisebooks.com this method will process commands
	 * by the user as verb and noun and run a processVerb method if there is 1 word
	 * and ProcessVerbNoun method if there is 2 words
	 * 
	 * @author Huw Collingbourne
	 * @param wordlist command from user
	 * @return response from processVerb or processVerbNoun method depending on the
	 *         size of the wordlist
	 */
	private String parseCommand(List<String> wordlist) {
		String msg;
		if (wordlist.size() == 1) {
			msg = processVerb(wordlist);// if command is 1 word
		} else if (wordlist.size() == 2) {
			msg = processVerbNoun(wordlist);// if command is 2 word
		} else {
			msg = "Only 2 word commands allowed!";
		}
		return msg;
	}

	/**
	 * Sample Java file by Huw Collingbourne from "The Little Book Of Adventure Game
	 * Programming in Java" www.bitwisebooks.com this method will seperate argumment
	 * with \t,.:;?!\"' as a space
	 * 
	 * @author Huw Collingbourne
	 * @param input command from user to be checked
	 * @return command from user seperated by a space
	 */
	private static List<String> seperation(String input) {
		String delims = "[ \t,.:;?!\"']+";// values to be checked
		List<String> strlist = new ArrayList<>();// create new list
		StringTokenizer tokenizer = new StringTokenizer(input, delims);
		String t;

		while (tokenizer.hasMoreTokens()) {
			t = tokenizer.nextToken();
			strlist.add(t);
		}
		return strlist;
	}

	/**
	 * Sample Java file by Huw Collingbourne from "The Little Book Of Adventure Game
	 * Programming in Java" www.bitwisebooks.com this method will run the argumment
	 * throw a word list and run a seperation and parseCommand methods
	 * 
	 * @author Huw Collingbourne
	 * @param inputstr command from user to be checked
	 * @return response from parseCommad method
	 */
	public String runCommand(String inputstr) {
		List<String> wordlist;
		String s = "";
		String lowstr = inputstr.trim().toLowerCase();
		if (dead == true)
			lowstr = "q";
		if (!lowstr.equals("q")) {
			if (lowstr.equals("")) {
				s = "You must enter a command";
			} else {
				wordlist = seperation(lowstr);
				s = parseCommand(wordlist);
			}
		}
		return s;
	}

	/**
	 * this method print the intro of the game and perform a look method
	 */
	public void showIntro() {
		String s;
		s = "a king send you a letter orders you to follow a map leads into a lost treasure.\r\n"
				+ "Where do you want to go? [Enter north, south, west or east]?\n" + ">press m to see the map.\r\n"
				+ "(or enter . to quit)";
		System.out.println(s);
		System.out.println(Player.look(player));
	}

}