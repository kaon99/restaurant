package model.entity;

import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id;
    private String note;
    private List<Menu> menu;
    private Integer billId;
    private Integer userId;

    public Order() {
    }

    public Order(String note, Integer userId) {
        this.note = note;
        this.userId = userId;
    }

    public Order(String note, Integer billId, Integer userId) {
        this.note = note;
        this.billId = billId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(note, order.note) &&
                Objects.equals(menu, order.menu) &&
                Objects.equals(billId, order.billId) &&
                Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note, menu, billId, userId);
    }
}
