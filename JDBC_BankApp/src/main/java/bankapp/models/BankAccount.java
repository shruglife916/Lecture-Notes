package bankapp.models;

public class BankAccount {
	private int customerId;
    private int bankId;
    private boolean pendingTransaction;
    private BankStatus bankStatus;
    
    
    
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public boolean isPendingTransaction() {
		return pendingTransaction;
	}
	public void setPendingTransaction(boolean pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}
	public String getBankingStatus() {
		return bankStatus.toString();
	}
	public void setBankingStatus(String bankStatus) {
		for(BankStatus bs : BankStatus.values()) {
			if(bs.toString().equals(bankStatus)) {
				this.bankStatus = bs; 
			}
		}
		
	}
	@Override
	public String toString() {
		return "BankAccount [customerId=" + customerId + ", bankId=" + bankId + ", pendingTransaction="
				+ pendingTransaction + "bankStatus" + bankStatus + "]\n";
	}
    
}
