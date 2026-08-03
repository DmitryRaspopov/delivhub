package io.delivhub.customerservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        configProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100);

        JsonMapper jsonMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        JacksonJsonSerializer<Object> jacksonJsonSerializer = new JacksonJsonSerializer<>(jsonMapper);

        DefaultKafkaProducerFactory<String, Object> factory = new DefaultKafkaProducerFactory<>(configProps);
        factory.setKeySerializerSupplier(StringSerializer::new);
        factory.setValueSerializerSupplier(() -> jacksonJsonSerializer);

        return factory;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());

        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
            @Override
            public void onSuccess(@NonNull ProducerRecord<String, Object> producerRecord, @NonNull RecordMetadata recordMetadata) {
                log.info("Event successfully sent to Kafka. Topic: {}, Partition: {}, Offset: {}, Key: {}",
                        producerRecord.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        producerRecord.key());
            }

            @Override
            public void onError(@NonNull ProducerRecord<String, Object> producerRecord, @Nullable RecordMetadata recordMetadata, @NonNull Exception exception) {
                log.error("Failed to send event to Kafka. Topic: {}, Key: {}. Error: {}",
                        producerRecord.topic(),
                        producerRecord.key(),
                        exception.getMessage(),
                        exception);
            }
        });

        return kafkaTemplate;
    }
}
