/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author yajnavalkya
 */
public class Marketmain {
    public static void main(String args[]){
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
        portsub ps = new portsub();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    }
        
    }
