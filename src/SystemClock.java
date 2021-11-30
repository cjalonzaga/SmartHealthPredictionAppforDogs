
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris John Alonzaga
 */
public class SystemClock {
    private String theTime;
    public void setTimeNow(){
        SimpleDateFormat frm = new SimpleDateFormat("hh:mm:ss a");
        String tm;
        
        Date date = new Date(System.currentTimeMillis());
        
        tm = frm.format(date);
        
        theTime = tm;
    }
    
    public String getTheTime(){
        return this.theTime;
    }
}
