package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.example.WebReader.getLine;

public class FileModifier {

    public static void writeFile() {
        String inputFile = "D:\\Ips\\input.txt";   // Replace with your input file path
        String outputFile = "D:\\Ips\\output.txt"; // Replace with your output file path

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String extraInfo = getLine(line);
                writer.write(extraInfo);
                writer.newLine();
            }
            System.out.println("Operation completed successfully.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


}
