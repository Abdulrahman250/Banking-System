package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidationTest {
	WithdrawCommandValidation withdrawCommandValidation;

	@BeforeEach
	void setUp() {
		withdrawCommandValidation = new banking.WithdrawCommandValidation();
	}

	@Test
	void invalid_withdraw_amount_negative() {
		boolean actual = withdrawCommandValidation.validate("withdraw -500");
		assertFalse(actual);

	}

	@Test
	void invalid_withdraw_amount_letters() {
		boolean actual = withdrawCommandValidation.validate("withdraw sdfsd");
		assertFalse(actual);
	}

	@Test
	void valid_withdraw_amount_positive() {
		boolean actual = withdrawCommandValidation.validate("withdraw 500");
		assertTrue(actual);

	}

	@Test
	void invalid_missing_withdraw_command() {
		boolean actual = withdrawCommandValidation.validate("100");
		assertFalse(actual);
	}

	@Test
	void valid_has_withdraw_command() {
		boolean actual = withdrawCommandValidation.validate("withdraw 200");
		assertTrue(actual);
	}

	@Test
	void invalid_typo_in_withdraw() {
		boolean actual = withdrawCommandValidation.validate("wisfraw 340");
		assertFalse(actual);
	}

	@Test
	void invalid_no_command_given() {
		boolean actual = withdrawCommandValidation.validate(" ");
		assertFalse(actual);
	}

	@Test
	void invalid_no_spaces() {
		boolean actual = withdrawCommandValidation.validate("withdraw350");
		assertFalse(actual);
	}

	@Test
	void invalid_random_letters() {
		boolean actual = withdrawCommandValidation.validate("aeddfsdfdfsdf");
		assertFalse(actual);
	}

	@Test
	void invalid_wrong_command() {
		boolean actual = withdrawCommandValidation.validate("Create savings 3.2 57832988");
		assertFalse(actual);
	}

	@Test
	void valid_withdraw_all_capital_letters() {
		boolean actual = withdrawCommandValidation.validate("WITHDRAW 450");
		assertTrue(actual);
	}

	@Test
	void valid_deposit_first_letter_is_capital() {
		boolean actual = withdrawCommandValidation.validate("Withdraw 450");
		assertTrue(actual);
	}

}
