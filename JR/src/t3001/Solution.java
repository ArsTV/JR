package t3001;

import java.math.BigInteger;

/*
 * Converter class
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumerationSystemType._10, "6");
        Number result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumerationSystemType._16, "6df");
        result = convertNumberToOtherNumerationSystem(number, NumerationSystemType._8);
        System.out.println(result);    //expected 3337
    }

    public static Number convertNumberToOtherNumerationSystem(Number number, NumerationSystem expectedNumerationSystem) {
        //write here your code
    	/*
        if(number.getNumerationSystem().getNumerationSystemIntValue() == 2){
            String[] temp = number.getDigit().split("");
            for (int i = 0; i < temp.length; i++) {
                if(!temp[i].equals("0") || !temp[i].equals("1"))
                    throw new NumberFormatException();
            }
        } else if(number.getNumerationSystem().getNumerationSystemIntValue() == 16){
            String[] temp = number.getDigit().split("");
            for (int i = 0; i < temp.length; i++) {
                if(!temp[i].equals("0")
                        || !temp[i].equals("1")
                        || !temp[i].equals("2")
                        || !temp[i].equals("3")
                        || !temp[i].equals("4")
                        || !temp[i].equals("5")
                        || !temp[i].equals("6")
                        || !temp[i].equals("7")
                        || !temp[i].equals("8")
                        || !temp[i].equals("9")
                        || !temp[i].equals("a")
                        || !temp[i].equals("b")
                        || !temp[i].equals("c")
                        || !temp[i].equals("d")
                        || !temp[i].equals("e")
                        || !temp[i].equals("f")
                        )
                    throw new NumberFormatException();
            }
        }
        // 10 -> 2 General

        if (number.getNumerationSystem().getNumerationSystemIntValue() == 10
                && expectedNumerationSystem.getNumerationSystemIntValue() == 2) {
            int number10 = Integer.parseInt(number.getDigit());
            StringBuilder stringNumber = new StringBuilder("");
            while (number10 >= 2) {
                stringNumber.append(number10 % 2);
                number10 = number10 / 2;
            }
            stringNumber.append(number10);
            stringNumber.reverse();
            return new Number(NumerationSystemType._2, stringNumber.toString());

            // 16 -> 8 General
        } else if ((number.getNumerationSystem().getNumerationSystemIntValue() == 16
                && expectedNumerationSystem.getNumerationSystemIntValue() == 8)) {

            StringBuilder temp = new StringBuilder(number.getDigit());
            temp.reverse();

            String number16String = temp.toString();
            String[] number16StringArray = number16String.split("");
            int[] number16IntArray = new int[number16StringArray.length];

            for (int i = 0; i < number16StringArray.length; i++) {
                if (number16StringArray[i].toLowerCase().equals("a"))
                    number16IntArray[i] = 10;
                else if (number16StringArray[i].toLowerCase().equals("b"))
                    number16IntArray[i] = 11;
                else if (number16StringArray[i].toLowerCase().equals("c"))
                    number16IntArray[i] = 12;
                else if (number16StringArray[i].toLowerCase().equals("d"))
                    number16IntArray[i] = 13;
                else if (number16StringArray[i].toLowerCase().equals("e"))
                    number16IntArray[i] = 14;
                else if (number16StringArray[i].toLowerCase().equals("f"))
                    number16IntArray[i] = 15;
                else
                    number16IntArray[i] = Integer.parseInt(number16StringArray[i]);
            }


            //16 --> 10
            int number10 = 0;
            for (int i = 0; i < number16IntArray.length; i++) {
                number10 = number10 + number16IntArray[i] * (int) Math.pow(16, (double) i);
            }


            // 10 -> 8

            StringBuilder stringNumber = new StringBuilder("");
            while (number10 >= 8) {
                stringNumber.append(number10 % 8);
                number10 = number10 / 8;
            }
            stringNumber.append(number10);
            stringNumber.reverse();
            return new Number(NumerationSystemType._8, stringNumber.toString());

        }
        return null;*/
        BigInteger bigInt = new BigInteger(number.getDigit(), number.getNumerationSystem().getNumerationSystemIntValue());

        return new Number(expectedNumerationSystem, bigInt.toString(expectedNumerationSystem.getNumerationSystemIntValue()));
    }
}
