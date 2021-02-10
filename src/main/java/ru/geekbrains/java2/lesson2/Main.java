package ru.geekbrains.java2.lesson2;

public class Main {
    
    /**
     * 1. Напишите метод, на вход которого подаётся
     * двумерный строковый массив размером 4х4, 
     * при подаче массива другого размера необходимо бросить 
     * исключение MyArraySizeException.
     *
     * 2. Далее метод должен пройтись по всем элементам массива, 
     * преобразовать в int, и просуммировать. 
     * Если в каком-то элементе массива преобразование не удалось 
     * (например, в ячейке лежит символ или текст вместо числа), 
     * должно быть брошено исключение MyArrayDataException, 
     * с детализацией в какой именно ячейке лежат неверные данные.
     *
     * 3. В методе main() вызвать полученный метод, 
     * обработать возможные исключения 
     * MySizeArrayException и MyArrayDataException, 
     * и вывести результат расчета.
     */
    public static void main(String[] args) {

        String[][] array2x2 = {{"11", "12"}, {"13", "1"}};
        
        String[][] array4xN = {{"1", "1", "1", "1"},
                               {"1", "1", "1", "1"},
                               {"1", "1", "1"},
                               {"1", "1", "1", "1"}};
        
        String[][] array4x4 = {{"1", "1", "1", "1"},
                               {"1", "1", "1", "1"},
                               {"1", "1", "1", "1"},
                               {"1", "1", "1", "1"}};
        
        String[][] arrayWithStr = {{"0", "1", "1", "1"},
                                   {"1", "1", "1", "1"},
                                   {"1", "1", "q", "1"},
                                   {"1", "1", "1", "1"}};

        printMatrix(array2x2);
        testCalculation(array2x2);
        System.out.println();

        printMatrix(array4xN);
        testCalculation(array4xN);
        System.out.println();

        printMatrix(array4x4);
        testCalculation(array4x4);
        System.out.println();

        printMatrix(arrayWithStr);
        testCalculation(arrayWithStr);
        System.out.println();
    }
    
    private static int calcArray(String[][] array)
    throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (array.length != 4 ) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(array[i][j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException(e, i, j);
                }
            }
        }
        return sum;
    }

    /**
     * Вспомогательный метод. Вызывает calcArray в блоке try/catch.
     * Нужен для удобства тестирования разных вариантов массива.
     * 
     * @param array
     *        массив для calcArray.
     */
    private static void testCalculation(String[][] array) {
        try {
            System.out.println("Sum = " + calcArray(array));
        } catch (MyArraySizeException | MyArrayDataException es) {
            es.printStackTrace();
        }
    }

    /**
     * Выводит в консоль двумерный массив.
     * 
     * @param array
     *        двумерный массив. 
     */
    private static void printMatrix(String[][] array) {
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

}
