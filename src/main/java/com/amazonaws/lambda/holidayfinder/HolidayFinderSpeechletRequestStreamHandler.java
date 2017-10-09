	package com.amazonaws.lambda.holidayfinder;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class HolidayFinderSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;
    
    static {
       supportedApplicationIds = new HashSet<String>();
       supportedApplicationIds.add("[ID HERE]");
   }

    public HolidayFinderSpeechletRequestStreamHandler() {
       super(new HolidayFinderSpeechlet(), supportedApplicationIds);
    }
}