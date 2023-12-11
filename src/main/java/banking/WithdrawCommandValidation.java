package banking;

public class WithdrawCommandValidation extends CommandValidation {
	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 2) {
			return false;
		}

		String firstWithdrawWord = parts[0];
		String amount = parts[1];

		return isValidWithdrawAmount(amount) && hasValidCommand(firstWithdrawWord);
	}

	protected boolean isValidWithdrawAmount(String amount) {
		try {
			double withdrawAmount = Double.parseDouble(amount);
			return withdrawAmount >= 0.0;
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

		return validCommand.equals("withdraw");

	}
}
