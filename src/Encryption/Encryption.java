package Encryption;

public interface Encryption {
    public void init(String password);
    public <T> T encrypt(T input);
    public <T> T decrypt(T encrypt);
}
