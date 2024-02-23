import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String stringInput = scanner.nextLine();
            String[] inputs = operations(stringInput);

            try {
                System.out.println(counting(inputs[1], Integer.parseInt(inputs[0]), Integer.parseInt(inputs[2])));
            } catch (Exception e) {
                rome(stringInput, inputs);
            }
        }
    }

    public static String[] operations(String str) throws Exception {

        int indexOfOperation = -1;
        char[] operationSigns = {'-', '+', '*', '/'};

        if (str.length() == 1 || str.length() == 2 || str.length() == 0) {
            throw new Exception("Введено не корректное выражение - " + "\"" + str + "\"");
        }

        for (int i = 1, k = 0; i < str.length(); i++) {
            for (int j = 0; j < operationSigns.length; j++) {
                if (str.charAt(i) == operationSigns[j]) {
                    k++;
                    if (k > 1) {
                        throw new Exception("В введённом выражении больше одной арифмитеческой операции или первое число отрицательное");
                    }
                    indexOfOperation = i;
                    break;
                }
            }
        }

        if (indexOfOperation == -1) {
            throw new Exception("Вы выбрали неподдерживаемый тип операции");
        }

        return new String[]{
                str.substring(0, indexOfOperation),
                str.substring(indexOfOperation, indexOfOperation + 1),
                str.substring(indexOfOperation + 1)
        };
    }

    public static void rome(String str, String[] inputs) throws Exception {

        String firstUnit = inputs[0];
        String twoUnit = inputs[2];

        boolean flag1 = false, flag2 = false;
        int numberOne = 0, numberTwo = 0;

        String[] romeNumbers = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
                "XXVI", "XXVII", "XXIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        for (int j = 1; j < 11; j++) {

            if (firstUnit.contains(romeNumbers[j]) && firstUnit.length() == romeNumbers[j].length()) {
                numberOne = j;
                flag1 = true;
            }
            if (twoUnit.contains(romeNumbers[j]) && twoUnit.length() == romeNumbers[j].length()) {
                numberTwo = j;
                flag2 = true;
            }
        }

        if (flag1 && flag2) {

            int resultRome = counting(str, numberOne, numberTwo);
            if (resultRome < 1) {
                throw new Exception("""
                        \nВ римской системе нет отрицательных чисел 
                        Или число между 0 и I""");
            }
            System.out.println(romeNumbers[resultRome]);

        } else {
            throw new Exception("""                
                    \nВведенное выражение содержит:
                                                1) одновременно разные системы счисления
                                                2) значение(-я) не являющееся цифрой(-ами)
                                                3) в введённом выражении число(-а) больше 10(X) или меньше 1(I)""");
        }
    }


    public static int counting(String str, int a, int b) throws Exception {

        int result = 0;

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception();
        }

        if (str.contains("+")) {
            result = a + b;
        } else if (str.contains("-")) {
            result = a - b;
        } else if (str.contains("/")) {
            result = a / b;
        } else if (str.contains("*")) {
            result = a * b;
        }
        return result;
    }
}
