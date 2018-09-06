/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacklist;


import fuentes.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2107990
 */
public class HostBlackListsValidatorAndrade extends Thread {
    static LinkedList<Integer> blackListOcurrences=new LinkedList<>();
    static int ocurrencesCount=0;
    static int checkedListsCount=0;
    static String ipaddress;
    static HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    static LinkedList<HiloBuscador> threads = new LinkedList();
    static int numThreads;
    private static final int BLACK_LIST_ALARM_COUNT=5;
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     *  @param numThreads how many threads execute.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public void run(){
        //variables de threads
        int cantidad=skds.getRegisteredServersCount();
        
        //Despliegue de Threads
        for (int i=0;i<numThreads;i++){
            int infd=((cantidad/numThreads)*i);
            int supd=((cantidad/numThreads)*(i+1));
            if (supd+1==cantidad){
            supd=cantidad;
            }
            threads.add(new HiloBuscador(infd,supd,ipaddress));
            threads.get(i).start();
        }
        try {
            //esperar a que terminen los threads
            for (int i=0;i<threads.size();i++){
            threads.get(i).join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(HostBlackListsValidatorAndrade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //actualizar presencia en en lista negra 
        for (int i=0;i<threads.size();i++){
           ocurrencesCount+=threads.get(i).numBadS();
           blackListOcurrences.addAll(threads.get(i).badS());
           checkedListsCount+=threads.get(i).numRevisados();
        }
        
        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
    }
    public List<Integer> checkHost(String ipaddress,int numThreads) throws InterruptedException{
        this.ipaddress=ipaddress;
        this.numThreads=numThreads;
        HostBlackListsValidatorAndrade control= new HostBlackListsValidatorAndrade();
        control.start();
        control.join();

        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidatorAndrade.class.getName());
    
    
    
}
