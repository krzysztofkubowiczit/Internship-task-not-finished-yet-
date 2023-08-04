package internshiptask;


import internshiptask.orders.Order;
import internshiptask.orders.OrderParser;
import internshiptask.pickers.Picker;
import internshiptask.pickers.PickerParser;
import internshiptask.schedule.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Order> orderList = new ArrayList<>();
        List<Picker> pickerList = new ArrayList<>();
        OrderParser orderParser = new OrderParser();
        orderParser.parseOrder(orderList);
        PickerParser pickerParser = new PickerParser();
        pickerParser.parsePicker(pickerList);
        Schedule schedule = new Schedule();
        schedule.scheduler(orderList,pickerList);

    }
}