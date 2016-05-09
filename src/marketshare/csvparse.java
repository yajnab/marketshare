/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Mamun Arfin, 4th Year(2016), Techno India College of Technology
 * @author Arjo Ghosh, 4th Year(2016), Techno India College of Technology
 * @author Yajnavalkya Bandyopadhyay, 2nd Year(2016), Techno India College of Technology
 */
public class csvparse {
    
    //Connection con = null;
    static String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    /*public csvparse() throws SQLException{
        initDatabase();        
    }
    /*public final void initDatabase() throws SQLException{
        try{
          Class.forName("com.mysql.jdbc.Driver");
          con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/marketshare?zeroDateTimeBehavior=convertToNull","yajnab","petrol123");           
        }
        catch(ClassNotFoundException | SQLException e){System.out.println(e);}
    }*/
    
    public static void main(String args[]) throws SQLException{
        //Marketshare ms = new Marketshare();
    }
    public final void createcsv(String content) throws IOException
      {/*Statement stmt= null;
        try{
            stmt = con.createStatement();
            
        }catch(Exception e){}*/
         //the code for csv file creation goes here... 
          //File input = new File("web.html");
         // File input = (File) content;
	//Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Document doc = Jsoup.parse(content);        
        File file = new File("./output/"+date);
        if(!file.isDirectory()){
            File outp = new File("./output");
                if(outp.isDirectory()){
                    File dir = new File("./output/"+date);
                    dir.mkdir();
                    }
                else{
                    outp.mkdir();
                    File dir = new File("./output/"+date);
                    dir.mkdir();}}
        String fname =  Long.toString(file.listFiles().length +1);
        
        /*This creates the initial date wise output directory*/   
        
        
        
         
	//Document doc = Jsoup.parse(input);
	try {
		//FileWriter writer = new FileWriter("./dataout.csv");
                FileWriter writer = new FileWriter("./output/"+date+"/stockdata_"+fname+".csv");
		try{for (Element table : doc.select("table#stocks")) {
			for (Element row : table.select("tr")) {
				
				Elements tds1 = row.select("td.f15 div a");
                                Elements tds2 = row.select("td.numericalColumn");
				String sname="";
                                int col=0;FileWriter writer1 = null;
				for ( Element cell : tds1){
                                    System.out.println(cell.text());
					writer.write(cell.text().replace(",","").concat(","));
                                        writer.flush();
                                }
                                for(Element cell : tds2){
                                    System.out.println(cell.text());
                                   writer.write(cell.text().replace(",","").concat(","));
                                    writer.flush();
                                        }writer.write("\n");writer.flush();
                                /*writer.append("\n");
                                writer.flush();
                                writer.close();*/                        }              
                        }}
        catch(Exception e){
           e.printStackTrace();
        }writer.close();}
                                        catch(Exception e){
                                            System.out.println("yajnab"+e);
                                        }
                                 
				}
    }
