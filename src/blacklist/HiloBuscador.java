/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacklist;

import fuentes.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

/**
 *
 * @author juan
 */
public class HiloBuscador extends Thread {
    int sup,inf;
    String ipaddress;
    LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    int ocurrencesCount=0;
    int checkedListsCount=0;
    static HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    public HiloBuscador (int inf,int sup,String ipaddress){
    this.inf=inf;
    this.sup=sup;
    this.ipaddress= ipaddress;
    }
    public void run(){  
            
        
        
        for (int i=inf;i<sup ;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)){
                
                blackListOcurrences.add(i);
                ocurrencesCount++;   
            }
        }
       
        
  }
  public int numBadS(){
      return ocurrencesCount;
  }
  public LinkedList<Integer> badS(){
      return blackListOcurrences;
  }
  public int numRevisados(){
      return checkedListsCount;
  }
  
  
}
