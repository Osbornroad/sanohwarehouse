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

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
  id                  SERIAL PRIMARY KEY,
  name                VARCHAR NOT NULL UNIQUE,
  email               VARCHAR NOT NULL,
  password            VARCHAR(60) NOT NULL,
  enabled             BOOLEAN DEFAULT TRUE,
  registered          TIMESTAMP DEFAULT now() NOT NULL
);

DROP TABLE IF EXISTS user_roles CASCADE;
CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS parts CASCADE;
CREATE TABLE parts
(
  id                 SERIAL PRIMARY KEY,
  name               VARCHAR NOT NULL,
  part_type          VARCHAR NOT NULL,
  length             INTEGER,
  part_code          VARCHAR
);

DROP TABLE IF EXISTS part_parts CASCADE;
CREATE TABLE part_parts
(
  self_part_id            INTEGER NOT NULL,
  child_part_id           INTEGER,
  quantity                INTEGER,
  CONSTRAINT part_parts_idx UNIQUE (self_part_id, child_part_id),
  FOREIGN KEY (self_part_id) REFERENCES parts (id) ON DELETE CASCADE
);


DROP TABLE IF EXISTS child_parts CASCADE;
CREATE TABLE child_parts
(
  id                  SERIAL PRIMARY KEY,
  part_id             INTEGER NOT NULL,
  child_part_id       INTEGER NOT NULL,
  quantity            INTEGER NOT NULL,
  FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS part_operations CASCADE;
CREATE TABLE part_operations
(
  part_id                 INTEGER NOT NULL,
  operation               VARCHAR,
  CONSTRAINT part_operations_idx UNIQUE (part_id, operation),
  FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
);


/*DROP TABLE IF EXISTS part_operation_detail CASCADE;
CREATE TABLE part_operation_detail
(
  operation_id        INTEGER NOT NULL,
  part_id             INTEGER NOT NULL,
  PRIMARY KEY (operation_id, part_id),
  CONSTRAINT fk_part_operation_detail_2 FOREIGN KEY (operation_id) REFERENCES operations (id),
  CONSTRAINT fk_part_operation_detail_1 FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
);*/

DROP TABLE IF EXISTS persons CASCADE;
CREATE TABLE persons
(
  id                  SERIAL PRIMARY KEY,
  person_name         VARCHAR NOT NULL
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


DROP TABLE IF EXISTS variants CASCADE;
CREATE TABLE variants
(
  id                 SERIAL PRIMARY KEY,
  name               VARCHAR NOT NULL,
  project            VARCHAR NOT NULL
);

DROP TABLE IF EXISTS finish_parts CASCADE;
CREATE TABLE finish_parts
(
  id                 SERIAL PRIMARY KEY,
  name               VARCHAR NOT NULL,
  part_type          VARCHAR NOT NULL
);

DROP TABLE IF EXISTS variants_finish_parts CASCADE;
CREATE TABLE variants_finish_parts
(
  variants_id      INTEGER NOT NULL,
  finish_parts_id  INTEGER NOT NULL,
  PRIMARY KEY (variants_id, finish_parts_id),
  CONSTRAINT fk_variants_finish_parts_1 FOREIGN KEY (variants_id) REFERENCES variants (id),
  CONSTRAINT fk_variants_finish_parts_2 FOREIGN KEY (finish_parts_id) REFERENCES finish_parts (id)
);

DROP TABLE IF EXISTS incomings CASCADE;
CREATE TABLE incomings
(
  id                  SERIAL PRIMARY KEY,
  finish_part_id      INTEGER NOT NULL,
  quantity            INTEGER NOT NULL,
  incoming_date_time  TIMESTAMP DEFAULT now() NOT NULL,
  comments            VARCHAR,
  FOREIGN KEY (finish_part_id) REFERENCES finish_parts (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS job CASCADE;
CREATE TABLE job
(
  id                  SERIAL PRIMARY KEY,
  part_id             INTEGER NOT NULL,
  operation           VARCHAR NOT NULL,
  machine             VARCHAR NOT NULL,
  cycle_time          INTEGER NOT NULL,
  FOREIGN KEY (part_id) REFERENCES parts (id) ON DELETE CASCADE
);



