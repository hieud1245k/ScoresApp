package com.hieuminh.scoresapp.model;

public class MatchScores {
    private static int count = 0;
    public int id = (count += 1);
    public int[] scoreList = new int[4];
}
