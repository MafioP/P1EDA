package uva.EDA.sorter1;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private static DataAnalyzer dataAnalyzer;
    private static DataReader dataReader;
    private final Scanner scanner;

    public UserInterface() throws IOException {
        scanner = new Scanner(System.in);
    }

    public void init() throws IOException {
        System.out.println("Introduzca el nombre del fichero: ");
        String filename = scanner.nextLine();
        dataReader = new DataReader("resources/" + filename);
        Persona[] data = dataReader.readData();
        dataAnalyzer = new DataAnalyzer(data);
        System.out.println("Numero de nombres a mostrar: ");
        dataAnalyzer.setShowNames(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Nombre del usuario: ");
        dataAnalyzer.setUserName(scanner.nextLine());
        System.out.println("Empezando la busqueda...");
        startQueryLoop();
    }

    private void startQueryLoop() {
        while (true) {
            System.out.println("Introduzca el intervalo de fechas de nacimiento: ");
            String dateInterval = scanner.nextLine();
            System.out.println();
            String[] dates = dataReader.splitDateInput(dateInterval);
            Persona[] interval = dataAnalyzer.sortByDate(dates[0], dates[1]);
            Popular[] populars = dataAnalyzer.managePopulars(interval);
            printTop(populars);
            printResults();
            System.out.println("Continuar? (S/N) ");
            if (scanner.nextLine().equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    private void printTop(Popular[] populars) {
        for (int i = 0; i < populars.length-1; i++) {
            System.out.println((i+1) + ": " + populars[i].getName() + " con un total de " + populars[i].getCount());;
        }
        System.out.println("Nombre de usuario: \n" + populars[populars.length-1].getName() + " con un total de " +
                populars[populars.length-1].getCount());
    }

    private void printResults() {
        System.out.println("*********** RESULTADOS ***********");
        DataResults dataResults = dataAnalyzer.getDataResults();
        System.out.println("Personas en el intervalo: " + dataResults.getIntervalSize());
        System.out.println("Nombres distintos en el intervalo: " + dataResults.getTotalNames());
        System.out.println("Dias en el intervalo: " + dataResults.getIntervalDays());

        TimeResults results = dataAnalyzer.getTimeResults();
        System.out.println("Time to get interval: " + results.getExtractIntervalTime() + " sec");
        System.out.println("Time to sort populars: " + results.getPopularSortTime() + " sec");
        System.out.println("Time to get top populars: " + results.getAddToTopTime() + " sec");
        System.out.println("Time to find username: " + results.getFindUsernameTime() + " sec");
    }
}
