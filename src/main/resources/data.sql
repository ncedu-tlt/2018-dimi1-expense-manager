INSERT INTO Person (person_id, login, pass, access, email, reg_date) VALUES (1, 'test', 'test', 'USER', 'test@mail.ru', TO_DATE('11.12.2018', 'dd.mm.yyyy'));
INSERT INTO Person (person_id, login, pass, access, email, phone_number) VALUES (2, 'test1', 'test1', 'USER', 'test1@mail.ru', 89171546552);
INSERT INTO Person (person_id, login, pass, access, email, description, reg_date, phone_number) VALUES (3, 'testtest', 'testtest', 'USER', 'test1@mail.ru', 'description', TO_DATE('11.12.2018', 'dd.mm.yyyy'), 89171546552);

INSERT INTO Accounts (account_id, account_number, person_id_fk, currency, balance, description)
                        VALUES (1, 4854630037067858, 1, 'RUB', 3500, 'Кредитная');
INSERT INTO Accounts (account_id, account_number, person_id_fk, currency, balance, description)
                        VALUES (2, 3608650012003000, 1, 'RUB', 500, 'Дебетовая');
INSERT INTO Accounts (account_id, account_number, person_id_fk, currency, balance, description)
                        VALUES (3, 4565123278982585, 2, 'USD', 5000, 'Дебетовая');
INSERT INTO Accounts (account_id, account_number, person_id_fk, currency, balance, description)
                        VALUES (4, 1, 1, 'RUB', 250, 'Наличные');
INSERT INTO Accounts (account_id, account_number, person_id_fk, currency, balance, description)
                        VALUES (5, 1, 2, 'RUB', 2000, 'Наличные');

INSERT INTO Budget_type (budget_type_id, name, required, check_max)
                        VALUES (1, 'Питание', true, 7000);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (2, 1, 'Магазины', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (3, 1, 'Столовая', true);
INSERT INTO Budget_type (budget_type_id, name, required)
                        VALUES (4, 'Квартирные платежи', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (5, 4, 'Коммуналка', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (6, 4, 'Кап ремонт', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (7, 4, 'Телефон/Интернет', true);
INSERT INTO Budget_type (budget_type_id, name, required)
                        VALUES (8, 'Проезд', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (9, 8, 'Маршрутка', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (10, 8, 'Такси', false);
INSERT INTO Budget_type (budget_type_id, name, required)
                        VALUES (11, 'Доходы', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (12, 11, 'Зарплата', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required)
                        VALUES (13, 11, 'Стипендия', true);


INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1000, 'Нал', 12, '', 2, TO_DATE('15.11.2018', 'dd.mm.yyyy'), 45000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1001, 'Нал', 12, '', 3, TO_DATE('13.11.2018', 'dd.mm.yyyy'), 30000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1002, 'Безнал', 2, '', 2, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 5000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1003, 'Безнал', 5, '', 3, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 4500);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1004, 'Безнал', 9, '', 2, TO_DATE('27.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1005, 'Безнал', 9, '', 2, TO_DATE('28.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1006, 'Безнал', 2, 'Предновогодняя закупка к праздничному столу', 3, TO_DATE('20.12.2018', 'dd.mm.yyyy'), 5000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1007, 'Безнал', 9, '', 4, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 54);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1008, 'Безнал', 9, '', 4, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 54);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1009, 'Безнал', 10, '', 5, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 154);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1010, 'Безнал', 10, '', 5, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 154);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1011, 'Безнал', 3, '', 4, TO_DATE('25.11.2018', 'dd.mm.yyyy'), 110);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1012, 'Безнал', 1, 'Запись для проверки работы Report1', 4, TO_DATE('30.11.2018', 'dd.mm.yyyy'), 200);

INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk,
                                              description, account_id_fk, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (100, 'Безнал', 6, '', 1, null, 430, '0 20 20 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk,
                                              description, account_id_fk, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (101, 'Безнал', 8, 'Заказан билет на поезд в Тамбов', 1, TO_DATE('3.01.2019', 'dd.mm.yyyy'), 3000, null, null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk,
                                              description, account_id_fk, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (102, 'Безнал', 7, '', 3, null, 360, '0 17 27 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk,
                                              description, account_id_fk, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (103, 'Безнал', 3, '', 5, null, 150, '15 12 * * * *', 5, TO_DATE('5.02.2019','dd.mm.yyyy'), TO_DATE('9.02.2019', 'dd.mm.yyyy'));