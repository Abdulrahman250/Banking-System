package banking;

import java.util.Map;

public class TransferValidation extends CommandValidation {

	private Bank bank;

	public TransferValidation(Bank bank) {
		this.bank = bank;
	}

	@Override
	public boolean validate(String command) {
		String[] parts = command.split(" ");

		if (parts.length != 4 || !parts[0].equalsIgnoreCase("Transfer")) {
			return false;
		}

		String accountOneId = parts[1];
		String accountTwoId = parts[2];
		String amountStr = parts[3];

		double transferAmount;
		try {
			transferAmount = Double.parseDouble(amountStr);
		} catch (NumberFormatException e) {
			return false;
		}

		if (!validAccountIds(accountOneId, accountTwoId)) {
			return false;
		}

		Account accountOne = getAccountById(accountOneId);
		Account accountTwo = getAccountById(accountTwoId);

		if (!validTransferBetweenAccountTypes(accountOne, accountTwo)
				|| !validTransferAmount(accountOne, transferAmount)) {
			return false;
		}

		return true;
	}

	protected boolean validAccountIds(String accountOneId, String accountTwoId) {
		return getAccountById(accountOneId) != null && getAccountById(accountTwoId) != null;
	}

	protected Account getAccountById(String accountId) {
		Map<Integer, Account> accountMap = bank.getAccount();
		return accountMap.get(Integer.parseInt(accountId));
	}

	protected boolean validTransferBetweenAccountTypes(Account accountOne, Account accountTwo) {
		return accountOne.canReceiveTransfers() && accountTwo.canReceiveTransfers();
	}

	protected boolean validTransferAmount(Account accountOne, double transferAmount) {
		boolean isValid = accountOne.getBalance() <= transferAmount;

		return isValid;
	}
}
