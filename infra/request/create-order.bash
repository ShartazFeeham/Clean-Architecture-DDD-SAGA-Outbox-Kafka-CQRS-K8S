curl --location 'http://localhost:10/orders' \
--header 'Content-Type: application/json' \
--data '{
    "customerId": "11111111-1111-1111-1111-111111111111",
    "restaurantId": "11111111-1111-1111-1111-111111111112",
    "price": 34.97,
    "items": [
        {
            "productId": "aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1",
            "quantity": 2,
            "price": 12.99,
            "subTotal": 25.98
        },
        {
            "productId": "aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2",
            "quantity": 1,
            "price": 8.99,
            "subTotal": 8.99
        }
    ],
    "address": {
        "street": "123 Main St",
        "city": "Springfield",
        "postalCode": "62704"
    }
}'