package internshiptask.orders;

import internshiptask.pickers.Picker;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Order implements Comparable<Order> {

    private String orderId;
    private BigDecimal orderValue;

    private LocalTime completeBy;
    private Duration pickingTime;
    private Duration timeToGo;

    public Order(String orderId, BigDecimal orderValue, LocalTime completeBy, Duration pickingTime, Duration timeToGo) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.completeBy = completeBy;
        this.pickingTime = pickingTime;
        this.timeToGo=timeToGo;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {

        this.orderId = orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public void setCompleteBy(LocalTime completeBy) {
        this.completeBy = completeBy;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(Duration pickingTime) {
        this.pickingTime = pickingTime;
    }

    public Duration getTimeToGo() {
        return timeToGo;
    }

    public void setTimeToGo(Duration timeToGo) {
        this.timeToGo = timeToGo;
    }

    public void updateTimeToGo(Order order,List<Picker> pickerList){


        LocalTime completeBy=order.getCompleteBy();
        LocalTime pickingStart=pickerList.get(0).getPickingStart();
        Duration pickingTime=order.getPickingTime();
        order.setTimeToGo(Duration.between(completeBy ,(pickingStart.plusMinutes(pickingTime.toMinutes()))));


    }
    @Override
    public int compareTo(Order order) {
        int result;
        if (order.getOrderValue().compareTo(this.getOrderValue())<0) {
            result=-1;
        } else if (order.getOrderValue().compareTo(this.getOrderValue())>0)
            result=1;
        else {
            if (this.getCompleteBy().isAfter(order.getCompleteBy()))
                result = 1;
            else if (this.getCompleteBy().isBefore(order.getCompleteBy()))
                result = -1;
            else {
                if (this.getTimeToGo().compareTo(order.getTimeToGo()) < 0)
                    result = 1;
                else if (this.getTimeToGo().compareTo(order.getTimeToGo()) > 0)
                    result = -1;
                else
                    result = 0;
            }
        }

        return result;
    }
}
