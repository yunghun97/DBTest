package com.daou.test.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class TimeResult {
    private List<Long> redisTime = new ArrayList<>();
    private List<Long> mysqlTime = new ArrayList<>();

    public double[] getAvg(){
        double[] arr = new double[2];
        for(int i=0; i<redisTime.size(); i++){
            arr[0] += redisTime.get(i);
            arr[1] += mysqlTime.get(i);
        }
        arr[0] /= redisTime.size();
        arr[1] /= mysqlTime.size();
        return arr;
    }
}
