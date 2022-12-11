import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String string = scanner.next();

    public static void main(String[] args) {
        char[] myChar = Main.string.toCharArray();
        int indexOperation = Main.getIndexOperation(myChar);
        char operation = myChar[indexOperation];
        String num1 = getFirstNumber(indexOperation, myChar);
        String num2 = getSecondNumber(indexOperation, myChar);

        System.out.println(result(num1, num2, operation));


    }

    public static String result(String num1, String num2, char operation) {
        int intNum1;
        int intNum2;

        RomanNumFirsts[] romanNumFirsts = RomanNumFirsts.values();
        String romans = Arrays.toString(romanNumFirsts);

        try {
            if (num1.equals("")) {
                throw new WrongOperationException("Неверно указан оператор");
            }
            if ((romans.contains(num1) & !romans.contains(num2)) | (!romans.contains(num1) & romans.contains(num2))) {
                throw new WrongTypeException("Используются одновременно разные системы счисления");
            }
            if (romans.contains(num1) & romans.contains(num2)) {
                num1 = RomanNumFirsts.valueOf(num1).getValue();
                num2 = RomanNumFirsts.valueOf(num2).getValue();

                intNum1 = Integer.parseInt(num1);
                intNum2 = Integer.parseInt(num2);

                if (intNum1 < 0 | intNum1 > 10 | intNum2 < 0 | intNum2 > 10) {
                    throw new WrongRangeException("Превышен диапозон допустимых для ввода значений");
                }

                if ((calc(intNum1, intNum2, operation)) < 1) {
                    throw new RomanException("Результатом работы калькулятора с римскими числами могут быть только положительные числа");
                }
                return (convert(calc(intNum1, intNum2, operation)));


            } else if (!romans.contains(num1) & !romans.contains(num2)) {
                intNum1 = Integer.parseInt(num1);
                intNum2 = Integer.parseInt(num2);

                if (intNum1 < 1 | intNum1 > 10 | intNum2 < 1 | intNum2 > 10) {
                    throw new WrongRangeException("Превышен диапозон допустимых для ввода значений");
                }
                return (String.valueOf(calc((Integer.parseInt(num1)), Integer.parseInt(num2), operation)));
            }
            return "";
        } catch (NumberFormatException | WrongTypeException | WrongRangeException | WrongOperationException |
                 RomanException e) {
            System.out.println("Некорректный формат выражения. Повторите попытку.");
        }
        return "";
    }


    public static String getFirstNumber(int indexOperation, char[] chars) {
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < indexOperation; i++) {
            String symbol = String.valueOf(chars[i]);
            number.append(symbol);
        }

        return number.toString();
    }


    public static String getSecondNumber(int indexOperation, char[] chars) {
        StringBuilder number = new StringBuilder();

        for (int i = indexOperation + 1; i < chars.length; i++) {
            String symbol = String.valueOf(chars[i]);
            number.append(symbol);
        }

        return number.toString();
    }

    public static int getIndexOperation(char[] chars) {
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < chars.length; i++) {
            String symbol = String.valueOf(chars[i]);
            for (String operation : operations) {
                if (symbol.equals(operation)) {
                    return i;
                }
            }
        }
        return 0;


    }


    public static int calc(int num1, int num2, char operation) {
        int result;
        switch (operation) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
            default -> result = calc(num1, num2, operation);

        }
        return result;
    }

    public static String convert(int result) {
        String num = "";
        int currentResult;
        String numResult = String.valueOf(result);
        RomanNumFirsts[] romanNumFirsts = RomanNumFirsts.values();
        RomanNumSeconds[] romanNumSeconds = RomanNumSeconds.values();
        char[] numResultChar = numResult.toCharArray();

        if (result == 100) {
            num = "C";
            return num;
        } else {
            if (numResultChar.length == 2) {
                currentResult = result / 10;
                num += romanNumSeconds[currentResult - 1].getKey();
                currentResult = result % 10;
                if (currentResult != 0) {
                    num += romanNumFirsts[currentResult - 1].getKey();
                }
                return num;
            } else {
                if (numResultChar.length == 1) {
                    num += romanNumFirsts[result - 1].getKey();
                    return num;
                }
            }
        }

        return num;
    }
}