package zad5;

import zad5.actions.AverageAction;
import zad5.actions.LogAction;
import zad5.actions.MedianAction;
import zad5.actions.SumAction;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        KeyboardSource ks = new KeyboardSource();
        SlijedBrojeva sb = new SlijedBrojeva(ks);

//        FileSource fs = new FileSource("testfile.txt");
//        SlijedBrojeva sb = new SlijedBrojeva(fs);

        sb.addAction(new SumAction());
        sb.addAction(new AverageAction());
        sb.addAction(new MedianAction());
        sb.addAction(new LogAction());

        sb.kreni();
    }
}
