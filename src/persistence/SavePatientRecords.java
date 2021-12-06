/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.DogDiseases;
import entities.SymptomsList;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import utilities.HibernateUtil;

/**
 *
 * @author Cris John Alonzaga
 */
public class SavePatientRecords {
    
    private static ArrayList<DogDiseases> diagnoseD = new ArrayList<>();
    private static ArrayList<SymptomsList> selectedS = new ArrayList<>();

    private String petName;
    private String ownerName;
    private String address;
    private Date date;
    
    SavePatientRecords(){};
    
    public void saveRecord(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        
        session.getTransaction().commit();
    }
    
    public static void setDiagnoseDisease(ArrayList<DogDiseases> d){
        diagnoseD = d;
    }
    
    public static ArrayList<DogDiseases> getDiagnoseDisease(){
        return diagnoseD;
    }
    
    public static ArrayList<SymptomsList> getSelectedS() {
        return selectedS;
    }
    
    public static void setSelectedS(ArrayList<SymptomsList> selectedS) {
        SavePatientRecords.selectedS = selectedS;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
