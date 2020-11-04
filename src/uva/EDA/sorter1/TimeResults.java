package uva.EDA.sorter1;

public class TimeResults {

    private long readDataTime;
    private long extractIntervalTime;
    private long popularSortTime;
    private long findUsernameTime;
    private long addToTopTime;

    public long getReadDataTime() {
        return readDataTime;
    }

    public void setReadDataTime(long data) {
        readDataTime = data;
    }

    public double getExtractIntervalTime() {
        return extractIntervalTime * 1e-9;
    }

    public void setExtractIntervalTime(long extractIntervalTime) {
        this.extractIntervalTime = extractIntervalTime;
    }

    public double getPopularSortTime() {
        return popularSortTime * 1e-9;
    }

    public void setPopularSortTime(long popularSortTime) {
        this.popularSortTime = popularSortTime;
    }

    public double getFindUsernameTime() {
        return findUsernameTime * 1e-9;
    }

    public void setFindUsernameTime(long findUsernameTime) {
        this.findUsernameTime = findUsernameTime;
    }

    public double getAddToTopTime() {
        return addToTopTime * 1e-9;
    }

    public void setAddToTopTime(long addToTopTime) {
        this.addToTopTime = addToTopTime;
    }
}