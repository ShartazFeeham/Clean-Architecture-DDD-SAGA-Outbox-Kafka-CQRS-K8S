curl --location 'http://localhost:20/payments' \
--header 'Content-Type: application/json' \
--data '{
    "customerId": "11111111-1111-1111-1111-111111111111",
    "orderId": "a7df6433-742f-46a0-8bce-cfd5f05b4065",
    "amount": 34.97
}'