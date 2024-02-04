package org.test.problem2;

import java.io.*;
import java.util.*;

public class WordCountApplication {
    private static final int MEMORY_LIMIT = 10 * 1024 * 1024; // 10 MB
    private static final int CHUNK_SIZE = 1 * 1024; // 1 MB

    public static void main(String[] args) {
        try {
            // Generate a sample large text file (1 MB)
            generateLargeTextFile("text_file.txt", CHUNK_SIZE);

            // Word count and fuzzy search
            wordCountAndFuzzySearch("text_file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateLargeTextFile(String filename, int multiplier) throws IOException {
        // Generates a large text file for testing purposes
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string to be generated");
        String s = sc.nextLine();
        FileWriter fileWriter = new FileWriter(filename);
        for (int i = 0; i < multiplier; i++) {
//            fileWriter.write("This is a sample sentence for testing word count and fuzzy search.\n");
            fileWriter.write(s);
        }
        fileWriter.close();
    }

    private static void wordCountAndFuzzySearch(String filename) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the word to be searched");
        String str = sc.next();
        // Read the file in chunks, sort words, and merge
        List<File> sortedChunks = createSortedChunks(filename);

        // Merge sorted chunks
        List<String> sortedWords = mergeSortedChunks(sortedChunks);

        // Trie for fuzzy search
        TrieNode root = buildTrie(sortedWords);

        // Word count and fuzzy search
        printWordCountAndFuzzySearch(root, str);

        // Clean up temporary sorted chunks
        for (File file : sortedChunks) {
            file.delete();
        }
    }

    private static List<File> createSortedChunks(String filename) throws IOException {
        List<File> sortedChunks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        char[] buffer = new char[MEMORY_LIMIT];
        int bytesRead;
        int chunkNumber = 0;

        while ((bytesRead = reader.read(buffer)) != -1) {
            // Read chunk into memory
            String chunk = new String(buffer, 0, bytesRead);

            // Tokenize and sort the chunk
            String[] words = chunk.split("\\s+");
            Arrays.sort(words);

            // Write sorted chunk to a temporary file
            File sortedChunkFile = new File("chunk_file" + chunkNumber + ".txt");
            FileWriter writer = new FileWriter(sortedChunkFile);
            for (String word : words) {
                writer.write(word + "\n");
            }
            writer.close();
            sortedChunks.add(sortedChunkFile);

            chunkNumber++;
        }

        reader.close();
        return sortedChunks;
    }

    private static List<String> mergeSortedChunks(List<File> sortedChunks) throws IOException {
        PriorityQueue<BufferedReader> readers = new PriorityQueue<>(Comparator.comparingInt(o -> {
            try {
                return Integer.parseInt(o.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return -1;
        }));

        // Initialize readers with the first line from each sorted chunk
        for (File file : sortedChunks) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            readers.add(reader);
        }

        List<String> sortedWords = new ArrayList<>();

        // Merge sorted chunks
        while (!readers.isEmpty()) {
            BufferedReader reader = readers.poll();
            String word = reader.readLine();

            if (word != null) {
                sortedWords.add(word);
                readers.add(reader);
            } else {
                reader.close();
            }
        }

        return sortedWords;
    }

    private static TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insertIntoTrie(root, word);
        }
        return root;
    }

    private static void insertIntoTrie(TrieNode root, String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.getChildren().putIfAbsent(ch, new TrieNode());
            current = current.getChildren().get(ch);
        }
        current.setEndOfWord(true);
        current.count++;
    }

    private static void printWordCountAndFuzzySearch(TrieNode root, String query) {
        TrieNode current = root;
        for (char ch : query.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                System.out.println("No matching words found for word : " + query);
                return;
            }
            current = current.getChildren().get(ch);
        }

        // Performing DFS in Trie to find matching words
        searchWordsDFS(current, query);
    }

    private static void searchWordsDFS(TrieNode node, String prefixWord) {
        if (node.isEndOfWord()) {
            System.out.println("Word: " + prefixWord + " | Count: " + node.count);
        }

        //recursive operation to check every line
        for (char ch : node.getChildren().keySet()) {
            searchWordsDFS(node.getChildren().get(ch), prefixWord + ch);
        }
    }
}
