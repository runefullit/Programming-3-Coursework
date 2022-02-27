package fi.tuni.prog3.round8.xmlcountries;

public class Country implements Comparable<Country>{
    private final String name;
    private final double area;
    private final long population;
    private final double gdp;

    Country(String name, double area, long population, double gdp){
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp= gdp;
    }

    @Override
    public int compareTo(Country other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return String.format("%S%n  %f%n  %d%n  %f%n",
                getName(), getArea(), getPopulation(), getGdp());
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public double getGdp() {
        return gdp;
    }
}
