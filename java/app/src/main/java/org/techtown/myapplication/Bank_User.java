package org.techtown.myapplication;

public class Bank_User {


    public static void main(String[] args) {

        Bank bank = new Bank();


        int customerMoney = bank.checkCustomerMoney();
        System.out.println(customerMoney);

//        int customerMoney1 = bank.cutomerMoney;
//        bank.cutomerMoney = 2000;
//        System.out.println(bank.cutomerMoney);

    }

}
