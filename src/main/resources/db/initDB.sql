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

DROP TABLE IF EXISTS parts CASCADE;
CREATE TABLE parts
(
  id                 SERIAL PRIMARY KEY,
  part_name          VARCHAR,
  part_type_id       INTEGER
);

DROP TABLE IF EXISTS part_operation_detail CASCADE;
CREATE TABLE part_operation_detail
(
  operation_id        INTEGER NOT NULL,
  part_id             INTEGER NOT NULL,
  PRIMARY KEY (operation_id, part_id),
  CONSTRAINT fk_part_operation_detail_2 FOREIGN KEY (operation_id) REFERENCES operations (id),
  CONSTRAINT fk_part_operation_detail_1 FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS persons CASCADE;
CREATE TABLE persons
(
  id                  SERIAL PRIMARY KEY,
  person_name         VARCHAR
);

DROP TABLE IF EXISTS person_operation_detail CASCADE;
CREATE TABLE person_operation_detail
(
  operation_id       INTEGER NOT NULL,
  person_id          INTEGER NOT NULL,
  PRIMARY KEY (operation_id, person_id),
  CONSTRAINT fk_person_operation_detail_1 FOREIGN KEY (operation_id) REFERENCES operations (id),
  CONSTRAINT fk_person_operation_detail_2 FOREIGN KEY (person_id) REFERENCES persons (id) ON DELETE CASCADE
);
