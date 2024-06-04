package org.casyu.tinynotepad.file;

import java.io.*;

public class FileAccess {

    private String filePath;
    private File file;

    public FileAccess(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        if (file == null) {
            file = new File(filePath);
        }
        return file;
    }

    public boolean exists() {
        return getFile().exists();
    }

    public boolean isReadable() {
        return getFile().canRead();
    }

    public boolean isWritable() {
        return getFile().canWrite();
    }

    public void createNewFile() throws IOException {
        getFile().createNewFile();
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(getFile()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public void writeFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(getFile()));
        writer.write(content);
        writer.close();
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
