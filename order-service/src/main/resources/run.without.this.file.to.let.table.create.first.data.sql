-- Clean up all tables (order matters for FK constraints)
DELETE FROM order_entity_items;
DELETE FROM restaurant_entity_products;
DELETE FROM order_item_entity;
DELETE FROM order_entity;
DELETE FROM product_entity;
DELETE FROM restaurant_entity;
DELETE FROM street_address_entity;
DELETE FROM customer_entity;

-- Customers
INSERT INTO customer_entity (customer_id, name) VALUES ('11111111-1111-1111-1111-111111111111', 'Alice');
INSERT INTO customer_entity (customer_id, name) VALUES ('22222222-2222-2222-2222-222222222222', 'Bob');
INSERT INTO customer_entity (customer_id, name) VALUES ('33333333-3333-3333-3333-333333333333', 'Charlie');
INSERT INTO customer_entity (customer_id, name) VALUES ('44444444-4444-4444-4444-444444444444', 'Diana');
INSERT INTO customer_entity (customer_id, name) VALUES ('55555555-5555-5555-5555-555555555555', 'Eve');

-- Products
INSERT INTO product_entity (id, name, price) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Pizza', '12.99');
INSERT INTO product_entity (id, name, price) VALUES ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99');
INSERT INTO product_entity (id, name, price) VALUES ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99');
INSERT INTO product_entity (id, name, price) VALUES ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Ramen', '10.99');
INSERT INTO product_entity (id, name, price) VALUES ('ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Salad', '7.99');
INSERT INTO product_entity (id, name, price) VALUES ('ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99');
INSERT INTO product_entity (id, name, price) VALUES ('ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99');
INSERT INTO product_entity (id, name, price) VALUES ('ddddddd2-dddd-dddd-dddd-ddddddddddd2', 'Taco', '6.99');

-- Restaurants
INSERT INTO restaurant_entity (id, active) VALUES ('11111111-1111-1111-1111-111111111112', true);
INSERT INTO restaurant_entity (id, active) VALUES ('22222222-2222-2222-2222-222222222223', true);
INSERT INTO restaurant_entity (id, active) VALUES ('33333333-3333-3333-3333-333333333334', true);
INSERT INTO restaurant_entity (id, active) VALUES ('44444444-4444-4444-4444-444444444445', true);

