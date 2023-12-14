package fr.mimifan.projethypixel.utils;

public class IntegerToRoman  {

    private static final IntegerToRoman instance = new IntegerToRoman();
    private final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private final String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String intToRoman(int num)  {

        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++) {
            while(num >= values[i]) {
                num = num - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString().length() > 0 ? roman.toString() : "0";
    }


    public static IntegerToRoman getInstance() {
        return instance;
    }
}