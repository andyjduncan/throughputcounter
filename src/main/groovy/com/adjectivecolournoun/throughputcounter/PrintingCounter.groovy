package com.adjectivecolournoun.throughputcounter

import groovy.transform.InheritConstructors

@InheritConstructors
abstract class PrintingCounter extends ThroughputCounter {

    @Override
    protected reportThroughput(long currentCount, double throughput) {
        printOutput String.format('%1$,d\t%2$,.3f per second', currentCount, throughput)
    }

    protected abstract printOutput(String output)
}
