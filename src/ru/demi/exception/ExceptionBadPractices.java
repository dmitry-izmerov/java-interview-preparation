package ru.demi.exception;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionBadPractices {

    private static Logger logger = Logger.getLogger(ExceptionBadPractices.class.getName());

    public static void main(String[] args) {
        System.out.println(concatStr("some"));
        parseFile("some");
    }

    // lose exception from try
    private static String concatStr(String input) {
        String name = "prefix";
        try {
            throw new IOException();
        } finally {
            return name + input;
        }
    }

    // lose exception from catch block
    private static void parseFile(String filePath) {
        try {
            throw new IOException();
        } catch (IOException io) {
            throw new IllegalStateException(io);
        } finally {
            throw new RuntimeException();
        }
    }

    // don't do both logging and throwing an exception
    // it duplicates log messages
    private static void parseFileWithLog(String filePath) throws IOException {
        try {
            throw new IOException();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IOException: ", ex);
            throw ex;
        }
    }
}
