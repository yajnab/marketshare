package marketshare;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yajnavalkya
 */
public class sharewrite {

    Marketshare ms;genFile gf;
    public sharewrite(){
        ms = new Marketshare();
        gf = new genFile();
    }
    public void run(){
        
        try {

                while (true) {
                    ms.sem.acquire(1);
                    ms.mutex.acquire();
                    String a = ms.list.removeFirst().toString();
                    gf.GenFile(a);
                    System.out.println("Consumer read");
                    ms.mutex.release();
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
