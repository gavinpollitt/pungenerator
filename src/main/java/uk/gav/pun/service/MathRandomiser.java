package uk.gav.pun.service;

import org.springframework.stereotype.Component;

@Component
public class MathRandomiser implements Randomiser{

    @Override
    public long getValue(long lowerLimit, long uppperLimit) {
        return (long)((uppperLimit-lowerLimit+1)*Math.random())+lowerLimit;
    }
    
}
