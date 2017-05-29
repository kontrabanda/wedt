package data.filewriter;


import java.io.*;

public class FileWriter {
    public void writeData(FileWriterData fileWriterData) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileWriterData.path + "/" + fileWriterData.filename), "utf-8"))) {
            writer.write(fileWriterData.data);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
