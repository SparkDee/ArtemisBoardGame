package artemisLite;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see Game.java
 *
 */
public class GameActions {
	protected static Scanner scanner = new Scanner(System.in);
	static Random random = new Random(); 
	protected static final int WIN_GAME_SLEEP = 2000; 

	/**
	 * Prompts the user to enter the number of players and adds the players to the
	 * players ArrayList. Number of players must be between 2 and 4.
	 */
	public static void setNumberOfPlayers() {

		// Requires the user to enter an integer value of the number of players
				
		int numberOfPlayers = getNumberOfPlayers();

		// Loops through each player and prompts them to enter their name.
		for (int loop = 0; loop < numberOfPlayers; loop++) {
			GameActions.drawLine();
			System.out.println("|Please enter player " + (loop + 1) + "'s name");
			GameActions.drawLine();

			String playerName = scanner.next();
			playerName = checkForDuplicateName(playerName);
			// Creates a new instance of the Player class and adds it to the Players
			// ArrayList
			Player player = new Player(playerName);
			Game.players.add(player);
			GameActions.drawLine();
			System.out.println("|" + playerName + " added successfully.");
			GameActions.drawLine();
		}
	}

	/**
	 * Checks for a duplicate player name and asks for a new user input if a
	 * duplicate is identified
	 * 
	 * @param playerName
	 * @return
	 */
	public static String checkForDuplicateName(String playerName) {

		for (Player player : Game.players) {
			if (player.getPlayerName().equalsIgnoreCase(playerName)) {
				GameActions.drawLine();
				System.out.println("|Another player has used this name! - please select another");
				GameActions.drawLine();
				playerName = scanner.next();
				playerName = checkForDuplicateName(playerName);
			}
		}

		return playerName;
	}

	/**
	 * Simulates the throwing of two six sided dice. Minimum value is 2, maximum is
	 * 12.
	 * 
	 * @return An <code>integer</code> value representing <em>two thrown
	 *         six-sided</em> dice
	 */
	public static int rollDice(Player player) {

		// Prompts the user to roll the dice.
		GameActions.drawLine();
		System.out.println("|" + player.getPlayerName() + ", are you ready to roll the dice? Y/N");
		GameActions.drawLine();
		boolean readyToRoll = getUserInput();

		// JASON: Is there any way to make game over automatically quit the game? the
		// way I'm currently
		// doing it makes the method finish it's current loop before quitting
		if (readyToRoll == false) {
			GameActions.drawLine();
			System.out.println("|Would you like to quit the game? Y/N");
			GameActions.drawLine();
			if (getUserInput() == true) {
				quitGame();
			}
		}

		// Ensures that the user is ready to roll the dice.
		while (!readyToRoll) {
			GameActions.drawLine();
			System.out.println("|Enter Y when you are ready to roll the dice.");
			GameActions.drawLine();
			readyToRoll = getUserInput();
		}

		Random random = new Random();

		// Adds one to the dice value in order to avoid returning a value of 0.
		int diceValue = random.nextInt(11) + 2;

		// Tells the user what value they rolled.
		GameActions.drawLine();
		System.out.println("|You rolled a " + diceValue);
		GameActions.drawLine();

		return diceValue;
	}

	/**
	 * Displays the attributes of each player in the game
	 */
	public static void showAllPlayerStats() {
		GameActions.drawLine();
		System.out.println("|Would you like to see the attributes of all players? (Y/N)");
		GameActions.drawLine();
		boolean isWantingStats = getUserInput();

		if (isWantingStats == true) {
			for (Player player : Game.players) {
				player.printAll();
			}
		}

	}

