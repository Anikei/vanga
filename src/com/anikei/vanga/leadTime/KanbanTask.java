package com.anikei.vanga.leadTime;

import com.anikei.vanga.util.Fraction;

import java.util.HashMap;
import java.util.Map;

public class KanbanTask {

    final String name;
    int duration;
    HashMap<Integer, Fraction> probabilities = new HashMap<>();

    KanbanTask(String name, String string) {
        this.name = name;

        //time parsing
        String[] times = string.split(":");
        duration = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    @Override
    public String toString() {
        return "KanbanTask{" +
                "name='" + name + '\'' +
                ",\tduration = " + duration + "}";
        //TODO: add probabilities
    }

    String showProbabilities() {
        String result = this.name;

        for (Map.Entry<Integer, Fraction> entry : probabilities.entrySet()) {
            Integer key = entry.getKey();
            Fraction value = entry.getValue();

            if (value.toDecimal().floatValue() == 0.0) {
                //DO nothing
            } else {
                result = result + " T+" + key + ": " + value.toDecimal().floatValue()*100 + "%";
            }
        }


        return result;
    }

}
