curl -X POST http://localhost:10/orders/track \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "a1a1a1a1-a1a1-a1a1-a1a1-a1a1a1a1a1a1",
    "trackingId": "t1t1t1t1-t1t1-t1t1-t1t1-t1t1t1t1t1t1"
  }'
