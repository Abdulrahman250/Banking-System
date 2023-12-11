package banking;

public class CD extends Account {

	private int monthsPassed;

	public CD(double APR, int uniqueID, double balance) {
		super(APR, uniqueID);
		this.balance = balance;
		this.monthsPassed = 0;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public boolean canReceiveTransfers() {
		return false;
	}

	@Override
	public void calculateInterest(int months) {
		double monthlyAPR = getAPR() / 100 / 12;

		for (int i = 0; i < 4 * months; i++) {
			double interest = getBalance() * monthlyAPR;
			depositBalance(interest);
		}

		monthsPassed += months;
	}

	@Override
	public void processWithdrawal(double takeFromBalance) {
		if (monthsPassed >= 12 && takeFromBalance >= getBalance()) {
			withdrawBalance(takeFromBalance);
		}
	}
}
