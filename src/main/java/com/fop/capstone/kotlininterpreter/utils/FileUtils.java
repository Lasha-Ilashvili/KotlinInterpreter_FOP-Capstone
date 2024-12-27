package com.fop.capstone.kotlininterpreter.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private FileUtils() {
    }

    /**
     * Reads the entire content of a file from the specified path and returns it as a String.
     *
     * @param path the path of the file to be read.
     * @return the content of the file as a String.
     * @throws IllegalArgumentException if the path is null or empty.
     * @throws IOException              if an I/O error occurs while reading the file.
     */
    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}