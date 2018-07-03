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
);

DROP TABLE IF EXISTS operations CASCADE;
CREATE TABLE operations
(
  id                 SERIAL PRIMARY KEY,
  operation_name     VARCHAR,
  operation_sequence INTEGER
);
INSERT INTO operations (operation_name, operation_sequence)
VALUES
  ( 'FakeOperationName #1', 10 ),
  ( 'FakeOperationName #2', 20 ),
  ( 'FakeOperationName #3', 30 ),
  ( 'FakeOperationName #4', 40 );

DROP TABLE IF EXISTS parts CASCADE;
CREATE TABLE parts
(
  id                 SERIAL PRIMARY KEY,
  part_name          VARCHAR,
  part_type_id       INTEGER
);
INSERT INTO parts (part_name, part_type_id)
VALUES
  ( 'FakePartName #1', 1 ),
  ( 'FakePartName #2', 2 ),
  ( 'FakePartName #3', 3 ),
  ( 'FakePartName #4', 4 );

DROP TABLE IF EXISTS part_operation_detail CASCADE;
CREATE TABLE part_operation_detail
(
  operation_id        INTEGER NOT NULL,
  part_id             INTEGER NOT NULL,
  PRIMARY KEY (operation_id, part_id),
  CONSTRAINT fk_part_operation_detail_2 FOREIGN KEY (operation_id) REFERENCES operations (id),
  CONSTRAINT fk_part_operation_detail_1 FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
)
