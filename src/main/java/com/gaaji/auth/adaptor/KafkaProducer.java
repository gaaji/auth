package com.gaaji.auth.adaptor;

import com.gaaji.auth.event.NicknameChangedEvent;

public interface KafkaProducer {

    void publishNicknameChangedEvent(NicknameChangedEvent event);

}
