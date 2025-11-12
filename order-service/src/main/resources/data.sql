-- Clean up all tables (order matters for FK constraints)
DELETE FROM order_items;
DELETE FROM restaurant_products;
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM restaurants;
DELETE FROM addresses;
DELETE FROM customers;

-- Customers
INSERT INTO customers (customer_id, name) VALUES ('11111111-1111-1111-1111-111111111111', 'Alice');
INSERT INTO customers (customer_id, name) VALUES ('22222222-2222-2222-2222-222222222222', 'Bob');
INSERT INTO customers (customer_id, name) VALUES ('33333333-3333-3333-3333-333333333333', 'Charlie');
INSERT INTO customers (customer_id, name) VALUES ('44444444-4444-4444-4444-444444444444', 'Diana');
INSERT INTO customers (customer_id, name) VALUES ('55555555-5555-5555-5555-555555555555', 'Eve');

-- Products
INSERT INTO products (id, name, price) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Pizza', '12.99');
INSERT INTO products (id, name, price) VALUES ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99');
INSERT INTO products (id, name, price) VALUES ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99');
INSERT INTO products (id, name, price) VALUES ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Ramen', '10.99');
INSERT INTO products (id, name, price) VALUES ('ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Salad', '7.99');
INSERT INTO products (id, name, price) VALUES ('ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99');
INSERT INTO products (id, name, price) VALUES ('ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99');
INSERT INTO products (id, name, price) VALUES ('ddddddd2-dddd-dddd-dddd-ddddddddddd2', 'Taco', '6.99');

-- Restaurants
INSERT INTO restaurants (id, active) VALUES ('11111111-1111-1111-1111-111111111112', true);
INSERT INTO restaurants (id, active) VALUES ('22222222-2222-2222-2222-222222222223', true);
INSERT INTO restaurants (id, active) VALUES ('33333333-3333-3333-3333-333333333334', true);
INSERT INTO restaurants (id, active) VALUES ('44444444-4444-4444-4444-444444444445', true);

