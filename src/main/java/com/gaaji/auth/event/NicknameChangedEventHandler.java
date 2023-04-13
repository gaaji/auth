package com.gaaji.auth.event;

import com.gaaji.auth.adaptor.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class NicknameChangedEventHandler {
    private final KafkaProducer kafkaProducer;
    @EventListener
    public void handleNicknameChangedEvent(NicknameChangedEvent event) {
        kafkaProducer.publishNicknameChangedEvent(event);
    }
}
