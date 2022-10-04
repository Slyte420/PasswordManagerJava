import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) {
        final String password = "azta geci";
        AES instance = AES.getInstance();
        instance.init(password);
        String encrypted = instance.encrypt("Bro");
        System.out.println(encrypted);
        String decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);
        instance.init("lol");
        decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);

    }
}
