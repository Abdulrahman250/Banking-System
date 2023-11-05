import java.util.HashSet;
import java.util.Set;

public class CommandValidation {
	private Set<String> uniqueIDs = new HashSet<>();

	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length == 0) {
			return false;
		}

		String action = parts[0].toLowerCase();

		switch (action) {
		case "create":
			return validateCreateCommand(parts);
		case "deposit":
			return validateDepositCommand(parts);
		default:
			return false;
		}
	}

	private boolean validateCreateCommand(String[] parts) {
		if (parts.length < 4) {
			return false;
		}

		String accountType = parts[1].toLowerCase();
		String uniqueID = parts[2];
		String apr = parts[3];

		if (accountType.equals("cd") && parts.length < 5) {
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

	private boolean validateDepositCommand(String[] parts) {
		if (parts.length < 2) {
			return false;
		}

		String amount = parts[1];

		return validDepositAmount(amount);

	}

	private boolean validUniqueID(String uniqueID) {
		return uniqueID.matches("\\d{8}");
	}

	private boolean validApr(String apr) {
		try {
			double aprValue = Double.parseDouble(apr);
			return aprValue >= 0.0 && aprValue <= 10.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean validDepositAmount(String amount) {
		try {
			double depositAmount = Double.parseDouble(amount);
			return depositAmount >= 0.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean duplicateID(String uniqueID) {
		return uniqueIDs.contains(uniqueID);
	}

	private boolean validInitialBalance(String initialBalance) {
		try {
			double balanceValue = Double.parseDouble(initialBalance);
			return balanceValue >= 1000 && balanceValue <= 10000;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
