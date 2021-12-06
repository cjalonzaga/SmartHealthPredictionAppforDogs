package entities;
// Generated 12 2, 21 8:33:13 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DogDiseases generated by hbm2java
 */
public class DogDiseases  implements java.io.Serializable {


     private Integer id;
     private String diseaseName;
     private String diseaseInfo;
     private Set diseaseSymptomsMaps = new HashSet(0);

    public DogDiseases() {
    }

	
    public DogDiseases(String diseaseName, String diseaseInfo) {
        this.diseaseName = diseaseName;
        this.diseaseInfo = diseaseInfo;
    }
    public DogDiseases(String diseaseName, String diseaseInfo, Set diseaseSymptomsMaps) {
       this.diseaseName = diseaseName;
       this.diseaseInfo = diseaseInfo;
       this.diseaseSymptomsMaps = diseaseSymptomsMaps;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDiseaseName() {
        return this.diseaseName;
    }
    
    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
    public String getDiseaseInfo() {
        return this.diseaseInfo;
    }
    
    public void setDiseaseInfo(String diseaseInfo) {
        this.diseaseInfo = diseaseInfo;
    }
    public Set getDiseaseSymptomsMaps() {
        return this.diseaseSymptomsMaps;
    }
    
    public void setDiseaseSymptomsMaps(Set diseaseSymptomsMaps) {
        this.diseaseSymptomsMaps = diseaseSymptomsMaps;
    } 
}


