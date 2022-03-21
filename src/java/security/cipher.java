package security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class cipher {

    private static byte[] key = { //change this custom key if you want
        0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53,
        0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79,
    };

    public static String encrypt(String encryptStr) {
        String string = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            string = Base64.encodeBase64String(cipher.doFinal(encryptStr.getBytes()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return string;
    }

    public static String decrypt(String decryptStr) {
        String string = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            string = new String(cipher.doFinal(Base64.decodeBase64(decryptStr)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return string;
    }
}
