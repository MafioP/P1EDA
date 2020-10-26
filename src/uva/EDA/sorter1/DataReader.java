package uva.EDA.sorter1;

import java.io.*;

public class DataReader {
    private String directory;
    private int entryCount;
    private Persona[] data;

    public DataReader(String directory) {
        this.directory = directory;
    }

    public Persona[] readData() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(directory)));
            entryCount = Integer.parseInt(reader.readLine());
            data = new Persona[1000];
            long t0, t1, t2;
            t0 = System.nanoTime();
            for (int i = 0; i < data.length; i++) {
                data[i] = new Persona();
                data[i].parseData(reader.readLine());
                data[i].setName(reader.readLine());
                //System.out.println(data[i].toString());
            }
            t1 = System.nanoTime() - t0;
            System.out.println("Time reading data: " + t1 * 1e-9 + "sec");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
