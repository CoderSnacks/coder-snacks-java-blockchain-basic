package codersnacks.java.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public abstract class Hash {
    public static String createStringHash(String data){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHex( byte[] hash){
        return DatatypeConverter.printHexBinary(hash);
    }
}