-- Restaurant products (join table)
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('11111111-1111-1111-1111-111111111112', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('11111111-1111-1111-1111-111111111112', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('22222222-2222-2222-2222-222222222223', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('22222222-2222-2222-2222-222222222223', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('33333333-3333-3333-3333-333333333334', 'ccccccc1-cccc-cccc-cccc-ccccccccccc1');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('33333333-3333-3333-3333-333333333334', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('44444444-4444-4444-4444-444444444445', 'ddddddd1-dddd-dddd-dddd-ddddddddddd1');
INSERT INTO restaurant_products (restaurant_id, product_id) VALUES ('44444444-4444-4444-4444-444444444445', 'ddddddd2-dddd-dddd-dddd-ddddddddddd2');

-- Street Addresses
INSERT INTO addresses (id, street, city, postal_code) VALUES ('11111111-1111-1111-1111-111111111113', '123 Main St', 'Springfield', '12345');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('22222222-2222-2222-2222-222222222224', '456 Elm St', 'Shelbyville', '67890');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('33333333-3333-3333-3333-333333333335', '789 Oak St', 'Ogdenville', '54321');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('44444444-4444-4444-4444-444444444446', '101 Maple St', 'North Haverbrook', '98765');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('55555555-5555-5555-5555-555555555557', '202 Pine St', 'Capital City', '11223');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('66666666-6666-6666-6666-666666666668', '303 Cedar St', 'Springfield', '12346');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('77777777-7777-7777-7777-777777777779', '404 Birch St', 'Shelbyville', '67891');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('88888888-8888-8888-8888-888888888889', '505 Walnut St', 'Ogdenville', '54322');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('99999999-9999-9999-9999-999999999990', '606 Chestnut St', 'North Haverbrook', '98766');
INSERT INTO addresses (id, street, city, postal_code) VALUES ('10101010-1010-1010-1010-101010101012', '707 Poplar St', 'Capital City', '11224');

-- Orders
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('11111111-1111-1111-1111-111111111114', '11111111-1111-1111-1111-111111111111', '11111111-1111-1111-1111-111111111112', '11111111-1111-1111-1111-111111111115', '21.98', 1, '11111111-1111-1111-1111-111111111113', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('22222222-2222-2222-2222-222222222225', '22222222-2222-2222-2222-222222222222', '22222222-2222-2222-2222-222222222223', '22222222-2222-2222-2222-222222222226', '26.98', 2, '22222222-2222-2222-2222-222222222224', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('33333333-3333-3333-3333-333333333336', '33333333-3333-3333-3333-333333333333', '33333333-3333-3333-3333-333333333334', '33333333-3333-3333-3333-333333333337', '13.99', 1, '33333333-3333-3333-3333-333333333335', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('44444444-4444-4444-4444-444444444447', '44444444-4444-4444-4444-444444444444', '44444444-4444-4444-4444-444444444445', '44444444-4444-4444-4444-444444444448', '22.99', 3, '44444444-4444-4444-4444-444444444446', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('55555555-5555-5555-5555-555555555558', '55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111112', '55555555-5555-5555-5555-555555555559', '8.99', 2, '55555555-5555-5555-5555-555555555557', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('66666666-6666-6666-6666-666666666669', '11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222223', '66666666-6666-6666-6666-666666666670', '15.99', 1, '66666666-6666-6666-6666-666666666668', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('77777777-7777-7777-7777-777777777780', '22222222-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333334', '77777777-7777-7777-7777-777777777781', '7.99', 2, '77777777-7777-7777-7777-777777777779', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('88888888-8888-8888-8888-888888888890', '33333333-3333-3333-3333-333333333333', '44444444-4444-4444-4444-444444444445', '88888888-8888-8888-8888-888888888891', '6.99', 1, '88888888-8888-8888-8888-888888888889', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('99999999-9999-9999-9999-999999999991', '44444444-4444-4444-4444-444444444444', '11111111-1111-1111-1111-111111111112', '99999999-9999-9999-9999-999999999992', '13.99', 3, '99999999-9999-9999-9999-999999999990', NULL);
INSERT INTO orders (id, customer_id, restaurant_id, tracking_id, price, order_status, delivery_address_id, failure_messages) VALUES ('10101010-1010-1010-1010-101010101013', '55555555-5555-5555-5555-555555555555', '22222222-2222-2222-2222-222222222223', '10101010-1010-1010-1010-101010101014', '22.99', 2, '10101010-1010-1010-1010-101010101012', NULL);

-- Order Items (with order_id foreign key)
-- Order 1: 11111111-1111-1111-1111-111111111114, price=21.98 (Pizza 12.99 + Burger 8.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa1', 'Pizza', '12.99', 1, '12.99', '12.99', '11111111-1111-1111-1111-111111111114');
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99', 1, '8.99', '8.99', '11111111-1111-1111-1111-111111111114');
-- Order 2: 22222222-2222-2222-2222-222222222225, price=26.98 (Sushi 15.99 + Ramen 10.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa3', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99', 1, '15.99', '15.99', '22222222-2222-2222-2222-222222222225');
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa4', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbb2', 'Ramen', '10.99', 1, '10.99', '10.99', '22222222-2222-2222-2222-222222222225');
-- Order 3: 33333333-3333-3333-3333-333333333336, price=13.99 (Pasta 13.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa5', 'ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99', 1, '13.99', '13.99', '33333333-3333-3333-3333-333333333336');
-- Order 4: 44444444-4444-4444-4444-444444444447, price=22.99 (Steak 22.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa6', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99', 1, '22.99', '22.99', '44444444-4444-4444-4444-444444444447');
-- Order 5: 55555555-5555-5555-5555-555555555558, price=8.99 (Burger 8.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa7', 'aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaa2', 'Burger', '8.99', 1, '8.99', '8.99', '55555555-5555-5555-5555-555555555558');
-- Order 6: 66666666-6666-6666-6666-666666666669, price=15.99 (Sushi 15.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa8', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbb1', 'Sushi', '15.99', 1, '15.99', '15.99', '66666666-6666-6666-6666-666666666669');
-- Order 7: 77777777-7777-7777-7777-777777777780, price=7.99 (Salad 7.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaa9', 'ccccccc1-cccc-cccc-cccc-ccccccccccc1', 'Salad', '7.99', 1, '7.99', '7.99', '77777777-7777-7777-7777-777777777780');
-- Order 8: 88888888-8888-8888-8888-888888888890, price=6.99 (Taco 6.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaa10', 'ddddddd2-dddd-dddd-dddd-ddddddddddd2', 'Taco', '6.99', 1, '6.99', '6.99', '88888888-8888-8888-8888-888888888890');
-- Order 9: 99999999-9999-9999-9999-999999999991, price=13.99 (Pasta 13.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaa11', 'ddddddd1-dddd-dddd-dddd-ddddddddddd1', 'Pasta', '13.99', 1, '13.99', '13.99', '99999999-9999-9999-9999-999999999991');
-- Order 10: 10101010-1010-1010-1010-101010101013, price=22.99 (Steak 22.99)
INSERT INTO order_items (id, product_id, product_name, product_price, quantity, price, sub_total, order_id) VALUES ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaa12', 'ccccccc2-cccc-cccc-cccc-ccccccccccc2', 'Steak', '22.99', 1, '22.99', '22.99', '10101010-1010-1010-1010-101010101013');
