package encryption;

public interface Encryption {
    public void init(char[] password);
    public void reset();
    String encrypt(String input);
    char[] encryptedMasterPassword();
    char[] encrypt(char[] encrypt);
    String decrypt(String encrypt);
    char[] decrypt(char[] a);
}
