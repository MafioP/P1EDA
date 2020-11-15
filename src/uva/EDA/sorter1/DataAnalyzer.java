package uva.EDA.sorter1;

public class DataAnalyzer {

    private static final String EMPTY_NAME = "}";       //la llave es un caracter mayor que cualquier letra en ascii

    private final Persona[] data;
    private int showNames;
    private String userName;
    private final TimeResults timeResults;
    private final DataResults dataResults;

    public DataAnalyzer(Persona[] data) {
        this.data = data;
        timeResults = new TimeResults();
        dataResults = new DataResults();
    }

    public Popular[] managePopulars(Persona[] interval) {
        dataResults.setIntervalSize(interval.length);
        long time = System.nanoTime();

        Popular[] populars = new Popular[10];       //Partially filled array
        for (int i = 0; i < populars.length; i++) {
            populars[i] = new Popular();
        }
        //add all different names to populars sequential form
        dataResults.setInsertionComps(0);
        for (int i = 0; i < interval.length; i++) {
            if (!populars[populars.length-1].getName().equals("")) {
                populars = increaseSize(populars);
            }
            checkPopulars(interval[i].getName(), populars);
        }
        timeResults.setFindPopularsTime(System.nanoTime() - time);

        //add all different names to populars binary form
        time = System.nanoTime();
        Popular[] populars2 = new Popular[10];       //Partially filled array
        for (int i = 0; i < populars2.length; i++) {
            populars2[i] = new Popular(EMPTY_NAME, 0);     //uso de EMPTY_NAME dado que tiene un valor ascii mayor que las letras
        }
        //populars2[0].setName(interval[0].getName());
        //populars2[0].setCount(1);
        dataResults.setBinaryInsertionComps(0);
        for (int i = 0; i < interval.length; i++) {
            if (!populars2[populars2.length-2].getName().equals(EMPTY_NAME)) {
                populars2 = increaseSize2(populars2);
            }
            checkPopularsBinary(interval[i].getName(), populars2, 0, populars2.length-2);
        }

        timeResults.setFindPopularsBinaryTime(System.nanoTime() - time);

        //sort populars array with quicksort algorithm
        time = System.nanoTime();
        sortPopulars(populars);
        timeResults.setPopularSortTime(System.nanoTime() - time);
        //add top populars to another array
        Popular[] top = findTopPopulars(populars);
        //add username to top list
        time = System.nanoTime();
        for (int i = 0; i < populars.length; i++) {
            if (populars[i].getName().equalsIgnoreCase(userName)) {
                dataResults.setUserNameComps(i);
                populars[i].setName(i+1 + ": " + userName);
                top[showNames] = populars[i];
                break;
            }
            if (i == populars.length-1) {
                userName = "notFound";
            }
        }
        timeResults.setFindUsernameTime(System.nanoTime() - time);
        return top;
    }

    private int checkPopularsBinary(String name, Popular[] populars, int ini, int end) {
        int mid = (ini + end) / 2;
        if (end >= 0) {
            dataResults.addBinaryInsertionComps();
            if (populars[mid].getName().equals(EMPTY_NAME)) {
                // vacio
                dataResults.addBinaryInsertionComps();
                if (mid == 0) {
                    dataResults.addBinaryInsertionComps();
                    // si la posicion 0 esta vacia insertar el primer nombre y acaba
                    insert(name, populars, mid);
                    return 0;
                } else {
                    // volvemos a buscar hacia la izquierda
                    return checkPopularsBinary(name, populars, ini, mid);
                }
            } else {
                // hay un nombre, comparamos:
                if (populars[mid].getName().equals(name)) {
                    dataResults.addBinaryInsertionComps();
                    // si encuentra el nombre suma 1 y acaba
                    populars[mid].add();
                    return 0;
                } else if (populars[mid].getName().compareTo(name) > 0) {
                    dataResults.addBinaryInsertionComps();
                    // nombre posterior
                    if (mid == 0 || populars[mid - 1].getName().compareTo(name) < 0) {
                        dataResults.addBinaryInsertionComps();
                        // estamos al inicio o el nombre anterior ya es menor, desplazamos e insertamos
                        displace(populars, mid);
                        insert(name, populars, mid);
                        return 0;
                    }
                    // buscamos de nuevo hacia la izquierda
                    return checkPopularsBinary(name, populars, ini, mid);
                } else if (populars[mid].getName().compareTo(name) < 0) {
                    dataResults.addBinaryInsertionComps();
                    // nombre anterior
                    if (populars[mid + 1].getName().equals(EMPTY_NAME)) {
                        dataResults.addBinaryInsertionComps();
                        // estamos al limite de nombres, insertamos a la derecha
                        insert(name, populars, mid + 1);
                        return 0;
                    }
                    // buscamos de nuevo hacia la derecha
                    return checkPopularsBinary(name, populars, mid, end);
                }
            }
        }
        return 0;
    }

