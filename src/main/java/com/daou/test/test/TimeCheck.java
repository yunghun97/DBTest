package com.daou.test.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeCheck
{
    public long beforeTime;
    public long afterTime;

    public void checkBefore(){
        beforeTime = System.currentTimeMillis();
    }
    public void checkAfter() {
        afterTime = System.currentTimeMillis();
        System.out.println("걸린 시간 : "+(afterTime-beforeTime));
    }
}
