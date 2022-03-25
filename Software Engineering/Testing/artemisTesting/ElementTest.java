package artemisTesting;

import static org.junit.Assert.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Element;
import artemisLite.ElementName;
import artemisLite.ElementType;

class ElementTest {

	// test data
	int squareNumberValid;
	int squareNumberInvalid;

	int rentValid;
	int rentInvalid;

	int levelValid;
	int levelInvalid;
	
	int minorUpgradeValid;
	int minorUpgradeInvalid;

	int majorUpgradeValid;
	int majorUpgradeInvalid;
	
	

	Element element;

	@BeforeEach
	void setUp() throws Exception {

		squareNumberValid = 7;
		squareNumberInvalid = 15;

		rentValid = 125;
		rentInvalid = 251;

		levelValid = 2;
		levelInvalid = 4;
		
		minorUpgradeValid = 1000;
		majorUpgradeValid = 1500;

		

		element = new Element();

	}

	@Test
	void testElementDefaultConst() {
		assertNotNull(element);
	}

	@Test
	void testElementConstWithArgs() {
		Element element = new Element(squareNumberValid, ElementName.BOOSTERS, ElementType.ORION, rentValid, minorUpgradeValid, majorUpgradeValid);
		assertEquals(squareNumberValid, element.getSquareNumber());
		assertEquals("BOOSTERS", element.getElementName().toString());
		assertEquals("ORION", element.getElementType().toString());
		assertEquals(rentValid, element.getRent());
		assertEquals(minorUpgradeValid, element.getMinorUpgrade());
		assertEquals(majorUpgradeValid, element.getMajorUpgrade());
	}

	@Test
	void testGetSetSquareNumberValid() {
		element.setSquareNumber(squareNumberValid);
		assertEquals(squareNumberValid, element.getSquareNumber());
	}

	@Test
	void testGetSetSquareNumberInvalid() {
		element.setSquareNumber(squareNumberInvalid);
		assertEquals(squareNumberInvalid, element.getSquareNumber());
	}


	@Test
	void testGetSetRentValid() {
		element.setRent(rentValid);
		assertEquals(rentValid, element.getRent());

	}

	@Test
	void testGetSetRentInvalid() {
		element.setRent(rentInvalid);
		assertEquals(rentInvalid, element.getRent());

	}

	@Test
	void testGetSetLevelValid() {
		element.setLevel(levelValid);
		assertEquals(levelValid, element.getLevel());

	}

	@Test
	void testGetSetLevelinValid() {
		element.setLevel(levelInvalid);
		assertEquals(levelInvalid, element.getLevel());

	}

}