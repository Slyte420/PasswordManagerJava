import Launcher.Launcher;
import User.EntryGeneral;

public class Main {
    public static void main(String[] args) {
        /*final String password = "azta geci";
        Encryption.AES instance = Encryption.AES.getInstance();
        instance.init(password);
        String encrypted = instance.encrypt("Bro");
        System.out.println(encrypted);
        String decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);
        instance.init("lol");
        decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);*/
        //new Launcher();
        EntryGeneral a = new EntryGeneral("Lol");
        a.setPassword("lol");
        System.out.println(a.getPassword());
    }
}
