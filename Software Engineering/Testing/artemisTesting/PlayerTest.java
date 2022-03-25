package artemisTesting;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import artemisLite.Player;

class PlayerTest {

	// test data

	String validPlayerName, invalidPlayerName;
	int validPosition, invalidPositionLower, invalidPositionHigher;
	int validBalance, invalidBalanceLower, invalidBalanceHigher;
	boolean bankruptValid, bankruptInvalid;

	@BeforeEach
	void setUp() throws Exception {

		validPlayerName = "validname";
		invalidPlayerName = "";

		validPosition = 0;
		invalidPositionLower = -1;
		invalidPositionHigher = 1;

		validBalance = 2000;
		invalidBalanceLower = 1999;
		validBalance = 2001;

		bankruptValid = false;
		bankruptInvalid = true;

	}

	@Test
	void testPlayerDefaultConst() {
		Player player = new Player();
		assertNotNull(player);
	}

	@Test
	void testPlayerConstWithArgsValid() {
		Player player = new Player(validPlayerName);
		assertEquals(validPlayerName, player.getPlayerName());

	}

	@Test
	void testPlayerConstWithArgsInvalid() {
		Player player = new Player(invalidPlayerName);
		assertEquals(invalidPlayerName, player.getPlayerName());

	}

	@Test
	void testGetSetPositionValid() {
		Player player = new Player();
		player.setPosition(validPosition);
		assertEquals(validPosition, player.getPosition());
	}

	@Test
	void testGetSetPositionInvalid() {
		Player player = new Player();
		player.setPosition(invalidPositionLower);
		assertEquals(invalidPositionLower, player.getPosition());

	}

	@Test
	void testGetSetBalanceValid() {
		Player player = new Player();
		player.setBalance(validBalance);
		assertEquals(validBalance, player.getBalance());
	}

	@Test
	void testGetSetBalanceInvalid() {
		Player player = new Player();
		player.setBalance(invalidBalanceHigher);
		assertEquals(invalidBalanceHigher, player.getBalance());
	}

	@Test
	void testGetSetBankruptValid() {
		Player player = new Player();
		player.setBankrupt(bankruptValid);
		assertEquals(bankruptValid, false);
	}

	@Test
	void testGetSetBankruptInvalid() {
		Player player = new Player();
		player.setBankrupt(bankruptInvalid);
		assertEquals(bankruptInvalid, true);
	}
}
