package bankapp.models;

public class Transaction {
	private int transactionId;	
	private int recipientId;
	private int senderId;
	private String senderFirstName;
	private String senderLastName;
	private String senderSSN;
	private String recipientSSN;
	private String senderAccountNumber;
	private double transactionAmount;
	private TransactionStatus transactionStatus;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int repicientId) {
		this.recipientId = repicientId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getSenderFirstName() {
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}
	public String getSenderLastName() {
		return senderLastName;
	}
	public void setSenderLastName(String senderLastName) {
		this.senderLastName = senderLastName;
	}
	public String getSenderSSN() {
		return senderSSN;
	}
	public void setSenderSSN(String senderSSN) {
		this.senderSSN = senderSSN;
	}
	public String getRecipientSSN() {
		return recipientSSN;
	}
	public void setRecipientSSN(String recipientSSN) {
		this.recipientSSN = recipientSSN;
	}
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		for(TransactionStatus ts : TransactionStatus.values()) {
			if(ts.toString().equals(transactionStatus)) {
				this.transactionStatus = ts;
			}
		}
		
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", repicientId=" + recipientId + ", senderId=" + senderId
				+ ", senderFirstName=" + senderFirstName + ", senderLastName=" + senderLastName + ", senderSSN="
				+ senderSSN + ", recipientSSN=" + recipientSSN + ", senderAccountNumber=" + senderAccountNumber
				+ ", transactionAmount=" + transactionAmount + ", transactionStatus=" + transactionStatus + "]";
	}
}
