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
import java.util.HashSet;
import org.hibernate.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author Cris John Alonzaga
 */
public class Diagnose extends DBQueries{
    
    private HashSet<Integer> arr = new HashSet<>();
    private HashSet<String> hash = new HashSet<>();
    private ArrayList<String> listDisease;
  
    private HashMap<Integer , String> map = new HashMap<>();
    
   
    public void detectSymptoms(boolean bool){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       
        ArrayList<DiseaseSymptomsMap> dsm = new ArrayList<>(); 
        
        for(Integer i : getSymptomsId()){
            
            ///System.out.println(i);
            Query qry = session.createQuery("SELECT d FROM DiseaseSymptomsMap d WHERE d.symptomsList = " + i );
            //qry.setParameter("ids", i);
            
            dsm = (ArrayList<DiseaseSymptomsMap>) qry.list();
            
        }
//        for(DiseaseSymptomsMap d : dsm){
//            System.out.println(d.getDogDiseases().getDiseaseName()+" "+d.getSymptomsList().getSymptoms());
//        }
        if(!bool){
            setFilteredSymptoms(dsm);
        }
        else{
            setFinalDiagnoses(dsm);
        }
        
        session.getTransaction().commit();
    }
    
    public void setSymptomsId(HashSet<Integer> arr){
        this.arr = arr;
    }
    
    public HashSet<Integer> getSymptomsId(){
         return this.arr;
    }
    
    public void setFilteredSymptoms(ArrayList<DiseaseSymptomsMap> dd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        for(DiseaseSymptomsMap dsm : dd){
            Query qry = session.createQuery("FROM DiseaseSymptomsMap dsm where dsm.dogDiseases ="+dsm.getDogDiseases().getId());
            
            ArrayList<DiseaseSymptomsMap> s = (ArrayList<DiseaseSymptomsMap>) qry.list();
            
            for(DiseaseSymptomsMap ss : s){
                //prevent the previous selected symptoms to appear again
                if(!getSymptomsId().contains(ss.getSymptomsList().getId())){
                    hash.add(ss.getSymptomsList().getSymptoms());
                }
                //System.out.println(ss.getDogDiseases().getDiseaseName());
            }
        }
        
        session.getTransaction().commit();
        
    }
    
    public HashSet<String> getFilteredSymptoms(){
        return this.hash;
    }
    
    public void setFinalDiagnoses(ArrayList<DiseaseSymptomsMap> dmap){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        for(DiseaseSymptomsMap dsm : dmap){
            
            this.hash.add(dsm.getDogDiseases().getDiseaseName());
            map.put(dsm.getDogDiseases().getId(), dsm.getDogDiseases().getDiseaseName());
            
        }
        
        listDisease = new ArrayList<>(this.hash);
        
        session.getTransaction().commit();
    }
    
    public HashMap<Integer , String> getDiseaseHashMap(){
        return this.map;
    }
    
    public ArrayList<String> getFinalDiagnoses(){
        return this.listDisease;
    }
    
}
