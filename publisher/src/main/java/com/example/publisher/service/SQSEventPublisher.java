package com.example.publisher.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SQSEventPublisher {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SQSEventPublisher.class);
    
    private final AmazonSQS amazonSQS;

    @Value("${cloud.aws.sqs.test.queue}")
    private String urlQueue;
    

    public void publishEvent(String message, String customerId) {
        try {
            
            SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(urlQueue)
                    .withMessageBody(message)
                    .withMessageDeduplicationId(customerId);
            
            amazonSQS.sendMessage(sendMessageRequest);
            LOGGER.info("Event has been published in SQS.");
            
        } catch (Exception e) {
            LOGGER.error("Exception ocurred while pushing event to sqs : {} and stacktrace ; {}", e.getMessage(), e);
        }
    }
    
}
