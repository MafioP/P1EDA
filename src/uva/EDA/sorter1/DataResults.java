package uva.EDA.sorter1;

public class DataResults {
    private int intervalSize;
    private int totalNames;
    private int intervalDays;

    public DataResults() {
        intervalSize = 0;
        totalNames = 0;
        intervalDays = 0;
    }

    public int getIntervalSize() {
        return intervalSize;
    }

    public void setIntervalSize(int intervalSize) {
        this.intervalSize = intervalSize;
    }

    public int getTotalNames() {
        return totalNames;
    }

    public void setTotalNames(int totalNames) {
        this.totalNames = totalNames;
    }

    public int getIntervalDays() {
        return intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        this.intervalDays = intervalDays;
    }
}
