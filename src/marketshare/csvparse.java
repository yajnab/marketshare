/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketshare;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    Connection con = null;
    static String date = (new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
    public csvparse() throws SQLException{
        initDatabase();        
    }
    public final void initDatabase() throws SQLException{
        try{
          Class.forName("com.mysql.jdbc.Driver");
          con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/marketshare?zeroDateTimeBehavior=convertToNull","yajnab","petrol123");           
        }
        catch(ClassNotFoundException | SQLException e){System.out.println(e);}
    }
    
    public static void main(String args[]) throws SQLException{
        Marketshare ms = new Marketshare();
    }
    public final void createcsv(String content)
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
                                int col=0;FileWriter writer1 = null;
				for ( Element cell : tds){
					writer.write(cell.text().replace(",","").concat(","));
                                        col++;
                                        //FileWriter writer2 = new FileWriter("./output/ishare/"+date+"/"+sname+".csv");
                                        try{
                                            if(col==1){
                                            File fch = new File("./output/ishare/"+date+"/"+cell.text()+".csv");
                                            sname = cell.text();
                                            System.out.println(sname);
                                            String rsname = sname.replaceAll("[-+.^:,]","");
                                            writer1 = new FileWriter("./output/ishare/"+date+"/"+sname+".csv",true);
                                            
                                            /*try{
                                                stmt.executeUpdate("CREATE TABLE "+rsname+"(prevc float(10,2), open float(10,2), "
                                                    + "price float(10,2), dayh float(10,2), dayl float(10,2), pe float(4,2), "
                                                    + "52wkh float(10,2), 52wkl float(10,2), volume int(15), "
                                                    + "avol int(8), bsize float(10,2), asks float(10,2), mktcap varchar(20), pchng float(3,2));");
                                                        }
                                            catch(SQLException e){System.out.println(e);
                                            }finally{
                                                //stmt.close();
                                            }*/
                                            if(!fch.exists()){
                                                fch.createNewFile();
                                                //writer1.write(cell.text().replace(",","").concat(","));
                                            }                                          
                                            }
                                            else if(col==16){
                                                writer1 = new FileWriter("./output/ishare/"+date+"/"+sname+".csv",true);
                                                writer1.write("\n");
                                                writer1.flush();
                                                writer1.close();                                               
                                                
                                            }
                                            else{
                                                try{
                                                    writer1 = new FileWriter("./output/ishare/"+date+"/"+sname+".csv",true);
                                                writer1.write(cell.text().toString()+"\t");
                                                writer1.flush();}
                                                catch(Exception e){System.out.println("Error");                                                
                                                }
                                                finally{writer1.close();}
                                            }
                                            
                                            
                                        }
                                        catch(Exception e){
                                            System.out.println(e);
                                        }
                                 
				}
				writer.write("\n");
                               // writer2.close();
			}//stmt.close();
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
