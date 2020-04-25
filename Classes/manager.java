/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import static bankmanagement.manager.c;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 *
 * @author ahmed ali
 */
public class manager extends customer {

    BankManagement b = new BankManagement();
    static manager m = new manager();
    static customer c = new customer();
    public static int id_Gaza = 1000000, id_kan = 2000000, id_rafah = 3000000;
    manager_node n = new manager_node();

    public static class manager_node {

        public manager_node next_manger;
        public manager_node prev_manger;

        public int Id_manger;
        public String created_date_manager;

        public String password_manger;
        public String name_manger;
        public String location_bank;
        public String address;
        public double salary_manager;
        public int id_pres;
    }

    public static manager_node head = null;

    public void delete_customer(int id) {//to delete an customer
        customer_node current = c.head;

        if (id == c.head.id_customer) {
            c.head = c.head.next_customer;
            c.tail.next_customer = c.head;
        } else if (id == c.tail.id_manager) {
            customer_node ptr = c.head;
            while (ptr.next_customer != c.tail) {
                ptr = ptr.next_customer;
            }
            ptr.next_customer = ptr.next_customer.next_customer;
            c.tail = ptr.next_customer;
        } else {
            customer_node ptr = c.head;

            do {
                ptr = ptr.next_customer;

            } while (ptr != c.head && ptr.next_customer.id_customer != id);
            ptr.next_customer = ptr.next_customer.next_customer;
        }

    }

    public void display(int id_manager) {//Show all customers in branches

        customer_node current = c.head;

        do {
            if (id_manager == current.id_manager) {
                System.out.println("id : " + current.id_customer + " //name : " + current.name_customer + " // balance : "
                        + current.Balance_customer);
            }
            current = current.next_customer;
        } while (current != c.head);

    }

    public void add_customer(String name, String password,
            double balance, String address, String phone, int id_manager, String location_bank) throws FileNotFoundException {//Add a customer
        customer_node new_node = new customer_node();
        if (location_bank.equalsIgnoreCase("Gaza")) {
            new_node.id_customer = id_Gaza;
            id_Gaza++;

        } else if (location_bank.equalsIgnoreCase("Khan Younes")) {
            new_node.id_customer = id_kan;
            id_kan++;

        } else if (location_bank.equalsIgnoreCase("Rafah")) {
            new_node.id_customer = id_rafah;
            id_rafah++;

        }

        new_node.location_bank = location_bank;
        new_node.name_customer = name;
        new_node.id_manager = id_manager;
        new_node.password_customer = password;
        new_node.Balance_customer = balance;
        new_node.address_customer = address;
        new_node.phone_customer = phone;
        new_node.next_customer = null;
        new_node.isFreazon = false;

        if (c.head == null) {
            c.head = (new_node);
            c.tail = (new_node);
            new_node.next_customer = c.head;
        } else {
            c.tail.next_customer = new_node;
            c.tail = (new_node);
            c.tail.next_customer = c.head;
        }
        /*
        (int id,String name,String loc,int id_manger,String pass,          
double balance,String address,String phone,boolean isFreas,boolean isEmpty)*/

        //   m.sort_customer();
        Date date = new Date();
        new_node.Date_created_customer = date.toString();
        b.save_customer(new_node.id_customer, new_node.name_customer, new_node.location_bank, new_node.id_manager,
                new_node.password_customer, new_node.Balance_customer, new_node.address_customer,
                new_node.phone_customer, new_node.isFreazon, new_node.Date_created_customer);
    }

    public manager_node logIn_manager(int id, String pass) {//Logging function
        manager_node ptr = head;

        while (ptr != null) {
            if ((id == ptr.Id_manger) && pass.equals(ptr.password_manger)) {
                System.out.println("wellcome : " + ptr.name_manger);
                return ptr;
            }
            ptr = ptr.next_manger;
        }
        return null;
    }

    public void search_customer(String name_id, int id_manager) {//To search for a customer
        int id_number = 0, d = 1;
        customer_node current = c.head;

        if ((int) name_id.charAt(0) >= 48 && (int) name_id.charAt(0) <= 57) {
            id_number = Integer.parseInt(name_id);

        }

        do {
            if (id_manager == current.id_manager) {
                if ((id_number == current.id_customer || name_id.equalsIgnoreCase(current.name_customer))) {
                    System.out.println(d + ". <<id : " + current.id_customer + "// name : " + current.name_customer + " //balance : " + current.Balance_customer + ">>\n");
                    d++;
                }
            }

            current = current.next_customer;
        } while (current != c.head);

    }

    public void transfer_customer(int id_cusotmer, int id_manager, String new_loc) {//Moving a customer from one branch to another
        customer_node current = c.head;
        customer_node prev = c.head;
        int e = 0;
        do {
            if (id_manager == current.id_manager) {
                if (id_cusotmer == current.id_customer) {

                    if (new_loc.equalsIgnoreCase("gaza")) {
                        current.id_customer = id_Gaza;
                        id_Gaza++;
                        current.id_manager = 1000;
                    } else if (new_loc.equalsIgnoreCase("Khan Younes")) {
                        current.id_customer = id_kan;
                        id_kan++;
                        current.id_manager = 2000;

                    } else if (new_loc.equalsIgnoreCase("rafah")) {
                        current.id_customer = id_rafah;
                        id_rafah++;
                        current.id_manager = 3000;

                    }
                    current.location_bank = new_loc;

                    break;

                }

            }
            if (e != 0) {
                prev = prev.next_customer;
            }
            e = 1;
            current = current.next_customer;
        } while (current != c.head);
        sort_customer();
    }

