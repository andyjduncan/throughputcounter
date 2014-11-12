package com.adjectivecolournoun.throughputcounter

import groovy.transform.InheritConstructors
import spock.lang.Specification


class TestThroughputCounter extends Specification {

    void 'prints throughput after 1,000 counts by default'() {
        given:
        def counter = new AssertableCounter()

        when:
        1000.times {
            counter.count()
        }

        then:
        counter.printed ==~ /1,000\t[,\d]+\.\d{3} per second/
    }

    void 'allows a reporting interval to be set'() {
        given:
        def counter = new AssertableCounter(10)

        when:
        10.times {
            counter.count()
        }

        then:
        counter.printed ==~ /10\t[,\d]+\.\d{3} per second/
    }
}

@InheritConstructors
class AssertableCounter extends PrintingCounter {

    String printed = ''

    @Override
    protected printOutput(String output) {
        printed += output
    }
}