package bankmanagement;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class President extends manager {

    manager m = new manager();
    static int d = 1000;
    manager_node n = new manager_node();

    public static class pres_Noede {

        public int id_pres = 100;
        public String name_pres = "President BANK";
        public String pass_pres = "ahmed213";
        public String address = "khan yonis_ Al Satar";
        public double salary = 10000;
    }
    public static pres_Noede head;

    public void add_manager(String name, String pass, String location, double salary, String address) throws IOException {//to add manager
        manager_node new_node = new manager_node();
        new_node.Id_manger = d;
        d = d + 1000;
        new_node.id_pres = 1;
        new_node.location_bank = location;
        new_node.name_manger = name;
        new_node.password_manger = pass;
        new_node.address = address;
        new_node.salary_manager = salary;
        if (m.head == null) {
            m.head = new_node;
            new_node.next_manger = null;
            new_node.prev_manger = null;
        } else {
            manager_node ptr = m.head;
            while (ptr.next_manger != null) {
                ptr = ptr.next_manger;
            }

            ptr.next_manger = new_node;
            new_node.prev_manger = ptr;
            new_node.next_manger = null;
        }
        Date date = new Date();
        new_node.created_date_manager = date.toString();
        b.save_manager(new_node.name_manger, new_node.password_manger, new_node.location_bank, salary, address, new_node.id_pres, new_node.created_date_manager);

    }

    public void delete_add_manager(int id_manager, String name, String pass, double salary, String address) throws IOException {
        int id_manager_old = 0;
        String old_loaction = "";
        manager_node mn = m.head;

        if (m.head.Id_manger == id_manager) {
            id_manager_old = m.head.Id_manger;
            old_loaction = m.head.location_bank;
            m.head = m.head.next_manger;
        } else {
            manager_node ptr = m.head;

            while (ptr.next_manger.Id_manger != id_manager) {
                ptr = ptr.next_manger;

            }
            id_manager_old = ptr.next_manger.Id_manger;
            old_loaction = ptr.next_manger.location_bank;
            ptr.next_manger = ptr.next_manger.next_manger;

        }
        System.out.println("manager deleted");
        //___________________________________________________________________________________
        manager_node new_node = new manager_node();
        new_node.Id_manger = id_manager_old + 1;
        new_node.location_bank = old_loaction;
        new_node.id_pres = 1;
        new_node.name_manger = name;
        new_node.password_manger = pass;
        new_node.address = address;
        new_node.salary_manager = salary;
        if (m.head == null) {
            m.head = new_node;
            new_node.next_manger = null;
            new_node.prev_manger = null;
        } else {
            manager_node ptr = m.head;
            while (ptr.next_manger != null) {
                ptr = ptr.next_manger;
            }

            ptr.next_manger = new_node;
            new_node.prev_manger = ptr;
            new_node.next_manger = null;
        }
        //----------------------------------------------------------------------------------------
        customer_node current = c.head;

        do {
            if (current.id_manager == id_manager) {
                current.id_manager = new_node.Id_manger;
            }

            current = current.next_customer;
        } while (current != c.head);
        Date date = new Date();
        new_node.created_date_manager = date.toString();
        b.save_manager(new_node.name_manger, new_node.password_manger, new_node.location_bank, salary, address, new_node.id_pres, new_node.created_date_manager);

    }

    public void add_bouns_for_manager(int id_manager, double amount) {
        manager_node ptr = m.head;
        while (ptr.Id_manger != id_manager) {//linear search

            ptr = ptr.next_manger;
        }
        ptr.salary_manager += amount;

    }

    public void display_customers(String name_id) {//To search for clients by name or ID
        int id_number = 0, d = 1;
        customer_node current = c.head;

        if ((int) name_id.charAt(0) >= 48 && ((int) name_id.charAt(0) <= 57)) {// لان البحث يتم اما بال اي دي او ب  الاسم
            id_number = Integer.parseInt(name_id);
        }

        do {
//            if (id_number == (int) current.next_customer.Balance_customer || name_id.equalsIgnoreCase(current.next_customer.location_bank)) 
            d++;

            current = current.next_customer;
        } while (current != c.head && (id_number == (int) current.next_customer.Balance_customer || name_id.equalsIgnoreCase(current.next_customer.location_bank)));
        System.out.println(d + ".id : " + current.id_customer + " // name : " + current.name_customer + " //Balance : " + current.Balance_customer + ">>\n");

    }

    public void display_all_customers() {//To search for clients by name or ID

        customer_node current = c.head;

        do {
            System.out.println("id : " + current.id_customer
                    + " // name : " + current.name_customer + "  //balance : " + current.Balance_customer + " //Location :" + current.location_bank + ">>\n");

            current = current.next_customer;
        } while (current != c.head);
    }

    public void display_all_freez_account() {
        customer_node current = c.head;

        do {
            if (current.isFreazon) {
                System.out.println("id : " + current.id_customer + " // name : " + current.name_customer + "  //balance : " + current.Balance_customer + ">>\n");
                
            }

            current = current.next_customer;
        } while (current != c.head);

    }

    public void display_all_money() {
        double sum = 0;

        customer_node current = c.head;

        do {
            sum += current.Balance_customer;
            current = current.next_customer;
        } while (current != c.head);
        System.out.println("Total balance in bank : " + sum + " $ ");
    }

    public void display_manager() {

        manager_node ptr = m.head;
        while (ptr != null) {
            System.out.println("id : " + ptr.Id_manger + " // name : " + ptr.name_manger + " // salary  :" + ptr.salary_manager + " // address : " + ptr.address
                    + " // location_bank : " + ptr.location_bank + "\n");
            ptr = ptr.next_manger;
        }

    }

    public pres_Noede logIn_pres(int id, String pass) {
        pres_Noede node = new pres_Noede();
        if (id == node.id_pres && pass.equals(node.pass_pres)) {
            System.out.println("Wellcome " + node.name_pres);
            return node;
        } else {
            return null;
        }

    }

}
