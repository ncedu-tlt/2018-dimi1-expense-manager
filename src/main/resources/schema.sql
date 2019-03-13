CREATE TABLE Person(
  person_id serial PRIMARY KEY not null,
  login VARCHAR(20) not null,
  pass VARCHAR(20) not null,
  access VARCHAR(10) not null,
  email VARCHAR(40) not null,
  description VARCHAR(350),
  reg_date DATE,
  phone_number VARCHAR2(12));


CREATE TABLE Accounts(
  account_id serial PRIMARY KEY not null,
  account_number VARCHAR(16),
  person_id_fk INTEGER(10),
  currency VARCHAR2(3),
  balance DECIMAL(7,2),
  description VARCHAR(350),
  FOREIGN KEY (person_id_fk) REFERENCES Person(person_id)
);

CREATE TABLE Budget_type (
  budget_type_id NUMBER(5) PRIMARY KEY not null,
  group_id INTEGER(10),
  name VARCHAR2(100),
  required BOOLEAN,
  check_max DECIMAL(7, 2),
);

CREATE TABLE Budget (
  budget_id serial PRIMARY KEY not null,
  operation_type VARCHAR2(15),
  budget_type_id_fk INTEGER(10),
  description VARCHAR2(350),
  account_id_fk INTEGER(10),
  operation_date DATE,
  charge_value DECIMAL(7, 2),
  FOREIGN KEY (budget_type_id_fk) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_fk) REFERENCES Accounts(account_id)
);

CREATE TABLE Plan_budget (
  plan_budget_id NUMBER(10),
  operation_type VARCHAR2(15),
  budget_type_id_fk INTEGER(10),
  description VARCHAR2(350),
  account_id_fk INTEGER(10),
  operation_date DATE,
  charge_value DECIMAL(7, 2),
  regular_mask VARCHAR2(650),
  repeat_count INTEGER(3),
  start_date DATE,
  end_date DATE,
  FOREIGN KEY (budget_type_id_fk) REFERENCES Budget_type(budget_type_id),
  FOREIGN KEY (account_id_fk) REFERENCES Accounts(account_id)
);