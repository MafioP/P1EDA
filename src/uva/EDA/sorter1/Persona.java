package uva.EDA.sorter1;

public class Persona {
    private String name;
    private int gender;
    private int birthDay;
    private int deathDay;

    public Persona(String name, int gender, int birthDay, int deathDay){
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.deathDay = deathDay;
    }

    public Persona(){

    }

    public void parseData(String data) {
        String[] split = data.split(" ");
        birthDay = Integer.parseInt(split[0]);
        deathDay = Integer.parseInt(split[1]);
        gender = Integer.parseInt(split[2]);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setDeathDay(int deathDay) {
        this.deathDay = deathDay;
    }

    public int getDeathDay() {
        return deathDay;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDay=" + birthDay +
                ", deathDay=" + deathDay +
                '}';
    }
}
