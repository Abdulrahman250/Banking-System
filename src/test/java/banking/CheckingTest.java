package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {

	private static final int UNIQUE_ID = 74538932;
	private static final double APR = 7.6;

	Checking checking;

	@BeforeEach
	public void setUp() {
		checking = new banking.Checking(APR, UNIQUE_ID);
	}

	@Test
	public void checking_created_with_starting_balance_0() {
		double actual = checking.getBalance();

		assertEquals(0, actual);

	}

	@Test
	public void the_checking_apr_value() {
		double actual = checking.getAPR();

		assertEquals(7.6, actual);
	}
}