package banking;

import java.security.SecureRandom;
import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	private long accnum = 0L;

	public Bank() {
		accounts = new LinkedHashMap<>();
	}

	private Account getAccount(Long accountNumber) {
		return accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		long accountNumber = getNextAccountNumber();

	
		CommercialAccount account = new CommercialAccount(company, accountNumber, pin, startingDeposit);
		
		accounts.put(accountNumber, account);
		
        return accountNumber;
	}

	private synchronized long getNextAccountNumber() {
		return accnum++;
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		long accountNumber = getNextAccountNumber();
		

		ConsumerAccount account = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
		
		accounts.put(accountNumber, account);
		
        return accountNumber;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		Account account = getAccount(accountNumber);
		return account.validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		Account account = getAccount(accountNumber);
		return account.getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		Account account = getAccount(accountNumber);
		account.creditAccount(amount);
	}

	public boolean debit(Long accountNumber, double amount) {
		Account account = getAccount(accountNumber);
		return account.debitAccount(amount);
	}
}
