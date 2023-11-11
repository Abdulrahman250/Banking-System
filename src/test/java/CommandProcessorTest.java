import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {

	private Bank bank;
	private CommandProcessor commandProcessor;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	public void create_checking_account() {
		String command = "Create checking 12345678 1.0";
		commandProcessor.processCommand(command);

		Account actual = bank.getAccount(12345678);

		assertEquals("checking", actual.getClass().getSimpleName().toLowerCase());
		assertEquals(1.0, actual.getAPR());
	}

	@Test
	public void create_savings_account() {
		String command = "Create savings 87654321 1.1";
		commandProcessor.processCommand(command);

		Account actual = bank.getAccount(87654321);

		assertEquals("savings", actual.getClass().getSimpleName().toLowerCase());
		assertEquals(1.1, actual.getAPR());
	}

	@Test
	public void create_cd_account() {
		String command = "Create cd 23456789 1.2 1234";
		commandProcessor.processCommand(command);

		Account actual = bank.getAccount(23456789);

		assertEquals("cd", actual.getClass().getSimpleName().toLowerCase());
		assertEquals(1.2, actual.getAPR());
		assertEquals(1234, actual.getBalance());
	}

	@Test
	public void deposit_into_checking() {
		String createAccount = "Create checking 13571357 1.3";
		String depositAmount = "deposit 13571357 123";
		commandProcessor.processCommand(createAccount);
		commandProcessor.processCommand(depositAmount);

		Account actual = bank.getAccount(13571357);

		assertEquals(123, actual.getBalance());
	}

	@Test
	public void deposit_into_savings() {
		String createAccount = "Create savings 12341234 1.4";
		String depositAmount = "deposit 12341234 111";
		commandProcessor.processCommand(createAccount);
		commandProcessor.processCommand(depositAmount);

		Account actual = bank.getAccount(12341234);

		assertEquals(111, actual.getBalance());
	}
}