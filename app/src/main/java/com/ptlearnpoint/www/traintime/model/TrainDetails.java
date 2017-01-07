package com.ptlearnpoint.www.traintime.model;

import java.io.Serializable;

/**
 * Created by PT on 1/6/2017.
 */

public class TrainDetails implements Serializable {
    private String trainName;
    private String trainCode;

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    private String trainTime;
    private String offDay ;
    private int ticketPrice;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public TrainDetails(String trainName, String trainCode,String trainTime, String offDay, int ticketPrice) {
        this.trainName = trainName;
        this.trainCode = trainCode;
        this.trainTime = trainTime;
        this.offDay = offDay;
        this.ticketPrice = ticketPrice;

    }
}
