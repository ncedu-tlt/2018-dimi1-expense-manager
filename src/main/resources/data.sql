INSERT INTO Person (person_id, login, pass, reg_date) VALUES (1, 'qwerty', '123456', TO_DATE('11.12.2018', 'dd.mm.yyyy'));
INSERT INTO Person (person_id, email, phone_number) VALUES (2, 'qqq@mail.ru', 89171546552);

INSERT INTO Accounts (account_id, person_id_fk, currency, balance, description) 
                        VALUES (4854630037067858, 1, 'RUB', 3500, 'Кредитная');
INSERT INTO Accounts (account_id, person_id_fk, currency, balance, description) 
                        VALUES (3608650012003000, 1, 'RUB', 500, 'Дебетовая');
INSERT INTO Accounts (account_id, person_id_fk, currency, balance, description) 
                        VALUES (4565123278982585, 2, 'USD', 5000, 'Дебетовая');
INSERT INTO Accounts (account_id, person_id_fk, currency, balance, description) 
                        VALUES (1, 1, 'RUB', 250, 'Наличные');
INSERT INTO Accounts (account_id, person_id_fk, currency, balance, description) 
                        VALUES (2, 2, 'RUB', 2000, 'Наличные');

INSERT INTO Budget_type (budget_type_id, name, required, check_max) 
                        VALUES (1, 'Питание', true, 7000);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (11, 1, 'Магазины', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (12, 1, 'Столовая', true);
INSERT INTO Budget_type (budget_type_id, name, required) 
                        VALUES (2, 'Квартирные платежи', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (21, 2, 'Коммуналка', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (22, 2, 'Кап ремонт', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (23, 2, 'Телефон/Интернет', true);
INSERT INTO Budget_type (budget_type_id, name, required) 
                        VALUES (3, 'Проезд', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (31, 3, 'Маршрутка', true);
INSERT INTO Budget_type (budget_type_id, group_id, name, required) 
                        VALUES (32, 3, 'Такси', false);
INSERT INTO Budget_type (budget_type_id, name, required) 
                        VALUES (100, 'Зарплата', true);
INSERT INTO Budget_type (budget_type_id, name, required) 
                        VALUES (101, 'Стипендия', true);

INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1000, 1, 100, '', 3608650012003000, TO_DATE('15.11.2018', 'dd.mm.yyyy'), 45000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1001, 1, 100, '', 4565123278982585, TO_DATE('13.11.2018', 'dd.mm.yyyy'), 30000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1002, 0, 11, '', 3608650012003000, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 5000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1003, 0, 21, '', 4565123278982585, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 4500);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1004, 0, 31, '', 3608650012003000, TO_DATE('27.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1005, 0, 31, '', 3608650012003000, TO_DATE('28.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1006, 0, 11, 'Предновогодняя закупка к праздничному столу', 4565123278982585, TO_DATE('20.12.2018', 'dd.mm.yyyy'), 5000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1007, 0, 31, '', 1, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 54);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1008, 0, 31, '', 1, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 54);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1009, 0, 32, '', 2, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 154);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1010, 0, 32, '', 2, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 154);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1011, 0, 12, '', 1, TO_DATE('25.11.2018', 'dd.mm.yyyy'), 110);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_fk, description, account_id_fk, operation_date, charge_value)
                        VALUES (1012, 0, 1, 'Запись для проверки работы Report1', 1, TO_DATE('30.11.2018', 'dd.mm.yyyy'), 200);

INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk, 
                                              description, account_id_fk, operation_date, charge_value, 
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (100, 0, 22, '', 4854630037067858, null, 430, '0 20 20 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk, 
                                              description, account_id_fk, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (101, 0, 3, 'Заказан билет на поезд в Тамбов', 4854630037067858, TO_DATE('3.01.2019', 'dd.mm.yyyy'), 3000, null, null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk, 
                                              description, account_id_fk, operation_date, charge_value, 
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (102, 0, 23, '', 4565123278982585, null, 360, '0 17 27 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_fk, 
                                              description, account_id_fk, operation_date, charge_value, 
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (103, 0, 12, '', 2, null, 150, '15 12 * * * *', 5, TO_DATE('5.02.2019','dd.mm.yyyy'), TO_DATE('9.02.2019', 'dd.mm.yyyy'));