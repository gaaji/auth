package com.gaaji.auth.adaptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.auth.event.NicknameChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducerImpl implements KafkaProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publishNicknameChangedEvent(NicknameChangedEvent event) {

        String nicknameChangedEvent = null;
        try {
            nicknameChangedEvent = new ObjectMapper().writeValueAsString(event);
            kafkaTemplate.send("nickname-changed",nicknameChangedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
