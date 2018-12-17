WITH RECURSIVE Report11 (budget_type_id, name, required, summa) AS
(
    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)
    FROM budget_type b_type, budget b
    WHERE b_type.budget_type_id = 1
                   AND b.operation_type = false
                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 1
                                                                   UNION ALL
                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 1)
     GROUP BY b_type.budget_type_id
           UNION ALL
    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)
    FROM Report11 rep
    INNER JOIN budget_type nested_budgT, budget nested_budg
    WHERE nested_budgT.group_id = rep.budget_type_id
                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk
                   AND nested_budg.operation_type = false
    GROUP BY(nested_budgT.budget_type_id)
),
Report12 (budget_type_id, name, required, summa) AS
(
    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)
    FROM budget_type b_type, budget b
    WHERE budget_type_id = 2
                   AND b.operation_type = false
                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 2
                                                                   UNION ALL
                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 2)
    GROUP BY b_type.budget_type_id
        UNION ALL
    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)
    FROM Report12 rep
    INNER JOIN budget_type nested_budgT, budget nested_budg
    WHERE nested_budgT.group_id = rep.budget_type_id
                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk
                   AND nested_budg.operation_type = false
    GROUP BY(nested_budgT.budget_type_id)
),
Report13 (budget_type_id, name, required, summa) AS
(
    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)
    FROM budget_type b_type, budget b
    WHERE budget_type_id = 3
                   AND b.operation_type = false
                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 3
                                                                   UNION ALL
                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 3)
    GROUP BY b_type.budget_type_id
        UNION ALL
    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)
    FROM Report13 rep
    INNER JOIN budget_type nested_budgT, budget nested_budg
    WHERE nested_budgT.group_id = rep.budget_type_id
                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk
                   AND nested_budg.operation_type = false
    GROUP BY(nested_budgT.budget_type_id)
),
totalSum (tSum) AS
(
    SELECT sum(charge_value)
    FROM budget
    WHERE operation_type = false 
)

SELECT * FROM Report11
UNION ALL
SELECT * FROM Report12
UNION ALL
SELECT * FROM Report13
UNION ALL
SELECT NULL, 'TOTAL: ', NULL, tSum
FROM totalSum;