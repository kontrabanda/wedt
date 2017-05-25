package data.filereader;


import java.io.File;

public class FileReader {
    private final String PATH_TO_DOCUMENTS = "./data/pagestorage_anno/";
    private FileReaderAction fileReaderAction;

    public FileReader(FileReaderAction fileReaderAction) {
        this.fileReaderAction = fileReaderAction;
    }

    public void readData() {
        File[] directories = getDirectories();

        for(File dir: directories) {
            getFilesFromDirectories(dir);
        }
    }

    private File[] getDirectories() {
        return new File(PATH_TO_DOCUMENTS).listFiles(File::isDirectory);
    }

    private void getFilesFromDirectories(File dir) {
        File[] filesInDirectories = dir.listFiles((file, name) -> name.toLowerCase().endsWith(".html"));

        for(File file: filesInDirectories) {
            FileReaderData fileReaderData = new FileReaderData();
            fileReaderData.file = file;

            fileReaderAction.readingData(fileReaderData);
        }
    }
}
