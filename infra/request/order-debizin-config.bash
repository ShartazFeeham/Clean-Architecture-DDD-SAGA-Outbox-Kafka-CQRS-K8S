curl --location 'http://localhost:8083/connectors' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
    "name": "order-outbox-connector",
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "tasks.max": "1",
        "plugin.name": "pgoutput",

        "database.hostname": "storage-order-service-pg-db",
        "database.port": "5432",
        "database.user": "user",
        "database.password": "password",
        "database.dbname": "db",
        "database.server.name": "ORDER_CDC_SOURCE",

        "topic.prefix": "cdc_order",
        "table.include.list": "public.outbox",

        "transforms": "outbox",
        "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",

        "transforms.outbox.route.by.field": "aggregatetype",
        "transforms.outbox.table.fields.additional.placement": "type:header:type",
        "transforms.outbox.table.expand.json.payload": "true",

        "key.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",

        "slot.name": "order_slot_outbox"
    }
}'