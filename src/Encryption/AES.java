package Encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AES {

    private final static String algorithm = "Encryption.AES/CBC/PKCS5Padding";
    private static AES instance;

    private IvParameterSpec iv;
    private SecretKey key;

    private SecureRandom sr;
    private String salt;

    private AES() {
    }

    public void init(String password){
        byte[] seed = getSeed(password);
        sr = new SecureRandom(seed);
        iv = generateIV();
        salt = generateSalt();
        key = getKeyFromPassword(password);
    }
    private byte[] getSeed(String password) {
        return Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8));
    }

    private String generateSalt() {
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Arrays.toString(salt);
    }

    public static AES getInstance() {
        if (instance == null) {
            instance = new AES();
        }
        return instance;
    }

    private IvParameterSpec generateIV() {
        byte[] iv = new byte[16];
        sr.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private SecretKey getKeyFromPassword(String password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);

            return new SecretKeySpec(factory.generateSecret(spec)
                    .getEncoded(), "Encryption.AES");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                System.out.println("Error getKeyFromPassword");
        }
        return null;
    }

    public String encrypt(String input) {

        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidAlgorithmParameterException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            System.out.println("Wrong password");
        }
        return null;
    }

    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(cipherText));
            return new String(plainText);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException |
                 InvalidAlgorithmParameterException | InvalidKeyException |
                 BadPaddingException | IllegalBlockSizeException e) {
            System.out.println("Wrong password");
        }
        return null;
    }
}
