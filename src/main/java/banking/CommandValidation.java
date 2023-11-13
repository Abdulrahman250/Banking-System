package banking;

import java.util.HashSet;
import java.util.Set;

public abstract class CommandValidation {
	protected Set<String> uniqueIDs = new HashSet<>();

	public abstract boolean validate(String command);

	protected boolean validUniqueID(String uniqueID) {
		return uniqueID.matches("\\d{8}");
	}

	protected boolean validApr(String apr) {
		try {
			double aprValue = Double.parseDouble(apr);
			return aprValue >= 0.0 && aprValue <= 10.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean validDepositAmount(String amount) {
		try {
			double depositAmount = Double.parseDouble(amount);
			return depositAmount >= 0.0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected boolean duplicateID(String uniqueID) {
		return uniqueIDs.contains(uniqueID);
	}

	protected boolean validInitialBalance(String initialBalance) {
		try {
			double balanceValue = Double.parseDouble(initialBalance);
			return balanceValue >= 1000 && balanceValue <= 10000;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
