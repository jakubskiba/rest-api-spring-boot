package com.codecool.freefoodmeetup.logger;

import com.codecool.freefoodmeetup.FreeFoodMeetupApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Service;

@Service
public class LoggerService implements LoggerServiceInterface {
    private static final Logger logger = LogManager.getLogger(FreeFoodMeetupApplication.class);
    private Marker marker = MarkerManager.getMarker("START");

    @Override
    public void logError(String message) {
        logger.error(marker, message);
    }

    @Override
    public void logInfo(String message) {
        logger.info(marker, message);
    }
}
