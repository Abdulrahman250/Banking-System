package banking;

public class CreateValidation extends CommandValidation {
	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length < 4 || !parts[0].equalsIgnoreCase("Create")) {
			return false;
		}

		String accountType = parts[1].toLowerCase();
		String uniqueID = parts[2];
		String apr = parts[3];

		return validateInput(accountType, uniqueID, apr, parts);
	}

	protected boolean validApr(String apr) {
		try {
			double aprValue = Double.parseDouble(apr);
			return aprValue >= 0.0 && aprValue <= 10.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean validInitialBalance(String accountType, String[] parts) {
		if (accountType.equals("cd")) {
			try {
				String initialBalance = parts[4];
				double balanceValue = Double.parseDouble(initialBalance);
				return balanceValue >= 1000 && balanceValue <= 10000;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	protected boolean validAccountType(String accountType, String[] parts) {
		if (!accountType.equals("savings") && !accountType.equals("checking") && !accountType.equals("cd")) {
			return false;
		}

		if ((accountType.equals("checking") || accountType.equals("savings")) && parts.length > 4) {
			return false;
		}

		return !(accountType.equals("cd") && parts.length < 5);
	}

	private boolean validateInput(String accountType, String uniqueID, String apr, String[] parts) {
		if (!validAccountType(accountType, parts) || !validUniqueID(uniqueID) || !validApr(apr)
				|| duplicateID(uniqueID)) {
			return false;
		} else {
			uniqueIDs.add(uniqueID);
		}

		return validInitialBalance(accountType, parts);
	}
}
