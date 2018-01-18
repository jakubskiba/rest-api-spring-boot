package com.codecool.freefoodmeetup.logger;

import com.codecool.freefoodmeetup.FreeFoodMeetupApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoggerService implements LoggerServiceInterface {
    private static final Logger logger = LogManager.getLogger(FreeFoodMeetupApplication.class);

    @Override
    public void logError(String message) {
        logger.error(message);
    }

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }
}
