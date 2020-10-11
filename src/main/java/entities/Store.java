package entities;

import java.util.Objects;

public class Store {
    private int id;
    private int price;
    private int quantity;
    private String date;
    private boolean actual;
    private Watch watch;

    public Store(int id, int price, int quantity, String date, boolean actual, Watch watch) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.actual = actual;
        this.watch = watch;
    }

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return getId() == store.getId() &&
                isActual() == store.isActual() &&
                Objects.equals(getPrice(), store.getPrice()) &&
                Objects.equals(getQuantity(), store.getQuantity()) &&
                Objects.equals(getDate(), store.getDate()) &&
                Objects.equals(getWatch(), store.getWatch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getQuantity(), getDate(), isActual(), getWatch());
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", date='" + date + '\'' +
                ", actual=" + actual +
                ", watch=" + watch +
                '}';
    }
}
