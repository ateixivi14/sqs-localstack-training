package com.example.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SQSConsumer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SQSConsumer.class);
    
    private final static int MAXIMUM_RETRIES = 10;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @SneakyThrows
    @SqsListener(value="${cloud.aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(Object message, @Headers Map<String, String> header) throws JsonProcessingException {
        LOGGER.info("SQS Message Received : {}", message);
       // LOGGER.info("SQS headers Received : {}", header);
      //  Thread.sleep(5000L);
        int retries = Integer.parseInt(header.get("ApproximateReceiveCount"));
        if (retries >= MAXIMUM_RETRIES) {
            LOGGER.info("Maximum retries exceed for Id: " + header.get("MessageDeduplicationId") );
        }
    }
    
}
