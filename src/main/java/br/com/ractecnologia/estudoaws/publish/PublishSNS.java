package br.com.ractecnologia.estudoaws.publish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.Map;

@Component
@Slf4j
public class PublishSNS {

    @Autowired
    private SnsClient snsClient;

    public  void sendMessage(String messageDto, String topicArn, Map<String, MessageAttributeValue> messageAttr) {
        try {

            final PublishRequest request = PublishRequest
                    .builder()
                    .message(messageDto)
                    .topicArn(topicArn)
                    .messageAttributes(messageAttr)
                    .build();

            PublishResponse publish = snsClient.publish(request);
            log.info("Message enviada para SNS com ID: {}", publish.messageId());


        } catch (Exception ex) {
            throw new RuntimeException("Error communicating with Amazon SNS API: " + ex.getMessage());
        }
    }
}
