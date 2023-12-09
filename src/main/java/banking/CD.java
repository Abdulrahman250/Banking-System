package banking;

public class CD extends Account {

	private double balance;

	public CD(double APR, int uniqueID, double balance) {
		super(APR, uniqueID);
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public boolean canReceiveTransfers() {
		return false;
	}
}
