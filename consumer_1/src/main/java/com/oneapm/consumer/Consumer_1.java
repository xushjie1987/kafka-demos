package com.oneapm.consumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer_1 {
    
    public static void main(String[] args) {
        // props
        Properties props = new Properties();
        props.setProperty("group.id",
                          "druid_metric_1hour");
        props.setProperty("auto.offset.reset",
                          "earliest");
        props.setProperty("enable.auto.commit",
                          "false");
        props.setProperty("key.deserializer",
                          "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer",
                          "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("bootstrap.servers",
                          "10.128.6.72:9092");
        props.setProperty("security.protocol",
                          "SASL_PLAINTEXT");
        props.setProperty("sasl.kerberos.service.name",
                          "kfk");
        // client
        KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<byte[], byte[]>(props);
        // subscribe
        consumer.subscribe(Arrays.asList("tps-dc-metric-formatdata"));
        // poll
        ConsumerRecords<byte[], byte[]> records = consumer.poll(Long.MAX_VALUE);
        Iterator<ConsumerRecord<byte[], byte[]>> iter = records.iterator();
        while (iter.hasNext()) {
            System.out.println("value: " +
                               new String(iter.next()
                                              .value()));
        }
        // close
        consumer.close();
        // over
        System.out.println("=====over=====");
    }
    
}
