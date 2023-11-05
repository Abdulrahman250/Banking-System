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

	@Test
	void valid_checking() {
		boolean actual = commandValidation.validate("Create checking 64738328 3.2");

		assertTrue(actual);
	}

	@Test
	void when_apr_is_negative_checking_is_invalid() {
		boolean actual = commandValidation.validate("Create checking 34279478 -3.2");

		assertFalse(actual);
	}

	@Test
	void when_apr_is_0_checking_is_valid() {
		boolean actual = commandValidation.validate("Create checking 46378438 0");

		assertTrue(actual);
	}

	@Test
	void if_apr_is_too_large_checking_is_invalid() {
		boolean actual = commandValidation.validate("Create checking 37468564 24824");

		assertFalse(actual);
	}

	@Test
	void if_apr_is_10_then_checking_is_valid() {
		boolean actual = commandValidation.validate("Create checking 45372876 10");

		assertTrue(actual);
	}

	@Test
	void invalid_checking_when_uniqueid_is_missing() {
		boolean actual = commandValidation.validate("Create checking 4.2");

		assertFalse(actual);
	}

	@Test
	void unique_id_contains_a_letter_which_makes_checking_invalid() {
		boolean actual = commandValidation.validate("Create checking 4536e837 7.4");

		assertFalse(actual);
	}

	@Test
	void invalid_checking_when_apr_is_missing() {
		boolean actual = commandValidation.validate("Create checking 46284638");

		assertFalse(actual);
	}

	@Test
	void invalid_checking_since_starting_balance_is_not_0() {
		boolean actual = commandValidation.validate("Create checking 56373829 5.3 382");

		assertFalse(actual);
	}

	@Test
	void invalid_checking_when_uniqueid_is_not_8_digits() {
		boolean actual = commandValidation.validate("Create checking 563728 3.2");

		assertFalse(actual);
	}

	@Test
	void valid_checking_even_when_create_is_capitalized() {
		boolean actual = commandValidation.validate("CREATE checking 57493827 5.3");

		assertTrue(actual);
	}

	@Test
	void invalid_cd_when_initial_balance_is_to_small() {
		boolean actual = commandValidation.validate("Create cd 56374382 5.3 21");

		assertFalse(actual);
	}

	@Test
	void valid_cd() {
		boolean actual = commandValidation.validate("Create cd 46372819 3.2 4302");

		assertTrue(actual);
	}

	@Test
	void invalid_cd_since_initial_balance_is_to_large() {
		boolean actual = commandValidation.validate("Create cd 57383726 5.2 34628819103");

		assertFalse(actual);
	}

	@Test
	void invalid_cd_apr_is_to_large() {
		boolean actual = commandValidation.validate("Create cd 46277462 762 4728");

		assertFalse(actual);
	}

	@Test
	void invalid_cd_missing_initial_balance() {
		boolean actual = commandValidation.validate("Create cd 76277462 3.1");

		assertFalse(actual);
	}

	@Test
	void valid_cd_when_apr_is_0() {
		boolean actual = commandValidation.validate("Create cd 96277462 0 4718");

		assertTrue(actual);
	}

	@Test
	void invalid_cd_since_uniqueid_contains_a_letter() {
		boolean actual = commandValidation.validate("Create cd 85d77462 8.3 4728");

		assertFalse(actual);
	}

	@Test
	void invalid_cd_when_apr_is_negative() {
		boolean actual = commandValidation.validate("Create cd 23277462 -4.7 3732");

		assertFalse(actual);
	}

	@Test
	void valid_cd_when_apr_is_10() {
		boolean actual = commandValidation.validate("Create cd 74277462 10 9828");

		assertTrue(actual);
	}

	@Test
	void invalid_cd_when_missing_uniqueid() {
		boolean actual = commandValidation.validate("Create cd 7.4 9463");

		assertFalse(actual);
	}

	@Test
	void invalid_cd_when_missing_apr() {
		boolean actual = commandValidation.validate("Create cd 16277462 7328");

		assertFalse(actual);
	}

	@Test
	void invalid_cd_when_uniqueid_is_not_8_digits() {
		boolean actual = commandValidation.validate("Create cd 46271217462 6.3 8562");

		assertFalse(actual);
	}

	@Test
	void valid_cd_even_when_create_is_capitalized() {
		boolean actual = commandValidation.validate("CREATE cd 74277462 2.2 4428");

		assertTrue(actual);
	}

	@Test
	void invalid_savings_when_missing_apr() {
		boolean actual = commandValidation.validate("Create savings 64277462");

		assertFalse(actual);
	}

	@Test
	void invalid_savings_when_uniqueid_contains_a_letter() {
		boolean actual = commandValidation.validate("Create savings r6277462 7.1");

		assertFalse(actual);
	}

	@Test
	void valid_savings() {
		boolean actual = commandValidation.validate("Create savings 99277462 5.2");

		assertTrue(actual);
	}

	@Test
	void invalid_savings_when_initial_balance_is_not_0() {
		boolean actual = commandValidation.validate("Create savings 23277462 9.0 4638");

		assertFalse(actual);
	}

	@Test
	void valid_savings_when_apr_is_0() {
		boolean actual = commandValidation.validate("Create savings 67277462 0");

		assertTrue(actual);
	}

	@Test
	void invalid_savings_when_apr_is_negative() {
		boolean actual = commandValidation.validate("Create savings 68277462 -6.2");

		assertFalse(actual);
	}

	@Test
	void invalid_savings_when_apr_is_to_large() {
		boolean actual = commandValidation.validate("Create savings 27277462 4466");

		assertFalse(actual);
	}

	@Test
	void valid_savings_when_apr_is_10() {
		boolean actual = commandValidation.validate("Create savings 58377462 10");

		assertTrue(actual);
	}

	@Test
	void invalid_savings_when_uniqueid_is_more_then_8() {
		boolean actual = commandValidation.validate("Create savings 834746277462 3.9");

		assertFalse(actual);
	}

	@Test
	void invalid_savings_when_there_is_no_uniqueid() {
		boolean actual = commandValidation.validate("Create savings 5.7");

		assertFalse(actual);
	}

	@Test
	void valid_savings_even_when_create_is_capitalized() {
		boolean actual = commandValidation.validate("CREATE savings 53277462 6.2");

		assertTrue(actual);
	}

	@Test
	void valid_case_capitalization() {
		boolean actual = commandValidation.validate("CrEaTe savings 74636257 7.5");

		assertTrue(actual);
	}

	@Test
	void invalid_cd_when_initial_balance_is_negative() {
		boolean actual = commandValidation.validate("Create cd 98765432 7.3 -27362");

		assertFalse(actual);
	}
}
