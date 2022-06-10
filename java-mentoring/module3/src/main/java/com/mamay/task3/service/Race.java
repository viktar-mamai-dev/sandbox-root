package com.mamay.task3.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.InitializingBean;

public class Race extends IdEntity implements InitializingBean {

    private String place;
    private LocalDateTime time;
    private List<Long> participants;
    private int length;

    private List<Double> positions;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Long> participants) {
        this.participants = participants;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void changePositions(int index, double value) {
        double oldValue = positions.get(index);
        positions.set(index, oldValue + value);
    }

    public boolean isFinished() {
        boolean flag = false;
        for (int i = 0; i < positions.size() && !flag; i++) {
            if (positions.get(i) >= length) {
                flag = true;
            }
        }
        return flag;
    }

    public List<Double> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Race [id=" + getId() + ", place=" + place + ", time=" + time + ", length=" + length + "]");
        return builder.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        positions = new ArrayList<>();
        IntStream.range(0, participants.size()).forEach(i -> positions.add(Double.valueOf(0)));
    }

    public int biggestPosition() {
        double max = 0;
        int indexForMax = 0;
        for (int i = 0; i < positions.size(); i++) {
            double position = positions.get(i);
            if (position > max) {
                max = position;
                indexForMax = i;
            }
        }
        return indexForMax;
    }
}
