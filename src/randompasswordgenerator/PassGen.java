package randompasswordgenerator;

import java.security.SecureRandom;
import java.util.Random;

public class PassGen {

    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static String specialCharacthers = "@!./?<>;[]\\(){}";
    private String customCharacthers;
    private boolean lower;
    private boolean upper;
    private boolean number;
    private boolean special;

    private boolean custom;


    public PassGen(boolean lower, boolean upper, boolean number, boolean special) {
        this.lower = lower;
        this.upper = upper;
        this.number = number;
        this.special = special;
        this.custom = false;
    }

    public PassGen() {
        this.lower = true;
        this.upper = true;
        this.number = true;
        this.special = true;
        this.custom = false;
    }

    public void setLower(boolean lower) {
        this.lower = lower;
    }

    public void setUpper(boolean upper) {
        this.upper = upper;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }


    public String getLowerCaseLetters() {
        return LOWER_CASE_LETTERS;
    }

    public String getNumbers() {
        return NUMBERS;
    }

    public String getSpecialCharacthers() {
        return specialCharacthers;
    }

    public String getUpperCaseLetters() {
        return UPPER_CASE_LETTERS;
    }


    public boolean isLower() {
        return lower;
    }

    public boolean isNumber() {
        return number;
    }

    public boolean isSpecial() {
        return special;
    }

    public boolean isUpper() {
        return upper;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PassGen) {
            PassGen a = (PassGen) o;
            boolean custom = false;
            return (custom && a.lower == this.lower && a.upper == this.upper && a.number == this.number && a.custom == this.custom);
        }
        return false;
    }

    public char[] generatePassword(int length) throws PassGenException {
        char[] password = new char[length];
        String combinedchar = "";
        if (lower) {
            combinedchar = combinedchar + LOWER_CASE_LETTERS;
        }
        if (upper) {
            combinedchar = combinedchar + UPPER_CASE_LETTERS;
        }
        if (number) {
            combinedchar = combinedchar + NUMBERS;
        }
        if (special) {
            combinedchar = combinedchar + specialCharacthers;
        }
        if (custom && customCharacthers.length() != 0) {
            combinedchar = combinedchar + customCharacthers;
        }

        Random rand = new SecureRandom();
        int bound = combinedchar.length();
        if (bound <= 0) {
            throw new PassGenException("No characters to select from");
        }
        for (int i = 0; i < length; ++i) {
            password[i] = combinedchar.charAt(rand.nextInt(bound));
        }

        return password;
    }

    public static boolean validUsername(String username){
        if(!(username.length() > 0 && username.length() <= 32)){
            return false;
        }
        if(username.contains(" ")){
            return false;
        }
        return true;
    }
    public static boolean validPassword(char[] password) {
        if (!(password.length >= 3 && password.length <= 24)) {
            return false;
        }

        boolean special = false;
        boolean big = false;
        boolean small = false;
        boolean number = false;
        for (int i = 0; i < password.length; ++i) {
            if (password[i] == ' ') {
                return false;
            }
            if (specialCharacthers.contains("" + password[i])) {
                special = true;
            }
            if (UPPER_CASE_LETTERS.contains("" + password[i])) {
                big = true;
            }
            if (LOWER_CASE_LETTERS.contains("" + password[i])) {
                small = true;
            }
            if (NUMBERS.contains("" + password[i])) {
                number = true;
            }
        }
        return (special && big && small && number);
    }
    public static boolean validOther(String other){
        return !other.contains(" ");
    }
}
