package uva.EDA.sorter1;

public class DataResults {
    private int intervalSize;
    private int totalNames;
    private int intervalDays;
    private int filterComps;
    private int insertionComps;
    private int binaryInsertionComps;
    private int extractionComps;
    private int userNameComps;
    private int filterMoves;
    private int insertionMoves;
    private int binaryInsertionMoves;
    private int extractionMoves;
    private int userNameMoves;


    public int getFilterComps() {
        return filterComps;
    }

    public void setFilterComps(int filterComps) {
        this.filterComps = filterComps;
    }

    public int getInsertionComps() {
        return insertionComps;
    }

    public void setInsertionComps(int insertionComps) {
        this.insertionComps = insertionComps;
    }

    public void addInsertionComps(int add) {
        insertionComps += add;
    }

    public int getBinaryInsertionComps() {
        return binaryInsertionComps;
    }

    public void setBinaryInsertionComps(int binaryInsertionComps) {
        this.binaryInsertionComps = binaryInsertionComps;
    }

    public void addBinaryInsertionComps() {
        binaryInsertionComps++;
    }

    public int getExtractionComps() {
        return extractionComps;
    }

    public void setExtractionComps(int extractionComps) {
        this.extractionComps = extractionComps;
    }

    public void addExtractionComps(int add) {
        extractionComps+= add;
    }

    public int getUserNameComps() {
        return userNameComps;
    }

    public void setUserNameComps(int userNameComps) {
        this.userNameComps = userNameComps;
    }

    public int getFilterMoves() {
        return filterMoves;
    }

    public void setFilterMoves(int filterMoves) {
        this.filterMoves = filterMoves;
    }

    public int getInsertionMoves() {
        return insertionMoves;
    }

    public void setBinaryInsertionMoves(int binaryInsertionMoves) {
        this.binaryInsertionMoves = binaryInsertionMoves;
    }

    public int getBinaryInsertionMoves() {
        return binaryInsertionMoves;
    }

    public void addBinaryInsertionMoves() {
        binaryInsertionMoves++;
    }

    public void setInsertionMoves(int insertionMoves) {
        this.insertionMoves = insertionMoves;
    }

    public void addInsertionMoves() {
        insertionMoves++;
    }

    public int getExtractionMoves() {
        return extractionMoves;
    }

    public void setExtractionMoves(int extractionMoves) {
        this.extractionMoves = extractionMoves;
    }

    public void addExtractionMoves(int add) {
        extractionMoves += add;
    }

    public int getUserNameMoves() {
        return userNameMoves;
    }

    public void setUserNameMoves(int userNameMoves) {
        this.userNameMoves = userNameMoves;
    }

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
