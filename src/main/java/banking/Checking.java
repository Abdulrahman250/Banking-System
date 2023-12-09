package banking;

public class Checking extends Account {

	public Checking(double APR, int uniqueID) {
		super(APR, uniqueID);
	}

	@Override
	public boolean canReceiveTransfers() {
		return true;
	}
}