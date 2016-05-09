/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author yajnavalkya
 */
public class Marketmain {
    public static void main(String args[]) throws IOException, InterruptedException{
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.          
        try {
            
            redifflogin lgn = new redifflogin();
            try {
                System.out.println("before login");
                webClient = lgn.tremorLogin(webClient);
            } catch (Exception x) {
                x.printStackTrace();
            }
         } catch (Exception x) {
            x.printStackTrace();
        }
       
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine;
        System.out.println("Do u know to make new list(y/n)");
        char c='n';
        if((inputLine = bufferedReader.readLine().trim()) == null){} else {
            c = inputLine.charAt(0);
        }
        switch(c){
            case 'y':
            {
                sfetch sf = new sfetch();
                sf.main2();
                break;
            }            
        }
        System.out.println("Do u know to Get 10 top/bottom list(y/n)");
        char c1='n';
        if((inputLine = bufferedReader.readLine()) != null){c1 = inputLine.charAt(0);}
        switch(c1){
            case 'y':
            {
                portsub ps = new portsub();
                ps.fetcher();
                break;
            }            
        }
        System.out.println("Do u want to scrape from watchlist(y/n)");
        char c2='n';
        if((inputLine = bufferedReader.readLine()) != null){c2 = inputLine.charAt(0);}
        switch(c2){
            case 'y':
            {
                watchlistf wl = new watchlistf();
                wl.wfetcher(webClient);
                break;
            }            
        }
        
        
    }
        
    }
