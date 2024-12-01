package src.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {

    public static final List<String> readFileLines(String filepath) {
        List<String> readLines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filepath))){
            while (scanner.hasNextLine()) {
                readLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return readLines;
    }
}
