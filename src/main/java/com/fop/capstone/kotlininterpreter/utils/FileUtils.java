package com.fop.capstone.kotlininterpreter.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private FileUtils() {
    }

    /**
     * Checks if a file exists at the given path.
     *
     * @param path the path to the file.
     * @return true if the file exists, false otherwise.
     */
    public static boolean fileExists(String path) {
        return path != null && !path.isEmpty() && Files.exists(Path.of(path));
    }

    /**
     * Reads the entire content of a file from the specified stringPath and returns it as a String.
     *
     * @param stringPath the stringPath of the file to be read.
     * @return the content of the file as a String.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static String readFile(String stringPath) throws IOException {
        return Files.readString(Path.of(stringPath));
    }

    /**
     * Reads the entire content of a file from the specified path and returns it as a String.
     *
     * @param path the path of the file to be read.
     * @return the content of the file as a String.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static String readFile(Path path) throws IOException {
        return Files.readString(path);
    }
}