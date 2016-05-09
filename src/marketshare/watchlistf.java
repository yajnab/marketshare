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

/**
 *
 * @author tict
 */
public class watchlistf {
    public void wfetcher(WebClient wc) throws IOException{
        for(int i=0;i<20;i++){
        String link = "http://portfolio.rediff.com/watchlist?src=top_nav";
        Page page=  wc.getPage(link);
        WebResponse ws =page.getWebResponse();
        String content = page.getWebResponse().getContentAsString();
        csvparse scv = new csvparse();
        scv.createcsv(content);
    }
    }
}
