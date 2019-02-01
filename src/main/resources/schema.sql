CREATE TABLE Person(
  person_id serial PRIMARY KEY not null,
  login VARCHAR(30),
  pass VARCHAR(30),
  email VARCHAR2(40),
  description VARCHAR(400),
  reg_date DATE,
  phone_number VARCHAR2(11)
);


CREATE TABLE Accounts(
  account_id NUMBER(16) PRIMARY KEY not null,
  person_id_gg NUMBER(5),
  currency VARCHAR2(3),
  balance NUMBER(7),
  description VARCHAR(400),
  FOREIGN KEY (person_id_gg) REFERENCES Person(person_id)
);

CREATE TABLE Budget_type (
  budget_type_id NUMBER(5) PRIMARY KEY not null,
  group_id NUMBER(5),
  name VARCHAR2(100),
  required BOOLEAN,
  check_max NUMBER(7),
);

CREATE TABLE Budget (
  budget_id serial PRIMARY KEY not null,
  operation_type BOOLEAN,
  budget_type_id_gg NUMBER(5),
  description VARCHAR2(400),
  account_id_gg NUMBER(16),
  operation_date DATE,
  charge_value NUMBER(7),
  FOREIGN KEY (budget_type_id_gg) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_gg) REFERENCES Accounts(account_id)
);

CREATE TABLE Plan_budget (
  plan_budget_id NUMBER(10),
  operation_type BOOLEAN,
  budget_type_id_gg NUMBER(5),
  description VARCHAR2(400),
  account_id_gg NUMBER(16),
  operation_date DATE,
  charge_value NUMBER(7),
  regular_mask VARCHAR2(650),
  repeat_count NUMBER(3),
  start_date DATE,
  end_date DATE,
  FOREIGN KEY (budget_type_id_gg) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_gg) REFERENCES Accounts(account_id)
);

