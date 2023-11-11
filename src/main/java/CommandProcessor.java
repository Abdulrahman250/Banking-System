public class CommandProcessor {
	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void processCommand(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];

		if (commandType.equals("Create")) {
			String accountType = parts[1];
			int uniqueID = Integer.parseInt(parts[2]);
			double apr = Double.parseDouble(parts[3]);

			if (accountType.equals("checking")) {
				bank.addAccount(new Checking(apr, uniqueID));
			} else if (accountType.equals("savings")) {
				bank.addAccount(new Savings(apr, uniqueID));
			} else if (accountType.equals("cd")) {
				double balance = Double.parseDouble(parts[4]);
				bank.addAccount(new CD(apr, uniqueID, balance));
			}
		} else if (commandType.equals("deposit")) {
			int uniqueID = Integer.parseInt(parts[1]);
			double amount = Double.parseDouble(parts[2]);

			Account account = bank.getAccount(uniqueID);
			account.depositBalance(amount);
		}
	}
}
