package org.acme;

import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;

public class Scheduler {

    private static final Logger LOG = Logger.getLogger(Scheduler.class);

    @Scheduled(every = "{my.feature.delay}", delayed = "3s")
    void myFeature() {
        LOG.info("running task");
    }
}
