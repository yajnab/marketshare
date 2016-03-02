/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import com.gargoylesoftware.htmlunit.WebClient;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author yajnavalkya
 */
public class genFile {
    static String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    csvparse cp = new csvparse();
    public void GenFile(String content) throws IOException, SQLException
      {
          while (true) {
                         
              File file = new File("./output/"+date);
              File file_s = new File("./output/ishare/"+date);
              if(file.isDirectory() && (file_s.isDirectory())){
                  cp.createcsv(content);
              }else{File outp = new File("./output");
              if(outp.isDirectory()){
                  
                  File dir = new File("./output/"+date);
                  File dir1 = new File("./output/ishare/"+date);
                  dir.mkdir();dir1.mkdirs();
                  cp.createcsv(content);
              }
              else{
                  outp.mkdir();
                  File dir = new File("./output/"+date);
                  File dir1 = new File("./output/ishare/"+date);
                  dir.mkdir();dir1.mkdirs();
                  cp.createcsv(content);
              }
              }
              
          }
           
      }
}
