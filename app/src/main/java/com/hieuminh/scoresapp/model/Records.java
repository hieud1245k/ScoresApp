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

    public String[] players = new String[4];

    public Map<String,MatchScores> scoresMap = new HashMap<>();

    public Map<String, Integer> playersMap = new HashMap<>();

    public Time startTime = new Time();

    public Time endTime;
}
