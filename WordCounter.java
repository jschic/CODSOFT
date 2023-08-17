import java.util.Scanner;
import java.io.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = "";

        System.out.println("Choose 'T' for text input or 'F' for file input:");
        String userInput = scanner.nextLine().trim();

        if (userInput.equalsIgnoreCase("T")) {
            System.out.println("Enter the text:");
            inputText = scanner.nextLine();
        } else if (userInput.equalsIgnoreCase("F")) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine().trim();
            try {
                inputText = readTextFromFile(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid choice!");
            System.exit(0);
        }

        int wordCount = countWords(inputText);
        System.out.println("Total word count: " + wordCount);
    }

    public static int countWords(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        String[] words = text.split("[\\p{Punct}\\s]+");

        int Count = 0;

        for (String word : words) {
            if (!word.isEmpty()) {
                Count++;
            }
        }

        return Count;
    }

    public static String readTextFromFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            content.append(fileScanner.nextLine()).append(" ");
        }

        fileScanner.close();
        return content.toString();
    }
}
