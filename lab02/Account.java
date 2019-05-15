/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 */
public class Account {

    public int balance;
    Account parentAccount;


    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }
    /** Initialize an account with the given BALANCE. */
    public Account(int balance,Account parentAccount) {
        this.balance = balance;
        this.parentAccount = parentAccount;
    }

    /** Deposits AMOUNT into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract AMOUNT from the account if possible. If subtracting AMOUNT
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public int calsum(){
        int sum = this.balance;
        Account temp = parentAccount;
        while(temp != null){
            sum += parentAccount.balance;
            temp = temp.parentAccount;
        }
        return sum;
    }

    public boolean withdraw(int amount) {
        // TODO
        int sum = calsum();
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
        } else if (balance < amount) {
            if (amount > sum) {
                System.out.println("Insufficient funds");
                return false;
            }
        	int cover = amount - balance;
        	balance = 0;
        	if (parentAccount != null) {
            
        		if (parentAccount.balance >= cover) {
        			parentAccount.balance -= cover;
                    return true;
                }
                else{
        			return parentAccount.withdraw(cover);
        		}
        	}
        	System.out.println("Insufficient funds");
        	return false;
        } else {
            balance -= amount;
        }
        return true;
    }

    /**
     * Merge account OTHER into this account by removing all money from OTHER
     * and depositing it into this account.
     */
    public void merge(Account other) {
        // TODO
        int transfer = other.balance;
        other.balance = 0;
        this.balance += transfer;
    }

    public static void main(String[] args) {
        // Account a = new Account(200);
        // Account b = new Account(100, a);
        // Account c = new Account(50, b);
        // System.out.println("c:"+ c.balance +" b:"+ b.balance +" a:"+ a.balance );
        // c.withdraw(450);
        // System.out.println("c:"+ c.balance +" b:"+ b.balance +" a:"+ a.balance );
    }
}
