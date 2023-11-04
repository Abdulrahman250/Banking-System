public class CommandValidation {
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length == 0) {
			return false;
		} else {
			return true;
		}
	}
}
