package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<Integer, Account> accounts;

	Bank() {
		this.accounts = new HashMap<>();
	}

	public Map<Integer, Account> getAccount() {
		return accounts;
	}

	public void addAccount(Account account) {
		accounts.put(account.getUniqueID(), account);
	}

	public Account getAccount(int uniqueID) {
		return accounts.get(uniqueID);
	}

	public void deposit(int uniqueID, double addMoney) {
		Account account = getAccount(uniqueID);
		account.depositBalance(addMoney);
	}

	public void withdraw(int uniqueID, double takeMoney) {
		Account account = getAccount(uniqueID);
		if (account != null) {
			account.withdrawBalance(takeMoney);
		}
	}
}
