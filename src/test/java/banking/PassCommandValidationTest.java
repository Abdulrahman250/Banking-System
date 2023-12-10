package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassCommandValidationTest {
	PassCommandValidation passCommandValidation;

	@BeforeEach
	void setUp() {
		passCommandValidation = new banking.PassCommandValidation();
	}

	@Test
	void invalid_month_amount_negative() {
		boolean actual = passCommandValidation.validate("pass -23");
		assertFalse(actual);

	}

	@Test
	void valid_month_amount_between_0_and_60() {
		boolean actual = passCommandValidation.validate("pass 24");
		assertTrue(actual);

	}

	@Test
	void invalid_month_amount_is_letters() {
		boolean actual = passCommandValidation.validate("pass hshdnksk");
		assertFalse(actual);
	}

	@Test
	void valid_pass_all_capital_letters() {
		boolean actual = passCommandValidation.validate("PASS 28");
		assertTrue(actual);
	}

	@Test
	void invalid_missing_pass_command() {
		boolean actual = passCommandValidation.validate("21");
		assertFalse(actual);
	}

	@Test
	void valid_pass_first_letter_is_capital() {
		boolean actual = passCommandValidation.validate("Pass 25");
		assertTrue(actual);
	}

	@Test
	void invalid_typo_in_pass() {
		boolean actual = passCommandValidation.validate("pss 54");
		assertFalse(actual);
	}

	@Test
	void invalid_no_command_given() {
		boolean actual = passCommandValidation.validate(" ");
		assertFalse(actual);
	}

	@Test
	void invalid_no_spaces() {
		boolean actual = passCommandValidation.validate("pass20");
		assertFalse(actual);
	}

	@Test
	void invalid_random_letters() {
		boolean actual = passCommandValidation.validate("dhdjnfjskk");
		assertFalse(actual);
	}

	@Test
	void invalid_wrong_command() {
		boolean actual = passCommandValidation.validate("Create checking 5.2 76483629");
		assertFalse(actual);
	}
}
