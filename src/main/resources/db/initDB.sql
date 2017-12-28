DROP TABLE IF EXISTS shipping;

CREATE TABLE shipping
(
  id               SERIAL PRIMARY KEY,
  shippingId       INTEGER,
  clusterCode      VARCHAR,
  barcode          VARCHAR,
  shippingDateTime TIMESTAMP DEFAULT now()
);

DROP TABLE IF EXISTS recieving_fg;

CREATE TABLE recieving_fg
(
  id                 SERIAL PRIMARY KEY,
  recieving_id        INTEGER,
  recieving_date_time  TIMESTAMP,
  comment            VARCHAR
)