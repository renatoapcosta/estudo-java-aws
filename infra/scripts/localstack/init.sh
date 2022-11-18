#!/bin/bash
set -x

awslocal sqs create-queue --queue-name ecs-buy-intent-dlq-dev --endpoint-url http://localhost:4566

awslocal sqs create-queue --queue-name ecs-singleshotbilling-buy-intent-dev \
 --attributes '{"RedrivePolicy": "{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:ecs-buy-intent-dlq-dev\",\"maxReceiveCount\":\"2\"}"}' \
 --endpoint-url http://localhost:4566

awslocal sns create-topic --name ecs-singleshotbilling-orquestrator-dev --endpoint-url http://localhost:4566
awslocal sqs create-queue --queue-name ecs-singleshotbilling-generate-report-dev --endpoint-url http://localhost:4566

awslocal sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:ecs-singleshotbilling-orquestrator-dev --protocol sqs --notification-endpoint http://localhost:4566/000000000000/ecs-singleshotbilling-generate-report-dev --attributes '{"RawMessageDelivery": "true", "FilterPolicy": "{\"action\": [\"generate_box_report\"]}"}'


set +x
