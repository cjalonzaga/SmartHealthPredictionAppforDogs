package persistence;


import entities.DogDiseases;
import entities.SymptomsList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.hibernate.Query;
import org.hibernate.Session;
import utilities.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cris John Alonzaga
 */
public class DBQueries{
    
    private final  ArrayList<String> symptoms = new ArrayList<>();
    private final  ArrayList<String> diseases = new ArrayList<>();
    private final HashMap<Integer , String> mapdata = new HashMap<>();
    private static HashSet<Integer> id = new HashSet<>();
    
    public DBQueries(){}
    
    public void setSymptoms(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query qry = session.createQuery("FROM SymptomsList");
        
        ArrayList<SymptomsList> arr = (ArrayList<SymptomsList>) qry.list();
        
        if(!arr.isEmpty()){
           for(SymptomsList s : arr){
               this.symptoms.add(s.getSymptoms());
               this.mapdata.put(s.getId(), s.getSymptoms());
           }
        }
        else{
            System.out.println("No record");
        }
        
        session.getTransaction().commit();
        //HibernateUtil.closeSession();
    }
    
    public void setDiseases(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query qry = session.createQuery("FROM DogDiseases");
        
        ArrayList<DogDiseases> arr = (ArrayList<DogDiseases>) qry.list();
        
        if(!arr.isEmpty()){
            for(DogDiseases d : arr){
                this.diseases.add(d.getDiseaseName());
                this.mapdata.put(d.getId(), d.getDiseaseName());
            }
        }
        else{
            System.out.println("No record");
        }
        
        session.getTransaction().commit();
        //HibernateUtil.closeSession();
    }

    public ArrayList<String> getSymptoms(){
        return this.symptoms;
    }
    
    public ArrayList<String> getDiseases(){
        return this.diseases;
    }
    
    public HashMap<Integer,String> getMapping(){
        return this.mapdata;
    }
    
    public static void setSymtomsId(Integer ids){
        if(id.contains(ids)){
            id.remove(ids);
        } else {
            id.add(ids);
        }
    }
    
    public static HashSet<Integer> getSymtomsId(){
        return id;
    }
}
