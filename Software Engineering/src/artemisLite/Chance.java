/**
 * 
 */
package artemisLite;

import java.util.Random;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see Element.java, SquareType.java, Chance.java, Go.java
 */
public class Chance extends Square {

	protected static final int chanceIncrease = 250;
	protected static final int chanceDeduct = -250;

	/**
	 * Default constructor
	 */
	public Chance() {

	}
	/**
	 * Constructor with arguments
	 * 
	 * @param squareNumber -The square's <em>position</em> within the board,
	 *                     represented numerically.
	 */
	public Chance(int squareNumber) {
		super(SquareType.CHANCE, squareNumber);

	}
	/**
	 * Produces a random number and increments or decrements the player's balance depending on the outcome
	 * 
	 * @param player - The player actively taking a turn
	 */
	public static void chanceOutcome(Player player) {

		Random random = new Random();
		int randomNumber;

		randomNumber = random.nextInt(5);
		randomNumber = randomNumber + 1;

		switch (randomNumber) {
		case 1:
			GameActions.drawLine();
			System.out.println("|The President has increased your project funding by 250");
			GameActions.drawLine();
			player.setBalance(chanceIncrease);
			break;
		case 2:
			GameActions.drawLine();
			System.out.println("|Lead engineer is ill, hire a replacement, pay 250 for a replacement");
			GameActions.drawLine();
			player.setBalance(chanceDeduct);
			break;
		case 3:
			GameActions.drawLine();
			System.out.println("|Foreign actors have hacked the network. Pay 250 to repair!");
			GameActions.drawLine();
			player.setBalance(chanceDeduct);
			break;
		case 4:
			GameActions.drawLine();
			System.out.println("|You have received a donation of 250 from a mystery billionaire!");
			GameActions.drawLine();
			player.setBalance(chanceIncrease);
			break;
		case 5:
			GameActions.drawLine();
			System.out.println("|The president has decreased funding by 250");
			GameActions.drawLine();
			player.setBalance(chanceDeduct);
			break;
		case 6:
			GameActions.drawLine();
			System.out.println("|Russia have made a breakthrough! Funding increased by 250");
			GameActions.drawLine();
			player.setBalance(chanceIncrease);
			break; 
		default:; 
		}
		System.out.println("Your balance is now: "+player.getBalance());
	}

}