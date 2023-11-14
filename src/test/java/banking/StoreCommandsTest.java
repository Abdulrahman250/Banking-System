package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoreCommandsTest {
	StoreCommands storeCommands;

	@BeforeEach
	public void setUp() {
		storeCommands = new banking.StoreCommands();
	}

	@Test
	public void check_if_there_is_no_invalid_commands() {
		assertTrue(storeCommands.getInvalidCommands().isEmpty());
	}

	@Test
	public void add_invalid_commands() {
		storeCommands.addInvalidCommands("Invalid command");

		assertEquals(1, storeCommands.getInvalidCommands().size());

	}

	@Test
	public void add_two_invalid_commands() {
		storeCommands.addInvalidCommands("Invalid command");
		storeCommands.addInvalidCommands("Other invalid command");

		assertEquals(2, storeCommands.getInvalidCommands().size());
		assertTrue(storeCommands.getInvalidCommands().contains("Invalid command"));
		assertTrue(storeCommands.getInvalidCommands().contains("Other invalid command"));
	}
}
