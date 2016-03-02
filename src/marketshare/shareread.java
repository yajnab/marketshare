/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author yajnavalkya
 */
public class shareread extends Thread{

    /**
                    ms.list.add(new String(content));
                    System.out.println("Written to LinkedList "+date);
                    ms.mutex.release();
                    ms.sem.release(1);
                    //Thread.sleep(500);
                }
            } catch (Exception x) {
     *
     * @param webClient
     * @throws java.io.IOException
     */
    Page page;WebClient wc;
    static String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    public shareread(WebClient webClient) throws IOException{
        wc = webClient;
    }
    public void run() {
        Marketshare ms = new Marketshare();    
        try {
            while(true){
                int N = 0;
                page = wc.getPage("https://in.finance.yahoo.com/portfolio/p_6/view/v1");
                WebResponse ws =page.getWebResponse();
                String content = page.getWebResponse().getContentAsString();
                //while (true) {
                    ms.mutex.acquire();
                    ms.list.add(new String(content));
                    System.out.println("Written to LinkedList "+date);
                    ms.mutex.release();
                    ms.sem.release(1);
                    //Thread.sleep(500);
                }
            } catch (Exception x) {
                System.out.println("File not Read  "+x);
            }
        }
}
