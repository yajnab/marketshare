/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

/**
 *
 * @author yajnavalkya
 */
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static marketshare.csvparse.date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class sfetch {
    public static void main(String args[]) throws IOException, InterruptedException
    {
    String link = "http://money.rediff.com/companies/nseall/";
    WebClient webc = new WebClient(BrowserVersion.FIREFOX_38);
    int i=0,k=0;
    for(i=0;i<2221;i=i+50)
    {k=i;
        if(i<2121){String flink = new String(link+(i+1)+"-"+(i+50)+"");
        //String flink = new String(link+(i)+"-"+(i)+"");
        System.out.println(flink);
        Page page=webc.getPage(flink);
        WebResponse ws =page.getWebResponse();
        String content = page.getWebResponse().getContentAsString();
        //System.out.println(content);
        sfetch sf = new sfetch();
        sf.csvparse(content);
    }}int j;
    System.out.println(k);
    Thread.sleep(5000);
    
    for(j=k+1;j<=2221;j++){
        //String flink = new String(link+(j+1)+"-"+(j+200)+"");
        String flink = new String(link+(j)+"-"+(j)+"");
        System.out.println(flink);
        Page page=webc.getPage(flink);
        WebResponse ws =page.getWebResponse();
        String content = page.getWebResponse().getContentAsString();
        //System.out.println(content);
        sfetch sf = new sfetch();
        sf.csvparse(content);
    }i=j;System.out.println(i);
    }
    public void csvparse(String content) throws IOException{
       	Document doc = Jsoup.parse(content);
        File file = new File("list.csv");
         if(!file.exists()){
                file.createNewFile();
            } 
        //String fname = Integer.toString(file.listFiles().length +1);
    try {
		FileWriter writer = new FileWriter("list.csv",true);
	
		for (Element table : doc.select("table.dataTable")) {
			for (Element row : table.select("tr")) {
				//Elements ths = row.select("th");
				Elements tds = row.select("td");				
				for ( Element cell : tds){
					writer.write(cell.text().replace(",","").concat(","));                                        
                                        System.out.println(cell.text());
                                        writer.flush();
				}
				writer.write("\n");
                                //writer.flush();
			}
			writer.close();
		}
	} catch (IOException e) {
		e.getStackTrace();
	}
    }
}
