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

    public Integer matchTotal = 16;

    public List<String> players = new ArrayList<>();

    public List<MatchScores> scoresMatrix = new ArrayList<>();

    public Map<String, Integer> playersMap = new HashMap<>();

    public Time startTime = new Time();

    public Time endTime;

    public Records(){}
}
