package uva.EDA.sorter1;

public class DataAnalyzer {
    private final Persona[] data;
    private static int START_DAY;
    private int showNames;
    private String userName;
    public DataAnalyzer(Persona[] data) {
        this.data = data;
        setStartDay();
    }

    public Popular[] sortPopulars(Persona[] interval) {
        Popular[] populars = new Popular[interval.length];
        for (int i = 0; i < interval.length; i++) {
            populars[i] = new Popular();
        }
        //add all different names to populars
        long time = System.nanoTime();
        for (int i = 0; i < interval.length; i++) {
            checkPopulars(interval[i].getName(), populars);
        }
        System.out.println("Elapsed time: " + (System.nanoTime() - time) * 1e-9 + " sec");

        time = System.nanoTime();
        //sort populars array
        for (int i = 0; i < populars.length-1; i++) {
            for (int j = populars.length - 1; j > i; j--) {
                if (populars[j-1].getCount() < populars[j].getCount()) {
                    Popular temp = populars[j-1];
                    populars[j-1] = populars[j];
                    populars[j] = temp;
                }
            }
        }
        System.out.println("Time to sort: " + (System.nanoTime() - time) * 1e-9 + " sec");
        //add top populars to another array
        Popular[] top = new Popular[showNames + 1];
        System.out.println("The top " + getShowNames() + " names are: ");
        for (int i = 0; i < top.length - 1; i++) {
            top[i] = populars[i];
            System.out.println(i + 1 + ". " + top[i].getName() + " with a total count of " + top[i].getCount());
        }
        //add username to top list
        for (int i = 0; i < populars.length; i++) {
            if (populars[i].getName().equals(userName)) {
                top[showNames] = populars[i];
                System.out.println(i + ". " + top[showNames].getName() + " with a total count of " + top[showNames].getCount());
                break;
            }
        }
        return top;
    }

    private void checkPopulars(String name, Popular[] populars) {
        for (int i = 0; i < populars.length; i++) {
            if (populars[i].getName().equals(name)) {
                populars[i].add();
                break;
            } else if (populars[i].getName().equals("")) {
                populars[i].setName(name);
                populars[i].setCount(1);
                break;
            }
        }
    }

    public Persona[] sortByDate(String startDate, String stopDate) {
        int dateStart = dateConverter(startDate) + START_DAY;
        int dateStop = dateConverter(stopDate) + START_DAY;
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
        System.out.println("Total count = " + intervalCount);
        Persona[] returnInterval = new Persona[intervalCount];
        for (int i = 0; i < intervalCount; i++) {
            returnInterval[i] = interval[i];
        }
        return returnInterval;
    }

    private Persona[] increaseSize(Persona[] array) {
        Persona[] temp = new Persona[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        return temp;
    }

    /**
     * Hallar el dia 1/1/1920 y hacer que sea el dia 0
     */
    private void setStartDay() {
        int min= data[0].getBirthDay();
        for (int i = 0; i<data.length; i++){
            if(min > data[i].getBirthDay()) {
                min = data[i].getBirthDay();
            }
        }
        START_DAY = min;
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
}
