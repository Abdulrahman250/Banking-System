package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private List<String> invalidCommands;

	public CommandStorage() {
		invalidCommands = new ArrayList<>();
	}

	public List<String> getInvalidCommands() {
		return new ArrayList<>(invalidCommands);
	}

	public void addInvalidCommands(String command) {
		invalidCommands.add(command);
	}
}
