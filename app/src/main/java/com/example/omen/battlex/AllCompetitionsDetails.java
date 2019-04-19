package com.example.omen.battlex;

public class AllCompetitionsDetails {
    private int min;
    private  int max;
    private String competitionName;

    public AllCompetitionsDetails() {
    }

    public AllCompetitionsDetails(int min, int max, String competitionName) {
        this.min = min;
        this.max = max;
        this.competitionName = competitionName;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }
}
