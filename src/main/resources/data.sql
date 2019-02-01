INSERT INTO Person (person_id, login, pass, reg_date) VALUES (1, 'qwerty', '123456', TO_DATE('14.12.2018', 'dd.mm.yyyy'));
INSERT INTO Person (person_id, email, phone_number) VALUES (2, 'slicerin@mail.ru', 89275346764);

INSERT INTO Accounts (account_id, person_id_gg, currency, balance, description)
                        VALUES (4794635537067869, 1, 'RUB', 3500, 'Кредитная');
INSERT INTO Accounts (account_id, person_id_gg, currency, balance, description)
                        VALUES (3086500120403020, 1, 'RUB', 500, 'Дебетовая');
INSERT INTO Accounts (account_id, person_id_gg, currency, balance, description)
                        VALUES (5865123248982325, 2, 'USD', 5000, 'Дебетовая');
INSERT INTO Accounts (account_id, person_id_gg, currency, balance, description)
                        VALUES (1, 1, 'RUB', 600, 'Наличные');
INSERT INTO Accounts (account_id, person_id_gg, currency, balance, description)
                        VALUES (2, 2, 'RUB', 4000, 'Наличные');

INSERT INTO Budget_type (budget_type_id, name, required, check_max)
                        VALUES (1, 'Питание', true, 8000);
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

INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1000, 1, 100, '', 3086500120403020, TO_DATE('14.11.2018', 'dd.mm.yyyy'), 50000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1001, 1, 100, '', 5865123248982325, TO_DATE('12.11.2018', 'dd.mm.yyyy'), 35000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1002, 0, 11, '', 3086500120403020, TO_DATE('22.11.2018', 'dd.mm.yyyy'), 7500);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1003, 0, 21, '', 5865123248982325, TO_DATE('23.11.2018', 'dd.mm.yyyy'), 5000);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1004, 0, 31, '', 3086500120403020, TO_DATE('27.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1005, 0, 31, '', 3086500120403020, TO_DATE('29.11.2018', 'dd.mm.yyyy'), 50);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1006, 0, 11, 'Закупка к дню рождения', 5865123248982325, TO_DATE('20.12.2018', 'dd.mm.yyyy'), 4500);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1007, 0, 31, '', 1, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 56);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1008, 0, 31, '', 1, TO_DATE('22.11.2018', 'dd.mm.yyyy'), 56);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1009, 0, 32, '', 2, TO_DATE('20.11.2018', 'dd.mm.yyyy'), 162);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1010, 0, 32, '', 2, TO_DATE('21.11.2018', 'dd.mm.yyyy'), 162);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1011, 0, 12, '', 1, TO_DATE('24.11.2018', 'dd.mm.yyyy'), 120);
INSERT INTO Budget (budget_id, operation_type, budget_type_id_gg, description, account_id_gg, operation_date, charge_value)
                        VALUES (1012, 0, 1, 'Запись для проверки работы Report', 1, TO_DATE('30.11.2018', 'dd.mm.yyyy'), 250);

INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_gg,
                                              description, account_id_gg, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (100, 0, 22, '', 4794635537067869, null, 430, '0 20 20 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_gg,
                                              description, account_id_gg, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (101, 0, 3, 'Заказан билет на поезд в Нижний Новгород',4794635537067869, TO_DATE('5.01.2019', 'dd.mm.yyyy'), 3000, null, null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_gg,
                                              description, account_id_gg, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (102, 0, 23, '', 5865123248982325, null, 360, '0 17 27 * * *', null, null, null);
INSERT INTO Plan_budget (plan_budget_id, operation_type, budget_type_id_gg,
                                              description, account_id_gg, operation_date, charge_value,
                                              regular_mask, repeat_count, start_date, end_date)
                       VALUES (103, 0, 12, '', 2, null, 150, '15 12 * * * *', 5, TO_DATE('6.02.2019','dd.mm.yyyy'), TO_DATE('11.02.2019', 'dd.mm.yyyy'));
