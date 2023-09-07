package com.fabu.rpgexample.model;

import java.util.Arrays;

public class LogModel {

    public String[] logs;

    public LogModel(){
        logs = new String[6];
        for (int i = 0; i < 6; i++) {
            logs[i] = null;
        }
    }

    public LogModel(String[] logs) {
        this.logs = logs;
    }

    public String[] getLogs() {
        return logs;
    }

    public void setLogs(String[] logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "logs=" + Arrays.toString(logs) +
                '}';
    }
}
