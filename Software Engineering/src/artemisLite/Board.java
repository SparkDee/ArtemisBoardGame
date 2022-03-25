package artemisLite;

import java.util.ArrayList;

/**
 * @author Stuart McCann
 * @author Jason McKillen
 * @author Mark Davidson
 * @author Jeff Mitchell
 * @see Square.java, Element.java
 *
 */
public class Board {

	protected static final int NUM_SQUARES = 12;
	protected static final int EXPLORATION_GROUND_SYSTEMS_RENT = 50;
	protected static final int SPACE_LAUNCH_SYSTEMS_RENT = 100;
	protected static final int ORION_RENT = 150;
	protected static final int LANDING_EQUIPMENT_RENT = 200;
	protected static final int MINOR_UPGRADE = 500;
	protected static final int MAJOR_UPGRADE = 1000;

	/**
	 * @return Returns an ArrayList populated with the elements for the board
	 */
	public static ArrayList<Square> createBoard() {
		ArrayList<Square> board = new ArrayList<>(NUM_SQUARES);
		// create go square and add to board at index 0
		Go go = new Go(0);
		board.add(go);
		// creating elements for system A exploration ground system and adding to index
		// 1-3
		Square launchPad = new Element(1, ElementName.LAUNCH_PAD_39B, ElementType.EXPLORATION_GROUND_SYSTEMS,
				EXPLORATION_GROUND_SYSTEMS_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		Square vAB = new Element(2, ElementName.VEHICLE_ASSEMBLY_BUILDING, ElementType.EXPLORATION_GROUND_SYSTEMS,
				EXPLORATION_GROUND_SYSTEMS_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		Square youngCrippen = new Element(3, ElementName.YOUNG_CRIPPEN_FIRING_ROOM,
				ElementType.EXPLORATION_GROUND_SYSTEMS, EXPLORATION_GROUND_SYSTEMS_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		board.add(launchPad);
		board.add(vAB);
		board.add(youngCrippen);
		// creating elements for system B Space Launch systems and adding to index 4-5
		Square rS25Engines = new Element(4, ElementName.RS_25_ENGINES, ElementType.SPACE_LAUNCH_SYSTEMS,
				SPACE_LAUNCH_SYSTEMS_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		Square boosters = new Element(5, ElementName.BOOSTERS, ElementType.SPACE_LAUNCH_SYSTEMS,
				SPACE_LAUNCH_SYSTEMS_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		board.add(rS25Engines);
		board.add(boosters);
		// creating Chance square and add to index 6
		Square chance = new Chance(6);
		board.add(chance);
		// creating elements for system C Orion and add to 7-9
		Square serviceModule = new Element(7, ElementName.SERVICE_MODULE, ElementType.ORION, ORION_RENT, MINOR_UPGRADE,
				MAJOR_UPGRADE);
		Square crewModule = new Element(8, ElementName.CREW_MODULE, ElementType.ORION, ORION_RENT, MINOR_UPGRADE,
				MAJOR_UPGRADE);
		Square launchAbortSystem = new Element(9, ElementName.LAUNCH_ABORT_SYSTEM, ElementType.ORION, ORION_RENT,
				MINOR_UPGRADE, MAJOR_UPGRADE);
		board.add(serviceModule);
		board.add(crewModule);
		board.add(launchAbortSystem);
		// creating elements for systems D landing equipment and add to index 10-11
		Square lunarLander = new Element(10, ElementName.LUNAR_LANDERS, ElementType.LANDING_EQUIPMENT,
				LANDING_EQUIPMENT_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		Square artemisGenSpacesuit = new Element(11, ElementName.ARTEMIS_GENERATION_SPACESUITS,
				ElementType.LANDING_EQUIPMENT, LANDING_EQUIPMENT_RENT, MINOR_UPGRADE, MAJOR_UPGRADE);
		board.add(lunarLander);
		board.add(artemisGenSpacesuit);

		return board;
	}

}
