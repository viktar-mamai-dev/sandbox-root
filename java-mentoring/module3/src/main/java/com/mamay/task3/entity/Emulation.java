package com.mamay.task3.entity;

public final class Emulation {

    private final Long raceId;
    private final Long animalId;
    private boolean isFinished = false;

    public Emulation(Long raceId, Long animalId) {
        this.raceId = raceId;
        this.animalId = animalId;
    }

    public Long getRaceId() {
        return raceId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

}
