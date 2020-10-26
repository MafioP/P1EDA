package uva.EDA.sorter1;

public class Popular {
    private String name;
    private int count;

    public Popular() {
        this.name = "";
        this.count = 0;
    }
    public void add() {
        count++;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Popular{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
