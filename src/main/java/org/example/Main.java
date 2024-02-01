package org.example;

import static org.example.FileModifier.writeFile;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

        writeFile();
    }

}
