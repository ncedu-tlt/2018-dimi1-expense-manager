CREATE TABLE Person(
  person_id serial PRIMARY KEY not null,
  login VARCHAR(20),
  pass VARCHAR(20),
  email VARCHAR2(40),
  description VARCHAR(350),
  reg_date DATE,
  phone_number VARCHAR2(12)
);


CREATE TABLE Accounts(
  account_id NUMBER(5) PRIMARY KEY not null,
  account_number NUMBER(16),
  person_id_fk NUMBER(5),
  currency VARCHAR2(3),
  balance NUMBER(7),
  description VARCHAR(350),
  FOREIGN KEY (person_id_fk) REFERENCES Person(person_id)
);

CREATE TABLE Budget_type (
  budget_type_id NUMBER(5) PRIMARY KEY not null,
  group_id NUMBER(5),
  name VARCHAR2(100),
  required BOOLEAN,
  check_max DECIMAL(7, 2),
);

CREATE TABLE Budget (
  budget_id serial PRIMARY KEY not null,
  operation_type VARCHAR2(15),
  budget_type_id_fk NUMBER(5),
  description VARCHAR2(350),
  account_id_fk NUMBER(16),
  operation_date DATE,
  charge_value DECIMAL(7, 2),
  FOREIGN KEY (budget_type_id_fk) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_fk) REFERENCES Accounts(account_id)
);

CREATE TABLE Plan_budget (
  plan_budget_id NUMBER(10),
  operation_type VARCHAR2(15),
  budget_type_id_fk NUMBER(5),
  description VARCHAR2(350),
  account_id_fk NUMBER(16),
  operation_date DATE,
  charge_value NUMBER(7),
  regular_mask VARCHAR2(650),
  repeat_count NUMBER(3),
  start_date DATE,
  end_date DATE,
  FOREIGN KEY (budget_type_id_fk) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_fk) REFERENCES Accounts(account_id)
);