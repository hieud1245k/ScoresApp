package com.hieuminh.scoresapp.model;

public class MatchScores {
    private static int count = 0;
    public int id;
    public int[] marks = new int[4];
    public MatchScores(int[] marks) {
        id = ++count;
        this.marks = marks;
    }
}
