import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDTest {

	private static final double APR = 4.6;
	private static final int UNIQUEID = 74538932;
	private static final double BALANCE = 4027.7;

	CD cd;

	@BeforeEach
	public void setUp() {
		cd = new CD(APR, UNIQUEID, BALANCE);
	}

	@Test
	public void cd_starting_balance() {
		double actual = cd.getBalance();

		assertEquals(4027.7, actual);

	}

	@Test
	public void the_supplied_apr_value_for_cd() {
		double actual = cd.getAPR();

		assertEquals(4.6, actual);
	}
}
