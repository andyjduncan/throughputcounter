package com.adjectivecolournoun.throughputcounter

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

class TestSLF4JCounter extends Specification {

    Logger logger = Mock()

    void setup() {
        LoggerFactory loggerFactory = GroovyMock(global: true)
    }

    void 'creates a logger with the name throughputcounter by default'() {
        when:
        def counter = new SLF4JCounter()

        then:
        1 * LoggerFactory.getLogger('throughputcounter') >> logger
    }

    void 'creates a logger with a supplied name'() {
        when:
        def counter = new SLF4JCounter('logname')

        then:
        1 * LoggerFactory.getLogger('logname') >> logger
    }

    void 'accepts a reporting interval with default logger name'() {
        when:
        def counter = new SLF4JCounter(10l)

        then:
        1 * LoggerFactory.getLogger('throughputcounter') >> logger
    }

    void 'accepts a reporting interval with a supplied logger name'() {
        when:
        def counter = new SLF4JCounter('logname', 10l)

        then:
        1 * LoggerFactory.getLogger('logname') >> logger
    }

    void 'logs to logger at debug level after reporting interval counts'() {
        given:
        1 * LoggerFactory.getLogger('throughputcounter') >> logger
        def counter = new SLF4JCounter()

        when:
        1000.times {
            counter.count()
        }

        then:
        1 * logger.debug({ it ==~ /1,000\t[,\d]+\.\d{3} per second/ })
    }
}