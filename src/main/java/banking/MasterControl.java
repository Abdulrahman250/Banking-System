package banking;

import java.util.List;

public class MasterControl {
	private CreateValidation createValidation;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;
	private DepositValidation depositValidation;
	private PassCommandValidation passCommandValidation;
	private WithdrawCommandValidation withdrawCommandValidation;
	private TransferValidation transferValidation;

	public MasterControl(CreateValidation createValidation, CommandProcessor commandProcessor,
			CommandStorage commandStorage, DepositValidation depositValidation,
			PassCommandValidation passCommandValidation, WithdrawCommandValidation withdrawCommandValidation,
			TransferValidation transferValidation) {
		this.createValidation = createValidation;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
		this.depositValidation = depositValidation;
		this.passCommandValidation = passCommandValidation;
		this.withdrawCommandValidation = withdrawCommandValidation;
		this.transferValidation = transferValidation;
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
