package com.hieuminh.scoresapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Records implements Serializable {

    public int matchTotal = 16;

    public String[] players = new String[4];

    public List<MatchScores> scoresMatrix = new ArrayList<>();

    public Map<Integer, Integer> playersMap = new HashMap<>();

    public Time startTime = new Time();

    public Time endTime;
}
