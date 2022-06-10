package com.mamay.task1.annotationconfig;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "race1")
public class Race extends IdEntity {

    @Value(value = "Central Ippodrom")
    private String place;

    private LocalDateTime time = LocalDateTime.of(2019, Month.DECEMBER, 5, 9, 30);
    @Autowired
    private List<Horse> horses;
    @Value(value = "100")
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
