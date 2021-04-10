package java_implementation;

public class Transaction {
    private String fromAddress;
    private String toAddress;
    private double amount;


    public Transaction(String fromAddress, String toAddress, double amount){
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Transaction {"+
                "To Address: "+ this.toAddress +
                ",From Address: " + this.fromAddress +
                ",Amount: " + this.fromAddress +
                "}";
    }
}
