package com.amazonaws.lambda.holidayfinder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;


public class HolidayFinderSpeechlet implements Speechlet {
	
	static final Logger logger = LogManager.getLogger(HolidayFinderSpeechlet.class);
	private CountriesMap countries = new CountriesMap();
	
	@Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
		
    }
	
	@Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
		
        return getHelpResponse();
    }
	
	@Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
		
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("RandomDestination".equals(intentName)) {
            return getRandDestinationResponse();
        } else if ("".equals(intentName)) {
        	return getWeatherDestinationResponse();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else if ("AMAZON.StopIntent".equals(intentName)){
        	return getGoodbyeResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }
	
	@Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
		
 	}
	
	 private SpeechletResponse getHelpResponse() {
	        String speechText = "Not sure where to go on holiday? Try asking me for a destination,"
	        		+ "and if you want specify your preferred weather!";

	        // Create the Simple card content.
	        SimpleCard card = new SimpleCard();
	        card.setTitle("Holiday Finder");
	        card.setContent(speechText);

	        // Create the plain text output.
	        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	        speech.setText(speechText);

	        // Create reprompt
	        Reprompt reprompt = new Reprompt();
	        reprompt.setOutputSpeech(speech);

	        return SpeechletResponse.newAskResponse(speech, reprompt, card);
	    }
	 
	 private SpeechletResponse getRandDestinationResponse() {
		 String country = countries.getRandCountry();
		 
		 PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		 speech.setText("You could visit " + country + "!");
		 
		 return SpeechletResponse.newTellResponse(speech);
	 }
	 
	 private SpeechletResponse getGoodbyeResponse() {
		 PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		 speech.setText("I hope you found a promising destination!");
		 
		 return SpeechletResponse.newTellResponse(speech);
	 }
	 
	 private SpeechletResponse getWeatherDestinationResponse() {
		 return null;
	 }
}