	/**
	 * Displays the rules of the game to the players.
	 */
	public static void showRules() {
		GameActions.drawLine();
		System.out.println("|Would you like to read the rules of the game? (Y/N)");
		GameActions.drawLine();
		boolean wantsToSeeRules = getUserInput();

		if (wantsToSeeRules == true) {

			System.out.println("\n|-----------------------------------------" + "\n RULES OF THE GAME"
					+ "\n• 2-4 players \r\n" + "• If one player quits or bankrupt game ends \r\n"
					+ "• Players start at “Go” – Kennedy Space Centre \r\n"
					+ "• Players take turns to roll the dice and move the num squares that they rolle\r\n"
					+ "• 2 6 sided dice \r\n"
					+ "• When a player lands on a square they have option to buy the square – if they do not it is offered to the other players \r\n"
					+ "• If already owned the player must pay rent unless the square owner agrees not to charge\r\n"
					+ "• A player must own the whole system to develop a square \r\n"
					+ "• Rent increases the more developed a square is \r\n"
					+ "• If one player goes bankrupt or quits all players quit\r\n"
					+ "• When game ends show final state of play – show all developments and player funds – no need to convert properties / developments into equivalent funds \r\n"
					+ "• If all developments are completed then it announces the path ahead for Artemis project \r\n"
					+ "" + "\n|-----------------------------------------");
		}
	}

	/**
	 * When the player selects Quit game gameOver is set to false and final game
	 * stats are displayed
	 */
	public static void quitGame() {

		GameActions.drawLine();
		System.out.println("|Are you sure you want to quit? Enter Y OR N");
		GameActions.drawLine();
		boolean wantsToQuit = getUserInput();
		if (wantsToQuit == true) {
			Game.gameOver = true;
			GameActions.drawLine();
			System.out.println("|Thank you for playing Artemis Lite");
			showAllPlayerStats();
			GameActions.drawLine();
			System.exit(0);

		} else if (wantsToQuit == false) {
			GameActions.drawLine();
			System.out.println("|You have decided to continue your Artemis Lite mission to the Moon");
			GameActions.drawLine();
		}
	}

	/**
	 * Updates the player's position and calls the correct method depending on the
	 * square they have arrived at.
	 * 
	 * @param The player who is actively taking a turn
	 */
	public static void landOnSquare(Player player) {

		int squareNumber = player.getPosition();
		Square square = Game.board.get(squareNumber);

		if (player.isPassGo()) {
			passGo(player);
		}

		GameActions.drawLine();
		System.out.println("|You have landed on square " + player.getPosition());
		GameActions.drawLine();

		if (square instanceof Element) {
			Element element = (Element) square;
			checkElement(player, element);
		} else if (square instanceof Chance) {
			// chance method
			Chance.chanceOutcome(player);
		} else {
			// go method
			GameActions.drawLine();
			System.out.println("|You're in " + SquareType.KENNEDY_SPACE_CENTRE);
			System.out.println("|Relax, stock up on supplies and prepare for the journey ahead!");
			GameActions.drawLine();
		}

		// display options method

	}

