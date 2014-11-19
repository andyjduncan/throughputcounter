package com.adjectivecolournoun.throughputcounter

import groovy.transform.InheritConstructors

@InheritConstructors
class ConsoleCounter extends PrintingCounter {

    @Override
    protected printOutput(String output) {
        println output
    }
}
