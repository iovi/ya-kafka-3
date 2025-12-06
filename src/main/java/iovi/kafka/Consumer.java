package iovi.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = {"${my.kafka.topic.orders}", "${my.kafka.topic.users}"}, groupId = "group1")
    public void listen(@Payload GenericData.Record record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("topic {}, message: {}", topic, record);
    }
}
