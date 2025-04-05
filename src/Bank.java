
public class Bank {
    /*Add Data members: ArrayList of BankAccount and index for first available index in array*/
    private BankAccount[] accounts;
    private int index;

    public Bank(int size) {
        accounts = new BankAccount[size];
        index = 0;
    }

    public void addAccount(int accountNumber, double balance) throws AccountAlreadyExistsException{
        if(searchAccount(accountNumber) != null){
            throw new AccountAlreadyExistsException("Account with number " +accountNumber+ " already exists.");
        }
        if(index < accounts.length){
            accounts[index] = new BankAccount(accountNumber, balance);
            index++;
            System.out.println("New Account created");
        }
        else{
            System.out.println("Bank capacity reached. Cannot add more accounts.");
        }
    }

    public BankAccount searchAccount(int accountNumber){
        for(int i = 0; i < index; i++){
            if(accounts[i].getAccountNumber() == accountNumber){
                return accounts[i];
            }
        }
        return null;
    }


    @Override
    public String toString(){
        // Add functionality to print all details within the function itself instead of returning a string.
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < index; i++){
            res.append(accounts[i].toString()).append("\n");
        }
        return res.toString();
    }
}
