package Encryption;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class DES implements Encryption {

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    private byte[] arrayBytes;
    private char[] password;
    private String myEncryptionScheme;
    private SecretKey key;
    public static DES instance;

    private DES() {
    }

    public void init(char[] password) {
        //#TODO key must be 24 bytes or char
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = new byte[24];
        int length = password.length;
        this.password = password;
        for (int i = 0; i < length; ++i) {
            arrayBytes[i] = (byte) password[i];
        }
        //Arrays.fill(password, (char) 0);
        initSpecs();

    }

    private void initSpecs() {
        try {
            ks = new DESedeKeySpec(arrayBytes);
            Arrays.fill(arrayBytes, (byte) 0);
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


    public char[] encryptedMasterPassword(){
       return encrypt(password);
    }
    @Override
    public String encrypt(String input) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytesText = input.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(bytesText);
            return new String(Base64.getEncoder().encode(encrypted));
        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public char[] encrypt(char[] encrypt) {
        try {
            byte[] bytesText = new byte[encrypt.length];
            for (int i = 0; i < encrypt.length; ++i) {
                bytesText[i] = (byte) encrypt[i];
            }
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(bytesText);
            byte[] rawbyte = Base64.getEncoder().encode(encrypted);
            char encoded[] = new char[rawbyte.length];
            for (int i = 0; i < rawbyte.length; ++i) {
                encoded[i] = (char) rawbyte[i];
            }
            return encoded;
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String decrypt(String encrypt) {
        try {
            String a = encrypt;
            cipher.init(cipher.DECRYPT_MODE, key);
            byte[] encrypted = Base64.getDecoder().decode(a);
            byte[] bytesText = cipher.doFinal(encrypted);
            return new String(bytesText);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public char[] decrypt(char[] encrypt) {
        try {
            byte[] encodedbytesText = new byte[encrypt.length];
            for (int i = 0; i < encrypt.length; ++i) {
                encodedbytesText[i] = (byte) encrypt[i];
            }
            cipher.init(cipher.DECRYPT_MODE, key);
            byte[] encrypted = Base64.getDecoder().decode(encodedbytesText);
            byte[] bytesText = cipher.doFinal(encrypted);
            char decoded[] = new char[bytesText.length];
            for (int i = 0; i < bytesText.length; ++i) {
                decoded[i] = (char) bytesText[i];
            }
            return decoded;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "DES";
    }
}
