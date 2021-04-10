package java_implementation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        /* Checking our Block.java work :
            ArrayList<Transaction> transactionsBlock1 = new ArrayList<Transaction>();
            transactionsBlock1.add(new Transaction("800 W Example", "900 W Example", 100));
            Block block = new Block("01/05/2021" ,transactionsBlock1,"125");
        */

        /* Checking our Blockchain.java work:
            Blockchain coderSnackCoin = new Blockchain("CoderSnackCoin");
            coderSnackCoin.addBlock();
            System.out.println(coderSnackCoin);
        */


        /* Checking our Mining & Reward System work:*/

        Blockchain coderSnackCoin = new Blockchain("CoderSnackCoin");

        coderSnackCoin.createTransaction(new Transaction("address1", "address2", 100));
        coderSnackCoin.createTransaction(new Transaction("address2", "address1", 50));

        System.out.println("\n Starting the miner.");
        coderSnackCoin.mineNextBlock("xaviers-address");
        System.out.println("\n Balance of xavier is " + coderSnackCoin.getBalanceOfAddress("xaviers-address"));

        System.out.println("\n Starting the miner again.");
        coderSnackCoin.mineNextBlock("xaviers-address");
        System.out.println("n Balance of xavier is " + coderSnackCoin.getBalanceOfAddress("xaviers-address"));
    }
}
