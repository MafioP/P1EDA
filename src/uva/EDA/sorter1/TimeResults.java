package uva.EDA.sorter1;

public class TimeResults {

    private long extractIntervalTime;
    private long findPopularsTime;
    private long findPopularsBinaryTime;
    private long popularSortTime;
    private long findUsernameTime;

    public double getExtractIntervalTime() {
        return extractIntervalTime * 1e-9;
    }

    public void setExtractIntervalTime(long extractIntervalTime) {
        this.extractIntervalTime = extractIntervalTime;
    }

    public void setFindPopularsTime(long findPopularsTime) {
        this.findPopularsTime = findPopularsTime;
    }

    public double getFindPopularsTime() {
        return findPopularsTime * 1e-9;
    }

    public void setFindPopularsBinaryTime(long popularBinarySortTime) {
        this.findPopularsBinaryTime = popularBinarySortTime;
    }

    public double getFindPopularsBinaryTime() {
        return findPopularsBinaryTime * 1e-9;
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
}