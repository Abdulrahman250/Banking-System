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

		if (accountType.equals("cd") && parts.length < 5) {
			return false;
		}

		if (accountType.equals("checking") && parts.length > 4) {
			return false;
		}

		if (accountType.equals("savings") && parts.length > 4) {
			return false;
		}

		if (!accountType.equals("savings") && !accountType.equals("checking") && !accountType.equals("cd")) {
			return false;
		}

		if (!validUniqueID(uniqueID) || !validApr(apr) || duplicateID(uniqueID)) {
			return false;
		} else {
			uniqueIDs.add(uniqueID);
		}

		if (accountType.equals("cd")) {
			String initialBalance = parts[4];
			if (!validInitialBalance(initialBalance)) {
				return false;
			}
		}
		return true;
	}
}
