/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author yajnavalkya
 */
public class create_porfolio {

    public void make_portfolio(WebClient webClient) throws Exception {

        final HtmlPage currentPage = webClient.getPage("https://in.finance.yahoo.com/portfolio/new/");
        WebResponse response1 = currentPage.getWebResponse();
        String content2 = response1.getContentAsString();
        System.out.println(content2);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final HtmlInput portfolioname = (HtmlInput) currentPage.getElementById("yfi_pf_name"); //Find element called yfi_pf_name for portfolioname
        System.out.println("Enter portfolio name");
        String pname = br.readLine();
        System.out.println(pname);
        try {
            portfolioname.setTextContent(pname);
        } catch (Exception e) {
            e.printStackTrace();
        }//Set value for Portfolio Name
        System.out.println("we have set the porfolio name");
        HtmlTextArea txt = currentPage.getElementByName("yfi_pf_symbols");
        txt.setText("acc.ns");
        final HtmlPage page2;
        HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@id=\"yfi_pf_save\"]");
        HtmlPage src = htmlElement.click();
        //return webClient;

    }

}
