curl -X POST http://localhost:10/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "11111111-1111-1111-1111-111111111111",
    "restaurantId": "33333333-3333-3333-3333-333333333333",
    "deliveryAddressId": "77777777-7777-7777-7777-777777777777",
    "items": [
      { "productId": "aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1", "quantity": 2 },
      { "productId": "aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2", "quantity": 1 }
    ]
  }'
