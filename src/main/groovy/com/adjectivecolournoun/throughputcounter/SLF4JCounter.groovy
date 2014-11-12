package com.adjectivecolournoun.throughputcounter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SLF4JCounter extends PrintingCounter {

    private static final DEFAULT_LOGGER = 'throughputcounter'

    private final Logger logger

    SLF4JCounter() {
        this(DEFAULT_LOGGER)
    }

    SLF4JCounter(long reportingInterval) {
        this(DEFAULT_LOGGER, reportingInterval)
    }

    SLF4JCounter(String loggerName) {
        super()
        logger = LoggerFactory.getLogger(loggerName)
    }

    SLF4JCounter(String loggerName, long reportingInterval) {
        super(reportingInterval)
        logger = LoggerFactory.getLogger(loggerName)
    }

    @Override
    protected printOutput(String output) {
        logger.debug output
    }
}
