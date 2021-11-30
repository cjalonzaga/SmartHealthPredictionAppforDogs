package persistence;


import java.util.HashSet;
import javax.swing.JCheckBox;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris John Alonzaga
 */
public final  class GUIUpdater extends SwingWorker<JCheckBox[], DBQueries>{
    private JCheckBox[] boxes;
    
    public  GUIUpdater(){
        setAllCheckBox();
    }

    @Override
    public JCheckBox[] doInBackground() throws Exception {
        return this.boxes;
    }
    
    public void setAllCheckBox(){
        DBQueries dbq = new DBQueries();
        dbq.setSymptoms();
        int num = dbq.getSymptoms().size();
        if(dbq.getSymptoms() != null){
           
            JCheckBox[] checkboxes = new JCheckBox[num];
            for(int i = 0 ; i < num ; i++){
                checkboxes[i] = new JCheckBox(dbq.getSymptoms().get(i));
                //System.out.println("Adding checkbox..."+Thread.currentThread().getName());
            }
            
            this.boxes = checkboxes;
        }
        else{
            System.out.println("Haha null");
        }
    }
    
    public void setFilteredCheckBox(HashSet<String> str){
        Object[] obj = str.toArray();
        if(!str.isEmpty()){
            int num = str.size();
            JCheckBox[] checkboxes = new JCheckBox[num];
            for(int i = 0 ; i < str.size() ; i++){
                checkboxes[i] = new JCheckBox(obj[i].toString());
                //System.out.println("Adding checkbox..."+Thread.currentThread().getName());
            }
            
            this.boxes = checkboxes;
        }
        else{
            System.out.println("Haha null");
        }
        
    }
}
