package br.com.healfy.ChallengeHealfySOA.model;

import br.com.healfy.ChallengeHealfySOA.dto.PlanRegistrationData;
import br.com.healfy.ChallengeHealfySOA.dto.PlanUpdateData;
import br.com.healfy.ChallengeHealfySOA.enums.Goals;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name="MealPlan")
public class MealPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active = true;
    private String userName;

    @Enumerated(EnumType.STRING)
    private Goals goal;


    private int calories;

    private LocalDate planDate;

    @PrePersist
    public void prePersist(){
        if(planDate == null){
            planDate = LocalDate.now();
        }
    }

    public MealPlanModel(PlanRegistrationData data){
                this.userName = data.userName();
                this.goal = data.goal();
                this.calories = data.calories();
    }
    public void updateInformation(PlanUpdateData data){
        if(data.userName() != null){
            this.userName = data.userName();
        }
        if(data.goal() != null ){
            this.goal = data.goal();
        }
        if(data.calories() != null ){
            this.calories = data.calories();
        }
    }

    public void delete() {
        this.active = false;
    }


}
