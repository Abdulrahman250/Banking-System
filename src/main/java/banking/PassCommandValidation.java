package banking;

public class PassCommandValidation extends CommandValidation {
	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 2) {
			return false;
		}

		String firstPassWord = parts[0];
		String amount = parts[1];

		return validMonthAmount(amount) && hasValidCommand(firstPassWord);
	}

	protected boolean validMonthAmount(String amount) {
		try {
			double monthAmount = Double.parseDouble(amount);
			return monthAmount >= 0 && monthAmount < 61;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean hasValidCommand(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 1) {
			return false;
		}

		String validCommand = parts[0].toLowerCase();

		return validCommand.equals("pass");

	}
}
