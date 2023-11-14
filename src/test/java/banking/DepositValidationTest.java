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
		boolean actual = depositValidation.validate("400");

		assertFalse(actual);
	}

	@Test
	void valid_deposit_amount() {
		boolean actual = depositValidation.validate("deposit 400");

		assertTrue(actual);
	}

	@Test
	void invalid_deposit_amount() {
		boolean actual = depositValidation.validate("deposit -300");

		assertFalse(actual);
	}
}
