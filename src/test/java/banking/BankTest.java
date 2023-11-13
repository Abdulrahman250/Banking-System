package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
	}

	@Test
	public void bank_is_created_with_no_accounts() {
		Bank bank = new Bank();

		assertTrue(Bank.getAccount().isEmpty());

	}

	@Test
	public void add_one_account_to_bank() {
		bank.addAccount(new Checking(5.3, 36237281));

		int actual = bank.getAccount().size();

		assertEquals(1, actual);
	}

	@Test
	public void add_two_accounts_to_bank() {
		bank.addAccount(new Checking(2.5, 45729294));
		bank.addAccount(new Savings(7.4, 36294728));

		int actual = bank.getAccount().size();

		assertEquals(2, actual);
	}

	@Test
	public void retrieve_one_account_from_bank_and_the_right_account_is_retrieved() {
		Checking checkingAccount = new Checking(3.2, 47291728);
		bank.addAccount(checkingAccount);

		Account accountRetrieved = bank.getAccount(47291728);

		assertEquals(checkingAccount, accountRetrieved);

	}

	@Test
	public void deposit_money_into_account_by_id_and_the_correct_bank_gets_the_money() {
		bank.addAccount(new Checking(7.2, 73826483));

		bank.deposit(73826483, 214);

		Account accountRetrieved = bank.getAccount(73826483);

		assertEquals(214, accountRetrieved.getBalance(), 0.001);

	}

	@Test
	public void withdraw_money_into_account_by_id_and_the_correct_bank_gets_the_money() {
		bank.addAccount(new Checking(7.2, 73826483));

		bank.deposit(73826483, 214);

		bank.withdraw(73826483, 114);

		Account accountRetrieved = bank.getAccount(73826483);

		assertEquals(100, accountRetrieved.getBalance(), 0.001);

	}

	@Test
	public void deposit_twice_through_the_bank() {
		bank.addAccount(new Checking(7.7, 73826483));

		bank.deposit(73826483, 214);
		bank.deposit(73826483, 114);

		Account accountRetrieved = bank.getAccount(73826483);

		assertEquals(328, accountRetrieved.getBalance(), 0.001);
	}

	@Test
	public void withdraw_twice_through_the_bank() {
		bank.addAccount(new Checking(7.7, 73826483));

		bank.deposit(73826483, 478);

		bank.withdraw(73826483, 114);
		bank.withdraw(73826483, 263);

		Account accountRetrieved = bank.getAccount(73826483);

		assertEquals(101, accountRetrieved.getBalance(), 0.001);
	}

}
