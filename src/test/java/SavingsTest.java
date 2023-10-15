import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsTest {

	private static final double APR = 6.3;
	private static final int UNIQUE_ID = 74538932;

	Savings savings;

	@BeforeEach
	public void setUp() {
		savings = new Savings(APR, UNIQUE_ID);
	}

	@Test
	public void checking_created_with_starting_balance_0() {
		double actual = savings.getBalance();

		assertEquals(0, actual);

	}

	@Test
	public void the_supplied_apr_value_for_savings() {
		double actual = savings.getAPR();

		assertEquals(6.3, actual);
	}
}