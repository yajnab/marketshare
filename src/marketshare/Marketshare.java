 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

/**
 *
 * @author Mamun Arfin, 4th Year(2016), Techno India College of Technology
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
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
class Marketshare {
static String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime())).toString();
    public static void main(String[] args) throws Exception {
        
               WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38); //Initiate a WebClient variable.  
               webClient = tremorLogin(webClient);
                  GenFile(webClient);
             
        
    }
    //Login into Yahoo and return the client(Saves the cookies).
      private static WebClient tremorLogin(WebClient webClient) throws Exception
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
      
      private static void GenFile(WebClient webClient) throws IOException
      {
              try {
        while (true) {
            System.out.println(new Date());
            Thread.sleep(1000);
       
          Page page = webClient.getPage("https://in.finance.yahoo.com/portfolio/p_6/view/v1");
              
          WebResponse response = page.getWebResponse();
               String content = response.getContentAsString();
              // System.out.println(content);
          
            File file = new File("./output/"+date);
            File file_s = new File("./output/ishare/"+date);
            if(file.isDirectory() && (file_s.isDirectory())){
             createcsv(content);
            }else{File outp = new File("./output");
                if(outp.isDirectory()){
                    File dir = new File("./output/"+date);
                    File dir1 = new File("./output/ishare/"+date);
                    dir.mkdir();dir1.mkdirs();
                    createcsv(content);
                }
                else{
                    outp.mkdir();
                    File dir = new File("./output/"+date);
                    File dir1 = new File("./output/ishare/"+date);
                    dir.mkdir();dir1.mkdirs();
                     createcsv(content);
                }
            }
            
             }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
           
      }
      
      public static void createcsv(String content) throws IOException
      {
          
          
          
         // the code for csv file creation goes here... 
          //File input = new File("web.html");
         // File input = (File) content;
	//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
          Document doc = Jsoup.parse(content);
        File file = new File("./output/"+date);
        String fname =  Long.toString(file.listFiles().length +1);
          
         
	//Document doc = Jsoup.parse(input);
	try {
		//FileWriter writer = new FileWriter("dataout.csv");
                FileWriter writer = new FileWriter("./output/"+date+"/stockdata_"+fname+".csv");
		for (Element table : doc.select("table.yfi_portfolios_multiquote")) {
			for (Element row : table.select("tr")) {
				Elements ths = row.select("th");
				Elements tds = row.select("td");
				if(ths.size()>0){
					for ( Element cellh : ths){
						writer.write(cellh.text().replace(",","").concat(","));
					}
				}String sname="";
                                int col=0;
				for ( Element cell : tds){
					writer.write(cell.text().replace(",","").concat(","));
                                        col++;
                                        //FileWriter writer2 = new FileWriter("./output/ishare/"+date+"/"+sname+".csv");
                                        try{
                                            if(col==1){
                                            FileWriter writer1 = new FileWriter("./output/ishare/"+date+"/"+cell.text()+".csv");
                                            File fch = new File("./output/ishare/"+date+"/"+cell.text()+".csv");
                                            sname = cell.text();
                                            System.out.println(sname);
                                            if(!fch.exists()){
                                                fch.createNewFile();
                                                //writer1.write(cell.text().replace(",","").concat(","));
                                            }                                          
                                            }
                                            else{
                                               try{
                                                   String path="./output/ishare/"+date+"/"+sname+".csv";
                                                FileWriter writer2 = new FileWriter(path,true);
                                               System.out.println("else");
                                               System.out.println(sname);
                                               System.out.println(cell.text());
                                               writer2.append(cell.text().replace(",","").concat(","));
                                               writer2.close();
                                               }
                                               catch(Exception e)
                                               {
                                                 System.out.println(e);  
                                               }
                                            }//writer2.write("\n");
                                            
                                        }
                                        catch(Exception e){
                                            System.out.println(e);
                                        }
                                 
				}
				writer.write("\n");
                               // writer2.close();
			}
			writer.close();
                        
		}}
	 catch (IOException e) {
		e.getStackTrace();
                 }
        catch(Exception e){
            System.out.println(e);
          
        }
          
          
      }
}
