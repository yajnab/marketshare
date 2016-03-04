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
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class Marketshare {
         final int maxsize=9000;
     String[] buffer = new String[maxsize];
     int fp=0;     
     int rp=0;
     int qs=0;
     
    
    private class shareproducer extends Thread{ // process thread for i
         Page page;WebClient wc;
    String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    
    public shareproducer(WebClient webClient) throws IOException{
        wc = webClient;
    }
  
    public void insert_item(String content)
    {
         if (!(qs==maxsize)) {
         qs++;
         rp = (rp + 1)%maxsize;
         buffer[rp] = content;
         System.out.println("added");
      }
      else
         System.err.println("Overflow\n");
    }
   
    
        public void run(){
                try{
                    do{
                        int N = 0;
                        //produce_data  produde_data();
                        page = wc.getPage("https://in.finance.yahoo.com/portfolio/p_6/view/v1");
                        WebResponse ws =page.getWebResponse();
                        String content = page.getWebResponse().getContentAsString();
                      
                        insert_item(content);
                                            
                        //remainder section
                    }while(true); // 100 is upper bound, to remove infinite looping
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }
       
        }
    private class shareconsumer extends Thread{ // process thread for j
        
         public boolean buffer_empty(){
             
        if(qs == 0)
        return true;
        return false;
    }
         public void remove_item()
        {
              if (!(qs == 0)) {
         qs--;
         fp = (fp + 1)%maxsize;
        // return buffer[fp];
        System.out.println(buffer[fp]);
         System.out.println("removed");
      }
      else {
         System.err.println("Underflow");
         }
        }
            public void run(){
                try{
                    do{
                        if(buffer_empty())
                            Thread.sleep(2500);
                        remove_item();
                      
                                                             
                    }while(true); // 100 is upper bound, to remove infinite looping
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    public Marketshare(WebClient webclient) throws IOException{
        System.out.println("Starting Threads/Processes");
       // Thread I= new shareproducer();
        System.out.println("producer started");
        Thread J = new shareconsumer();
        new shareproducer(webclient).start(); //start process i
         System.out.println("consumer strted");
        J.start(); //start process j
    }
        public static void main(String[] args) throws Exception{
            
          try{
             WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               login lgn = new login();
               try{
               System.out.println("before login");
               webClient = lgn.tremorLogin(webClient);
               }
               catch (Exception x) {
                x.printStackTrace();
            }
             System.out.println("in main");
            Marketshare cSec = new Marketshare(webClient);
          }
                catch (Exception x) {
                x.printStackTrace();
            }
    }
}