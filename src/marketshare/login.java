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
import java.io.InputStreamReader;

/**
 *
 * @author yajnavalkya
 */
public class login {

    public WebClient tremorLogin(WebClient webClient) throws Exception {
        webClient.getOptions().setJavaScriptEnabled(false);
        int netflag = 1;
        try {
            try {
                final HtmlPage currentPage = webClient.getPage("https://login.yahoo.com/");
            } catch (Exception e) {
                netflag = 0;
                System.out.println("TICT net failure");
            }//Load page at the STRING address.
            if (netflag == 1) {
                final HtmlPage currentPage = webClient.getPage("https://login.yahoo.com/");
                try{final HtmlInput username = currentPage.getElementByName("username"); //Find element called loginuser for username
                username.setValueAttribute("nizamuddin_laskar@yahoo.com"); //Set value for username
                //First invoke to get the password page
                try{HtmlElement idbutton = currentPage.getFirstByXPath("//*[@id=\"login-signin\"]");
                HtmlPage idsrc = idbutton.click();}catch(Exception e){System.out.println(e);}}
                catch(Exception e){System.out.println(e);}
                try{final HtmlInput password = currentPage.getElementByName("passwd"); //Find element called loginpassword for password
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String uPwd = br.readLine();
                password.setValueAttribute(uPwd); //Set value for password
                HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@id=\"login-signin\"]");                
                HtmlPage src = htmlElement.click();}
                catch(Exception e){System.out.println(e);}
                
                WebResponse response1 = currentPage.getWebResponse();
                String content2 = response1.getContentAsString();
                final HtmlPage page2;
               // Page page = webClient.getPage("https://in.finance.yahoo.com/portfolio/p_6/view/v1");                    
               // WebResponse ws = page.getWebResponse();
                //    String content = page.getWebResponse().getContentAsString();
                    System.out.println(content2);
                return webClient;
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return webClient;
    }
}
