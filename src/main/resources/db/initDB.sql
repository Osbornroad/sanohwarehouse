DROP TABLE IF EXISTS shipping;

CREATE TABLE shipping
(
  id               SERIAL PRIMARY KEY,
  shippingId       INTEGER NOT NULL ,
  clusterCode      VARCHAR NOT NULL,
  barcode          VARCHAR NOT NULL,
  shippingDateTime TIMESTAMP DEFAULT now()
)
