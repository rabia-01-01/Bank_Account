
public class BankAccount {
    /*Add data members: accountNumber and balance*/
    private int accountNumber;
    double balance;

    // Constructor
    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    public void widthdraw(double amount) throws IllegalAccessException{
        if(amount <= 0){
            throw new IllegalAccessException("Withdrawal amount must be positive.");
        }
        if(amount > balance) {
            throw new IllegalAccessException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("After withdrawing, the current balance = " +balance);
    }

    public void deposit(double amount) throws IllegalAccessException{
        if(amount <= 0) {
            throw new IllegalAccessException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("After depositing some amount, the overall balance is " +balance);

    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    @Override
    public String toString(){
        return "Account number = " +accountNumber+ ", Balance = $" +balance;
    }

}