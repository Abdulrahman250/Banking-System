public abstract class Account {
	protected double APR;
	protected double balance;
	protected int uniqueID;

	public Account(double APR, int uniqueID) {
		this.APR = APR;
		this.balance = balance;
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
		} else {
			System.out.println("You can't deposit a negative number");
		}
	}

	public void withdrawBalance(double takeFromBalance) {
		if (takeFromBalance >= 0) {
			balance -= takeFromBalance;
			if (balance < 0) {
				balance = 0;
			}
		} else {
			System.out.println("You can't withdraw a negative number");
		}
	}

	public int getUniqueID() {
		return uniqueID;
	}
}