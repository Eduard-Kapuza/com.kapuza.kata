package v2;

import java.util.List;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String stringInput = scanner.nextLine();
        calc(stringInput);

    }
    public static String calc(String input) throws Exception {


        while (true) {

            String[] inputs = operation(input);
            try {
                System.out.println(counting(Integer.parseInt(inputs[0]), inputs[1], Integer.parseInt(inputs[2])));
            }catch (Exception e) {
                rome(inputs);
            }
        }}

    public static String[] operation(String str) throws Exception {

        int index = validation(str);
        String[] inputArray = str.split("[\\-*+/]");  // Можно пойти через List<String> strings=List.of(str.split("[\\-*+/]"));
        return new String[]{inputArray[0],                  // с небольшими изменениями в коде
                str.substring(index, index + 1),
                inputArray[1]};
    }

    public static int validation(String str) throws Exception {

        int operationIndex = -1;
        char[] chars = new char[]{'+', '-', '*', '/'};

        if (str.length() < 3 ) {
            throw new Exception("Длина входной строки должна быть минимум 3 символа");
        }

        if (str.contains(",") || str.contains(".")) {
            throw new Exception("Калькулятор работает только с целыми числами");
        }

        if (str.charAt(0) == '-' ) {
            throw new Exception("Принемаемые числа на вход должны быть от 1(I) до 10(X) включительно");
        }

        for (int i = 0, checkDoubleOperation = 0; i < chars.length; i++) {  // проверка входных данных типа 5-+8 (/5-8) и т.п.
            if (str.contains(String.valueOf(chars[i]))) {
                checkDoubleOperation++;
            }
            if (checkDoubleOperation >= 2 || str.endsWith("-")/*второе условие нужно т.к. не корректно отображается ошибка типа -5-*/) {
                throw new Exception("""                
                        \nВведенное выражение содержит:
                                                    1) больше одной арифметической операции
                                                    2) введены некорректные данные""");
            }
        }

        OUTER:
        for (int i = 0; i < str.length(); i++) {    // определение знак арифметической операции
            for (char sign : chars) {
                if (str.charAt(i) == sign) {
                    operationIndex = i;
                    break OUTER;
                }
            }
        }
        if (operationIndex == -1) {
            throw new Exception("Недопустимая арифметическая операция");
        }
        return operationIndex;
    }

    public static void rome(String[] inputs) throws Exception {

        String firstNumber = inputs[0];
        String twoNumber = inputs[2];

        boolean flag1 = false, flag2 = false;
        int numberOne = -1, numberTwo = -1;
        List<String> list = List.of("0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
                "XXVI", "XXVII", "XXIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
                "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C");

        if (list.contains(firstNumber)) {
            numberOne = list.indexOf(firstNumber);
            flag1 = true;
        }
        if (list.contains(twoNumber)) {
            numberTwo = list.indexOf(twoNumber);
            flag2 = true;
        }

        if (flag1 && flag2) {
            int resultRome = counting(numberOne, inputs[1], numberTwo);
            if (resultRome < 1) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            System.out.println(list.get(resultRome));
        } else {
            throw new Exception("""                
                    \nВведенное выражение содержит:
                                                1) одновременно разные системы счисления
                                                2) значение(-я) не являющееся цифрой(-ами)""");
        }
    }

    public static int counting(int a, String str, int b) throws Exception {

        int result = 0;
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("Принемаемые числа на вход должны быть от 1(I) до 10(X) включительно");
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
