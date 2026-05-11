/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaoopp;
import java.util.Scanner;



/**
 *
 * @author hp
 */

class BankAccount {
    String name;
    int accountNo;
    int Pin;
    double balance;
    public BankAccount(String name, int No , double balance,int pin){
        this.accountNo=No;
        this.balance=balance;
        this.name=name;
        this.Pin=pin;
    }
    public BankAccount(int accountNo){
        this.accountNo=accountNo;
    }
    public BankAccount() {
    }
    @Override
    public String toString() {
        return "BankAccount{" + "name=" + name + ", accountNo=" + accountNo + ", balance=" + balance + '}';
    }
}

class Node{
    BankAccount data;
    Node next;
    
    public Node(BankAccount data) {
        this.data = data;
        this.next = null;
    }
}
class LinkedList {
    Node head;
    int nElement=0;
    public void add(BankAccount data){
        Node newNode=new Node(data);
        Node current=head;
        if(head==null){
            head=newNode;
        }else{
            while(current.next!=null){
            current=current.next;
            }
            current.next=newNode;
        }
        nElement++;
    }
    public void display(){
        Node current=head;
        while(current!=null){
            System.out.println(current.data);
            current=current.next;
        }
    }
    public Node search (int accountNo){
        Node current=head;
        while(current!=null){
            if(current.data.accountNo==accountNo){
                return current;
            }
            current=current.next;
        }
        return null;
    }
    
    public void BankStatistics(){
        if(head == null){
            System.out.println("No Accounts");
            return;
        }
        Node current=head;
        int count=0;
        double totalBalance=0;
        double greatest=head.data.balance;
        while(current!=null){
            totalBalance+=current.data.balance;
            if(current.data.balance>greatest) greatest=current.data.balance;
            current=current.next;
            count++;
        }
        System.out.println("Total Balance : "+totalBalance );
        double averageBalance=totalBalance/count;
        System.out.println("Average Balance : "+averageBalance);
        System.out.println("The greatest Balance : "+greatest);
    }
   
}

class ATM{
    BankAccount BA;
    public ATM(BankAccount b){
        BA=b;
    }
    boolean checkPIN(int PIN){
        if(BA.Pin==PIN) return true;
        else return false;
    }
    public void withdraw(double amount){
        
            if(amount<=BA.balance){
            BA.balance-=amount;
            System.out.println(amount+"$ withdraw done!!");
            System.out.println("the current Balance : "+BA.balance);
            }else
                System.out.println("Amount Not Enough !!");
    }
    public void deposit(double amount){
            BA.balance+=amount;
            System.out.println(amount+"$ Deposit done!!");
            System.out.println("the current Balance : "+BA.balance);
    }
    
    public void checkBalance(){
        System.out.println("Balance is "+BA.balance+"$");
    }
    
    public void transferMoney(double amount,Node recieveAcc ){
        if(amount<=BA.balance){
            BA.balance-=amount;
            recieveAcc.data.balance+=amount;
            System.out.println("transfer done!!");
            System.out.println("Current Balance : "+BA.balance);
        }else
            System.out.println("Balance Not Enough!!");
    }
    
    public void details(){
        System.out.println("Name: "+BA.name+" , Account Number: "+BA.accountNo);
    }
}

public class Bank{   
    public static void main (String []args){
        BankAccount custmer1=new BankAccount("Mohamed",22, 100,1234);
        BankAccount custmer2=new BankAccount("Ziad",28, 200,1222);       
        BankAccount custmer3=new BankAccount("Ahmed",29, 500,1266);
        BankAccount custmer4=new BankAccount("nabawy",238, 200,1328);
        BankAccount custmer5=new BankAccount("jo",20, 700,1292);

        int pin=0;
        LinkedList BankAccounts=new LinkedList();
        BankAccounts.add(custmer1);
        BankAccounts.add(custmer2);
        BankAccounts.add(custmer3);
        BankAccounts.add( custmer4);
        BankAccounts.add(custmer5);
        
        // display Bank Statistics 
        System.out.println("Bank Statistics : ");
        BankAccounts.BankStatistics();
        
        // ATM 
        boolean state=true;
        Scanner in=new Scanner(System.in);
        int countTry=0;
        int i=3;
        System.out.println("-------------------");
        System.out.print("Enter Your Account Number : ");
        int no=in.nextInt();
        Node ac=BankAccounts.search(no);
        if(ac==null){
            System.out.println("Account Not Found!!");
            return;
        }
        ATM a=new ATM(ac.data);
        a.details();
        while(state){
            System.out.print("Enter PIN : ");
            pin=in.nextInt();
            if(a.checkPIN(pin)){
                while(state){
                    System.out.println("1.Check Balance"
                    + "\n2.Deposit"
                    + "\n3.Withdraw"
                    + "\n4.Transfer"
                    + "\n5.Exit");
                System.out.print("Enter number for any process : ");
                    int n =in.nextInt();
                    switch (n){
                        case 1:
                            a.checkBalance();
                            break;
                        case 2:
                            System.out.print("Enter amount :");
                            double amountD=in.nextDouble();
                            a.deposit(amountD);
                            break;
                        case 3:
                            System.out.print("Enter amount :");
                            double amountW=in.nextDouble();
                            a.withdraw(amountW);
                            break;
                        case 4:
                            System.out.print("Enter amount :");
                            double amountT=in.nextDouble();
                            System.out.print("Enter account number receiver : ");
                            int accountNo=in.nextInt();
                            Node acc=BankAccounts.search(accountNo);
                            if(acc==null){
                                System.out.println("Account Not Found!!");
                            }else
                                a.transferMoney(amountT, acc);
                            break;
                        case 5:
                            state=false;
                    }
                    System.out.println("------------------");
                }
            }else{
                System.out.println("Wrong PIN remaining : "+--i);
                countTry++;
                if(countTry==3){
                    break;
                }
            }
        }
        System.out.println("test after changing ");
        System.out.println(custmer1.toString());
        System.out.println(custmer2.toString());
        System.out.println(custmer3.toString());
        System.out.println(custmer4.toString());
        System.out.println(custmer5.toString());
    }
}
