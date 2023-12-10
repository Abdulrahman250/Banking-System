package banking;

public class Checking extends Account {

	public Checking(double APR, int uniqueID) {
		super(APR, uniqueID);
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
	public void processWithdrawal(double takeFromBalance) {
		if (takeFromBalance >= 0 && takeFromBalance <= 400) {
			withdrawBalance(takeFromBalance);
		}
	}

}