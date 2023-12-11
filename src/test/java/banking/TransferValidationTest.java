package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferValidationTest {
	Bank bank;
	TransferValidation transferValidation;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		transferValidation = new TransferValidation(bank);
	}

	@Test
	public void valid_transfer() {
		Account fromAccount = new Savings(4.5, 12345678);
		Account toAccount = new Checking(3.2, 98765432);

		bank.addAccount(fromAccount);
		bank.addAccount(toAccount);

		boolean actual = transferValidation.validate("Transfer 12345678 98765432 500");

		assertTrue(actual);
	}

	@Test
	public void invalid_transfer() {
		boolean actual = transferValidation.validate("Withdraw 12345678 500");

		assertFalse(actual);
	}

	@Test
	public void invalid_transfer_amount_negative() {
		boolean actual = transferValidation.validate("Transfer 12345678 98765432 -500");

		assertFalse(actual);
	}

	@Test
	public void invalid_transfer_amount_not_a_number() {
		boolean actual = transferValidation.validate("Transfer 12345678 98765432 abc");

		assertFalse(actual);
	}

	@Test
	public void invalid_accountids_nonexistent() {
		boolean actual = transferValidation.validate("Transfer 99999999 98765432 500");

		assertFalse(actual);
	}

	@Test
	public void valid_transfer_checking_to_savings() {
		Account fromAccount = new Savings(4.5, 23232323);
		Account toAccount = new Checking(3.2, 32323232);

		bank.addAccount(fromAccount);
		bank.addAccount(toAccount);

		boolean actual = transferValidation.validate("Transfer 32323232 23232323 700");

		assertTrue(actual);
	}

	@Test
	public void valid_transfer_savings_to_checking() {
		Account fromAccount = new Savings(4.5, 23456789);
		Account toAccount = new Checking(3.2, 87654321);

		bank.addAccount(fromAccount);
		bank.addAccount(toAccount);

		boolean actual = transferValidation.validate("Transfer 87654321 23456789 300");

		assertTrue(actual);
	}

	@Test
	public void invalid_transfer_between_cd_and_savings() {
		boolean actual = transferValidation.validate("Transfer 11111111 98765432 500");

		assertFalse(actual);
	}

	@Test
	public void invalid_transfer_between_checking_and_cd() {
		boolean actual = transferValidation.validate("Transfer 12345678 11111111 400");

		assertFalse(actual);
	}

	@Test
	public void transfer_exceeds_balance() {
		boolean actual = transferValidation.validate("Transfer 12345678 98765432 1000");

		assertFalse(actual);
	}
}
