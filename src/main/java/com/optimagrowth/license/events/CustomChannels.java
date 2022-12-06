package com.optimagrowth.license.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * CustomChannels.
 *
 * @author Vitalii Chura
 */
public interface CustomChannels {

    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}
