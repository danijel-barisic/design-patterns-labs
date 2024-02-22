package zad5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileSource implements SourceStrategy{
    private final BufferedReader reader;

    public FileSource(String filepath) throws IOException {
        reader = new BufferedReader(new FileReader(filepath));
    }

    @Override
    public int getNextNumber() {
        String line;
        try {
            line = reader.readLine();
            if (line == null) {
                reader.close();
                return -1;
            } else {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void close() throws IOException {
        reader.close();
    }

}
