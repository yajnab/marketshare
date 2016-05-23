        /*/
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author tict
 */
public class watchlistf {
    public void wfetcher(WebClient wc) throws IOException{
        DateFormat df = new SimpleDateFormat("HHmmss");
        Date dateobj = new Date();
        String datestr =(df.format(dateobj));
        int timestr = Integer.parseInt(datestr);
        int timeend = 183000; //Sharemarket values stops updating at 6:30PM IST
        for(int i=timestr;i<132825;i++){
        String link = "http://portfolio.rediff.com/watchlist?src=top_nav";
        Page page=  wc.getPage(link);
        WebResponse ws =page.getWebResponse();
        String content = page.getWebResponse().getContentAsString();
        csvparse scv = new csvparse();
        scv.createcsv(content);
    }
    }
}
