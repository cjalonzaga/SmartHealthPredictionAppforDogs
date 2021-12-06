/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.DiseaseSymptomsMap;
import entities.DogDiseases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author Cris John Alonzaga
 */
public class ViewRecords {
    
    private static HashMap<Integer , String> map = new HashMap<>();
    private ArrayList<String> mapper = new ArrayList<>();
    private ArrayList<String> diseaseInfos = new ArrayList<>();
    
    public void searchRecords(String diseases){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        for( Entry<Integer , String> entry : getDiseaseMap().entrySet() ){
            
            if( entry.getValue().equals(diseases) ){
               
                Query qry = session.createQuery( "FROM DiseaseSymptomsMap dsm where dsm.dogDiseases=" + entry.getKey() );
               
                ArrayList<DiseaseSymptomsMap> dsm =  (ArrayList<DiseaseSymptomsMap>) qry.list();
                setSymptomsViewRecord(dsm);
               
                DogDiseases dd = ( DogDiseases) session.load(DogDiseases.class, entry.getKey() );
                this.diseaseInfos.add( dd.getDiseaseName() );
                this.diseaseInfos.add( dd.getDiseaseInfo() );
               
            }
        }
        
        session.getTransaction().commit();
        
    }
    
    public void searchRecords(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query qry = session.createQuery( "FROM DiseaseSymptomsMap dsm where dsm.dogDiseases=" + id );
        
        ArrayList<DiseaseSymptomsMap> dsm =  (ArrayList<DiseaseSymptomsMap>) qry.list();
        setSymptomsViewRecord(dsm);
        
        DogDiseases dd = ( DogDiseases) session.load(DogDiseases.class, id);
        this.diseaseInfos.add( dd.getDiseaseName() );
        this.diseaseInfos.add( dd.getDiseaseInfo() );
        
        session.getTransaction().commit();
    }
    
    public ArrayList<String> getDiseaseInfo(){
        return this.diseaseInfos;
    }
    
    public  void setSymptomsViewRecord(ArrayList<DiseaseSymptomsMap> dsm){
        for(DiseaseSymptomsMap dd : dsm){
            this.mapper.add(dd.getSymptomsList().getSymptoms());
            //System.out.println(dd.getDogDiseases().getDiseaseName());
        }
    }
    
    public ArrayList<String> getSymptomsViewRecord(){
        return this.mapper;
    }
    
    public static void setDiseaseMap(HashMap<Integer, String> map){
        ViewRecords.map = map;
    }
    
    public static HashMap<Integer , String> getDiseaseMap(){
        return map;
    }
}
