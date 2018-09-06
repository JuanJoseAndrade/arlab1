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
public class CountThreadsMain {
    public static void main(String[] args){
        CountThread cont1 = new CountThread(0,99);
        CountThread cont2 = new CountThread(100,199);
        CountThread cont3 = new CountThread(200,299);
        cont1.start();
        cont2.start();
        cont3.start();
    }
}
