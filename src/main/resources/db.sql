CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS products
(
    id bigint PRIMARY KEY,
    name CHARACTER VARYING(180),
   price float,
   remainder int,
   product_type CHARACTER VARYING(10)

);


CREATE TABLE IF NOT EXISTS users
(
    id bigint PRIMARY KEY,
    name CHARACTER VARYING(256),
   phone CHARACTER VARYING(12)

);

CREATE TABLE IF NOT EXISTS orders
(
    id bigint PRIMARY KEY,
   author_id bigint,
   product_id bigint,
    CONSTRAINT fk_autor
      FOREIGN KEY(author_id)
      REFERENCES users(id),
    CONSTRAINT fk_product
      FOREIGN KEY(product_id)
      REFERENCES products(id)

);