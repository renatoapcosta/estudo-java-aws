package br.com.ractecnologia.estudoaws.send;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSSend {

    @Autowired
    private AmazonSQS amazonSQS;

    @Async
    public void sendMessage(String messageDto, String queue) {
        try {
            var sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(queue)
                    .withMessageBody(messageDto);

            SendMessageResult sendMessageResult = amazonSQS.sendMessage(sendMessageRequest);
            log.info("SQS MessageId {} in queue {}", sendMessageResult.getMessageId(), queue);
        } catch (Exception ex) {
            throw new RuntimeException("Error communicating with Amazon SQS API: " + ex.getMessage());
        }
    }
}
