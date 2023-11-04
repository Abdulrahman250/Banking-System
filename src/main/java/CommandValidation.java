public class CommandValidation {
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
			return validateCreateCommand(parts);
		default:
			return false;
		}
	}

	private boolean validateCreateCommand(String[] parts) {
		if (parts.length < 4) {
			return false;
		}
		String accountType = parts[1].toLowerCase();

		if (accountType.equals("cd") && parts.length < 5) {
			return false;
		}

		if (!accountType.equals("savings") && !accountType.equals("checking") && !accountType.equals("cd")) {
			return false;
		} else {
			return true;
		}
	}
}