	/**
	 * Displays the options which the player has available to them upon landing on
	 * an element.
	 * 
	 * @param player  The player who is actively taking a turn
	 * @param element The element which the player has landed on.
	 */
	public static void checkElement(Player player, Element element) {

		GameActions.drawLine();
		System.out.println("|You have landed on " + element.getElementName());
		System.out.println("|This is part of the " + element.getElementType() + " system.");
		GameActions.drawLine();
		if (element.getOwner() == null && player.getBalance() > element.getRent()) {

			GameActions.drawLine();
			System.out.println("|No one owns this Element yet. This element costs " + element.getRent()
					+ "- Would you like to buy it?");
			GameActions.drawLine();
			boolean wantsToBuy = getUserInput();
			if (wantsToBuy == true) {
				// buy element method
				buyElement(player, element);
				element.setLevel(1);
			} else {
				// offer to the rest of players
				offerElementToAll(player, element);
			}
		} else if (element.getOwner() == null && player.getBalance() <= element.getRent()) {
			GameActions.drawLine();
			System.out.println("|No one owns this element but your balance is " + player.getBalance());
			System.out.println("|This element costs " + element.getRent());
			System.out.println("|You are not able to buy this element without going bankrupt");
			GameActions.drawLine();
		} else if (element.getOwner() == player) {
			boolean ownsall = checkOwnsAllSystem(player, element);
			if (ownsall) {
				GameActions.drawLine();
				System.out.println("|You already own this element - would you like to buy a development?");
				GameActions.drawLine();
				boolean wantsToDevelop = getUserInput();
				if (wantsToDevelop) {
					if (element.getLevel() == 1) {
						GameActions.drawLine();
						System.out.println(
								"|This square is currently at level 1 (basic), would you like to upgrade to level 2 (Intermediate?) Y/N");
						GameActions.drawLine();
						boolean wantsMinorUpgrade = getUserInput();

						if (wantsMinorUpgrade == true) {
							buyMinorDevelopment(player, element);

							if (wantsMinorUpgrade) {

								buyMinorDevelopment(player, element);

								element.setLevel(2);
							}

						} else if (element.getLevel() == 2) {
							GameActions.drawLine();
							System.out.println(
									"|This square is currently at level 2 (intermediate), would you like to upgrade to level 3 (advanced?) Y/N");
							GameActions.drawLine();

							boolean wantsMajorUpgrade = getUserInput();
							if (wantsMajorUpgrade) {

								buyMajorDevelopment(player, element);
								element.setLevel(3);
							}
						} else if (element.getLevel() == 3) {
							GameActions.drawLine();
							System.out.println("|This square has already been fully upgraded! moving on..");
							GameActions.drawLine();
						}
					}
				}
			} else {
				GameActions.drawLine();
				System.out.println("|You own this element but you need to own the whole system to develop!");
				GameActions.drawLine();
			}
		} else {
			GameActions.drawLine();
			System.out.println("|" + element.getOwner().getPlayerName() + " owns this square");
			System.out.println("|The rent for this square is: " + element.getRent());
			System.out.println("|" + element.getOwner().getPlayerName() + ", do you wish to charge the rent owed?");
			GameActions.drawLine();
			if (getUserInput() == true) {
				// Calls the charge rent method
				player.chargeRent(element.getRent());
				GameActions.drawLine();
				System.out.println("|" + player.getPlayerName() + ", your balance is now " + player.getBalance());
				GameActions.drawLine();
			} else if (getUserInput() == false) {
				GameActions.drawLine();
				System.out.println("|You are very generous. No rent will be collected.");
				GameActions.drawLine();
			}

		}
	}

	/**
	 * Buys a specified element for a given player
	 * 
	 * @param player  - The player to whom the element will be assigned
	 * @param element - The element to be bought
	 */
	public static void buyElement(Player player, Element element) {

		GameActions.drawLine();
		System.out.println("|This element costs " + element.getRent());
		System.out.println("|Are you sure you want to buy the element? Y/N");
		GameActions.drawLine();
		boolean wantsToBuy = getUserInput();
		if (wantsToBuy == true) {
			element.setOwner(player);
			element.setLevel(1);
			player.setBalance(-element.getRent());
			GameActions.drawLine();
			System.out.println("|Congratulations! You now own " + element.getElementName() + ", part of the "
					+ element.getElementType() + " system");
			System.out.println("|Your balance is now: " + player.getBalance());
			GameActions.drawLine();
		} else if (wantsToBuy == false) {
			// offer to group method
			offerElementToAll(player, element);
		}

	}

	/**
	 * Upgrades a specified element for a given player through building a minor
	 * development
	 * 
	 * @param player  - The player who will buy the minor upgrade
	 * @param element - The element to be upgraded
	 */
	public static void buyMinorDevelopment(Player player, Element element) {

		GameActions.drawLine();
		System.out.println("|This minor upgrade costs " + element.getMinorUpgrade());
		System.out.println("|Are you sure you want to buy the minor upgrade? Y/N");
		GameActions.drawLine();
		boolean wantsToUpgrade = getUserInput();
		if (wantsToUpgrade == true) {
			element.setOwner(player);
			element.setLevel(2);
			player.setBalance(-element.getMinorUpgrade());
			GameActions.drawLine();
			System.out.println("|Congratulations! You have just upgraded " + element.getElementName() + ", part of the "
					+ element.getElementType() + " system");
			System.out.println("|Your balance is now: " + player.getBalance());
			GameActions.drawLine();
		} else if (wantsToUpgrade == false) {
			// add group method offer when added
		}

	}

