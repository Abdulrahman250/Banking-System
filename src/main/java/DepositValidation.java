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
}
