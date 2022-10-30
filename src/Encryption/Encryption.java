package Encryption;

public interface Encryption {
    public void init(char[] password);
    <T> T encrypt(T input);
    <T> T decrypt(T encrypt);
}
