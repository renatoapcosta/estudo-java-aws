package br.com.ractecnologia.estudoaws.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Slf4j
@Component
public class SQSConsumer {


    @JmsListener(destination = "ecs-singleshotbilling-buy-intent-dev")
    public void receiveBuyIntentEvent(TextMessage textMessage) throws JMSException, JsonProcessingException {

        log.info("Consumindo mensagens -----------------------------------------");
        final String text = textMessage.getText();
        if(text.contains("erro")) {
            log.info("mensagem não pode ser processada");
            throw new RuntimeException("messagem contem erro");
        }
        log.info("Fim do processamento");

    }
}