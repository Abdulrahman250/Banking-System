package banking;

import java.util.ArrayList;
import java.util.List;

public class StoreCommands {
	private List<String> invalidCommands;

	public StoreCommands() {
		invalidCommands = new ArrayList<>();
	}

	public List<String> getInvalidCommands() {
		return new ArrayList<>(invalidCommands);
	}

	public void addInvalidCommands(String command) {
		invalidCommands.add(command);
	}
}
