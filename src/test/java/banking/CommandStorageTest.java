package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {
	CommandStorage commandStorage;

	@BeforeEach
	public void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	public void check_if_there_is_no_invalid_commands() {
		assertTrue(commandStorage.getInvalidCommands().isEmpty());
	}

	@Test
	public void add_invalid_commands() {
		commandStorage.addInvalidCommands("Invalid command");

		assertEquals(1, commandStorage.getInvalidCommands().size());

	}

	@Test
	public void add_two_invalid_commands() {
		commandStorage.addInvalidCommands("Invalid command");
		commandStorage.addInvalidCommands("Other invalid command");

		assertEquals(2, commandStorage.getInvalidCommands().size());
		assertTrue(commandStorage.getInvalidCommands().contains("Invalid command"));
		assertTrue(commandStorage.getInvalidCommands().contains("Other invalid command"));
	}
}
