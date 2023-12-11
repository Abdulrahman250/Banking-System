package banking;

public class DepositValidation extends CommandValidation {
	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 2) {
			return false;
		}

		String validDeposit = parts[0];
		String amount = parts[1];

		return validDepositAmount(amount) && validCommand(validDeposit);

	}

	protected boolean validDepositAmount(String amount) {
		try {
			double depositAmount = Double.parseDouble(amount);
			return depositAmount >= 0.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean validCommand(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 1) {
			return false;
		}

		String validCommand = parts[0].toLowerCase();

		return validCommand.equals("deposit");

	}
}
