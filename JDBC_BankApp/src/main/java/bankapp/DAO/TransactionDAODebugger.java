package bankapp.DAO;

import bankapp.models.BankAccount;
import bankapp.models.CheckingAccount;
import bankapp.models.Transaction;
import org.apache.log4j.Logger;

public class TransactionDAODebugger {
	private static Logger log = Logger.getLogger(TransactionDAOImpl.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				TransactionDAOImpl tpd = new TransactionDAOImpl();
				BankAccount ba = new CheckingAccount();
				Transaction tsc = new Transaction();
//				tsc.setRepicientSSN("7465");
//				tsc.setSenderAccountNumber("654456");
//				tsc.setSenderId(2);
//				tsc.setTransactionAmount(250);
//			    tpd.saveOne(tsc);
				//tpd.findRepicient(4);
				log.info(tpd.findRecipient(4));

	}

}
