import java.util.Scanner;

/**
 * @author Mikhail Latypov
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите арифметическую операцию из двух чисел (арабских или римских) :");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        // Вычисление и вывод результата основываясь на введенной строке
        String result = calc(input);
        System.out.println(result);
    }

    /**
     * Основной метод для выполнения арифметических операций с арабскими и римскими числами
     */
    public static String calc(String input) {
        // Разделение строки на составные части для обработки (число, оператор, число)
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }
        // Проверка типа чисел (римские или арабские)
        boolean isRoman = isRomanNumber(parts[0]) && isRomanNumber(parts[2]);
        // Преобразование чисел в арабские значения для операций
        int a = isRoman ? romanToArabic(parts[0]) : Integer.parseInt(parts[0]);
        int b = isRoman ? romanToArabic(parts[2]) : Integer.parseInt(parts[2]);
        String operator = parts[1];
        // Проверка на сочетание римских и арабских чисел
        if ((isRoman && !isRomanNumber(parts[0])) || (!isRoman && isRomanNumber(parts[2]))) {
            throw new IllegalArgumentException("Можно использовать только арабские или римские числа одновременно");
        }
        // Проверка на допустимый диапазон чисел
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
        }
        // Выполнение арифметической операции
        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Неверный оператор");
        };
        // Возврат результата в соответствующем формате (римском или арабском)
        return isRoman ? arabicToRoman(result) : Integer.toString(result);
    }

    /**
     * Метод проверяет, является ли входная строка римским числом
     */
    private static boolean isRomanNumber(String str) {
        boolean result = false;
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        for (String roman : romanNumbers) {
            if (str.equalsIgnoreCase(roman)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Метод конвертирует римские числа в арабские
     */
    private static int romanToArabic(String roman) {
        int result = 0;
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        for (int i = 0; i < romanNumbers.length; i++) {
            if (roman.equalsIgnoreCase(romanNumbers[i])) {
                result = i + 1;
                break;
            }
        }

        return result;
    }

    /**
     * Метод конвертирует арабские числа в римские
     */
    private static String arabicToRoman(int arabic) {
        if (arabic <= 0) {
            throw new IllegalArgumentException("Результат меньше 1");
        }
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII",
                "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII",
                "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        return romanNumbers[arabic - 1];
    }
}