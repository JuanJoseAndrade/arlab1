/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacklist;



import java.lang.Runtime;
import java.util.List;

/**
 *
 * @author 2107990
 */
public class Main {
    
    public static void main(String a[]) throws InterruptedException{
        //HostBlackListExample hblv=new HostBlackListExample();
        //List<Integer> blackListOcurrences=hblv.checkHost("212.24.24.55");
        HostBlackListsValidatorAndrade hblv=new HostBlackListsValidatorAndrade();
        List<Integer> blackListOcurrences=hblv.checkHost("202.24.34.55",100);
        //212.24.24.55
        //202.24.34.55
        //Runtime.getRuntime().availableProcessors()
        System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        
    }
    
}
