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
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Do u know to make new list(y)");
        char c = br.readLine().charAt(0);
        switch(c){
            case 'y':
            {
                sfetch sf = new sfetch();
                sf.main2();
                break;
            }            
        }
        System.out.println("Do u know to Get 10 top/bottom list(y)");
        char c1 = br.readLine().charAt(0);
        switch(c1){
            case 'y':
            {
                portsub ps = new portsub();
                ps.fetcher();
                break;
            }            
        }
        
        
    }
        
    }
