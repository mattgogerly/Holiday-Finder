package com.amazonaws.lambda.holidayfinder;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class HolidayFinderSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;
    
    static {
       supportedApplicationIds = new HashSet<String>();
       supportedApplicationIds.add("amzn1.ask.skill.8d87cda6-c293-4c24-8e4b-fa2d0d5be3d2");
   }

    public HolidayFinderSpeechletRequestStreamHandler() {
       super(new HolidayFinderSpeechlet(), supportedApplicationIds);
    }
}