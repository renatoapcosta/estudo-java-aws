package br.com.ractecnologia.estudoaws.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;

@Configuration
@Slf4j
public class SnsConfiguration {

    @Bean
    public SnsClient snsClient() {
        var clientBuilder = SnsClient.builder();
        final Boolean usoProducao = false;
        if (!usoProducao) {
            clientBuilder.endpointOverride(URI.create("http://localhost:4566"));
        }
        return clientBuilder.build();
    }
}

