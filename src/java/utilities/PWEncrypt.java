package utilities;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Created by Derssss on 4/26/2017.
 */
public class PWEncrypt {

    private static PWEncrypt pw = null;

    private static byte[] salt = new String("SuperDuperSaltySalt").getBytes();
    private static int keyLength = 128;
    private static int iterations = 40000;
    // Master password used for encryption
    private static final String masterPassword = "ThisIsNotSafeIWonderWhereWeShouldPutThis";

    private SecretKeySpec key = createKey(masterPassword.toCharArray(), salt, iterations, keyLength);

    private PWEncrypt() throws InvalidKeySpecException, NoSuchAlgorithmException {}

    public static PWEncrypt getInstance() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (pw == null)
            pw = new PWEncrypt();
        return pw;
    }


    public String encryptKicker(String password) throws GeneralSecurityException, UnsupportedEncodingException {
        return encrypt(password, key);
    }


    public String decryptKicker(String password) throws GeneralSecurityException, IOException {
        return decrypt(password, key);
    }


    // Generates a cryptographic key using hash functions with the given parameters above.
    private static SecretKeySpec createKey (char[] password, byte[] salt, int iteration, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iteration, keyLength);
        SecretKey tempKey = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(tempKey.getEncoded(), "AES");
    }


    private static String encrypt(String unencryptedPass, SecretKeySpec key) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters parameters = cipher.getParameters();
        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
        byte[] cryptoText = cipher.doFinal(unencryptedPass.getBytes("UTF-8"));
        byte[] iv = ivParameterSpec.getIV();
        return base64Encode(iv) + ":" + base64Encode(cryptoText);
    }


    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }


    private static String decrypt(String string, SecretKeySpec key) throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(cipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }
}
