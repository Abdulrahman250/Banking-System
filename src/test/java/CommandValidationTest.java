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

	@Test
	void valid_when_it_includes_deposit_command() {
		boolean actual = commandValidation.validate("Deposit 150");

		assertTrue(actual);
	}

	@Test
	void invalid_when_missing_deposit_command() {
		boolean actual = commandValidation.validate("400");

		assertFalse(actual);
	}

	@Test
	void valid_account_type_is_listed() {
		boolean actual = commandValidation.validate("Create savings 64738362 6.8");

		assertTrue(actual);
	}

	@Test
	void invalid_account_type_is_listed() {
		boolean actual = commandValidation.validate("Create mastercard 48693947 5.4");

		assertFalse(actual);
	}

	@Test
	void valid_since_cd_has_all_required_inputs() {
		boolean actual = commandValidation.validate("Create cd 85648376 6.4 1200");

		assertTrue(actual);
	}

	@Test
	void invalid_since_cd_does_not_have_required_outputs() {
		boolean actual = commandValidation.validate("Create cd 83766463 4.2");

		assertFalse(actual);
	}

	@Test
	void valid_since_apr_is_in_range() {
		boolean actual = commandValidation.validate("Create checking 78564575 8.9");

		assertTrue(actual);
	}

	@Test
	void invalid_since_apr_is_not_in_range() {
		boolean actual = commandValidation.validate("Create checking 75637437 736.9");

		assertFalse(actual);
	}

	@Test
	void valid_uniqueid_has_the_correct_amount_of_digits() {
		boolean actual = commandValidation.validate("Create savings 75838564 5.3");

		assertTrue(actual);
	}

	@Test
	void invalid_since_uniqueid_has_the_incorrect_amount_of_digits() {
		boolean actual = commandValidation.validate("Create checking 56382 3.4");

		assertFalse(actual);
	}

	@Test
	void valid_deposit_amount() {
		boolean actual = commandValidation.validate("deposit 400");

		assertTrue(actual);
	}

	@Test
	void invalid_deposit_amount() {
		boolean actual = commandValidation.validate("deposit -300");

		assertFalse(actual);
	}

	@Test
	void invalid_when_there_is_duplicate_ids_made() {
		boolean actual = commandValidation.validate("Create checking 12345678 3.4");
		boolean actual2 = commandValidation.validate("Create savings 12345678 2.7");

		assertFalse(actual2);
	}

	@Test
	void invalid_cd_does_not_have_the_correct_amount_for_starting_balance() {
		boolean actual = commandValidation.validate("Create cd 64738276 7.5 3848728492");

		assertFalse(actual);
	}

	@Test
	void valid_cd_has_the_correct_amount_for_starting_balance() {
		boolean actual = commandValidation.validate("Create cd 65748378 4.3 6753");

		assertTrue(actual);
	}
}
