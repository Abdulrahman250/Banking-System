import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {

	MasterControl masterControl;
	private List<String> input;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new CreateValidation(), new CommandProcessor(bank), new StoreCommands(),
				new DepositValidation());

	}

	@Test
	void type_in_create_command_is_invalid() {
		input.add("creat checking 12345678 1.0");

		List<String> actual = masterControl.start(input);
		assertEquals("creat checking 12345678 1.0", actual.get(0));
	}

	@Test
	void type_in_deposit_command_is_invalid() {
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);
		assertEquals("depositt 12345678 100", actual.get(0));
	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add("creat checking 12345678 1.0");
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("depositt 12345678 100", actual.get(1));
	}

	@Test
	void invalid_to_create_accounts_with_same_ID() {
		input.add("create checking 12345678 1.0");
		input.add("create checking 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("create checking 12345678 1.0", actual);
	}

	@Test
	void invalid_missing_create_command() {
		input.add("checking 12456789 2.1");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("checking 12456789 2.1", actual);
	}

	@Test
	void invalid_cd_does_not_have_all_inputs() {
		input.add("Create cd 98765432 2.3");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create cd 98765432 2.3", actual);
	}

	@Test
	void invalid_cd_does_not_have_correct_starting_balance() {
		input.add("Create cd 21112221 1.9 928463289202028372");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create cd 21112221 1.9 928463289202028372", actual);
	}

	@Test
	void invalid_typo_in_account() {
		input.add("Create checkin 11112222 2.9");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checkin 11112222 2.9", actual);
	}

	@Test
	void invalid_when_missing_uniqueID() {
		input.add("Create checking 7.9");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 7.9", actual);
	}

	@Test
	void invalid_uniqueID_does_not_have_the_correct_amount_of_digits() {
		input.add("Create checking 111222 5.9");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 111222 5.9", actual);
	}

	@Test
	void invalid_uniqueID_has_letters() {
		input.add("Create checking 6789ou789 8.9");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 6789ou789 8.9", actual);
	}

	@Test
	void uniqueID_is_too_short() {
		input.add("Create checking 123 3.1");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 123 3.1", actual);
	}

	@Test
	void invalid_missing_apr() {
		input.add("Create checking 12345677");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 12345677", actual);
	}

	@Test
	void invalid_apr_not_in_range() {
		input.add("Create checking 12121212 532827278.3229");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 12121212 532827278.3229", actual);
	}

	@Test
	void invalid_when_apr_is_negative() {
		input.add("Create checking 24682468 -4.2");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 24682468 -4.2", actual);
	}

	@Test
	void invalid_savings_has_starting_balance() {
		input.add("Create savings 54635463 6.3 321");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create savings 54635463 6.3 321", actual);
	}

	@Test
	void invalid_checking_has_starting_balance() {
		input.add("Create checking 34635463 3.3 221");
		List<String> actual = masterControl.start(input);

		assertSingleCommand("Create checking 34635463 3.3 221", actual);
	}
}
