package tech.snnukf.Producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


/**
 * @author simple.jbx
 * @ClassName CustomProducer
 * @description 同步发送API 顺序的
 * @email jb.xue@qq.com
 * @github https://github.com/simple-jbx
 * @date 2022/02/15/ 15:20
 */
public class CustomProducer03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.232.128:9092");//kafka 集群，broker-list
        props.put("acks", "all");
        props.put("retries", 1);//重试次数
        props.put("batch.size", 16384);//批次大小
        props.put("linger.ms", 1);//等待时间
        props.put("buffer.memory", 33554432);//RecordAccumulator 缓冲区大小
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new
                KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("first",
                    Integer.toString(i), Integer.toString(i))).get();
        }
        producer.close();
    }
}
