package banking;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bank {
	private static Map<Integer, Account> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public static Map<Integer, Account> getAccount() {
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

	public void withdraw(int uniqueID, double takeFromBalance) {
		Account account = getAccount(uniqueID);

		if (account != null) {
			account.processWithdrawal(takeFromBalance);
		}
	}

	public void passTime(int months) {
		if (months > 0 && months < 61) {
			Iterator<Map.Entry<Integer, Account>> iterator = accounts.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, Account> entry = iterator.next();
				Account account = entry.getValue();

				if (account.getBalance() <= 0 && months > 0 && months < 61) {
					iterator.remove();
				} else {
					if (account.getBalance() < 100) {
						account.withdrawBalance(25);
					}
					account.calculateInterest(months);

					try {
						Method resetMethod = account.getClass().getMethod("resetMonthlyWithdrawals");
						resetMethod.invoke(account);
					} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					}
				}
			}
		}
	}

	public void transfer(int fromID, int toID, double amount) {
		Account accountOne = getAccount(fromID);
		Account accountTwo = getAccount(toID);

		if (accountOne == null || accountTwo == null) {
			return;
		}

		if (accountOne.canReceiveTransfers() && accountTwo.canReceiveTransfers()) {
			double availableBalance = accountOne.getBalance();
			if (availableBalance >= amount) {
				accountOne.processWithdrawal(amount);
				accountTwo.depositBalance(amount);
			} else if (availableBalance > 0 && availableBalance < amount) {
				accountOne.processWithdrawal(availableBalance);
				accountTwo.depositBalance(availableBalance);
			}
		} else {
			return;
		}
	}
}
