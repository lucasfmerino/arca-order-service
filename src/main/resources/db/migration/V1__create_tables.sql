CREATE TABLE t_order (
    id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    order_status VARCHAR(20) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id)
);


CREATE TABLE t_order_product (
    id BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    name VARCHAR(255),
    price DOUBLE,
    quantity DOUBLE,
    order_id BINARY(16),
    created_at DATETIME,
    updated_at DATETIME,

    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES t_order(id) ON DELETE CASCADE
);