    public void Freeze_account_customer(int customer_id, int id_manager) {//To freeze an account
        customer_node current = c.head;

        do {
            if (id_manager == current.id_manager) {
                if (customer_id == current.id_customer) {
                    current.isFreazon = true;
                    break;
                }
            }

            current = current.next_customer;
        } while (current != c.head && current.id_customer != customer_id);
        if (id_manager == current.id_manager) {
            if (customer_id == current.id_customer) {
                current.isFreazon = true;

            }
        }

    }

    public void display_all_freez_account(int id_manager) {//View freaz accounts
        customer_node current = c.head;

        do {
            if (current.isFreazon && id_manager == current.id_manager) {
                System.out.println("id : " + current.id_customer + "  //name : " + current.name_customer + "  //balance : " + current.Balance_customer + ">>\n");

            }

            current = current.next_customer;
        } while (current != c.head);

    }

    public void display_details_customer(int id_manager, int id_customer) {//To view customer data

        customer_node current = c.head;

        do {
            if (id_manager == current.id_manager && id_customer == current.id_customer) {
                System.out.println("id : " + current.id_customer + " // name : " + current.name_customer + " // balance : "
                        + current.Balance_customer + "//address: " + current.address_customer + " // location : " + current.location_bank);
                break;
            }
            current = current.next_customer;
        } while (current != c.head);

    }

    public void display_all_money_branch(int id_manager) {//To display the total money of the branch
        double sum = 0;

        customer_node current = c.head;

        do {
            if (id_manager == current.id_manager) {
                sum += current.Balance_customer;

            }
            current = current.next_customer;
        } while (current != c.head);
        System.out.println("Total balance in bank : " + sum + " $ ");

    }

    public void sort_customer() {//To arrange customers after adding a customer or transfer
        customer_node current = c.head, prev = null, temp = null;

        do {
            if (current.id_customer > current.next_customer.id_customer) {
                if (current == c.head) {
                    temp = current;
                    c.head = c.head.next_customer;
                    break;
                } else {
                    temp = current;

                    prev.next_customer = current.next_customer;
                    break;
                }
            }
            prev = current;
            current = current.next_customer;
        } while (current != c.head);
//-------------------------------------------------------------------------------
        current = c.head;

        if (current == null) {
            temp.next_customer = temp;
            c.head = temp;

        } else if (current.id_customer >= temp.id_customer) {

            while (current.next_customer != c.head) {
                current = current.next_customer;
            }

            current.next_customer = temp;
            temp.next_customer = c.head;
            c.head = temp;
        } else {

            while (current.next_customer != c.head
                    && current.next_customer.id_customer < temp.id_customer) {
                current = current.next_customer;
            }

            temp.next_customer = current.next_customer;
            current.next_customer = temp;
        }

    }

    public void update_customer_detalis(int customer_id, int id_manager, int New_id_manager) {

        customer_node current = c.head;

        do {

            current = current.next_customer;
        } while (current != c.head && current.id_customer != customer_id);
        if (id_manager == current.id_manager) {
            current.id_manager = New_id_manager;
        }

        sort_customer();

    }

    public void dibosit_customer(double amount, int id_customer, int id_manager) {
        customer_node current = c.head;
        int t = 0;
        do {
            current = current.next_customer;
            t++;
            if (t > customer.i) {
                if (id_manager == current.id_manager) {
                    current.Balance_customer -= amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                    break;

                }
            }
        } while (id_customer != current.id_customer);

        current.Balance_customer += amount;

        Date date = new Date();
        current.deposit_reports += "The deposited amount :" + amount + "// Time of deposit :" + date.toString() + "// total amount : " + current.Balance_customer + "\n";
        bank.deposet_reports_save("The deposited amount :" + amount + "// Time of deposit :" + date.toString() + "// total amount : " + current.Balance_customer);
        System.out.println("operation accomplished successfully");

    }

    public void wer_customer(double amount, int id_customer, int id_manager) {
        customer_node current = c.head;
        int t = 0;

        do {
            current = current.next_customer;
            t++;
            if (t > customer.i) {
                if (id_manager == current.id_manager) {
                    current.Balance_customer += amount;  //لانه حيضل يرجع و يلف و يزود تحت ل نود خاطئة ف بنشيل المبلغ بعدين بنضيف في حال كان النود خاطيء
                    break;

                }
            }
        } while (id_customer != current.id_customer);
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
    
  
    public void display_all_customers() {//To search for clients by name or ID

        customer_node current = c.head;

        do {
            System.out.println("id : " + current.id_customer
                    + " // name : " + current.name_customer + "  //balance : " + current.Balance_customer + " //Location :" + current.location_bank + ">>\n");

            current = current.next_customer;
        } while (current != c.head);
    }
    
    
}
