package uva.EDA.sorter1;

public class DataAnalyzer {
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
        //add all different names to populars
        for (int i = 0; i < interval.length; i++) {
            if (!populars[populars.length-1].getName().equals("")) {
                populars = increaseSize(populars);
            }
            checkPopulars(interval[i].getName(), populars);
        }
        time = System.nanoTime();
        //sort populars array with quicksort algorithm
        sortPopulars(populars);
        timeResults.setPopularSortTime(System.nanoTime() - time);
        //add top populars to another array
        time = System.nanoTime();
        Popular[] top = new Popular[showNames + 1];
        System.out.println("The top " + getShowNames() + " names are: ");
        for (int i = 0; i < top.length - 1; i++) {
            top[i] = populars[i];
            System.out.println(i + 1 + ". " + top[i].getName() + " with a total count of " + top[i].getCount());
        }
        timeResults.setAddToTopTime(System.nanoTime() - time);
        //add username to top list
        time = System.nanoTime();
        for (int i = 0; i < populars.length; i++) {
            if (populars[i].getName().equals(userName)) {
                top[showNames] = populars[i];
                System.out.println(i + ". " + top[showNames].getName() + " with a total count of " + top[showNames].getCount());
                break;
            }
        }
        timeResults.setFindUsernameTime(System.nanoTime() - time);
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
        }
    }

    private void sortPopulars(Popular[] populars) {
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
    }

    private int partition(Popular[] populars, int ini, int end) {
        int lim = populars[ini].getCount();      //pivot
        while (ini <= end) {
            while(populars[ini].getCount() > lim) {
                ini++;
            }
            while (populars[end].getCount() < lim) {
                end--;
            }
            if (ini <= end) {
                Popular temp = populars[ini];
                populars[ini] = populars[end];
                populars[end] = temp;

                ini++;
                end--;
            }
        }
        return ini;
    }

    public Persona[] sortByDate(String startDate, String stopDate) {
        long time = System.nanoTime();
        int dateStart = dateConverter(startDate);
        int dateStop = dateConverter(stopDate);
        if(dateStop - dateStart < 0) {
            System.out.println("Fecha no valida");
        }
        dataResults.setIntervalDays(dateStop - dateStart);
        System.out.println("StartDate " + dateStart + " StopDate " + dateStop);
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
        System.out.println(" D: " + days + " M: " + months + " Y: " + years);
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