-- Restaurant products (join table)
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('11111111-1111-1111-1111-111111111112', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('11111111-1111-1111-1111-111111111112', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('22222222-2222-2222-2222-222222222223', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('22222222-2222-2222-2222-222222222223', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('33333333-3333-3333-3333-333333333334', 'ccccccc1-cccc-cccc-cccc-ccccccccccc1');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('33333333-3333-3333-3333-333333333334', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('44444444-4444-4444-4444-444444444445', 'ddddddd1-dddd-dddd-dddd-ddddddddddd1');
INSERT INTO restaurant_entity_products (restaurant_entity_id, products_id) VALUES ('44444444-4444-4444-4444-444444444445', 'ddddddd2-dddd-dddd-dddd-ddddddddddd2');

-- Street Addresses
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('11111111-1111-1111-1111-111111111113', '123 Main St', 'Springfield', '12345');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('22222222-2222-2222-2222-222222222224', '456 Elm St', 'Shelbyville', '67890');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('33333333-3333-3333-3333-333333333335', '789 Oak St', 'Ogdenville', '54321');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('44444444-4444-4444-4444-444444444446', '101 Maple St', 'North Haverbrook', '98765');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('55555555-5555-5555-5555-555555555557', '202 Pine St', 'Capital City', '11223');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('66666666-6666-6666-6666-666666666668', '303 Cedar St', 'Springfield', '12346');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('77777777-7777-7777-7777-777777777779', '404 Birch St', 'Shelbyville', '67891');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('88888888-8888-8888-8888-888888888889', '505 Walnut St', 'Ogdenville', '54322');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('99999999-9999-9999-9999-999999999990', '606 Chestnut St', 'North Haverbrook', '98766');
INSERT INTO street_address_entity (id, street, city, postal_code) VALUES ('10101010-1010-1010-1010-101010101012', '707 Poplar St', 'Capital City', '11224');

-- Orders
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('11111111-1111-1111-1111-111111111114', '11111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111112', '11111111-1111-1111-1111-111111111115', '21.98', 1, '11111111-1111-1111-1111-111111111113', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('22222222-2222-2222-2222-222222222225', '22222222-2222-2222-2222-222222222222', '22222222-2222-2222-2222-222222222223', '22222222-2222-2222-2222-222222222226', '26.98', 2, '22222222-2222-2222-2222-222222222224', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('33333333-3333-3333-3333-333333333336', '33333333-3333-3333-3333-333333333333', '33333333-3333-3333-3333-333333333334', '33333333-3333-3333-3333-333333333337', '13.99', 1, '33333333-3333-3333-3333-333333333335', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('44444444-4444-4444-4444-444444444447', '44444444-4444-4444-4444-444444444444', '44444444-4444-4444-4444-444444444445', '44444444-4444-4444-4444-444444444448', '22.99', 3, '44444444-4444-4444-4444-444444444446', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('55555555-5555-5555-5555-555555555558', '55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111112', '55555555-5555-5555-5555-555555555559', '8.99', 2, '55555555-5555-5555-5555-555555555557', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('66666666-6666-6666-6666-666666666669', '11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222223', '66666666-6666-6666-6666-666666666670', '15.99', 1, '66666666-6666-6666-6666-666666666668', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('77777777-7777-7777-7777-777777777780', '22222222-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333334', '77777777-7777-7777-7777-777777777781', '7.99', 2, '77777777-7777-7777-7777-777777777779', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('88888888-8888-8888-8888-888888888890', '33333333-3333-3333-3333-333333333333', '44444444-4444-4444-4444-444444444445', '88888888-8888-8888-8888-888888888891', '6.99', 1, '88888888-8888-8888-8888-888888888889', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('99999999-9999-9999-9999-999999999991', '44444444-4444-4444-4444-444444444444', '11111111-1111-1111-1111-111111111112', '99999999-9999-9999-9999-999999999992', '13.99', 3, '99999999-9999-9999-9999-999999999990', NULL);
INSERT INTO order_entity (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('10101010-1010-1010-1010-101010101013', '55555555-5555-5555-5555-555555555555', '22222222-2222-2222-2222-222222222223', '10101010-1010-1010-1010-101010101014', '22.99', 2, '10101010-1010-1010-1010-101010101012', NULL);

-- Order Items
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (1, 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Pizza', '12.99', 1, '12.99', '12.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (2, 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99', 1, '8.99', '8.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (3, 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99', 1, '15.99', '15.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (4, 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Ramen', '10.99', 1, '10.99', '10.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (5, 'ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Salad', '7.99', 2, '15.98', '15.98');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (6, 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99', 1, '22.99', '22.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (7, 'ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99', 1, '13.99', '13.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (8, 'ddddddd2-dddd-dddd-dddd-ddddddddddd2', 'Taco', '6.99', 3, '20.97', '20.97');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (9, 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Pizza', '12.99', 2, '25.98', '25.98');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (10, 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99', 1, '15.99', '15.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (11, 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99', 2, '45.98', '45.98');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (12, 'ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99', 2, '27.98', '27.98');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (13, 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Ramen', '10.99', 1, '10.99', '10.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (14, 'ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Salad', '7.99', 1, '7.99', '7.99');
INSERT INTO order_item_entity (id, product_id, product_name, product_price, quantity, price, sub_total) VALUES (15, 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99', 2, '17.98', '17.98');

-- Order items join table (order_entity_items)
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('11111111-1111-1111-1111-111111111114', 1);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('11111111-1111-1111-1111-111111111114', 2);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('22222222-2222-2222-2222-222222222225', 3);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('22222222-2222-2222-2222-222222222225', 4);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('33333333-3333-3333-3333-333333333336', 5);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('44444444-4444-4444-4444-444444444447', 6);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('55555555-5555-5555-5555-555555555558', 7);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('66666666-6666-6666-6666-666666666669', 8);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('77777777-7777-7777-7777-777777777780', 9);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('88888888-8888-8888-8888-888888888890', 10);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('99999999-9999-9999-9999-999999999991', 11);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('10101010-1010-1010-1010-101010101013', 12);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('11111111-1111-1111-1111-111111111114', 13);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('22222222-2222-2222-2222-222222222225', 14);
INSERT INTO order_entity_items (order_entity_id, items_id) VALUES ('33333333-3333-3333-3333-333333333336', 15);
