package banking;

public class Savings extends Account {

	public Savings(double APR, int uniqueID) {
		super(APR, uniqueID);
	}

	@Override
	public boolean canReceiveTransfers() {
		return true;
	}
}
