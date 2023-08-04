package internshiptask.pickers;

import java.time.Duration;
import java.time.LocalTime;

public class Picker implements Comparable<Picker>{
    private String pickerId;
    private LocalTime pickingStart;
    private LocalTime pickingEnd;


    public Picker(String pickerId, LocalTime pickingStart, LocalTime pickingEnd) {
        this.pickerId = pickerId;
        this.pickingStart = pickingStart;
        this.pickingEnd = pickingEnd;
    }
    public Picker() {
    }

    public String getPickerId() {
        return pickerId;
    }

    public void setPickerId(String pickerId) {
        this.pickerId = pickerId;
    }

    public LocalTime getPickingStart() {
        return pickingStart;
    }

    public void setPickingStart(LocalTime pickingStart) {
        this.pickingStart = pickingStart;
    }

    public LocalTime getPickingEnd() {
        return pickingEnd;
    }

    public void setPickingEnd(LocalTime pickingEnd) {
        this.pickingEnd = pickingEnd;
    }
    public void nextTask(LocalTime start, String orderId, Duration pickingTime) {
        if (start.isBefore(getPickingEnd())) {
            System.out.println(getPickerId()+" "+orderId+" "+start);
            setPickingStart(start.plusMinutes(pickingTime.toMinutes()));
        }
    }

    @Override
    public int compareTo(Picker picker) {
        int result;
        if (this.getPickingStart().isBefore(picker.getPickingStart())) {
            result=-1;
        } else if (this.getPickingStart().isAfter(picker.getPickingStart())) {
            result=1;
        } else {
            result=0;
        }

        return result;
    }
}
