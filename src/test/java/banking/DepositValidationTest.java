package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidationTest {
	DepositValidation depositValidation;

	@BeforeEach
	void setUp() {
		depositValidation = new banking.DepositValidation();
	}

	@Test
	void valid_when_it_includes_deposit_command() {
		boolean actual = depositValidation.validate("Deposit 150");

		assertTrue(actual);
	}

	@Test
	void invalid_when_missing_deposit_command() {
		boolean actual = depositValidation.validate("200");

		assertFalse(actual);
	}

	@Test
	void valid_deposit_amount() {
		boolean actual = depositValidation.validate("deposit 800");

		assertTrue(actual);
	}

	@Test
	void invalid_deposit_amount_when_negative() {
		boolean actual = depositValidation.validate("deposit -210");

		assertFalse(actual);
	}

	@Test
	void valid_deposit_first_letter_is_capital() {
		boolean actual = depositValidation.validate("Deposit 980");
		assertTrue(actual);
	}

	@Test
	void valid_deposit_all_capital_letters() {
		boolean actual = depositValidation.validate("DEPOSIT 620");
		assertTrue(actual);
	}

	@Test
	void invalid_wrong_command() {
		boolean actual = depositValidation.validate("Create checking 1.2 26351781");
		assertFalse(actual);
	}

	@Test
	void invalid_no_spaces() {
		boolean actual = depositValidation.validate("Deposit150");
		assertFalse(actual);
	}

	@Test
	void invalid_no_command_given() {
		boolean actual = depositValidation.validate(" ");
		assertFalse(actual);
	}

	@Test
	void invalid_random_letters() {
		boolean actual = depositValidation.validate("afhdja");
		assertFalse(actual);
	}

	@Test
	void invalid_deposit_amount_has_letters() {
		boolean actual = depositValidation.validate("deposit ahshaj");
		assertFalse(actual);
	}

	@Test
	void invalid_typo_in_deposit() {
		boolean actual = depositValidation.validate("Dyeposit 710");
		assertFalse(actual);
	}

}
