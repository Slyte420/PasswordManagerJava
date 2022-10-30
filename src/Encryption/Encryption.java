package Encryption;

public interface Encryption {
    public void init(String password);
    <T> T encrypt(T input);
    <T> T decrypt(T encrypt);
}
