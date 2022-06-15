package com.mamay.task3.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
@ToString
public class Race extends IdEntity implements InitializingBean {

    private String place;
    private LocalDateTime time;
    private List<Long> participants;
    private int length;

    private List<Double> positions;

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
