/*
 * This class is inteded to creation of the portfolio as per the order of Nizamuddin Laskar
 */
package marketshare;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author yajnavalkya
 */
public class portsub {

    public portsub() {
    }

    public void fetcher() throws IOException {
        File file = new File("list.csv");
        String[] slistn = new String[11];
        Double[] slistv = new Double[11];
        for (int j = 0; j < 10; j++) {
            slistn[j] = "";
            slistv[j] = Double.NEGATIVE_INFINITY;

        }
        int k = 0;
        String qlink = "https://in.finance.yahoo.com/q/hp?s=";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String newline = System.getProperty("line.separator");
        WebClient webc = new WebClient(BrowserVersion.FIREFOX_38);
        webc.getOptions().setJavaScriptEnabled(false);
        while (br.readLine() != null) {
            try {
                if (!br.readLine().trim().isEmpty()) {
                    try {
                        String l = br.readLine();
                        if (l.length() > 0) {
                            Thread.sleep(250);
                            System.out.println(l);
                            Document doc = null;
                            String content = null;
                            String s = "https://in.finance.yahoo.com/q/hp?s=" + l.trim() + ".NS";
                            System.out.println(s);
                            double max = 0, min = 0;
                            try {
                                Page page = webc.getPage(s);
                                WebResponse ws = page.getWebResponse();
                                content = page.getWebResponse().getContentAsString();
                                //System.out.println(content);
                            } catch (IOException | FailingHttpStatusCodeException e) {
                                System.out.println(e);
                            }
                            doc = Jsoup.parse(content);
                            int b = 0;//Flat variable to determine the position of the JSoup Parser in the table
                            for (Element table : doc.select("table#yfncsumtab table.yfnc_datamodoutline1 table")) {
                                for (Element row : table.select("tr")) {
                                    Elements tds = row.select("td");
                                    int c = 0;
                                    for (Element cell : tds) {
                                        if (c == 2) {
                                            max = Double.parseDouble(cell.text());
                                            System.out.println(cell.text());
                                        }
                                        if (c == 3) {
                                            min = Double.parseDouble(cell.text());
                                            System.out.println(min);
                                        }
                                        c++;
                                    }
                                    try {
                                        makelist(max, min, l.trim(), slistn, slistv);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    c = 0;
                                    b++;
                                    if (b > 1) {
                                        break;
                                    }//b=0 throws the <th> tag and the first row or the <td> is returned on b=0;
                                }
                            }
                        }
                    } catch (IOException | InterruptedException e) {
                    }
                } else {
                    System.err.println("List Error at" + br.readLine());
                }
            } catch (Exception e) {
            }

        }
    }

    public static void main(String args[]) throws IOException {
        portsub p = new portsub();
        p.fetcher();
    }

    private void makelist(double max, double min, String sname, String[] slistn, Double[] slistv) {
        //String test_share_name="demo";     
        double percent_change = (max - min) / ((max + min) / 2);

        
        slistv[10]=percent_change;
        slistn[10]= sname;
        
        for(int i=0;i<10;i++){
         int c=i+1;double k;String sk;
         for(int j=0;j<10-i;j++)
         {
             if(slistv[i]<slistv[c])
             {
                 //For Value
                 k=slistv[c];
                 slistv[c]=slistv[i];
                 slistv[i]=k;
                 //For String
                 sk=slistn[c];
                 slistn[c]=slistn[i];
                 slistn[i]=sk;
                }
                c++;
            }
        }   
        for (int j = 0; j < 10; j++) {
            System.out.print("name  "+slistn[j]+"\t");
            System.out.println("value  "+slistv[j]);           
        }
    }
}
