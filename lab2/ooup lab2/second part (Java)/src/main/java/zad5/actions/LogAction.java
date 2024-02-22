package zad5.actions;

import zad5.Listener;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogAction implements Listener {
    @Override
    public void update(List<Integer> sequence) {
        try {
            FileWriter writer = new FileWriter("zad5logs.txt", true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            for (Integer num : sequence) {
                writer.write(num + " ");
            }
            writer.write("[" + dtf.format(now) + "]\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
