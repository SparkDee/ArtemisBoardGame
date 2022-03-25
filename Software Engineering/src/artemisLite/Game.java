/**
 * 
 */
package artemisLite;

import java.util.ArrayList;


/**
 * 
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 *
 */
public class Game {

	// Stuart: made NUM_SQUARES protected in board class so is available project
	// wide to remove
	// duplicate and easy update
	// private static final int NUM_SQUARES = 12;

	protected static boolean gameOver = false;
	protected static boolean WIN_GAME = true; 

	protected static ArrayList<Player> players = new ArrayList<Player>();
	protected static ArrayList<Square> board = Board.createBoard();

	public static void main(String[] args) {

		GameActions.drawLine();
		System.out.println("|Welcome to Artemis Lite");
		GameActions.drawLine();
		

		GameActions.setNumberOfPlayers();
		

		// Stuart: game in do while loop with condition gameOver = true
		// any methods that cause game to end i.e bankrupt / quit will set gameOver =
		// false
		GameActions.showAllPlayerStats();
		GameActions.showRules();
		
		do {

			// Stuart: For loop through an arrayList of players?
			for (Player player : players) {

				// Rolls the dice
				int playerRoll = GameActions.rollDice(player);

				// Moves the player to their new position from the dice roll
				player.setPosition(playerRoll);

				// Shows the player information about their new position and their available
				// choicesplayer
				GameActions.landOnSquare(player);

				GameActions.showMenu(player);

				// Checks if the conditions necessary to win the game have been met
				GameActions.checkWinConditions();
			}

		} while (!gameOver);
		
		if(WIN_GAME) {
			GameActions.winGame();
		}
		// game now over
		System.out.println("The final state of play for your Artemis Lite mission was: ");
		GameActions.showBoard();

	}
}
