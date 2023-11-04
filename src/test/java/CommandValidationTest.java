import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidationTest {
	CommandValidation commandValidation;

	@BeforeEach
	void setUp() {
		commandValidation = new CommandValidation();
	}

	@Test
	void valid_command() {
		boolean actual = commandValidation.validate("Create checking 64737284 3.9");
		assertTrue(actual);

	}

	@Test
	void empty_command_will_be_invalid() {
		boolean actual = commandValidation.validate(" ");
		assertEquals(false, actual);
	}

	@Test
	void valid_when_it_has_create_command() {
		boolean actual = commandValidation.validate("Create checking 74638463 6.3");
		assertTrue(actual);
	}

	@Test
	void invalid_when_missing_create_command() {
		boolean actual = commandValidation.validate("checking 64729375 7.3");

		assertFalse(actual);
	}
}
