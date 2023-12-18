package fr.mimifan.projethypixel.utils;

/**
 * @author <a href="https://www.javatpoint.com/convert-integer-to-roman-numerals-in-java">javatpoint.com</a> <br>
 * Converts Integer to Roman numerals <br>
 * In this project, it will be used for skyblock skill's levels.
 */
public class IntegerToRoman  {

    /**
     * Current instance of this class;
     */
    private static final IntegerToRoman instance = new IntegerToRoman();

    /**
     * Values used as roman constants.
     */
    private final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};

    /**
     * Roman letters associated to their values.
     */
    private final String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    /**
     * Converts an Integer to a Roman Numeral
     * @param num the number to convert.
     * @return a String representation of the number in Roman notation.
     */
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


    /**
     * @return {@link IntegerToRoman#instance}
     */
    public static IntegerToRoman getInstance() {
        return instance;
    }
}