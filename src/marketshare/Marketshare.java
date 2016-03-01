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
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;  
import java.io.BufferedReader;
//jsoup lib

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;
import static marketshare.csvparse.date;


public class Marketshare {
    
    Connection con =null;
    
    public Marketshare()throws SQLException{        
            initDatabase();        
    }
    public final void initDatabase() throws SQLException{
        try{
          Class.forName("com.mysql.jdbc.Driver");
          con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/marketshare?zeroDateTimeBehavior=convertToNull","yajnab","petrol123");           
        }
        catch(ClassNotFoundException | SQLException e){System.out.println(e);}
    }
    //Login into Yahoo and return the client(Saves the cookies).
      private WebClient tremorLogin(WebClient webClient) throws Exception
    {
        webClient.getOptions().setJavaScriptEnabled(false); int netflag=1; 
        try{
        final HtmlPage currentPage = webClient.getPage("https://login.yahoo.com/");}
        catch(Exception e){netflag=0;
        System.out.println("TICT net failure");}//Load page at the STRING address.
        if(netflag==1){
        final HtmlPage currentPage = webClient.getPage("https://login.yahoo.com/");
        final  HtmlInput username = currentPage.getElementByName("username"); //Find element called loginuser for username
        username.setValueAttribute("nizamuddin_laskar@yahoo.com"); //Set value for username       
        final  HtmlInput password = currentPage.getElementByName("passwd"); //Find element called loginpassword for password
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String uPwd = br.readLine();
        password.setValueAttribute(uPwd); //Set value for password  
        WebResponse response1 = currentPage.getWebResponse();
        String content2 = response1.getContentAsString();    
        final HtmlPage page2; 
        HtmlElement htmlElement = currentPage.getFirstByXPath("//*[@id=\"login-signin\"]");
        HtmlPage src=htmlElement.click();       
        return webClient;
        }
    return webClient;
    }
      
      public void GenFile(WebClient webClient) throws IOException, SQLException
      {
          while (true) {
              System.out.println(new Date());
              //Thread.sleep(1000);
              
              Page page = webClient.getPage("https://in.finance.yahoo.com/portfolio/p_6/view/v1");
              
              WebResponse response = page.getWebResponse();
              String content = response.getContentAsString();
              //System.out.println(content);
              
              File file = new File("./output/"+date);
              File file_s = new File("./output/ishare/"+date);
              if(file.isDirectory() && (file_s.isDirectory())){
                  //createcsv(content);
              }else{File outp = new File("./output");
              if(outp.isDirectory()){
                  
                  File dir = new File("./output/"+date);
                  File dir1 = new File("./output/ishare/"+date);
                  dir.mkdir();dir1.mkdirs();
                  //createcsv(content);
              }
              else{
                  outp.mkdir();
                  File dir = new File("./output/"+date);
                  File dir1 = new File("./output/ishare/"+date);
                  dir.mkdir();dir1.mkdirs();
                  //createcsv(content);
              }
              }
              
          }
           
      }
      
      
      public static void main(String[] args) throws Exception {            
               Marketshare mkt = new Marketshare();
               WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               webClient = mkt.tremorLogin(webClient);
               mkt.GenFile(webClient);
             
        
    }
}
