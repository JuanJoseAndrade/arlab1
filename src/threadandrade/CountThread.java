/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadandrade;

/**
 *
 * @author 2107990
 */
public class CountThread extends Thread{
    int a,b;
    public CountThread(int a,int b){
    this.a=a;
    this.b=b;
    }
    public void run(){  
      for (int i=a;i<=b;i++){
      System.out.print("\n");
      System.out.print(i);
      System.out.print("\n");
      }
  } 
}

