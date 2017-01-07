package com.ptlearnpoint.www.traintime.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PT on 1/6/2017.
 */

public class Place implements Serializable {
    private String from ;
    private String to ;
    private int distance;
    private int placeImageId ;
    private ArrayList<TrainDetails> trainDetailsList;

    public ArrayList<TrainDetails> getTrainDetailsList() {
        return trainDetailsList;
    }

    public void setTrainDetailsList(ArrayList<TrainDetails> trainDetailsList) {
        this.trainDetailsList = trainDetailsList;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPlaceImageId() {
        return placeImageId;
    }

    public void setPlaceImageId(int placeImageId) {
        this.placeImageId = placeImageId;
    }

    public Place(String from, String to,  int distance,int placeImageId, ArrayList<TrainDetails> trainDetailsList) {
        this.from = from;
        this.to = to;
        this.placeImageId = placeImageId;
        this.distance = distance;
        this.trainDetailsList = trainDetailsList;
    }
}
