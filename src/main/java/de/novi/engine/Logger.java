package de.novi.engine;

public class Logger {
    public static <T> void log(T t) {
        System.out.println(t.toString());
    }
}
