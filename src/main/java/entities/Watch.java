package entities;

import java.util.Objects;

public class Watch {
    private int id;
    private String mark;
    private String type;
    private Vendor vendor;

    public Watch(int id, String mark, String type, Vendor vendor) {
        this.id = id;
        this.mark = mark;
        this.type = type;
        this.vendor = vendor;
    }

    public Watch(int id, String mark, String type) {
        this.id = id;
        this.mark = mark;
        this.type = type;
        this.vendor = null;
    }

    public Watch() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Watch)) return false;
        Watch watch = (Watch) o;
        return getId() == watch.getId() &&
                Objects.equals(getMark(), watch.getMark()) &&
                Objects.equals(getType(), watch.getType()) &&
                Objects.equals(getVendor(), watch.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMark(), getType(), getVendor());
    }

    @Override
    public String toString() {
        return "Watch{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", type='" + type + '\'' +
                ", vendor=" + vendor +
                '}';
    }
}
