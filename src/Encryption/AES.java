package Encryption;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AES implements Encryption {

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    private SecretKey key;
    public static AES instance;
    private AES() {
    }

    public void init(String password) {
        //#TODO key must be 24 bytes or char
        myEncryptionKey = password;
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes();
        initSpecs();

    }

    private void initSpecs() {
        try {
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = skf.generateSecret(ks);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }


    public static AES getInstance() {
        if (instance == null) {
            instance = new AES();
        }
        return instance;
    }




    public <T> T encrypt(T input) {
        if (input instanceof String) {
            try {
                String a = (String) input;
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] bytesText = a.getBytes(StandardCharsets.UTF_8);
                byte[] encrypted = new byte[0];
                encrypted = cipher.doFinal(bytesText);
                return (T) new String(Base64.getEncoder().encode(encrypted));
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public <T> T decrypt(T encrypt) {
        if (encrypt instanceof String) {
            try {

                String a = (String) encrypt;
                cipher.init(cipher.DECRYPT_MODE,key);
                byte[] encrypted = Base64.getDecoder().decode(a);
                byte[] bytesText = cipher.doFinal(encrypted);

                return (T) new String(bytesText);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                System.out.println("Decryption went bad");
            }
        }
        return null;
    }
}
