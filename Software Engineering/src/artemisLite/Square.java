package artemisLite;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see Element.java, SquareType.java, Board.java, Chance.java, Go.java
 *
 */
public class Square {
	/**
	 * The type of square (chosen from the <em>SquareType</em> class).
	 */
	private SquareType squareType;
	/**
	 * The <em>position</em> of the square on the board, represented numerically.
	 */
	int squareNumber;

	/**
	 * Default constructor.
	 */
	public Square() {

	}

	/**
	 * Constructor with arguments
	 * 
	 * @param squareType   - The type of square (chosen from the <em>SquareType</em>
	 *                     class).
	 * @param squareNumber The square's <em>position</em> within the board,
	 *                     represented numerically.
	 */
	public Square(SquareType squareType, int squareNumber) {
		this.squareType = squareType;
		this.squareNumber = squareNumber;
	}

	/**
	 * Gets the type of the square.
	 * 
	 * @return Returns an instance of the <em>SquareType</em> <code>class</code>.
	 */
	public SquareType getSquareType() {
		return squareType;
	}

	/**
	 * Sets the type of the square
	 * 
	 * @param squareType the squareType to set
	 */
	public void setSquareType(SquareType squareType) {
		this.squareType = squareType;
	}

	/**
	 * Gets the squareNumber value, which represents the square's position on the
	 * board.
	 * 
	 * @return Returns an <code>integer</code> representing the position of the
	 *         square on the board
	 */
	public int getSquareNumber() {
		return squareNumber;
	}

	/**
	 * Sets the square's position.
	 * 
	 * @param squareNumber The position of the square, represented numerically.
	 */
	public void setSquareNumber(int squareNumber) {
		this.squareNumber = squareNumber;
	}

}
