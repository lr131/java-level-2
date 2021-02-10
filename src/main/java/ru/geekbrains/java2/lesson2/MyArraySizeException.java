package ru.geekbrains.java2.lesson2;

/**
 * Класс исключения MyArraySizeException.
 * Возникает в случае получения массива размером, отличным от 4х4.
 * 
 * @author Kristina Retivykh
 */
public class MyArraySizeException extends Exception{
    
    private static final int SIZE = 4;
    private static final String DEFAULT_MESSAGE = "Array must be " + 
            SIZE + "x" + SIZE + ".\n";
    
    public MyArraySizeException() {
        super(DEFAULT_MESSAGE);
    }

    public MyArraySizeException(String message) {
        super(message);
    }

    public MyArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArraySizeException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public MyArraySizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
