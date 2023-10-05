package org.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = "";
        while (!input.equals("q")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            System.out.println(calc(input));
        }

    }

    // Конвертирует из строки в число
    static int toInteger(String number) {
        int intNumber;
        String numbers = "1234567890";
        if (numbers.contains(number)) {
            intNumber = Integer.parseInt(number);

        } else {
            switch (number) {
                case "I" -> intNumber = 1;
                case "II" -> intNumber = 2;
                case "III" -> intNumber = 3;
                case "IV" -> intNumber = 4;
                case "V" -> intNumber = 5;
                case "VI" -> intNumber = 6;
                case "VII" -> intNumber = 7;
                case "VIII" -> intNumber = 8;
                case "IX" -> intNumber = 9;
                case "X" -> intNumber = 10;
                default -> intNumber = 0;
            }
        }
        return intNumber;
    }

    // Сам калькулятор
    public static String calc(String input) throws Exception {

        String[] expression = input.split(" ");
        if (expression.length != 3) {
            throw new Exception("wrong expression");
        }
        String typeOfNumbers = validator(expression[0], expression[2], expression[1]);
        int firstNumber = toInteger(expression[0]);
        int secondNumber = toInteger(expression[2]);
        String symbol = expression[1];
        int result = switch (symbol) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> 0;
        };
        if (typeOfNumbers.equals("romanNumbers") && result >= 1) {
            return IntegerConverter.intToRoman(result);
        } else if (typeOfNumbers.equals("arabicNumbers")) {
            return Integer.toString(result);
        } else {
            throw new Exception("Not positive roman number");
        }
    }

    // проверяет правильность операции, возвращает строку с наименованием типа введенных чисел
    static String validator(String firstNum, String secondNum, String symbol) throws Exception {
        String arabicNumbers = "1234567890";
        String romanNumbers = "I II III IV V VI VII VIII IX X";
        String trueSymbols = "+ - * /";

        if (!trueSymbols.contains(symbol)) {
            throw new Exception("Wrong symbol");
        }

        if (arabicNumbers.contains(firstNum) & arabicNumbers.contains(secondNum)) {
            return "arabicNumbers";
        } else if (romanNumbers.contains(firstNum) & romanNumbers.contains(secondNum)) {
            return "romanNumbers";
        } else {
            throw new Exception("Different type of numbers");
        }
    }
}