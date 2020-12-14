package com.hieuminh.scoresapp.model;

import java.util.ArrayList;
import java.util.List;

public class MatchScores {
    private static int count = 0;
    public int id;
    public List<Integer> marks = new ArrayList<>(4);
    public MatchScores(List<Integer> marks) {
        id = ++count;
        this.marks = marks;
    }

    public MatchScores() {}
}
