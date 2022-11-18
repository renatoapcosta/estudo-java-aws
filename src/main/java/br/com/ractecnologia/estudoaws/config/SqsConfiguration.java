package br.com.ractecnologia.estudoaws.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

@Slf4j
@EnableJms
@Configuration
public class SqsConfiguration {

    @Bean
    public AmazonSQSAsync amazonSqs() {

        final AmazonSQSAsyncClientBuilder builder;
        final Boolean usoProducao = false;
        if (usoProducao) {
            builder = AmazonSQSAsyncClientBuilder
                    .standard()
                    .withRegion("us-east-1");
        } else {
            builder = AmazonSQSAsyncClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                           "http://localhost:4566","us-east-1"));
        }
        return builder.build();
    }

    @Bean
    public ProviderConfiguration providerConfiguration() {
        return new ProviderConfiguration().withNumberOfMessagesToPrefetch(5);
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(final ProviderConfiguration providerConfiguration, final AmazonSQS amazonSQS) {
        return new SQSConnectionFactory(providerConfiguration, amazonSQS);
    }

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> jmsListenerContainerFactory(
            final SQSConnectionFactory sqsConnectionFactory) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("1-3");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setErrorHandler(throwable ->
                log.error("There was an error occurred in the transaction, transaction returned {}",
                        throwable.getMessage()));
        factory.setAutoStartup(true);

        return factory;
    }
}
