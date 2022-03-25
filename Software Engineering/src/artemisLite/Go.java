
package artemisLite;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see Element.java, SquareType.java, Chance.java, Go.java
 *
 */
public class Go extends Square {

	protected static final int GO_FUNDING = 200;

	/**
	 * Default constructor
	 */
	public Go() {

	}
	/**
	 * Constructor with arguments
	 * 
	 * @param squareNumber -The square's <em>position</em> within the board,
	 *                     represented numerically.
	 */
	public Go(int squareNumber) {
		super(SquareType.KENNEDY_SPACE_CENTRE, squareNumber);
	}

	/**
	 * Adds credits to the specified player's balance
	 * 
	 * @param player - The player who is to be given money
	 */
	public void addFunding(Player player) {
		player.setBalance(GO_FUNDING);
	}

}