	public static void buyMajorDevelopment(Player player, Element element) {

		GameActions.drawLine();
		System.out.println("|This major upgrade costs " + element.getMinorUpgrade());
		System.out.println("|Are you sure you want to buy the major upgrade? Y/N");
		GameActions.drawLine();
		boolean wantsToUpgrade = getUserInput();
		if (wantsToUpgrade == true) {
			element.setOwner(player);
			element.setLevel(3);
			player.setBalance(-element.getMajorUpgrade());
			GameActions.drawLine();
			System.out.println("|Congratulations! You have just upgraded " + element.getElementName() + ", part of the "
					+ element.getElementType() + " system");
			GameActions.drawLine();
			System.out.println("|This square has now been fully upgraded");
			GameActions.drawLine();
			System.out.println("|Your balance is now: " + player.getBalance());
			GameActions.drawLine();
		} else if (wantsToUpgrade == false) {
			// add group method offer when added
		}

	}

	/**
	 * 
	 * @param player  The player actively taking a turn.
	 * @param element The element to be purchased.
	 */
	public static void offerElementToAll(Player player, Element element) {
		boolean elementPurchased = false;
		for (Player playerOffered : Game.players) {
			if (!player.getPlayerName().equalsIgnoreCase(playerOffered.getPlayerName()) && !elementPurchased) {
				GameActions.drawLine();
				System.out.println("|" + playerOffered.getPlayerName() + ", would you like to buy "
						+ element.getElementName() + "?");
				GameActions.drawLine();
				boolean wantsToBuy = getUserInput();
				if (wantsToBuy == true) {
					buyElement(playerOffered, element);
					elementPurchased = true;
				}
			}
		}
		if (!elementPurchased) {
			GameActions.drawLine();
			System.out.println("|No one decided to buy " + element.getElementName() + ".");
			GameActions.drawLine();
		}

	}

	/**
	 * Draws a line to the screen.
	 */
	public static void drawLine() {
		System.out.println("|-----------------------------------------");
	}

