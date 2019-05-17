package model.entity;

import java.util.List;

public class Menu {
    private Integer id;
    private String nameEn;
    private String nameUa;
    private Integer price;
    private List<Order> orders;

    public Menu(String nameEn, String nameUa, Integer price) {
        this.nameEn = nameEn;
        this.nameUa = nameUa;
        this.price = price;
    }

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }
}
