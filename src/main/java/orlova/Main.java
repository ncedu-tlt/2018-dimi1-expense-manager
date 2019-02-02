package orlova;

import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);



        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
        while(true) {
            int choice = menuMain();
            int choiceTable;
            int choiceValue;
            int choiceReport;
            switch (choice) {
                case 1:                         //Выбран пункт главного меню "Добавить"
                    choiceTable = menuTable();      //Меню выбора таблиц
                    switch (choiceTable){
                        case 1:                         //Выбран пункт меню для добавления записи в таблицу Person
                            addFieldPerson();
                            break;
                        case 2:                         //Выбран пункт меню для добавления записи в таблицу Accounts
                            System.out.println("Input person id, hwo is creating new account: ");
                            BigInteger persId = in.nextBigInteger();
                            PersonImpl pers = new PersonImpl(conn);
                            if (pers.isPersonExist(persId)){
                                pers.setPersonId(persId);
                                addFieldAccount(pers);
                            }
                            break;
                        case 3:                         //Выбран пункт меню для добавления записи в таблицу Budget
                            addFieldBudget();
                            break;
                        case 4:                         //Выбран пункт меню для добавления записи в таблицу BudgetType
                            addFieldsBudgetType();
                            break;
                        case 5:                         //Выбран пункт меню для добавления записи в таблицу PlanBudget
                            addFieldPlanBudget();
                            break;
                        case 0:                         //Выбран пункт "Назад"
                            break;
                    }
                    break;
                case 2:                         //Выбран пункт главного меню "Удалить"
                    choiceTable = menuTable();      //Меню выбора таблиц
                    switch (choiceTable){
                        case 1:                         //Выбран пункт меню для удаления записи из таблицы Person
                            deleteFieldPerson();
                            break;
                        case 2:                         //Выбран пункт меню для удаления записи из таблицы Accounts
                            System.out.println("Input person id, hwo is creating new account: ");
                            BigInteger persId = in.nextBigInteger();
                            PersonImpl pers = new PersonImpl(conn);
                            if (pers.isPersonExist(persId)){
                                pers.setPersonId(persId);
                                deleteFieldAccount(pers);
                            }
                            break;
                        case 3:                         //Выбран пункт меню для удаления записи из таблицы Budget
                            deleteFieldBudget();
                            break;
                        case 4:                         //Выбран пункт меню для удаления записи из таблицы BudgetType
                            deleteFieldBudgetType();
                            break;
                        case 5:                         //Выбран пункт меню для удаления записи из таблицы PlanBudget
                            deleteFieldPlanBudget();
                            break;
                        case 0:                         //Выбран пункт "Назад"
                            break;
                    }
                    break;
                case 3:                         //Выбран пункт главного меню "Изменить"
                    choiceTable = menuTable();      //Меню выбора таблиц
                    switch (choiceTable){
                        case 1:                         //Выбран пункт меню для изменения записи в таблице Person
                            modifyFieldPerson();
                            break;
                        case 2:                         //Выбран пункт меню для изменения записи в таблице Accounts
                            System.out.println("Input person id, hwo is creating new account: ");
                            BigInteger persId = in.nextBigInteger();
                            PersonImpl pers = new PersonImpl(conn);
                            if (pers.isPersonExist(persId)){
                                pers.setPersonId(persId);
                                modifyFieldAccount(pers);
                            }
                            break;
                        case 3:                         //Выбран пункт меню для изменения записи в таблице Budget
                            modifyFieldBudget();
                            break;
                        case 4:                         //Выбран пункт меню для изменения записи в таблице BudgetType
                            modifyFieldBudgetType();
                            break;
                        case 5:                         //Выбран пункт меню для изменения записи в таблице PlanBudget
                            modifyFieldPlanBudget();
                            break;
                        case 0:                         //Выбран пункт "Назад"
                            break;
                    }
                    break;
                case 4:                         //Выбран пункт главного меню "Вывод"
                    choiceValue = menuChoice();     //Меню выбора репортов/таблиц
                    switch(choiceValue) {
                        case 1:                         //Выбран пункт для отображения меню репортов
                            choiceReport = menuReport();        //Меню выбора репортов
                            switch (choiceReport) {
                                case 1:                             //Выбран пункт меню для отображения репорта 1
                                    showReport1();
                                    break;
                                case 2:                             //Выбран пункт меню для отображения репорта 2
                                    break;
                                case 3:                             //Выбран пункт меню для отображения репорта 3
                                    break;
                                case 0:                             //Выбран пункт "Назад"
                                    break;
                            }
                            break;
                        case 2:                         //Выбран пункт меню для отображения меню таблиц
                            choiceTable = menuTable();      //Меню выбора таблиц
                            switch (choiceTable){
                                case 1:                         //Выбран пункт меню для отображения данных таблицы Person
                                    showFieldsPerson();
                                    break;
                                case 2:                         //Выбран пункт меню для отображения данных таблицы Accounts
                                    showFieldsAccounts();
                                    break;
                                case 3:                         //Выбран пункт меню для отображения данных таблицы Budget
                                    showFieldsBudget();
                                    break;
                                case 4:                         //Выбран пункт меню для отображения данных таблицы BudgetType
                                    showFieldsBudgetType();
                                    break;
                                case 5:                         //Выбран пункт меню для отображения данных таблицы PlanBudget
                                    showFieldsPlanBudget();
                                    break;
                                case 0:                         //Выбран пункт "Назад"
                                    break;
                            }
                            break;
                        case 0:                         //Выбран пункт меню "Назад"
                            break;
                    }
                    break;
                case 0:                             //Выбран пункт главного меню "Выход"
                    System.exit(0);
                    break;
                default:                            //Остальные случаи некорректного ввода
                    System.out.println("Такого пункта нет, повторите ввод");
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int menuMain() {
        int choice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Меню:");
        System.out.println("1 - Добавить");
        System.out.println("2 - Удалить");
        System.out.println("3 - Изменить");
        System.out.println("4 - Вывод");
        System.out.println("0 - Выход");

        try{
            choice =  input.nextInt();
        } catch(Exception ex){
            System.out.println("Вы ввели некорректное значение");
        }
        return choice;
    }

    public static int menuTable(){
        int choice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Выберите таблицу:");
        System.out.println("1 - Person");
        System.out.println("2 - Accounts");
        System.out.println("3 - Budget");
        System.out.println("4 - BudgetType");
        System.out.println("5 - PlanBudget");
        System.out.println("0 - Back");

        try{
            choice =  input.nextInt();
        } catch(Exception ex){
            System.out.println("Вы ввели некорректное значение");
        }
        return choice;
    }

    public static int menuChoice() {
        int choice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Выберите:");
        System.out.println("1 - Перейти к выбору репортов");
        System.out.println("2 - Перейти к выбору таблиц");
        System.out.println("0 - Назад");

        try{
            choice =  input.nextInt();
        } catch(Exception ex){
            System.out.println("Вы ввели некорректное значение");
        }
        return choice;
    }

    public static int menuReport() {
        int choice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Выберите:");
        System.out.println("1 - Вывод расходов по группам с суммированием");
        System.out.println("2 - Вывод обязательных/необязательных расходов с суммированием");
        System.out.println("3 - Планируемые операции");
        System.out.println("0 - Назад");

        try{
            choice =  input.nextInt();
        } catch(Exception ex){
            System.out.println("Вы ввели некорректное значение");
        }
        return choice;
    }

    public static void addFieldPerson() {
        Scanner in = new Scanner(System.in);

        PersonImpl insertPerson = new PersonImpl(conn);
        insertPerson.setPersonId();
        System.out.print("Input your login: ");
        insertPerson.setLogin(in.nextLine());
        System.out.print("Input your password: ");
        insertPerson.setPass(in.nextLine());
        System.out.print("Input your email (optional): ");
        insertPerson.setEmail(in.nextLine());
        System.out.print("Input description (optional): ");
        insertPerson.setDescription(in.nextLine());
        insertPerson.setRegDate();
        System.out.print("Input your phone number (optional): ");
        insertPerson.setPhonenumber(in.nextLine());

        insertPerson.create();
    }

    public static void addFieldAccount(PersonImpl person){
        Scanner in = new Scanner(System.in);

        AccountsImpl insertAccount = new AccountsImpl(conn);
        insertAccount.setAccountId();
        insertAccount.setPersonId(person.getPersonId());
        System.out.print("Input your account's number: ");
        String checkAccNum = in.nextLine();
        while(!insertAccount.isAccountNumberNotExist(checkAccNum, person.getPersonId())){
            checkAccNum = in.nextLine();
        }
        insertAccount.setAccountNumber(checkAccNum);
        insertAccount.setCurrency();
        System.out.print("Input your card balance: ");
        Double checkBalance = 0.0;
        try{
            checkBalance = in.nextDouble();
        } catch (InputMismatchException e){
            System.out.print("You entered an incorrect value. Try again: ");
            in.next();
            checkBalance = in.nextDouble();
        }
        insertAccount.setBalance(checkBalance);
        in.nextLine();
        System.out.print("Input card's type (optional): ");
        insertAccount.setDescription(in.nextLine());

        insertAccount.create();
    }

    public static void addFieldsBudgetType(){
        Scanner in = new Scanner(System.in);

        BudgetTypeImpl insertBudgetType = new BudgetTypeImpl(conn);
        insertBudgetType.setBudgetTypeId();

        System.out.println("Choose: ");
        System.out.print("1 - new budget type group\n2 - new budget type in one of groups\n");
        int choose = in.nextInt();
        if (choose == 1){
            insertBudgetType.setGroupId(null);
        } else if (choose == 2){
            System.out.println("Select the group your type will belong to:");
            insertBudgetType.showGroups();
            int chooseGr = in.nextInt();
            if(insertBudgetType.isGroupExsist(chooseGr)){
                insertBudgetType.setGroupId(BigInteger.valueOf(chooseGr));
            } else {
                return;
            }
        } else {
            System.out.println("You input incorrect number");
        }
        System.out.print("Input name of new budget type group: ");
        in.nextLine();
        insertBudgetType.setName(in.nextLine());
        System.out.println("Select group's priority: ");
        System.out.print("1 - required\n0 - not necessary\n");
        int boolVal = in.nextInt();
        if (boolVal == 1){
            insertBudgetType.setRequired(true);
        } else if (boolVal == 0){
            insertBudgetType.setRequired(false);
        } else {
            System.out.println("You input incorrect number");
            return;
        }
        System.out.print("Input a limit on the amount of expenses for this group (optional): ");
        insertBudgetType.setCheckMax(in.nextDouble());

        insertBudgetType.create();
    }

    public static void addFieldPlanBudget(){
        Scanner in = new Scanner(System.in);

        PlanBudgetImpl insertPlanBudget = new PlanBudgetImpl(conn);
        insertPlanBudget.setPlanBudgetId();
        System.out.println("Select operation type: ");
        System.out.print("1 - Нал\n2 - Безнал\n");
        int boolVal = in.nextInt();
        if (boolVal == 1){
            insertPlanBudget.setOperationType("Нал");
        } else if (boolVal == 2){
            insertPlanBudget.setOperationType("Безнал");
        } else {
            System.out.println("You input incorrect number");
            return;
        }
        System.out.println("Input budget type's ID: ");
        BudgetTypeImpl obj = new BudgetTypeImpl(conn);
        obj.showTable();
        int choice = in.nextInt();
        if (obj.isBudgetTypeExists(BigInteger.valueOf(choice))){
            insertPlanBudget.setBudgetTypeId(BigInteger.valueOf(choice));
        }
        in.nextLine();
        System.out.print("Input description (optional): ");
        insertPlanBudget.setDescription(in.nextLine());
        System.out.print("Input account's ID: ");
        AccountsImpl objAcc = new AccountsImpl(conn);
        BigInteger checkAcc = in.nextBigInteger();
        if (objAcc.isAccountExists(checkAcc)){
            insertPlanBudget.setAccountId(checkAcc);
        } else {
            return;
        }
        in.nextLine();
        try{
            System.out.print("Input operation date in format dd-MM-yyyy (optional): ");
            String operDate = in.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = format.parse(operDate);
            java.sql.Date opD = new java.sql.Date(utilDate.getTime());
            insertPlanBudget.setOperationDate(opD);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("Input charge value (optional): ");
        insertPlanBudget.setChargeValue(in.nextDouble());
        in.nextLine();
        System.out.print("Input regular mask (optional): ");
        insertPlanBudget.setRegularMask(in.nextLine());
        in.nextLine();
        System.out.print("Inpuе the number of repetition of the operation (optional): ");
        insertPlanBudget.setRepeatCount(in.nextInt());
        in.nextLine();
        try{
            System.out.print("Input start date of operation in format dd/MM/yyyy (optional): ");
            String operStartDate = in.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilStartDate = format.parse(operStartDate);
            java.sql.Date opStartD = new java.sql.Date(utilStartDate.getTime());
            insertPlanBudget.setStartDate(opStartD);
        } catch (Exception e){
            e.printStackTrace();
        }
        in.nextLine();
        try{
            System.out.print("Input end date of operation in format dd/MM/yyyy (optional): ");
            String operEndDate = in.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilEndDate = format.parse(operEndDate);
            java.sql.Date opEndD = new java.sql.Date(utilEndDate.getTime());
            insertPlanBudget.setEndDate(opEndD);
        } catch (Exception e){
            e.printStackTrace();
        }

        insertPlanBudget.create();
    }

    public static void addFieldBudget() {
        Scanner in = new Scanner(System.in);

        BudgetImpl insertBudget = new BudgetImpl(conn);
        insertBudget.setBudgetId();
        System.out.println("Select operation type: ");
        System.out.print("1 - Нал\n2 - Безнал\n");
        int boolVal = in.nextInt();
        if (boolVal == 1){
            insertBudget.setOperationType("Нал");
        } else if (boolVal == 2){
            insertBudget.setOperationType("Безнал");
        } else {
            System.out.println("You input incorrect number");
            return;
        }
        System.out.println("Input budget type's ID: ");
        BudgetTypeImpl obj = new BudgetTypeImpl(conn);
        obj.showTable();
        int choice = in.nextInt();
        if (obj.isBudgetTypeExists(BigInteger.valueOf(choice))){
            insertBudget.setBudgetTypeId(BigInteger.valueOf(choice));
        }
        in.nextLine();
        System.out.print("Input description (optional): ");
        insertBudget.setDescription(in.nextLine());
        System.out.print("Input account's ID: ");
        AccountsImpl objAcc = new AccountsImpl(conn);
        BigInteger checkAcc = in.nextBigInteger();
        if (objAcc.isAccountExists(checkAcc)){
            insertBudget.setAccountId(checkAcc);
        } else {
            return;
        }
        in.nextLine();
        try{
            System.out.print("Input operation date in format dd-MM-yyyy (optional): ");
            String operDate = in.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = format.parse(operDate);
            java.sql.Date opD = new java.sql.Date(utilDate.getTime());
            insertBudget.setOperationDate(opD);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print("Input charge value (optional): ");
        insertBudget.setChargeValue(in.nextDouble());

        insertBudget.create();
    }

    public static void deleteFieldPerson(){
        Scanner in = new Scanner(System.in);
        PersonImpl objPersForDelete = new PersonImpl(conn);
        System.out.print("Enter the Primary key of the user you want to delete: ");
        objPersForDelete.setPersonId(in.nextBigInteger());
        objPersForDelete.delete();
    }

    public static void deleteFieldAccount(PersonImpl pers){
        Scanner in = new Scanner(System.in);
        AccountsImpl objAccountForDelete = new AccountsImpl(conn);
        objAccountForDelete.setPersonId(pers.getPersonId());
        System.out.print("Enter the account number that you want to delete: ");
        objAccountForDelete.setAccountNumber(in.nextLine());
        objAccountForDelete.delete();
    }

    public static void deleteFieldBudgetType(){
        Scanner in = new Scanner(System.in);
        BudgetTypeImpl objBudgetTypeForDelete = new BudgetTypeImpl(conn);
        System.out.print("Enter the Primary key of the budget type you want to delete: ");
        objBudgetTypeForDelete.setBudgetTypeId(in.nextBigInteger());
        objBudgetTypeForDelete.delete();
    }

    public static void deleteFieldPlanBudget() {
        Scanner in = new Scanner(System.in);
        PlanBudgetImpl objPlanBudgetForDelete = new PlanBudgetImpl(conn);
        System.out.print("Enter the Primary key of the plan budget you want to delete: ");
        objPlanBudgetForDelete.setPlanBudgetId(in.nextBigInteger());
        objPlanBudgetForDelete.delete();
    }

    public static void deleteFieldBudget() {
        Scanner in = new Scanner(System.in);
        BudgetImpl objBudgetForDelete = new BudgetImpl(conn);
        System.out.print("Enter the Primary key of the budget you want to delete: ");
        objBudgetForDelete.setBudgetId(in.nextBigInteger());
        objBudgetForDelete.delete();
    }

    public static void modifyFieldPerson(){
        Scanner in = new Scanner(System.in);

        PersonImpl objPersForUpdate = new PersonImpl(conn);

        System.out.println("Enter the Primary key of the user you want to update: ");
        int personIdForUpdate = in.nextInt();
        if (objPersForUpdate.isPersonExist(BigInteger.valueOf(personIdForUpdate))){
            objPersForUpdate.load(BigInteger.valueOf(personIdForUpdate));
            objPersForUpdate.setPersonId(BigInteger.valueOf(personIdForUpdate));
            System.out.print("Enter new field values if necessary\n" +
                    "(If you don't want to change the field, skip it)\n\n");
            String newLogin, newPass, newEmail, newPhone, newDescr;
            in.nextLine();
            System.out.print("Input your new login: ");
            newLogin = in.nextLine();
            in.nextLine();
            System.out.print("Input your new password: ");
            newPass = in.nextLine();
            in.nextLine();
            System.out.print("Input your new email: ");
            newEmail = in.nextLine();
            in.nextLine();
            System.out.print("Input your new phone number: ");
            newPhone = in.nextLine();
            in.nextLine();
            System.out.print("Input your new description: ");
            newDescr = in.nextLine();
            in.nextLine();

            if (!"".equals(newLogin.trim())) { objPersForUpdate.setLogin(newLogin); }
            if (!"".equals(newPass.trim())) { objPersForUpdate.setPass(newPass); }
            if (!"".equals(newEmail.trim())) { objPersForUpdate.setEmail(newEmail); }
            if (!"".equals(newPhone.trim())) { objPersForUpdate.setPhonenumber(newPhone); }
            if (!"".equals(newDescr.trim())) { objPersForUpdate.setDescription(newDescr); }

            objPersForUpdate.update();
        }
    }

    public static void modifyFieldAccount(PersonImpl pers){
        Scanner in = new Scanner(System.in);

        AccountsImpl objAccountForUpdate = new AccountsImpl(conn);

        System.out.println("Enter the account's number that you want to update: ");
        String accNumForUpdate = in.nextLine();
        if (!objAccountForUpdate.isAccountNumberNotExist(accNumForUpdate, pers.getPersonId())){
            objAccountForUpdate.load(objAccountForUpdate.findAccountId(accNumForUpdate, pers.getPersonId()));
            objAccountForUpdate.setAccountId(objAccountForUpdate.findAccountId(accNumForUpdate, pers.getPersonId()));
            System.out.print("Enter new field values if necessary\n" +
                    "(If you don't want to change the field, skip it)\n\n");
            String newCurrency, newDescr;
            Double newBalance;
            System.out.print("Input your new currency: ");
            newCurrency = in.nextLine();
            in.nextLine();
            System.out.print("Input your new balance: ");
            newBalance = in.nextDouble();
            in.nextLine();
            System.out.print("Input your new description: ");
            newDescr = in.nextLine();
            in.nextLine();

            if (!"".equals(newCurrency.trim())) { objAccountForUpdate.setCurrency(newCurrency); }
            if (newBalance != null) { objAccountForUpdate.setBalance(newBalance); }
            if (!"".equals(newDescr.trim())) { objAccountForUpdate.setDescription(newDescr); }

            objAccountForUpdate.update();
        }
    }

    public static void modifyFieldBudgetType(){
        Scanner in = new Scanner(System.in);

        BudgetTypeImpl objBudgTypeForUpdate = new BudgetTypeImpl(conn);

        System.out.println("Enter the Primary key of the budget type you want to update: ");
        int budgetTypeIdForUpdate = in.nextInt();
        if (objBudgTypeForUpdate.isBudgetTypeExists(BigInteger.valueOf(budgetTypeIdForUpdate))){
            objBudgTypeForUpdate.load(BigInteger.valueOf(budgetTypeIdForUpdate));
            objBudgTypeForUpdate.setBudgetTypeId(BigInteger.valueOf(budgetTypeIdForUpdate));
            System.out.print("Enter new field values if necessary\n" +
                    "(If you don't want to change the field, skip it)\n\n");
            BigInteger newGroupId = null;
            String newName;
            Boolean newRequired = null;
            Double newCheckMax = null;
            in.nextLine();

            System.out.println("Select the new group of variable type:");
            objBudgTypeForUpdate.showGroups();
            int chooseGr = in.nextInt();
            if(objBudgTypeForUpdate.isGroupExsist(chooseGr)){
                newGroupId = BigInteger.valueOf(chooseGr);
            }
            in.nextLine();
            System.out.print("Input new type's name: ");
            newName = in.nextLine();
            in.nextLine();
            System.out.print("Input new type's priority: ");
            System.out.print("1 - required\n0 - not necessary\n");
            int newBoolVal = in.nextInt();
            if (newBoolVal == 1){
                newRequired = true;
            } else if (newBoolVal == 0){
                newRequired = false;
            }
            in.nextLine();
            System.out.print("Input a limit on the amount of expenses for this group (optional): ");
            newCheckMax = in.nextDouble();

            if (newGroupId != null) { objBudgTypeForUpdate.setGroupId(newGroupId); }
            if (!"".equals(newName.trim())) { objBudgTypeForUpdate.setName(newName); }
            if (newRequired != null) { objBudgTypeForUpdate.setRequired(newRequired); }
            if (newCheckMax != null) { objBudgTypeForUpdate.setCheckMax(newCheckMax); }

            objBudgTypeForUpdate.update();
        }
    }

    public static void modifyFieldPlanBudget() {
        Scanner in = new Scanner(System.in);

        PlanBudgetImpl objPlanBudgForUpdate = new PlanBudgetImpl(conn);

        System.out.println("Enter the Primary key of the plan budget you want to update: ");
        BigInteger planBudgetIdForUpdate = in.nextBigInteger();
        if (objPlanBudgForUpdate.isPlanBudgetExists(planBudgetIdForUpdate)){
            objPlanBudgForUpdate.load(planBudgetIdForUpdate);
            objPlanBudgForUpdate.setPlanBudgetId(planBudgetIdForUpdate);
            System.out.print("Enter new field values if necessary\n" +
                    "(If you don't want to change the field, skip it)\n\n");
            BigInteger newBudgetTypeId, newAccountId;
            String newOperationType, newDescription, newRegularMask;
            java.sql.Date newOperationDate = null, newStartDate = null, newEndDate = null;
            Double newChargeValue;
            Integer newRepeatCount = null;

            in.nextLine();
            System.out.println("Select new operation type: ");
            System.out.print("1 - Нал\n2 - Безнал\n");
            int boolVal = in.nextInt();
            if (boolVal == 1){
                newOperationType = "Нал";
            } else if (boolVal == 2){
                newOperationType = "Безнал";
            } else {
                newOperationType = null;
            }
            System.out.println("Input new budget type's ID: ");
            BudgetTypeImpl obj = new BudgetTypeImpl(conn);
            obj.showTable();
            int choice = in.nextInt();
            if (obj.isBudgetTypeExists(BigInteger.valueOf(choice))){
                newBudgetTypeId = BigInteger.valueOf(choice);
            } else {
                newBudgetTypeId = null;
            }
            in.nextLine();
            System.out.print("Input new description (optional): ");
            newDescription = in.nextLine();
            System.out.print("Input new account's ID: ");
            AccountsImpl objAcc = new AccountsImpl(conn);
            BigInteger checkAcc = in.nextBigInteger();
            if (objAcc.isAccountExists(checkAcc)){
                newAccountId = checkAcc;
            } else {
                newAccountId = null;
            }
            in.nextLine();
            try{
                System.out.print("Input new operation date in format dd-MM-yyyy (optional): ");
                String operDate = in.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date utilDate = format.parse(operDate);
                newOperationDate = new java.sql.Date(utilDate.getTime());
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.print("Input new charge value (optional): ");
            newChargeValue = in.nextDouble();
            in.nextLine();
            System.out.print("Input new regular mask (optional): ");
            newRegularMask = in.nextLine();
            in.nextLine();
            System.out.print("Inpuе new number of repetition of the operation (optional): ");
            newRepeatCount = in.nextInt();
            in.nextLine();
            try{
                System.out.print("Input  new start date of operation in format dd/MM/yyyy (optional): ");
                String operStartDate = in.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date utilStartDate = format.parse(operStartDate);
                newStartDate = new java.sql.Date(utilStartDate.getTime());
            } catch (Exception e){
                e.printStackTrace();
            }
            in.nextLine();
            try{
                System.out.print("Input new end date of operation in format dd/MM/yyyy (optional): ");
                String operEndDate = in.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date utilEndDate = format.parse(operEndDate);
                newEndDate = new java.sql.Date(utilEndDate.getTime());
            } catch (Exception e){
                e.printStackTrace();
            }


            if (newOperationType != null) { objPlanBudgForUpdate.setOperationType(newOperationType); }
            if (newBudgetTypeId != null) { objPlanBudgForUpdate.setBudgetTypeId(newBudgetTypeId); }
            if (newDescription.trim().length() != 0) { objPlanBudgForUpdate.setDescription(newDescription); }
            if (newAccountId != null) { objPlanBudgForUpdate.setAccountId(newAccountId); }
            if (newOperationDate != null) { objPlanBudgForUpdate.setOperationDate(newOperationDate);}
            if (newChargeValue != null) { objPlanBudgForUpdate.setChargeValue(newChargeValue);}
            if (newRegularMask.trim().length() != 0) { objPlanBudgForUpdate.setRegularMask(newRegularMask);}
            if (newRepeatCount != null) { objPlanBudgForUpdate.setRepeatCount(newRepeatCount);}
            if (newStartDate != null) { objPlanBudgForUpdate.setStartDate(newStartDate);}
            if (newEndDate != null) { objPlanBudgForUpdate.setEndDate(newEndDate);}

            objPlanBudgForUpdate.update();
        }
    }

    public static void modifyFieldBudget() {
        Scanner in = new Scanner(System.in);

        BudgetImpl objBudgForUpdate = new BudgetImpl(conn);

        System.out.println("Enter the Primary key of the budget you want to update: ");
        BigInteger budgetIdForUpdate = in.nextBigInteger();
        if (objBudgForUpdate.isBudgetExists(budgetIdForUpdate)){
            objBudgForUpdate.load(budgetIdForUpdate);
            objBudgForUpdate.setBudgetId(budgetIdForUpdate);

            System.out.print("Enter new field values if necessary\n" +
                    "(If you don't want to change the field, skip it)\n\n");

            BigInteger newBudgetTypeId, newAccountId;
            String newOperationType, newDescription;
            java.sql.Date newOperationDate = null;
            Double newChargeValue;

            in.nextLine();
            System.out.println("Select new operation type: ");
            System.out.print("1 - Нал\n2 - Безнал\n");
            int boolVal = in.nextInt();
            if (boolVal == 1){
                newOperationType = "Нал";
            } else if (boolVal == 2){
                newOperationType = "Безнал";
            } else {
                newOperationType = null;
            }
            System.out.println("Input new budget type's ID: ");
            BudgetTypeImpl obj = new BudgetTypeImpl(conn);
            obj.showTable();
            int choice = in.nextInt();
            if (obj.isBudgetTypeExists(BigInteger.valueOf(choice))){
                newBudgetTypeId = BigInteger.valueOf(choice);
            } else {
                newBudgetTypeId = null;
            }
            in.nextLine();
            System.out.print("Input new description (optional): ");
            newDescription = in.nextLine();
            System.out.print("Input new account's ID: ");
            AccountsImpl objAcc = new AccountsImpl(conn);
            BigInteger checkAcc = in.nextBigInteger();
            if (objAcc.isAccountExists(checkAcc)){
                newAccountId = checkAcc;
            } else {
                newAccountId = null;
            }
            in.nextLine();
            try{
                System.out.print("Input new operation date in format dd-MM-yyyy (optional): ");
                String operDate = in.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date utilDate = format.parse(operDate);
                newOperationDate = new java.sql.Date(utilDate.getTime());
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.print("Input new charge value (optional): ");
            newChargeValue = in.nextDouble();


            if (newOperationType != null) { objBudgForUpdate.setOperationType(newOperationType); }
            if (newBudgetTypeId != null) { objBudgForUpdate.setBudgetTypeId(newBudgetTypeId); }
            if (newDescription.trim().length() != 0) { objBudgForUpdate.setDescription(newDescription); }
            if (newAccountId != null) { objBudgForUpdate.setAccountId(newAccountId); }
            if (newOperationDate != null) { objBudgForUpdate.setOperationDate(newOperationDate);}
            if (newChargeValue != null) { objBudgForUpdate.setChargeValue(newChargeValue);}

            objBudgForUpdate.update();
        }
    }

    public static void showReport1() throws SQLException{
        System.out.println("Расходы за месяц по группам:");
        ResultSet res = stmt.executeQuery(
                "WITH RECURSIVE Report11 (budget_type_id, name, required, summa) AS\n" +
                "(\n" +
                "    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)\n" +
                "    FROM budget_type b_type, budget b\n" +
                "    WHERE b_type.budget_type_id = 1\n" +
                "                   AND b.operation_type = false\n" +
                "                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 1\n" +
                "                                                                   UNION ALL\n" +
                "                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 1)\n" +
                "     GROUP BY b_type.budget_type_id\n" +
                "           UNION ALL\n" +
                "    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)\n" +
                "    FROM Report11 rep\n" +
                "    INNER JOIN budget_type nested_budgT, budget nested_budg\n" +
                "    WHERE nested_budgT.group_id = rep.budget_type_id\n" +
                "                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk\n" +
                "                   AND nested_budg.operation_type = false\n" +
                "    GROUP BY(nested_budgT.budget_type_id)\n" +
                "),\n" +
                "Report12 (budget_type_id, name, required, summa) AS\n" +
                "(\n" +
                "    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)\n" +
                "    FROM budget_type b_type, budget b\n" +
                "    WHERE budget_type_id = 2\n" +
                "                   AND b.operation_type = false\n" +
                "                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 2\n" +
                "                                                                   UNION ALL\n" +
                "                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 2)\n" +
                "    GROUP BY b_type.budget_type_id\n" +
                "        UNION ALL\n" +
                "    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)\n" +
                "    FROM Report12 rep\n" +
                "    INNER JOIN budget_type nested_budgT, budget nested_budg\n" +
                "    WHERE nested_budgT.group_id = rep.budget_type_id\n" +
                "                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk\n" +
                "                   AND nested_budg.operation_type = false\n" +
                "    GROUP BY(nested_budgT.budget_type_id)\n" +
                "),\n" +
                "Report13 (budget_type_id, name, required, summa) AS\n" +
                "(\n" +
                "    SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value)\n" +
                "    FROM budget_type b_type, budget b\n" +
                "    WHERE budget_type_id = 3\n" +
                "                   AND b.operation_type = false\n" +
                "                   AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type WHERE group_id = 3\n" +
                "                                                                   UNION ALL\n" +
                "                                                                   SELECT budget_type_id FROM budget_type WHERE budget_type_id = 3)\n" +
                "    GROUP BY b_type.budget_type_id\n" +
                "        UNION ALL\n" +
                "    SELECT nested_budgT.budget_type_id, nested_budgT.name, nested_budgT.required, sum(nested_budg.charge_value)\n" +
                "    FROM Report13 rep\n" +
                "    INNER JOIN budget_type nested_budgT, budget nested_budg\n" +
                "    WHERE nested_budgT.group_id = rep.budget_type_id\n" +
                "                   AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk\n" +
                "                   AND nested_budg.operation_type = false\n" +
                "    GROUP BY(nested_budgT.budget_type_id)\n" +
                "),\n" +
                "totalSum (tSum) AS\n" +
                "(\n" +
                "    SELECT sum(charge_value)\n" +
                "    FROM budget\n" +
                "    WHERE operation_type = false \n" +
                ")\n" +
                "\n" +
                "SELECT * FROM Report11\n" +
                "UNION ALL\n" +
                "SELECT * FROM Report12\n" +
                "UNION ALL\n" +
                "SELECT * FROM Report13\n" +
                "UNION ALL\n" +
                "SELECT NULL, 'TOTAL: ', NULL, tSum\n" +
                "FROM totalSum;"
        );

        System.out.println("ID\tНаименование\tОбязательный/необязательный\t\tСумма");

        while (res.next()){
            if (res.getString("name").length() > 10){
                System.out.println(res.getInt("budget_type_id") + "\t\t" +
                        res.getString("name").substring(0, 10) + "\t\t\t\t\t" +
                        res.getBoolean("required") + "\t\t\t" +
                        res.getInt("summa"));
            } else {
                System.out.println(res.getInt("budget_type_id") + "\t\t" +
                        res.getString("name") + "\t\t\t\t\t" +
                        res.getBoolean("required") + "\t\t\t" +
                        res.getInt("summa"));
            }
        }
    }

    public static void showFieldsPerson() {
        PersonImpl objPers = new PersonImpl(conn);
        objPers.showTable();
    }

    public static void showFieldsAccounts() {
        AccountsImpl objAccount = new AccountsImpl(conn);
        objAccount.showTable();
    }

    public static void showFieldsBudgetType() {
        BudgetTypeImpl objBudgetType = new BudgetTypeImpl(conn);
        objBudgetType.showTable();
    }

    public static void showFieldsPlanBudget() {
        PlanBudgetImpl objPlanBudget = new PlanBudgetImpl(conn);
        objPlanBudget.showTable();
    }

    public static void showFieldsBudget() {
        BudgetImpl objBudget = new BudgetImpl(conn);
        objBudget.showTable();
    }

}
