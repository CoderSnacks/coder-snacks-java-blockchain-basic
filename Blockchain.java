package java_implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

public class Blockchain {
    private ArrayList<Block> chain;
    private ArrayList<Transaction> pendingTransactions;
    private final int difficulty = 2;
    private final int miningReward = 100;
    private String name;

    Blockchain(String name){
        this.chain = new ArrayList<Block>();
        this.chain.add(createGenesisBlock());
        this.pendingTransactions = new ArrayList<Transaction>();
        this.name = name;
    }

    private Block createGenesisBlock(){
        return new Block("01/01/2020", new ArrayList<Transaction>(), "0");
    }


    public void mineNextBlock(String miningRewardAddress){
        LocalDate timestamp = LocalDate.now();
        Block block = new Block(timestamp.toString(), this.pendingTransactions, this.getLatestBlock().getHash());
        block.mineBlock(this.difficulty);
        System.out.println("Block successfully mined!");
        this.chain.add(block);
        this.pendingTransactions = new ArrayList<Transaction>();
        this.pendingTransactions.add(new Transaction("summerCoin", miningRewardAddress, this.miningReward));
    }

    public String stringify(){
        String JSONBlockchain = "[\n";
        ListIterator<Block> chainIterator = chain.listIterator();
        while(chainIterator.hasNext()){
            Block currentBlock = chainIterator.next();
            JSONBlockchain += "{"
                    + ",\n hash:" + currentBlock.getHash()
                    + ",\n previousHash:" + currentBlock.getPreviousHash()
                    + ",\n transactions:" + currentBlock.getTransactions()
                    + ",\n timestamp:" + currentBlock.getTimestamp()
                    + "\n }";
            if(chainIterator.hasNext())
                JSONBlockchain += ",";
        }
        JSONBlockchain += "]\n";
        return JSONBlockchain;
    }

    public void addBlock(){
        LocalDate timestamp = LocalDate.now();
        Block block = new Block(timestamp.toString(), new ArrayList<Transaction>(), this.getLatestBlock().getHash());
        this.chain.add(block);
    }
    public Block getLatestBlock(){
        //get block at top of stack (latest added to chain)
        return this.chain.get(this.chain.size()-1);
    }
    public void createTransaction(Transaction transaction){
        this.pendingTransactions.add(transaction);
    }
    public String getName() {
        return name;
    }
    public ArrayList<Block> getChain(){
        return this.chain;
    }
    public double getBalanceOfAddress(String address){
        double balance = 0;
        for(Block block : this.chain){
            for(Transaction trans : block.getTransactions()){
                if(trans.getFromAddress().equals(address)){
                    balance -= trans.getAmount();
                }
                if(trans.getToAddress().equals(address)){
                    balance += trans.getAmount();
                }
            }
        }
        return balance;
    }

    public boolean isChainValid(){
        ListIterator<Block> chainIterator = this.chain.listIterator();
        System.out.println("Verifying Blockchain...");
        while(chainIterator.hasNext()){
            System.out.println("Blockchain has next..");
            //assign previous block, if a previous block exists
            Block previousBlock = (chainIterator.hasPrevious())? this.chain.get(chainIterator.previousIndex()) : null;
            Block currentBlock = chainIterator.next();

            //check if hash value matches output of block's hash seed
            if(!currentBlock.getHash().equals(currentBlock.calculateHash())){
                return false;
            }

            //if this is the first block, skip thru to next iteration
            if(chainIterator.nextIndex() != 1){
                if(!currentBlock.getPreviousHash().equals(previousBlock.getHash()))
                    return false;
            }
        }

        return true;
    }

    @Override
    public String toString(){
        String valid = (this.isChainValid())? "true" : "false";
        String blockchainInfo = "Blockchain Data ("+this.name+") ->{"
                + "\n size:" + this.chain.size()
                + ",\n creationDate:" + this.chain.get(0).getTimestamp()
                + ",\n name:" + this.name
                + ",\n valid:" + valid
                + "\n}";

        return blockchainInfo;
    }

}
