package uva.EDA.sorter1;

public class DataAnalyzer {
    private Persona[] data;
    public DataAnalyzer(Persona[] data) {
        this.data = data;
    }

    public void sortPopulars(Popular[] populars) {
        for(int i = 0; i < populars.length; i++) {
            for(int j = 0; j < populars.length - 1; j++) {
                if (populars[j].getCount() < populars[j+1].getCount()) {
                    String tempName = populars[j].getName();
                    int tempCount = populars[j].getCount();
                    populars[j].setName(populars[j+1].getName());
                    populars[j].setCount(populars[j+1].getCount());
                    populars[j+1].setName(tempName);
                    populars[j+1].setCount(tempCount);
                }
            }
        }
    }

    public Persona[] sortByDate(String startDate, String stopDate) {
        int dateStart = dateConverter(startDate);
        int dateStop = dateConverter(stopDate);
        System.out.println("StartDate " + dateStart + " StopDate " + dateStop);
        Persona[] temp = new Persona[data.length];
        int dataCounter = 0;
        for(int i = 0; i < temp.length; i++) {
            while(dataCounter < data.length) {
                System.out.println("Birthday: " + data[dataCounter].getBirthDay());
                if (dateStart < data[dataCounter].getBirthDay() && data[dataCounter].getBirthDay() < dateStop) {
                    temp[i] = new Persona(data[dataCounter].getName(), data[dataCounter].getGender(),
                            data[dataCounter].getBirthDay(), data[dataCounter].getDeathDay());
                    System.out.println(temp[i].toString());
                    dataCounter++;
                    break;
                }
                dataCounter++;
            }
        }
        Persona[] interval = new Persona[dataCounter];
        for (int i = 0; i < interval.length; i++) {
            interval[i] = temp[i];
        }
        System.out.println("Total count " + dataCounter);
        return interval;
    }

    private String dateConverter(int date) {

        return "";
    }

    private int dateConverter(String date) {
        String[] split = date.split("/");
        int days = Integer.parseInt(split[0]);
        int months = Integer.parseInt(split[1]);
        int years = Integer.parseInt(split[2]);
        System.out.println("D: " + days + " M: " + months + " Y: " + years);
        return 367*years - (7*(years+5001+(months-9)/7))/4 + (275*months)/9 + days - 170000;
    }
}
