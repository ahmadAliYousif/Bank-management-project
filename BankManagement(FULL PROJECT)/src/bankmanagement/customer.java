/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import static bankmanagement.manager.c;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ahmed ali
 */
public class customer {

    Scanner in = new Scanner(System.in);
    BankManagement bank = new BankManagement();

    public static int i = 1000000;

    public static class customer_node {

        public customer_node next_customer;
        public int id_customer;
        public String password_customer;
        public String name_customer;
        public double Balance_customer;
        public String address_customer;
        public String phone_customer;
        public String location_bank;
        public int id_manager;
        public boolean isFreazon;
        public String deposit_reports = " ";
        public String Withdraw_reports = " ";
        public String Date_created_customer = " ";

    }
    public static customer_node head = null;
    public static customer_node tail = null;

    public void Deposit(int id, double amount) {//To deposit funds

        customer_node current = head;
        int t = 0;
        do {
            current = current.next_customer;
            t++;
            if (t > i) {
                current.Balance_customer -= amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                break;
            }
        } while (id != current.id_customer);
    
        current.Balance_customer += amount;
        Date date = new Date();
        current.deposit_reports += "The deposited amount :" + amount + "// Time of deposit :" + date.toString() + "// total amount : " + current.Balance_customer + "\n";
        bank.deposet_reports_save("The deposited amount :" + amount + "// Time of deposit :" + date.toString() + "// total amount : " + current.Balance_customer);
        System.out.println("operation accomplished successfully");

    }

    public void display_details_customer(int id) {//To view customer details

        customer_node current = head;
        do {
            current = current.next_customer;
        } while (id != current.id_customer);
        System.out.print("ID : " + current.id_customer + "//NAME : " + current.name_customer + " //Address : "
                + current.address_customer + " //Balance : " + current.Balance_customer + "//Number Phone : " + current.phone_customer + " //id_manager :" + current.id_manager + "\n");

    }

    public void Withdraw(int id, double amount) {//To withdraw money

        customer_node current = head;
        int t = 0;

        do {
            current = current.next_customer;
            t++;
            if (t > i) {
                current.Balance_customer += amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                break;
            }
        } while (id != current.id_customer);
        if (current.Balance_customer >= amount) {
            current.Balance_customer -= amount;
            System.out.println("operation accomplished successfully");
            Date date = new Date();
            current.Withdraw_reports += "The Withdrawed amount :" + amount + "// Time of Withdraw :" + date.toString() + "// total amount : " + current.Balance_customer + "\n";
            bank.withdrawal_reports_save("The Withdrawed amount :" + amount + "// Time of Withdraw :" + date.toString() + "// total amount : " + current.Balance_customer);
        } else {
            System.out.println("You do not have enough money");
        }

    }

    public customer_node logIn_customer(int id, String password) {// log in funchen
        customer_node node = new customer_node();
        customer_node ptr = head;
        do {
            if ((ptr.id_customer == id && ptr.password_customer.equals(password))) {
                return ptr;
            }

            ptr = ptr.next_customer;

        } while (ptr != head);
        return null;

    }

    public void money_transfer(int id, int id_trans_customer, double amount) {//To transfer money from one customer to another
        customer_node current = head;
        int t = 0;

        do {
            current = current.next_customer;
            t++;
            if (t > i) {
                current.Balance_customer += amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                break;
            }
        } while (id != current.id_customer);
        if (current.Balance_customer >= amount) {
            current.Balance_customer -= amount;
            Date date = new Date();
            current.Withdraw_reports += "Transfer the amount : " + amount + " //from :" + current.id_customer + " //to : " + id_trans_customer + " //at time :" + date.toString() + "\n";
            bank.withdrawal_reports_save("Transfer the amount : " + amount + " //from :" + current.id_customer + "// to : " + id_trans_customer + " //at time :" + date.toString());
        } else {
            System.out.println("You do not have enough money");
            return;
        }

        current = head;

        t = 0;
        do {
            current = current.next_customer;
            t++;
            if (t > i) {
                current.Balance_customer -= amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                break;
            }
        } while (id_trans_customer != current.id_customer);
        current.Balance_customer += amount;
        Date date = new Date();

        current.deposit_reports += "\nReceive the amount:  " + amount + "  from : " + id + "  to : " + id_trans_customer + "at time :" + date.toString() + "\n";
        bank.deposet_reports_save("Receive the amount:  " + amount + "  from : " + id + "  to : " + id_trans_customer + "at time :" + date.toString());
        System.out.println("operation accomplished successfully");

    }

    public void cheek_balance(int id) {//Money revealed

        customer_node current = head;
        do {
            current = current.next_customer;
        } while (id != current.id_customer);
        System.out.print("the name : " + current.name_customer + "\nthe balance :" + current.Balance_customer);

    }

    public void update_customer_detalis(int customer_id) {//Update customer data

        customer_node current = c.head;

        do {

            current = current.next_customer;
        } while (current != c.head && current.id_customer != customer_id);

        System.out.println("Choose what you went update ? : ");
        bank.space();
        bank.space();
        bank.space();
        System.out.println("1.number phone\n2.address\n3.password");
        String c = in.nextLine();
        switch (bank.String_to_int(c)) {
            case 1:
                System.out.println("Enter new number phone");
                current.phone_customer = in.nextLine();
                break;
            case 2:
                System.out.println("Enter new address ");

                current.address_customer = in.nextLine();

                break;
            case 3:
                System.out.println("Enter new password ");

                current.password_customer = in.nextLine();

                break;
            default:
                System.out.println("invlaed input ");
                throw new AssertionError();
        }

    }

    public void Withdrawal_reports(int id) {
        customer_node current = head;
        do {
            current = current.next_customer;
        } while (id != current.id_customer);

        System.out.println("Withdrawal reports: \n" + current.Withdraw_reports);

    }

    public void Deposit_reports(int id) {
        customer_node current = head;
        do {
            current = current.next_customer;
        } while (id != current.id_customer);

        System.out.println("Deposit reports: \n" + current.deposit_reports);

    }

}
