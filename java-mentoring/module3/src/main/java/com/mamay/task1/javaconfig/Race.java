package com.mamay.task1.javaconfig;

import java.time.LocalDateTime;
import java.util.List;

public class Race extends IdEntity {

    private String place;
    private LocalDateTime time;
    private List<Horse> horses;
    private int length;

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

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Race [id=" + getId() + ", place=" + place + ", time=" + time + ", length=" + length + "]");
        if (horses != null) {
            for (Horse horse : horses) {
                builder.append("\n\t" + horse);
            }
        }
        return builder.toString();
    }
}
