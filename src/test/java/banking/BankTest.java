package banking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new banking.Bank();
	}

	@Test
	public void bank_is_created_with_no_accounts() {
		Bank bank = new Bank();

		assertTrue(bank.getAccount().isEmpty());
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

	@Test
	public void passing_time_with_0_months_does_nothing() {
		bank.addAccount(new Checking(4.7, 12345678));

		bank.passTime(0);

		Account accountGotten = bank.getAccount(12345678);

		assertEquals(0.0, accountGotten.getBalance());
	}

	@Test
	public void passing_time_with_61_months_does_nothing() {
		bank.addAccount(new Checking(4.7, 12345678));

		bank.passTime(61);

		Account accountGotten = bank.getAccount(12345678);

		assertEquals(0.0, accountGotten.getBalance());
	}

	@Test
	public void account_with_less_than_100_dollars_is_deducted_25_dollars() {
		bank.addAccount(new Checking(4.7, 12345678));

		bank.deposit(12345678, 90);

		bank.passTime(1);

		Account accountGotten = bank.getAccount(12345678);

		assertEquals(65, accountGotten.getBalance(), 0.5);

	}

	@Test
	public void account_balance_is_correct_after_passing_1_month() {
		bank.addAccount(new Checking(3, 12345678));

		bank.deposit(12345678, 1000);

		bank.passTime(1);

		Account accountGotten = bank.getAccount(12345678);

		assertEquals(1002.5, accountGotten.getBalance());

	}

	@Test
	public void account_is_removed_with_zero_balance_after_passing_time_for_many_months() {
		bank.addAccount(new Checking(3, 12345678));

		bank.passTime(6);

		Account accountGotten = bank.getAccount(12345678);

		assertNull(accountGotten);

	}

	@Test
	public void account_is_removed_with_zero_balance_after_passing_time_for_one_month() {
		bank.addAccount(new Checking(3, 12345678));

		bank.passTime(1);

		Account accountGotten = bank.getAccount(12345678);

		assertNull(accountGotten);

	}

	@Test
	public void multiple_accounts_are_removed_with_zero_balance_after_passing_time_for_many_months() {
		bank.addAccount(new Checking(3, 12345678));

		bank.addAccount(new Savings(2, 45698444));

		bank.addAccount(new CD(3, 98765433, 0));

		bank.passTime(1);

		Account accountGotten1 = bank.getAccount(12345678);

		Account accountGotten2 = bank.getAccount(45698444);

		Account accountGotten3 = bank.getAccount(98765433);

		assertNull(accountGotten1);
		assertNull(accountGotten2);
		assertNull(accountGotten3);
	}

	@Test
	public void savings_can_not_withdraw_more_than_once_per_month() {
		bank.addAccount(new Savings(4.3, 56788444));

		bank.deposit(56788444, 1500);

		bank.withdraw(56788444, 500);

		bank.withdraw(56788444, 500);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(1000, accountGotten.getBalance());

	}

	@Test
	public void savings_withdrawing_in_one_month_and_then_in_another() {
		bank.addAccount(new Savings(4.3, 56788444));

		bank.deposit(56788444, 1500);

		bank.withdraw(56788444, 500);

		bank.passTime(1);

		bank.withdraw(56788444, 500);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(500, accountGotten.getBalance(), 4);

	}

	@Test
	public void savings_withdrawing_in_many_months() {
		bank.addAccount(new Savings(4.3, 56788444));

		bank.deposit(56788444, 1500);

		bank.withdraw(56788444, 500.0000);

		bank.passTime(1);

		bank.withdraw(56788444, 500.00000);

		bank.passTime(1);

		bank.withdraw(56788444, 300.0000);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(205.39, accountGotten.getBalance(), .1);

	}

	@Test
	public void checking_can_not_withdraw_more_than_400() {
		bank.addAccount(new Checking(4.3, 56788444));

		bank.deposit(56788444, 1500);

		bank.withdraw(56788444, 500);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(1500, accountGotten.getBalance());

	}

	@Test
	public void checking_can_withdraw_less_than_400() {
		bank.addAccount(new Checking(4.3, 56788444));

		bank.deposit(56788444, 1500);

		bank.withdraw(56788444, 350);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(1150, accountGotten.getBalance());

	}

	@Test
	public void cd_cant_withdraw_less_than_12_months_have_passed() {
		bank.addAccount(new CD(4.3, 56788444, 1500));

		bank.withdraw(56788444, 500);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(1500, accountGotten.getBalance());

	}

	@Test
	public void cd_can_withdraw_after_12_months_have_passed() {
		bank.addAccount(new CD(0, 56788444, 1500));

		bank.passTime(12);

		bank.withdraw(56788444, 1500);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(0, accountGotten.getBalance());

	}

	@Test
	public void cd_cant_withdraw_if_not_full_withdraw() {
		bank.addAccount(new CD(0, 56788444, 1500));

		bank.passTime(12);

		bank.withdraw(56788444, 200);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(1500, accountGotten.getBalance());

	}

	@Test
	public void cd_can_withdraw_over_balance() {
		bank.addAccount(new CD(0, 56788444, 1500));

		bank.passTime(12);

		bank.withdraw(56788444, 1800);

		Account accountGotten = bank.getAccount(56788444);

		assertEquals(0, accountGotten.getBalance());

	}

}
