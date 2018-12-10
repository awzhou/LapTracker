package com.example.laptracker;

import java.util.ArrayList;

public class Workout {

    private ArrayList<Set> sets;

    public Workout() {
        sets = new ArrayList<>();
    }

    public void addSet(Set set) {
        sets.add(set);
    }

    public int getTotalYardage() {
        int yardage = 0;

        for (Set s : sets) {
            int rounds = s.getRounds();

            for (int i = 0; i < rounds; i++) {
                yardage += s.getYardage();
            }
        }

        return yardage;
    }

    @Override
    public String toString() {

        String workout = "";

        for (Set s : sets) {
            workout = workout + s.toString();
        }

        return workout;
    }

}
