package ru.geekbrains.java2.lesson2;

/**
 * Класс исключения MyArrayDataException.
 * Возникает в случае невозможности конвертации значения ячейки в целое число.
 * 
 * @author Kristina Retivykh
 */
public class MyArrayDataException extends Exception {
    
    private static final String DEFAULT_MESSAGE = "Failed parsing to Integer.";
    
    public MyArrayDataException() {
        super(DEFAULT_MESSAGE);
    }
    
    public MyArrayDataException(String message) {
        super(message);
    }
    
    public MyArrayDataException(int i, int j) {
        super(String.format(DEFAULT_MESSAGE +
                "Error in cell(%d, %d).%n", i, j));
    }

    public MyArrayDataException(String message, int i, int j) {
        super(message + String.format(DEFAULT_MESSAGE +
                "Error in cell(%d, %d).%n", i, j));
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArrayDataException(String message, Throwable cause, int i, int j) {
        super(message + String.format(DEFAULT_MESSAGE +
                "Error in cell(%d, %d).%n", i, j), cause);
    }

    public MyArrayDataException(Throwable cause, int i, int j) {
        super(String.format(DEFAULT_MESSAGE +
                "Error in cell(%d, %d).%n", i, j), cause);
    }

    public MyArrayDataException(
            String message, 
            Throwable cause, 
            boolean enableSuppression, 
            boolean writableStackTrace) 
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public MyArrayDataException(
            String message, 
            Throwable cause, 
            boolean enableSuppression, 
            boolean writableStackTrace,  
            int i, 
            int j) 
    {
        super(message + String.format(DEFAULT_MESSAGE +
                "Error in cell(%d, %d).%n", i, j), 
                cause, enableSuppression, writableStackTrace);
    }

}
