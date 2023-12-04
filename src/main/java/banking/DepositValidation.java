package banking;

public class DepositValidation extends CommandValidation {
	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 2) {
			return false;
		}

		String amount = parts[1];

		return validDepositAmount(amount);

	}

	protected boolean validDepositAmount(String amount) {
		try {
			double depositAmount = Double.parseDouble(amount);
			return depositAmount >= 0.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
