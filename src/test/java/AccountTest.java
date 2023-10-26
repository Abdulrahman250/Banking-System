import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

	private static final int UNIQUE_ID = 74538932;
	private static final double APR = 7.6;

	Account checking;

	@BeforeEach
	public void setUp() {
		checking = new Checking(APR, UNIQUE_ID);
	}

	@Test
	public void account_starts_with_balance_of_zero() {
		double actual = checking.getBalance();

		assertEquals(0, actual);
	}

	@Test
	public void the_supplied_apr_value_is_started_with_specified_number() {
		double actual = checking.getAPR();

		assertEquals(7.6, actual);

	}

	@Test
	public void deposit_money_into_accounts() {
		checking.depositBalance(20);

		double actual = checking.getBalance();

		assertEquals(20, actual);
	}

	@Test
	public void withdraw_goes_to_0_when_money_goes_over_the_balance() {
		checking.withdrawBalance(20);

		double actual = checking.getBalance();

		assertEquals(0, actual);

	}

	@Test
	public void deposit_twice_into_the_same_account() {
		checking.depositBalance(70);
		checking.depositBalance(30);

		double actual = checking.getBalance();

		assertEquals(100, actual);
	}

	@Test
	public void withdraw_twice_into_the_same_account() {
		checking.balance = 70;

		checking.withdrawBalance(10);
		checking.withdrawBalance(30);

		double actual = checking.getBalance();

		assertEquals(30, actual);
	}

	@Test
	public void withdraw_money_from_accounts_with_money_in_accounts() {
		checking.depositBalance(30);
		checking.withdrawBalance(10);

		double actual = checking.getBalance();

		assertEquals(20, actual);

	}

	@Test
	public void account_will_have_a_unique_ID() {
		double actual = checking.getUniqueID();

		assertEquals(74538932, actual);
	}

}