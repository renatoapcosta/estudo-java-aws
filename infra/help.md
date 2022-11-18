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