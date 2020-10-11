package entities;

import java.util.Objects;

public class Vendor {
    private int id;
    private String name;
    private Country country;

    public Vendor(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Vendor(int id, String name) {
        this(id, name, null);
    }

    public Vendor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendor)) return false;
        Vendor vendor = (Vendor) o;
        return getId() == vendor.getId() &&
                Objects.equals(getName(), vendor.getName()) &&
                Objects.equals(getCountry(), vendor.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry());
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}