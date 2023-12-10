package banking;

public class Savings extends Account {
	protected int monthlyWithdrawals;

	public Savings(double APR, int uniqueID) {
		super(APR, uniqueID);
		this.monthlyWithdrawals = 0;
	}

	@Override
	public boolean canReceiveTransfers() {
		return true;
	}

	@Override
	public void calculateInterest(int months) {
		double monthlyAPR = getAPR() / 100 / 12;
		double interest = getBalance() * monthlyAPR * months;
		depositBalance(interest);
	}

	@Override
	public void resetMonthlyWithdrawals() {
		this.monthlyWithdrawals = 0;
	}

	@Override
	public void processWithdrawal(double takeFromBalance) {
		if (monthlyWithdrawals < 1 && takeFromBalance >= 0 && takeFromBalance <= 1000) {
			withdrawBalance(takeFromBalance);
			monthlyWithdrawals++;
		}
	}

}
