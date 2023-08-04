package internshiptask.schedule;


import internshiptask.orders.Order;
import internshiptask.pickers.Picker;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class Schedule {
    private String pickerID;
    private String orderID;
    private LocalTime pickingStart;

    public Schedule(String pickerID, String orderID, LocalTime pickingStart) {
        this.pickerID = pickerID;
        this.orderID = orderID;
        this.pickingStart = pickingStart;
    }

    public Schedule() {
    }

    public String getPickerID() {
        return pickerID;
    }

    public void setPickerID(String pickerID) {
        this.pickerID = pickerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalTime getPickingStart() {
        return pickingStart;
    }

    public void setPickingStart(LocalTime pickingStart) {
        this.pickingStart = pickingStart;
    }
    public void scheduler(List<Order> orderList, List<Picker> pickerList){
        LocalTime currentTime;
        int numberOfOrders=orderList.size();
        for (int i = 0; i < numberOfOrders; i++) {
            Collections.sort(pickerList);
            for (int j = 0; j < orderList.size(); j++)
                orderList.get(j).updateTimeToGo(orderList.get(j),pickerList);

            Collections.sort(orderList);
            if(freePickersLeft(pickerList)==true) {
                if(OrderIsActive(pickerList,orderList)) {
                    System.out.println(pickerList.get(0).getPickerId() + " " + orderList.get(0).getOrderId() + " "
                            + pickerList.get(0).getPickingStart());
                    currentTime = pickerList.get(0).getPickingStart().plusMinutes(orderList.get(0).getPickingTime().toMinutes());
                    pickerList.get(0).setPickingStart(currentTime);
                    orderList.remove(orderList.get(0));

                }
            } else break;


        }
    }
    public boolean freePickersLeft(List<Picker> pickerList){

        int j=0;
        if(pickerList.get(j).getPickingStart().isAfter(pickerList.get(j).getPickingEnd()))
            return false;
        else
            return true;
    }
    public void chooseToGo(List<Order> orderList){

    }
    public boolean OrderIsActive(List<Picker> pickerList,List<Order> orderList){
        LocalTime start=pickerList.get(0).getPickingStart();
        LocalTime end=orderList.get(0).getCompleteBy();
        Duration pickingTime=orderList.get(0).getPickingTime();
        if (start.plusMinutes(pickingTime.toMinutes()).isAfter(end)) {
            orderList.remove(orderList.get(0));
            return false;
        }
        else return true;
    }
}
