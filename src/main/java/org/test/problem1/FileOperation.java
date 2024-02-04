package org.test.problem1;

import java.io.*;

public class FileOperation {
    public static void main(String[] args) {
        // Create a new versioned file
        VesionFile versionedFile = new VesionFile("Base content.");

        // Add deltas to create versions
        versionedFile.addDelta(" Version 1.");
        versionedFile.addDelta(" Version 2.");
        versionedFile.addDelta(" Version 3.");

        // Serialize and save the versioned file to a single file
        versionedFile.serializeToFile("my_versioned_file.txt");

        // Deserialize and load the versioned file from the file
        VesionFile loadedFile = VesionFile.deserializeFromFile("my_versioned_file.txt");

        // Get the content of any version
        if (loadedFile != null) {
            String version2Content = loadedFile.getCurrentVersion();
            System.out.println("Content of Version 2: " + version2Content);
        }
    }
}
