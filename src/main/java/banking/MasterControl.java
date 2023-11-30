package banking;

import java.util.List;

public class MasterControl {
	private CreateValidation createValidation;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;
	private DepositValidation depositValidation;

	public MasterControl(CreateValidation createValidation, CommandProcessor commandProcessor,
			CommandStorage commandStorage, DepositValidation depositValidation) {
		this.createValidation = createValidation;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
		this.depositValidation = depositValidation;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (createValidation.validate(command)) {
				commandProcessor.processCommand(command);
			} else {
				commandStorage.addInvalidCommands(command);
			}
		}
		return commandStorage.getInvalidCommands();
	}
}
