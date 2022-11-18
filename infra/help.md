# Getting Started

### Reference Documentation

Consultar tópicos (sns)
```bash
aws sns list-topics 
```

Consultar queues (sqs)
```bash
aws sqs list-queues
```

Enviando mensagem SQS
```bash
aws sqs send-message --queue-url http://sqs.us-east-1.localhost:4566/000000000000/ecs-singleshotbilling-buy-intent-dev --message-body "Teste message."  
```

Recebendo uma mensagem SQS
```bash
aws sqs receive-message --queue-url http://sqs.us-east-1.localhost:4566/000000000000/ecs-singleshotbilling-buy-intent-dev 
```

Consultando as configurações de uma fila
```bash
aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/ecs-buy-intent-dlq-dev --attribute-names All

aws --endpoint-url=http://localhost:4566 sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/ecs-singleshotbilling-buy-intent-dev --attribute-names All

```

Enviando uma mensagem com erro
```bash
 aws sqs send-message --queue-url http://sqs.us-east-1.localhost:4566/000000000000/ecs-singleshotbilling-buy-intent-dev --message-body "Teste message com erro" 
```




