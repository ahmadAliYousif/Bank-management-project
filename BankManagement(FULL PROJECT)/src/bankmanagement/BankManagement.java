package bankmanagement;

import bankmanagement.President.pres_Noede;
import bankmanagement.customer.customer_node;
import bankmanagement.manager.manager_node;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BankManagement {

    static PrintWriter write_manager, write_customer, withdrawal_reports, deposet_reports;
    static President p = new President();
    static manager m = new manager();
    static customer c = new customer();
    Scanner in = new Scanner(System.in);
    int r = 1;

    public static void main(String[] args) throws IOException {
        BankManagement bank = new BankManagement();

        write_manager = new PrintWriter("manager.txt");
        write_customer = new PrintWriter("customer.txt");
        withdrawal_reports = new PrintWriter("withdrawal_reports.txt");
        deposet_reports = new PrintWriter("deposet_reports.txt");
        bank.Initial_values();

        while (true) {
            bank.main_bank();

        }

    }

    public void main_bank() throws FileNotFoundException, IOException {// main menu of bank

        int id;
        String password;
        System.out.println("*****************Wellcome to Rafah Bank ******************");
        System.out.println("Enter Your id : ");
        if (r == 0) {
            space();
            space();
            space();
            space();
            System.out.println("");
        } else {
            r = 0;

        }
        id = String_to_int(in.nextLine());
        System.out.println("Enter your password : ");

        password = in.nextLine();
        space();

        if (id == 100) {

            pres_Noede pn = p.logIn_pres(id, password);
            if (pn != null) {
                while (true) {
                    pres_Operations(id, password, pn);

                }
            } else {
                System.out.println("invalid password .. clicl Enter and try agen");
                main_bank();
            }

        } else if (id >= 1000 && id <= 3000) {
            manager_node mn = m.logIn_manager(id, password);

            if (mn != null) {
                while (true) {
                    manger_Operations(id, password, mn);

                }
            } else {
                System.out.println("invalid password .. clicl Enter and try agen");
                main_bank();
            }
        } else if (id >= 1000000 && id < 4000000) {
            customer_node cn = c.logIn_customer(id, password);
            if (cn != null) {
                while (true) {
                    customer_Operations(id, password, cn);

                }
            } else {
                System.out.println("invalid password .. clicl Enter and try agen");
                main_bank();
            }
        } else {
            System.out.println("invalid id ");
            main_bank();
        }

    }

    public void pres_Operations(int id, String password, pres_Noede pn) throws IOException {//The operations performed by the presednet
        System.out.println("choose one of thes Operations : ");
        int choose;
        System.out.println("1.Delete and Add manager\n2.Display managers\n3.Add bouns for manager\n4.Display  customers via the location or the balance\n5."
                + "Display all customers \n6.Display all freez account customer\n7.Display all money in bank\n8.Back to main mnue Bank\n9.Exit");
        space();

        choose = String_to_int(in.nextLine());
        space();
        space();
        space();
        space();
        int id_manager;
        switch (choose) {
            case 1:
                System.out.println("Enter id manager ");

                id_manager = String_to_int(in.nextLine());

                System.out.println("_________________ADD NEW MANAGER___________________");
                System.out.println("Enter the name of the manager");
                String name = in.nextLine();
                System.out.println("Etner password :");
                String password_manger = in.nextLine();

                System.out.println("Enter salary :");
                double salary = String_to_double(in.nextLine());

                System.out.println("Enter address : ");
                String address = in.nextLine();
                space();
                space();
                space();
                space();
                p.delete_add_manager(id_manager, name, password_manger, salary, address);

                break;
            case 2:
                p.display_manager();
                break;
            case 3:
                System.out.println("Enter id manager : ");

                id_manager = String_to_int(in.nextLine());
                System.out.println("Enter amount bouns : $$ ");
                double amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                p.add_bouns_for_manager(id_manager, amount);
                break;
            case 4:
                System.out.println("Enter balance  customer or loc_customer ");

                String loc_balance = in.nextLine();
                space();
                space();
                space();
                space();
                p.display_customers(loc_balance);
                break;

            case 5:
                p.display_all_customers();

                break;
            case 6:
                p.display_all_freez_account();
                break;
            case 7:
                p.display_all_money();
                break;
            case 8:

                main_bank();
                break;
            case 9:
                System.out.println("Good Bay");
                write_manager.close();
                withdrawal_reports.close();
                deposet_reports.close();
                write_customer.close();
                System.exit(0);
            default:
                System.out.println("invalid input ..");
                break;
        }
        System.out.println("Click Enter to continue");
        in.nextLine();

    }

    public void manger_Operations(int id, String password, manager_node mn) throws FileNotFoundException, IOException {//The operations performed by the manager
        System.out.println("choose one of thes Operations : ");
        System.out.println("1.Add customer\n2.Delete_customer\n3.Search_customer\n4.Transfer customer\n5.Freeze_account_customer\n"
                + "6.Display all customer \n7.Display all freez account\n8.display all customers in bank"
                + "\n9.Withdraw money for a customer\n10.Deposit money to a customer\n11"
                + ".display details customer\n12.display all money branch \n13.Return to main mnue\n14.Exit"
        );

        int choose;
        choose = String_to_int(in.nextLine());
        int id_customer;
        double amount;
        space();
        space();
        space();
        space();
        space();
        switch (choose) {
            case 1:
                //int id,String name, String password,
                // double balance, String address, String phone, int id_manager, String location_bank

                System.out.println("Enter name customer : ");
                space();
                space();
                space();
                space();
                String name_customer = in.nextLine();
                System.out.println("Enter passwors customer : ");
                String password_customer = in.nextLine();
                System.out.println("Enter balance customer : ");
                double balance_customer = String_to_double(in.nextLine());//String_to_int(in.nextLine());
                System.out.println("Enter address customer : ");
                String address = in.nextLine();
                System.out.println("Enter number phone customer : ");
                String phone_customer = in.nextLine();
                space();
                space();
                space();
                space();
                m.add_customer(name_customer, password_customer, balance_customer, address, phone_customer, id, mn.location_bank);
                m.sort_customer();

                break;
            case 2:
                System.out.println("enter id customer : ");
                space();
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());
                space();
                space();
                space();
                space();
                m.delete_customer(id_customer);
                break;
            case 3:
                System.out.println("Enter name or ID");
                space();
                space();
                space();
                space();
                space();
                String name_id = in.nextLine();
                space();
                space();
                space();
                space();
                m.search_customer(name_id, id);
                break;
            case 4:
                System.out.println("Enter your id customer : ");
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());
                System.out.println("Choose one of these Locations");
                System.out.println("1.Gaza\n2.Khan Younes\n3.Rafah");
                int choose_loc = in.nextByte();
                int new_id_manger = id;
                String new_loc = mn.location_bank;
                switch (choose_loc) {
                    case 1:
                        new_id_manger = 1000;
                        new_loc = "Gaza";
                        break;
                    case 2:
                        new_id_manger = 2000;
                        new_loc = "Khan Younes";

                        break;
                    case 3:
                        new_id_manger = 3000;

                        new_loc = "Rafah";
                        break;
                    default:

                        System.out.println("invalid input");

                        throw new AssertionError();

                }

                m.transfer_customer(id_customer, id, new_loc);
                break;
            case 5:
                System.out.println("Enter customer id : ");
                space();
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());
                space();
                space();
                space();
                space();
                m.Freeze_account_customer(id_customer, id);
                space();
                space();
                break;
            case 6:
                space();
                space();
                space();
                space();
                m.display(id);
                space();
                space();
                break;
            case 7:
                space();
                space();
                space();
                space();
                m.display_all_freez_account(id);
                space();
                space();
                break;
            case 8:

                m.display_all_customers();
                space();
                space();
                break;
            case 9:
                System.out.println("Enter id customer : ");
                space();
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());

                System.out.println("How much do you want to Withdraw $$ ? ");
                space();
                space();
                space();
                space();
                amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                m.wer_customer(amount, id_customer, id);
                space();
                space();
                space();
                space();
                break;
            case 10:

                System.out.println("Enter id customer : ");
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());

                System.out.println("How much do you want to deposit $$ ? ");

                amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                m.dibosit_customer(amount, id_customer, id);
                break;
            case 11:
                System.out.println("Enter customer id : ");
                space();
                space();
                space();
                space();
                id_customer = String_to_int(in.nextLine());
                space();
                space();
                space();
                space();
                p.display_details_customer(id, id_customer);
                break;
            case 12:
                space();
                space();
                space();
                space();
                p.display_all_money_branch(id);
                space();
                space();
                break;
            case 13:

                main_bank();
                space();
                space();
                space();
                space();
                break;
            case 14:
                System.out.println("Good bay");
                write_manager.close();
                withdrawal_reports.close();
                deposet_reports.close();
                write_customer.close();
                System.exit(0);
            default:
                System.out.println("invalid input ..");

                break;
        }
        System.out.println("Click Enter to continue");
        in.nextLine();

    }

    public void customer_Operations(int id, String password, customer_node cn) throws IOException {//The operations performed by the customer
        if (cn.isFreazon == true) {
            System.out.println("Sorry your account is frozen ..");
            System.exit(0);
        }
        System.out.println("Wellcome " + cn.name_customer);
        System.out.println("choose one of thes Operations : ");
        System.out.println("1. Deposit money \n2. Withdraw money\n3.Transfer money from my account to another account \n"
                + "4.Withdrawal reports" + "\n5.Deposit reports\n"
                + "6.update deltais \n7.Balance check\n8.View my full details\n9.Back to main mnue \n10.exit");

        int choose;
        choose = String_to_int(in.nextLine());
        double amount;
        switch (choose) {
            case 1:
                System.out.println("How much do you want to Withdraw $$ ? ");
                space();
                space();
                space();
                space();
                amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                c.Deposit(id, amount);
                break;
            case 2:
                System.out.println("How much do you want to deposit $$ ? ");
                space();
                space();
                space();
                space();
                amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                c.Withdraw(id, amount);

                break;
            case 3:
                System.out.println("Enter the ID to be transferred to ");
                space();
                space();
                space();
                space();
                int id_trans_customer = String_to_int(in.nextLine());
                System.out.println("Enter the amount ($) you want transformation :");
                amount = String_to_double(in.nextLine());
                space();
                space();
                space();
                space();
                c.money_transfer(id, id_trans_customer, amount);
                break;
            case 4:
                space();
                space();
                space();
                space();
                c.Withdrawal_reports(id);
                space();
                space();

                break;
            case 5:
                space();
                space();
                space();
                space();
                c.Deposit_reports(id);
                space();
                space();

                break;
            case 6:
                space();
                space();
                space();
                space();
                c.update_customer_detalis(id);
                space();
                space();
                break;

            case 7:
                space();
                space();
                space();
                space();
                c.cheek_balance(id);
                space();
                space();

                break;
            case 8:
                space();
                space();
                space();
                space();
                c.display_details_customer(id);
                space();
                space();

                break;
            case 9:
                space();
                space();
                space();
                space();
                main_bank();

                break;
            case 10:
                System.out.println("Good Bay");
                write_manager.close();
                withdrawal_reports.close();
                deposet_reports.close();
                write_customer.close();
                System.exit(0);
            default:
                System.out.println("invalid input ..");
                break;
        }
        System.out.println("Click Enter to continue");
        in.nextLine();

    }

    public void Initial_values() throws IOException {//حطها ف ملف و استدعيها

        p.add_manager("Rami", "hassan", "Gaza", 2500, "address1");
        p.add_manager("ahmed", "ahmed123", "Khan Younes", 2500, "address2");
        p.add_manager("Raed", "hytham", "Rafah", 2500, "address3");

        m.add_customer("ahmed", "a1", 1200, "Gaza-tal al hawa", "0599999999", 1000, "Gaza");
        m.add_customer("mohammed", "m2", 1300, "Gaza-tal al genan", "0599999999", 1000, "Gaza");
        m.add_customer("saed", "s3", 12000, "Gaza-ber salem", "0599999999", 1000, "Gaza");
        m.add_customer("raed", "r4", 1300, "Khan Younes- qarara", "0599999999", 2000, "Khan Younes");
        m.add_customer("hassan", "h5", 1400, "Khan Younes- balad", "0599999999", 2000, "Khan Younes");
        m.add_customer("hani", "h6", 1400, "Khan Younes satra", "0599999999", 2000, "Khan Younes");
        m.add_customer("khalil", "k7", 1100, "rafah_adana", "0599999999", 3000, "Rafah");

    }

    public void save_manager(String name, String password, String location,
            double salary, String address, int id_pres, String createDate) throws IOException {//Save any process of adding a manager
        write_manager.append("name : " + name + " //password : " + password + "// create date : " + createDate + "//location : " + location + "// salary : " + salary + "//"
                + "address : " + address + " // id_pres : " + id_pres);
        write_manager.println("");
    }

    public void save_customer(int id, String name, String loc, int id_manger, String pass,
            double balance, String address, String phone, boolean isFreas, String createDate) throws FileNotFoundException {//Save any process of adding a customer
        write_customer.append("id: " + id + "name : " + name + " // password : " + pass + "// create aconut date : "
                + createDate + "//location : " + loc + "+//address: " + address + "//phone : "
                + phone + "//balance : " + balance + "//isFreas : " + isFreas + "// id_manger : " + id_manger);
        write_customer.println("");

    }

    public int String_to_int(String str) {//Convert the number from text to a number .. to reduce the occurrence of errors
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= 48 && str.charAt(i) <= 57)) {
                System.out.println("Just Enter number ... ");
                return -1;

            }

        }
        return Integer.parseInt(str);

    }

    public double String_to_double(String str) {//Convert the number from text to a number .. to reduce the occurrence of errors
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= 48 && str.charAt(i) <= 57 || str.charAt(i) == 46)) {
                System.out.println("Just Enter number ... ");
                return -1;

            }

        }
        return Double.parseDouble(str);

    }

    public void space() {//to clear screen

        for (int i = 0; i < 2; i++) {
            System.out.println("");
        }
    }

    public void deposet_reports_save(String str_save) {

        deposet_reports.append(str_save);
        deposet_reports.println();
    }

    public void withdrawal_reports_save(String str_save) {
        withdrawal_reports.append(str_save);
        withdrawal_reports.println();

    }
}
