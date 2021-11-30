/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.DiseaseSymptomsMap;
import entities.DogDiseases;
import entities.SymptomsList;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.SwingWorker;
import org.apache.commons.collections.map.MultiValueMap;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author Cris John Alonzaga
 */
public class SaveRecords extends SwingWorker{
    
    private static Object object;
    private MultiValueMap symptomsListId = new MultiValueMap();

    @Override
    public Object doInBackground() throws Exception {
        
        saveData(getDogDiseaseData());
        
        return "Disease information has been saved!";
    }
    
    public static void setDogDiseasesData(Object obj){
        object = obj;
    }
    
    public static Object getDogDiseaseData(){
        return object;
    }
    
    public void saveData(Object obj){
        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        DogDiseases dd = (DogDiseases) obj;
        
        session.save(dd);
        
        List list;
        Set entrySet = getIdForSelectedSymptoms().entrySet();
        Iterator it = entrySet.iterator();
        
        while (it.hasNext()) {
            
            Map.Entry mapEntry = (Map.Entry) it.next();
            list = (List) getIdForSelectedSymptoms().get(mapEntry.getKey());
            for (int j = 0; j < list.size(); j++) {
                
                DiseaseSymptomsMap dsm = new DiseaseSymptomsMap();
                SymptomsList sl = (SymptomsList) session.get(SymptomsList.class, (Serializable) mapEntry.getKey());
                //dsm.setDogDiseases(dd);
                if(Integer.parseInt(mapEntry.getKey().toString()) == 0 && sl == null){
                    SymptomsList sl3 = new SymptomsList(list.get(j).toString());
                    //System.out.println(sl3.getSymptoms());
                    dsm.setDogDiseases(dd);
                    session.save(sl3);
                    dsm.setSymptomsList(sl3);
                    session.save(dsm);
                }
                else{
                    SymptomsList sl2 = (SymptomsList) session.load(SymptomsList.class, (Serializable) mapEntry.getKey());
                    dsm.setDogDiseases(dd);
                    dsm.setSymptomsList(sl2);
                    session.save(dsm);
                }
            }
        }
        
        session.getTransaction().commit();
    }
    
    public void setIdForSelectedSymptoms(MultiValueMap arr){
        this.symptomsListId = arr;
    }
    
    public MultiValueMap getIdForSelectedSymptoms(){
        return this.symptomsListId;
    }
}
