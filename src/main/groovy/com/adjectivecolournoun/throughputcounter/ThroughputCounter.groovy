package com.adjectivecolournoun.throughputcounter

import java.util.concurrent.atomic.AtomicLong

abstract class ThroughputCounter {

    private final counter = new AtomicLong()

    private final long reportingInterval

    private final startTime = System.currentTimeMillis()

    private long lastTime = startTime

    ThroughputCounter() {
        this(1000)
    }

    ThroughputCounter(long reportingInterval) {
        this.reportingInterval = reportingInterval
    }

    void count() {
        def currentCount = counter.incrementAndGet()

        if (!(currentCount % reportingInterval)) {
            def currentTime = System.currentTimeMillis()
            def throughput = reportingInterval / ((currentTime - lastTime) / 1000d)
            lastTime = currentTime
            reportThroughput(currentCount, throughput)
        }
    }

    protected abstract reportThroughput(long currentCount, double throughput)
}
