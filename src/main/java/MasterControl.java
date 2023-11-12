import java.util.List;

public class MasterControl {
	private CreateValidation createValidation;
	private CommandProcessor commandProcessor;
	private StoreCommands storeCommands;
	private DepositValidation depositValidation;

	public MasterControl(CreateValidation createValidation, CommandProcessor commandProcessor,
			StoreCommands storeCommands, DepositValidation depositValidation) {
		this.createValidation = createValidation;
		this.commandProcessor = commandProcessor;
		this.storeCommands = storeCommands;
		this.depositValidation = depositValidation;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (createValidation.validate(command)) {
				commandProcessor.processCommand(command);
			} else {
				storeCommands.addInvalidCommands(command);
			}
		}
		return storeCommands.getInvalidCommands();
	}
}
