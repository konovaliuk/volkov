package training.ua.dao.entities;

import java.sql.Timestamp;

public class Order {

    private Long id;
    private Timestamp orderDate;
    private Boolean isPaid;
    private Timestamp payDay;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Timestamp getPayDay() {
        return payDay;
    }

    public void setPayDay(Timestamp payDay) {
        this.payDay = payDay;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order [" +
                "id = " + id +
                ", orderDate = " + orderDate +
                ", isPaid = " + isPaid +
                ", payDay = " + payDay +
                ", userId = " + userId +
                ']';
    }
}
