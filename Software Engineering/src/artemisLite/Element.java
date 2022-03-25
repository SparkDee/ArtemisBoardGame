package artemisLite;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see ElementName.java, ElementType.java, Square.java
 * 
 *
 */
public class Element extends Square {
	protected static final int FULLY_UPGRADED_LEVEL = 3; 
	protected static final int MINOR_UPGRADE_LEVEL = 2; 

	// Sets instance variables
	private ElementName elementName;
	private ElementType elementType;
	private Player owner;
	private boolean minorUpgraded; 
	private boolean fullyupgraded;
	private int rent;
	private int minorUpgrade;
	private int majorUpgrade;
	private int level;

	/**
	 * 
	 * Default constructor for the element class
	 */
	public Element() {

	}

	/**
	 * Constructor with args for the element class
	 * 
	 * @param squareNumber
	 * @param elementName
	 * @param elementType
	 * @param rent
	 */
	public Element(int squareNumber, ElementName elementName, ElementType elementType, int rent, int minorUpgrade,
			int majorUpgrade) {
		super(SquareType.SYSTEM, squareNumber);
		this.elementName = elementName;
		this.elementType = elementType;
		this.rent = rent;
		this.minorUpgrade = minorUpgrade;
		this.majorUpgrade = majorUpgrade;
		this.level = 1; 
	}

	/**
	 * @return the elementName
	 */
	public ElementName getElementName() {
		return elementName;
	}

	/**
	 * @param elementName the elementName to set
	 */
	public void setElementName(ElementName elementName) {
		this.elementName = elementName;
	}

	/**
	 * Gets the type of the element
	 * 
	 * @return the elementType
	 */
	public ElementType getElementType() {
		return elementType;
	}

	/**
	 * Sets the type of the element
	 * 
	 * @param elementType the elementType to set
	 */
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	/**
	 * Gets the owner of the element
	 * 
	 * @return the owner of the element
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of the element
	 * 
	 * @param owner the owner of the element
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	

	/**
	 * @return the majorDevelopment
	 */
	public boolean isFullyUpgraded() {
		return fullyupgraded;
	}

	/**
	 * @param majorDevelopment the majorDevelopment to set
	 */
	public void setFullyUpgraded(boolean fullyupgraded) {
		this.fullyupgraded = fullyupgraded;
	}

	/**
	 * @return the rent
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * @param rent the rent to set
	 */
	public void setRent(int rent) {
		this.rent = rent;
	}

	public int getMajorUpgrade() {
		return majorUpgrade;
	}

	public void setMajorUpgrade(int majorUpgrade) {
		this.majorUpgrade = majorUpgrade;
		
	}

	public int getMinorUpgrade() {
		return minorUpgrade;
	}

	public void setMinorUpgrade(int minorUpgrade) {
		this.minorUpgrade = minorUpgrade;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		if(this.level==FULLY_UPGRADED_LEVEL) {
			this.setFullyUpgraded(true);
		}else if(this.level==MINOR_UPGRADE_LEVEL) {
			this.setMinorUpgraded(true);
		}
	}
	
	/**
	 * @return the minorUpgraded
	 */
	public boolean isMinorUpgraded() {
		return minorUpgraded;
	}

	/**
	 * @param minorUpgraded the minorUpgraded to set
	 */
	public void setMinorUpgraded(boolean minorUpgraded) {
		this.minorUpgraded = minorUpgraded;
	}

	/**
	 * Shows details of all elements to screen
	 */
	public void showElementDetails() {
		GameActions.drawLine();
		System.out.println("|" + this.elementName + ": " + this.elementType);
		if (this.owner != null) {
			System.out.println("|Owned by: " + this.owner.getPlayerName());
		} else {
			System.out.println("|Not currently owned");
		}
		System.out.println("|Minor upgrades: " + this.minorUpgraded);
		System.out.println("|Major upgrades: " + this.fullyupgraded);
		GameActions.drawLine();

	}

	

}