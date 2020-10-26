package uva.EDA.sorter1;

import java.io.IOException;

public class Sorter {
    private static Persona[] data;
    private static Popular[] populars;

    private static DataAnalyzer dataAnalyzer;

    public static void main(String[] args) throws IOException {
        DataReader dataReader = new DataReader("C:/Users/mnky6/Documents/Clase/EDA/personas_va/personas_va.txt");
        data = dataReader.readData();
        dataAnalyzer = new DataAnalyzer(data);
        initPopulars();
        addToPopulars();
        //printTop(10);
        Persona[] interval = dataAnalyzer.sortByDate("12/4/1973", "20/5/1980");
        printArray(interval);
    }

    public static void initPopulars() {
        populars = new Popular[data.length];
        for (int i = 0; i < populars.length; i++) {
            populars[i] = new Popular();
        }
    }

    public static void addToPopulars() {
        for(int i = 0; i < data.length; i++) {
            addName(data[i].getName());
        }
        dataAnalyzer.sortPopulars(populars);
    }

    public static void addName(String name) {
        for(int i = 0; i< populars.length; i++){
            if (name.equals(populars[i].getName())) {
                populars[i].add();
                return;
            } else if (populars[i].getName().equals("")) {
                populars[i].setName(name);
                populars[i].setCount(1);
                return;
            }
        }

    }
    public static void printTop(int top) {
        System.out.println("The top " + top + " names are: ");
        for (int i = 0; i < top; i++){
            System.out.println(i + 1 + ". " + populars[i].getName() + " with a total count of " + populars[i].getCount());
        }
    }

    public static void printArray(Persona[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
    }
}
