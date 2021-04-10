package java_implementation;

import java.util.ArrayList;

public class Block {
    private String hash;
    private ArrayList<Transaction> transactions;
    private final String timestamp;
    private String previousHash;

    private int nonce;

    public Block( String timestamp, ArrayList<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.hash = this.calculateHash();
    }
    public String calculateHash(){
        return Hash.createStringHash(this.previousHash + this.transactions.toString() + this.timestamp + Integer.toString(this.nonce));
    }

    public void mineBlock(final int difficulty){
        String stringDifficulty = stringRepeat("0", difficulty);
        while(!this.hash.substring(0, difficulty ).equals(stringDifficulty)){
            this.nonce--;
            this.hash = this.calculateHash();
        }

        System.out.println("Block Mined: " + this.hash);
    }

    public String stringRepeat(String string, int quantity){
        String repeated = "";
        while(repeated.length() < quantity){
            repeated += string;
        }
        return repeated;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}
