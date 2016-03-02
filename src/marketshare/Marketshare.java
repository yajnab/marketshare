 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

/**
 * @author Mamun Arfin, 4th Year(2016), Techno India College of Technology
 * @author Arjo Ghosh, 4th Year(2016), Techno India College of Technology
 * @author Yajnavalkya Bandyopadhyay, 2nd Year(2016), Techno India College of Technology
 */
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class Marketshare {
    static Object LOCK = new Object();
    static LinkedList list = new LinkedList();
    static Semaphore sem = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

      
      
      /**/
      
      
      public static void main(String[] args) throws Exception {            
               Marketshare mkt = new Marketshare();
               WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               login lgn = new login();
               webClient = lgn.tremorLogin(webClient);               
               new shareread(webClient).start();
               new sharewrite().start();
        
    }
}
