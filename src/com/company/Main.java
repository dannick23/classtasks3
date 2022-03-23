package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static class bankexception extends Exception{
        bankexception(String message){
            super(message);
        }
    }
    static void lowfunds(BankAccount a, double sum) throws bankexception{
        if(a.amount<sum)
            throw new bankexception("Transfer cancelled. You are trying to transfer "+sum+" units, but only " + a.amount + " are available.");
    }
    static void fundlimit(double a) throws bankexception{
        if(a>5000)
            throw new bankexception("Transaction cancelled. Max deposit - 5000 | Your deposit: " + a);
    }
    static class BankAccount{
        private double amount;
        BankAccount(){amount =0;}
        BankAccount(double a){
            amount = a;
        }
        void deposit(double a){
            try{
                fundlimit(a);
                amount+=a;
            }catch(Exception e){
                System.out.println(e);
            }
        }
        void withdraw(double a){
            try{
                lowfunds(this, a);
                amount-=a;
            }catch(Exception e){
                System.out.println(e);
            }
        }
        void printbalance(){
            System.out.println("Your current balance is: " + amount);
        }
        void transfer(BankAccount a,double sum){
            try{
                lowfunds(a,sum);
                amount+=sum;
                a.amount-=sum;
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        Scanner inpt = new Scanner(System.in);
        int choice;
        do{
            System.out.println("input choice of task(0 to exit): ");
            try{
                choice=inpt.nextInt();
                switch (choice){
                    case 1:
                        int[][] arrtable = new int[10][10];
                        for(int i=1;i<= arrtable.length;i++)
                            for(int j=1;j<arrtable.length;j++){
                                arrtable[i-1][j-1]=i*j;
                                System.out.println(i + " * " + j + " = " + arrtable[i-1][j-1]);
                            }
                        break;
                    case 2:
                        Random rand = new Random();
                        int[] arrrand = new int[4];
                        System.out.print("the 4 random numbers are:");
                        for(int i=0;i< arrrand.length;i++){
                            arrrand[i] = rand.nextInt(1000);
                            System.out.print(" " + arrrand[i]);
                        }System.out.println(" They're bound to 1000, because it gets crazy");
                        break;
                    case 3:
                        BankAccount a = new BankAccount(50);
                        BankAccount b = new BankAccount(30);
                        a.printbalance();
                        a.withdraw(60);
                        a.printbalance();
                        a.deposit(5001);
                        a.printbalance();
                        a.transfer(b,35);
                        a.printbalance();
                        break;
                    case 4:
                        System.out.println("3 and 4 are actually the same, so i didn't bother with leaving the actual 3rd version");
                    break;
                    case 0: break;
                    default:
                        System.out.println("try again");break;
                }
            }catch(Exception e){
                choice=-1;
                inpt.next();
                System.out.println("Did you seriously mess up already?");
            }}while(choice!=0);
    }
}