package org.test.problem1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VesionFile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String baseVersion;
    private List<String> deltas;

    public VesionFile(String baseVersion) {
        this.baseVersion = baseVersion;
        this.deltas = new ArrayList<>();
    }

    public String getCurrentVersion() {
        StringBuilder content = new StringBuilder(baseVersion);
        for (String delta : deltas) {
            applyDelta(content, delta);
        }
        return content.toString();
    }

    public void addDelta(String delta) {
        deltas.add(delta);
    }

    private void applyDelta(StringBuilder content, String delta) {
        content.append(delta);
    }

    public void serializeToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static VesionFile deserializeFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (VesionFile) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
