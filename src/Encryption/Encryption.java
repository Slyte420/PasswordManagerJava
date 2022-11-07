package Encryption;

public interface Encryption {
    public void init(char[] password);
    String encrypt(String input);
    char[] encryptedMasterPassword();
    char[] encrypt(char[] encrypt);
    String decrypt(String encrypt);
    char[] decrypt(char[] a);
}