	/**
	 * Gets the user's input. If input is not affirmative or negative, calls itself
	 * recursively.
	 * 
	 * @return A boolean value, true if the user enters "Y" or "Yes", false if "N"
	 *         or "No" (both case insensitive).
	 */
	public static boolean getUserInput() {
		String userInput = scanner.next();
		if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("Yes")) {
			return true;
		} else if (userInput.equalsIgnoreCase("N") || userInput.equalsIgnoreCase("No")) {
			return false;
		} else {
			GameActions.drawLine();
			System.out.println("|Unrecognised input, please try again");
			GameActions.drawLine();
			boolean secondInput = getUserInput();
			return secondInput; 
		}
		
	}

	/**
	 * Checks if the winning conditions have been met and ends the game if true.
	 */
	public static void checkWinConditions() {
		
		int elementCount = 0; 
		int fullyDevelopedCount = 0; 
		// TODO: Loop through all the squares in the board and see if they have been
		// developed.
		for(Square square : Game.board) {
			if(square instanceof Element) {
				elementCount++; 
				if(((Element)square).isFullyUpgraded()) {
					fullyDevelopedCount++; 
				}
			}
		}
		
		if (fullyDevelopedCount==elementCount) {
			Game.WIN_GAME = true; 
			Game.gameOver = true; 
		}
	}

	/**
	 * Prints the "Win Game" message and ends the game
	 */
	public static void winGame() {
		// TODO: Write a better "Win Game" message.
		drawLine();
		System.out.println("|Congratulations team, you have successfully launched!");
		System.out.println("|Incoming transmission:");
		drawLine();
		// TODO: Start thread here
		drawLine();
		System.out.println("|In 2021, " + Game.players.size() + " intrepid explorers took the next step for mankind.");
		drawLine();
		try {
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println("These brave explorers had one mission - to land the first woman and the next man on the MOON");
			drawLine();
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println("They worked as a team to change the world, building sustainable technologies such as: ");
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println(ElementType.EXPLORATION_GROUND_SYSTEMS);
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println(ElementType.LANDING_EQUIPMENT);
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println(ElementType.ORION);
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println(ElementType.SPACE_LAUNCH_SYSTEMS);
			Thread.sleep(WIN_GAME_SLEEP); 
			drawLine();
			System.out.println("NASA and the world salute all involved. Congratulations to: ");
			Thread.sleep(WIN_GAME_SLEEP);
			for(Player player : Game.players) {
				System.out.println(player.getPlayerName());
				Thread.sleep(WIN_GAME_SLEEP);
			}
			System.out.println("You are the pinnacle of innovation and have shown that working as a team is more powerful than going alone");
			Thread.sleep(WIN_GAME_SLEEP);
			System.out.println("Enjoy this victory - Next stop is MARS!!!");
			Thread.sleep(WIN_GAME_SLEEP);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Checks if a player owns all of a system
	 * 
	 * @param player
	 * @param element
	 * @return
	 */
	public static boolean checkOwnsAllSystem(Player player, Element element) {
		boolean ownsAll = false;
		int elementCount = 0;
		int elementsOwned = 0;

		for (Square square : Game.board) {
			if (square instanceof Element) {
				if (((Element) square).getElementType() == element.getElementType()) {
					elementCount++;
				}
				if (((Element) square).getOwner() == player
						&& ((Element) square).getElementType() == element.getElementType()) {
					elementsOwned++;
				}
			}
		}

		if (elementsOwned == elementCount) {
			ownsAll = true;
		}

		return ownsAll;

	}

	/**
	 * Alerts the player that they have completed a loop of the board and increases
	 * their balance by 200.
	 * 
	 * @param player The player actively taking a turn.
	 */
	public static void passGo(Player player) {

		System.out.println("You have passed through " + SquareType.KENNEDY_SPACE_CENTRE);
		System.out.println("Great news! You have recieved funding of " + Go.GO_FUNDING);
		player.setBalance(Go.GO_FUNDING);
		System.out.println(player.getPlayerName() + ", your balance is now " + player.getBalance());
		player.setPassGo(false);

	}

	/**
	 * Displays menu of actions to user
	 * 
	 * @param player
	 */
	public static void showMenu(Player player) {
		String option;
		System.out.println(player.getPlayerName() + " select your option");
		do {
			System.out.println("1. Show game progress");
			System.out.println("2. Check balance");
			System.out.println("3. End turn");
			System.out.println("4. Quit");
			option = scanner.next();

			switch (option) {
			case "1":
				drawLine();
				showBoard();
				drawLine();
				break;
			case "2":
				System.out.println("Your balance is: " + player.getBalance());
				break;
			case "3":
				// do nothing as loop ends and next player turn
				break;
			case "4":
				quitGame();
				break;
			default:
				System.out.println("Invalid Option!");
				;

			}
		} while (!option.equalsIgnoreCase("3"));
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getNumberOfPlayers() {
		int numberOfPlayers = 0;
		do {
			drawLine();
			System.out.println("|Please enter the number of players between 2 and 4 (inclusive) or 0 to quit.");
			GameActions.drawLine();
			String playersString = scanner.next();
			
		switch (playersString) {
		case "0":
			quitGame();
			break; 
		case "2":
			numberOfPlayers= 2; 
			
			break; 
		case "3":
			numberOfPlayers = 3; 
			
			break; 
		case "4":
			numberOfPlayers = 4; 
			
			break; 
			//numberOfPlayers = Integer.parseInt(playersString);
			
		default:
			drawLine();
			System.out.println("Invalid input");
			drawLine();
			break; 
		
		
		}
		
		}while(numberOfPlayers!=2&&numberOfPlayers!=3&& numberOfPlayers!=4); 
		return numberOfPlayers; 
		// Checks if the number of players is invalid and prompts the user to enter a
		// valid number.
		/*
		while (numberOfPlayers < 2 || numberOfPlayers > 4) {
			GameActions.drawLine();
			System.out
					.println("|Invalid number of players. Please enter a number between 2 and 4, or press 0 to leave.");
			GameActions.drawLine();
			getNumberOfPlayers();
			// Quits the game when 0 is entered.
			if (numberOfPlayers == 0) {
				quitGame();
			}

		}*/
		
	}
	/**
	 * Shows all Elements on the board and their status 
	 */
	public static void showBoard() {
		for(Square square: Game.board) {
			if(square instanceof Element) {
				((Element)square).showElementDetails();
			}
		}
	}
}
