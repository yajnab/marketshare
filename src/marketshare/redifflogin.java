/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author yajnavalkya
 */
public class redifflogin {
    
    public WebClient tremorLogin(WebClient webClient) throws Exception {
        webClient.getOptions().setJavaScriptEnabled(false);
        int netflag = 1;
        try {
            try {
                final HtmlPage currentPage = webClient.getPage("https://mail.rediff.com/cgi-bin/login.cgi");
            } catch (Exception e) {
                netflag = 0;
                System.out.println("Network Failure");
            }//Load page at the STRING address.
            if (netflag == 1) {
                final HtmlPage currentPage = webClient.getPage("https://mail.rediff.com/cgi-bin/login.cgi");
                try{final HtmlInput username = (HtmlInput) currentPage.getElementById("login1"); //Find element called login1 for username
                username.setValueAttribute("yajnavalkya.ban@rediffmail.com"); //Set value for username               
                final HtmlInput password = (HtmlInput) currentPage.getElementById("password"); //Find element called password for password
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String uPwd = br.readLine();
                password.setValueAttribute(uPwd); //Set value for password
                HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@name=\"proceed\"]");                
                HtmlPage src = htmlElement.click();}
                catch(Exception e){System.out.println(e);}
                
                WebResponse response1 = currentPage.getWebResponse();
                String content2 = response1.getContentAsString();
                final HtmlPage page2;
                Page page = webClient.getPage("http://portfolio.rediff.com/watchlist?src=top_nav");                    
                WebResponse ws = page.getWebResponse();
                    String content = page.getWebResponse().getContentAsString();
                    try{
                        FileWriter fw = new FileWriter("rediff.html",true);                                        
                        fw.write(content);
                        fw.flush();
                        fw.close();}
                    catch(Exception e){
                        System.out.println(e);
                    }
                return webClient;
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return webClient;
}
    public static void main(String args[]) throws IOException, Exception{
    WebClient wc = new WebClient();
        redifflogin rl = new redifflogin();
    rl.tremorLogin(wc);
    
    
}
}
