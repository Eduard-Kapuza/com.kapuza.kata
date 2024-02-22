import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String stringInput = scanner.nextLine();

        String[] inputs = stringInput.split("[\\-+*/]");

        if (inputs.length > 2) {
            throw new Exception("В введённом выражении больше одной арифмитеческой операции");
        }

        try {
            Integer.parseInt(inputs[0]);
            Integer.parseInt(inputs[1]);
            arab(stringInput, inputs/*, negativeNumber*/);
        } catch (Exception e) {
            rome(stringInput, inputs);
        }
    }

    public static void rome(String str, String[] inputs) throws Exception {

        String firstUnit = inputs[0];
        String twoUnit = inputs[1];

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
                throw new Exception("Результат операций над римскими цифрами меньше 1");
            }
            System.out.println(romeNumbers[resultRome]);

        } else {
            throw new Exception("""
                                        
                    Введенное выражение содержит:
                                                1) цифры разных систем исчисления
                                                2) значение(-я) не являющееся цифрой(-ами)
                                                3) в введённом выражении число(-а) больше 10(X) или меньше 1(I)""");
        }
    }


    public static void arab(String str, String[] inputs) throws Exception {

        int a = Integer.parseInt(inputs[0]);
        int b = Integer.parseInt(inputs[1]);
        System.out.println(counting(str, a, b));
    }


    public static int counting(String str, int a, int b) throws Exception {

        int result = 0;

        if (a < 1 || a > 10 || b < 0 || b > 10) {
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
