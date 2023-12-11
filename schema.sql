CREATE TABLE IF NOT EXISTS "customer" (
    id serial PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(100),
    phone_number VARCHAR(20),
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0)
);

CREATE TABLE IF NOT EXISTS "city" (
    id serial PRIMARY KEY,
    name_text VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0)
);

CREATE TABLE IF NOT EXISTS "area" (
    id serial PRIMARY KEY,
    name_text VARCHAR(255) NOT NULL,
    city_id INT REFERENCES city(id),
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0)
);

CREATE TABLE IF NOT EXISTS "hotel" (
    id serial PRIMARY KEY,
    name VARCHAR(100),
    address VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    area_id INT,
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0),
    FOREIGN KEY (area_id) REFERENCES "area"(id)
);

CREATE TABLE IF NOT EXISTS "room" (
    id serial PRIMARY KEY,
    hotel_id INT,
    room_number VARCHAR(50),
    room_type VARCHAR(50),
    price_per_night DECIMAL(10, 2),
    version INT DEFAULT 0,
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0),
    FOREIGN KEY (hotel_id) REFERENCES "hotel"(id)
);

CREATE TABLE IF NOT EXISTS "booking" (
    id serial PRIMARY KEY,
    customer_id INT,
    room_id INT,
    checkin_date DATE,
    checkout_date DATE,
    total_amount DECIMAL(10, 2),
    cancel_time TIMESTAMP(0),
    created_at TIMESTAMP(0),
    updated_at TIMESTAMP(0),
    FOREIGN KEY (customer_id) REFERENCES "customer"(id),
    FOREIGN KEY (room_id) REFERENCES "room"(id)
);

INSERT INTO city
(name_text, created_at, updated_at)
VALUES('Hà Nội', CURRENT_DATE, CURRENT_DATE);

INSERT INTO city
(name_text, created_at, updated_at)
VALUES('Tp Hồ Chí Minh', CURRENT_DATE, CURRENT_DATE);

INSERT INTO area
(name_text, city_id, created_at, updated_at)
values
('Quận 1', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 2', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 3', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 4', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 5', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 6', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 7', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 8', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 9', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 10', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 11', 2, CURRENT_DATE, CURRENT_DATE),
('Quận 12', 2, CURRENT_DATE, CURRENT_DATE),
('Gò Vấp', 2, CURRENT_DATE, CURRENT_DATE),
('Bình Tân', 2, CURRENT_DATE, CURRENT_DATE),
('Tân Phú', 2, CURRENT_DATE, CURRENT_DATE),
('Tân Bình', 2, CURRENT_DATE, CURRENT_DATE),
('Bình Thạnh', 2, CURRENT_DATE, CURRENT_DATE),
('Phú Nhuận', 2, CURRENT_DATE, CURRENT_DATE),
('Củ Chi', 2, CURRENT_DATE, CURRENT_DATE),
('Hóc Môn', 2, CURRENT_DATE, CURRENT_DATE),
('Bình Chánh', 2, CURRENT_DATE, CURRENT_DATE),
('Nhà Bè', 2, CURRENT_DATE, CURRENT_DATE),
('Cần Giờ', 2, CURRENT_DATE, CURRENT_DATE);

INSERT INTO area
(name_text, city_id, created_at, updated_at)
values
('Thuận An', 1, CURRENT_DATE, CURRENT_DATE),
('Ba Đình', 1, CURRENT_DATE, CURRENT_DATE),
('Hoàn Kiếm', 1, CURRENT_DATE, CURRENT_DATE),
('Tây Hồ', 1, CURRENT_DATE, CURRENT_DATE),
('Long Biên', 1, CURRENT_DATE, CURRENT_DATE),
('Cầu Giấy', 1, CURRENT_DATE, CURRENT_DATE),
('Đống Đa', 1, CURRENT_DATE, CURRENT_DATE),
('Hai Bà Trưng', 1, CURRENT_DATE, CURRENT_DATE),
('Hoàng Mai', 1, CURRENT_DATE, CURRENT_DATE),
('Thanh Xuân', 1, CURRENT_DATE, CURRENT_DATE),
('Sóc Sơn', 1, CURRENT_DATE, CURRENT_DATE),
('Đông Anh', 1, CURRENT_DATE, CURRENT_DATE),
('Gia Lâm', 1, CURRENT_DATE, CURRENT_DATE),
('Nam Từ Liêm', 1, CURRENT_DATE, CURRENT_DATE),
('Thanh Trì', 1, CURRENT_DATE, CURRENT_DATE),
('Bắc Từ Liêm', 1, CURRENT_DATE, CURRENT_DATE),
('Mê Linh', 1, CURRENT_DATE, CURRENT_DATE),
('Hà Đông', 1, CURRENT_DATE, CURRENT_DATE),
('Sơn Tây', 1, CURRENT_DATE, CURRENT_DATE),
('Ba Vì', 1, CURRENT_DATE, CURRENT_DATE),
('Phúc Thọ', 1, CURRENT_DATE, CURRENT_DATE),
('Đan Phượng', 1, CURRENT_DATE, CURRENT_DATE),
('Hoài Đức', 1, CURRENT_DATE, CURRENT_DATE),
('Quốc Oai', 1, CURRENT_DATE, CURRENT_DATE),
('Thạch Thất', 1, CURRENT_DATE, CURRENT_DATE),
('Chương Mỹ', 1, CURRENT_DATE, CURRENT_DATE),
('Thanh Oai', 1, CURRENT_DATE, CURRENT_DATE),
('Thường Tín', 1, CURRENT_DATE, CURRENT_DATE),
('Phú Xuyên', 1, CURRENT_DATE, CURRENT_DATE),
('Ứng Hòa', 1, CURRENT_DATE, CURRENT_DATE),
('Mỹ Đức', 1, CURRENT_DATE, CURRENT_DATE);

WITH area_list AS (
    SELECT array(SELECT generate_series(1, 54)) as area
)
INSERT INTO hotel
(name, address, phone_number, email, area_id, created_at)
select 'Hotel ' || n as name, '' as address , '090838124' || n as phone_number, 'hotel_' || n || '@gmail.com' as email, area[1 + mod(n, array_length(area, 1))] as area_id, CURRENT_DATE as created_at
from area_list, generate_series(1, 50) as n;

WITH hotel_list AS (
    SELECT array(SELECT generate_series(1, 50)) as hotel
),
type_list AS (
        SELECT '{Deluxe, Suite, King}'::TEXT[] as r_type
),
price_list AS (
        SELECT '{1200, 1300, 1400}'::numeric[] as price
)
INSERT INTO room
(hotel_id, room_number , room_type, price_per_night, created_at)
select hotel[1 + mod(n, array_length(hotel, 1))] as hotel_id, n as room_number, r_type[1 + mod(n, array_length(r_type, 1))] as room_type,
price[1 + mod(n, array_length(price, 1))] as price_per_night,  current_date as created_at
from hotel_list, generate_series(1, 300) as n, type_list, price_list;

INSERT INTO customer
(first_name, last_name, email, password, phone_number)
values
('Tien', 'Tran Dinh', 'tien@gmail.com', '1234', '1234');