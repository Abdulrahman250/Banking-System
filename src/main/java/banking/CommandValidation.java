package banking;

import java.util.HashSet;
import java.util.Set;

public abstract class CommandValidation {
	protected Set<String> uniqueIDs = new HashSet<>();

	public abstract boolean validate(String command);

	protected boolean validUniqueID(String uniqueID) {
		return uniqueID.matches("\\d{8}");
	}

	protected boolean duplicateID(String uniqueID) {
		return uniqueIDs.contains(uniqueID);
	}
}
