package RandomPasswordGenerator;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

public class PassGen {

    private final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    private final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String numbers = "0123456789";
    private String specialCharacthers = "@!./?<>;[]\\(){}";
    private String customCharacthers;
    private boolean lower;
    private boolean upper;
    private boolean number;
    private boolean special;

    private boolean custom;
    public PassGen(String customCharacthers,boolean lower,boolean upper, boolean number, boolean special,boolean custom){
        this.customCharacthers = customCharacthers;
        this.lower = lower;
        this.upper = upper;
        this.number = number;
        this.special = special;
        this.custom = custom;
    }
    public PassGen(boolean lower,boolean upper, boolean number, boolean special){
        this.lower = lower;
        this.upper = upper;
        this.number = number;
        this.special = special;
        this.custom = false;
    }

    public PassGen(){
        this.lower = true;
        this.upper = true;
        this.number = true;
        this.special = true;
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

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public void setCustomCharacthers(String customCharacthers) {
        this.customCharacthers = customCharacthers;
    }

    public String getCustomCharacthers() {
        return customCharacthers;
    }

    public String getLowerCaseLetters() {
        return lowerCaseLetters;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getSpecialCharacthers() {
        return specialCharacthers;
    }

    public String getUpperCaseLetters() {
        return upperCaseLetters;
    }

    public boolean isCustom() {
        return custom;
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
        if(o instanceof PassGen){
            PassGen a = (PassGen)o;
            boolean custom = false;
            if(a.customCharacthers != null && this.customCharacthers != null){
                custom = a.customCharacthers.equals(this.customCharacthers);
            }
            else{
                if(a.customCharacthers == null && this.customCharacthers == null){
                    custom = true;
                }
            }
            return (custom && a.lower == this.lower && a.upper == this.upper && a.number == this.number && a.custom == this.custom);
        }
        return false;
    }

    public char[] generatePassword(int length){
        char[] password = new char[length];
        String combinedchar = "";
        if(lower){
            combinedchar = combinedchar + lowerCaseLetters;
        }
        if(upper){
            combinedchar = combinedchar + upperCaseLetters;
        }
        if(number){
            combinedchar = combinedchar + numbers;
        }
        if(special){
            combinedchar = combinedchar + specialCharacthers;
        }
        if(custom && customCharacthers.length() != 0){
            combinedchar = combinedchar + customCharacthers;
        }

        Random rand = new SecureRandom();
        int bound = combinedchar.length();
        if(bound <= 0){

        }
        for(int i = 0; i < length; ++i){
            password[i] = combinedchar.charAt(rand.nextInt(bound));
        }

        return password;
    }
}
