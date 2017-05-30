package data.filewriter;


import java.io.*;

public class FileWriter {
    public void writeData(FileWriterData fileWriterData) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileWriterData.path + "/" + fileWriterData.filename, true), "utf-8"))) {
            writer.write("\n*****************************" + fileWriterData.readFileName + "*****************************\n");
            writer.write(fileWriterData.data);
            writer.write("*****************************END*****************************\n");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
