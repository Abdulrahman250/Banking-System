package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidationTest {
	CommandValidation createValidation;

	@BeforeEach
	void setUp() {
		createValidation = new CreateValidation();
	}

	@Test
	void valid_command() {
		boolean actual = createValidation.validate("Create checking 64737284 3.9");

		assertTrue(actual);
	}

	@Test
	void empty_command_will_be_invalid() {
		boolean actual = createValidation.validate(" ");

		assertEquals(false, actual);
	}

}
