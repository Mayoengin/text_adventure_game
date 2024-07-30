package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * this class is the main class which create a new game
 * 
 * @author Moe
 *
 */
public class StartGame {
	static GameRun game;

	/**
	 * this method is the main method of the game upon which the user enter his
	 * command.
	 * 
	 * @param args argumment given by the user
	 * @throws IOException error fed back to the user if an execpotion occurred
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in;
		String input;
		String output = "";
		game = new GameRun();
		in = new BufferedReader(new InputStreamReader(System.in));
		game.showIntro();
		do {
			System.out.print("> ");
			input = in.readLine();
			output = game.runCommand(input);

			if (!output.trim().isEmpty()) {
				System.out.println(output);
			}
		} while (!"q".equals(input));
	}

}
