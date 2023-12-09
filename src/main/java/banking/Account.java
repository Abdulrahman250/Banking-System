package banking;

public abstract class Account {
	protected double APR;
	protected double balance;
	protected int uniqueID;

	public Account(double APR, int uniqueID) {
		this.APR = APR;
		this.balance = 0;
		this.uniqueID = uniqueID;
	}

	public double getBalance() {
		return balance;
	}

	public double getAPR() {
		return APR;
	}

	public void depositBalance(double addToBalance) {
		if (addToBalance >= 0) {
			balance += addToBalance;
		}
	}

	public void withdrawBalance(double takeFromBalance) {
		System.out.println("Before Withdraw - Balance: " + balance);
		if (takeFromBalance >= 0 && balance >= takeFromBalance) {
			balance -= takeFromBalance;
		}
		System.out.println("After Withdraw - Balance: " + balance);
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public abstract boolean canReceiveTransfers();

}
