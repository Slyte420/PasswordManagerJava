package Encryption;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class DES implements Encryption {

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    private SecretKey key;
    public static DES instance;
    private DES() {
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


    public static DES getInstance() {
        if (instance == null) {
            instance = new DES();
        }
        return instance;
    }




    public <T> T encrypt(T input) {
        if (input instanceof String) {
            try {
                String a = (String) input;
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] bytesText = a.getBytes(StandardCharsets.UTF_8);
                byte[] encrypted = cipher.doFinal(bytesText);
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
