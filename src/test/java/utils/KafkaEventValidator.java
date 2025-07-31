package utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaEventValidator {

    private KafkaConsumer<String, String> consumer;

    public KafkaEventValidator(String bootstrapServers, String topic, String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
    }

    public boolean validateEvent(String expectedPayloadFragment, int timeoutSeconds) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(timeoutSeconds));
        for (ConsumerRecord<String, String> record : records) {
            if (record.value().contains(expectedPayloadFragment)) {
                System.out.println("✅ Event validated: " + record.value());
                return true;
            }
        }
        System.out.println("❌ Event not found within timeout.");
        return false;
    }

    public void close() {
        consumer.close();
    }
}