    private void insert(String name, Popular[] populars, int index) {
        populars[index].setName(name);
        populars[index].setCount(1);
    }

    private void displace(Popular[] populars, int ini) {
        int end = populars.length-1;
        for (int i = end - 1; i >= ini; i--) {
            populars[i+1] = populars[i];
        }
        populars[ini] = new Popular();
    }

    private Popular[] findTopPopulars(Popular[] populars) {
       Popular[] top = new Popular[showNames + 1];
        for (int i = 0; i < top.length - 1; i++) {
            top[i] = populars[i];
        }
        return top;
    }

    private Popular[] increaseSize(Popular[] array) {
        Popular[] temp = new Popular[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        for (int i = array.length; i < temp.length; i++) {
            temp[i] = new Popular();
        }
        return temp;
    }
    private Popular[] increaseSize2(Popular[] array) {
        Popular[] temp = new Popular[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        for (int i = array.length; i < temp.length; i++) {
            temp[i] = new Popular(EMPTY_NAME, 0);
        }
        return temp;
    }

    private void checkPopulars(String name, Popular[] populars) {
        for (int i = 0; i < populars.length; i++) {
            if (populars[i].getName().equals(name)) {
                populars[i].add();
                break;
            } else if (populars[i].getName().equals("")) {
                populars[i].setName(name);
                populars[i].setCount(1);
                dataResults.setTotalNames(dataResults.getTotalNames() + 1);
                break;
            }
            dataResults.addInsertionComps(2);
        }
    }

    private void sortPopulars(Popular[] populars) {
        dataResults.setExtractionComps(0);
        sortPopularsRec(populars, 0, populars.length-1);
    }

    private void sortPopularsRec(Popular[] populars, int ini, int end) {
        if (ini >= end) {
            return;
        }
        int lim = partition(populars, ini, end);
        if (ini < lim - 1) {
            sortPopularsRec(populars, ini, lim-1);
        }
        if (end > lim){
            sortPopularsRec(populars, lim, end);
        }
        dataResults.addExtractionComps(3);
    }

    private int partition(Popular[] populars, int ini, int end) {
        int lim = populars[ini].getCount();      //pivot
        while (ini <= end) {
            dataResults.addExtractionComps(1);
            while(populars[ini].getCount() > lim) {
                dataResults.addExtractionComps(1);
                ini++;
            }
            while (populars[end].getCount() < lim) {
                dataResults.addExtractionComps(1);
                end--;
            }
            if (ini <= end) {
                Popular temp = populars[ini];
                populars[ini] = populars[end];
                populars[end] = temp;

                ini++;
                end--;
            }
            dataResults.addExtractionComps(1);
        }
        return ini;
    }

    public Persona[] sortByDate(String startDate, String stopDate){
        long time = System.nanoTime();
        int dateStart = dateConverter(startDate);
        int dateStop = dateConverter(stopDate);
        dataResults.setIntervalDays(dateStop - dateStart);
        Persona[] interval = new Persona[2];        //Partially filled array (2x its size once full)
        int intervalCount = 0;
        for (int i = 0; i < data.length; i++) {
            if (intervalCount == interval.length - 1) {
                interval = increaseSize(interval);
            }
            if (data[i].getBirthDay() > dateStart && data[i].getBirthDay() < dateStop) {
                interval[intervalCount] = data[i];
                intervalCount++;
            }
            dataResults.setFilterComps(data.length);
            dataResults.setFilterMoves(0);
        }
        Persona[] returnInterval = new Persona[intervalCount];
        for (int i = 0; i < intervalCount; i++) {
            returnInterval[i] = interval[i];
        }
        timeResults.setExtractIntervalTime(System.nanoTime() - time);
        return returnInterval;
    }

    private Persona[] increaseSize(Persona[] array) {
        Persona[] temp = new Persona[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        return temp;
    }


    public int dateConverter(String date) {
        String[] split = date.split("/");
        int days = Integer.parseInt(split[0]);
        int months = Integer.parseInt(split[1]);
        int years = Integer.parseInt(split[2]);
        return 367*years - (7*(years+5001+(months-9)/7))/4 + (275*months)/9 + days - 692561;
    }

    public void setShowNames(int showNames) {
        this.showNames = showNames;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getShowNames() {
        return showNames;
    }

    public String getUserName() {
        return userName;
    }

    public TimeResults getTimeResults() {
        return timeResults;
    }

    public DataResults getDataResults() {
        return dataResults;
    }
}
