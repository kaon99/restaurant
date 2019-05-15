package model.entity;


import java.sql.Date;

public class Bill {
    private Integer id;
    private Date date;
    private Integer sum;
    private Integer status;
    private Integer userId;
    private Integer orderId;

    public Bill(Date date, Integer sum, Integer status, Integer userId, Integer orderId) {
        this.date = date;
        this.sum = sum;
        this.status = status;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
