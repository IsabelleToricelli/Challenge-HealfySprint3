package br.com.healfy.ChallengeHealfySOA.dto;

import br.com.healfy.ChallengeHealfySOA.enums.Goals;
import br.com.healfy.ChallengeHealfySOA.model.MealPlanModel;

import java.time.LocalDate;

//O que a API deve devolver na requisição get
public record PlanDetailedData(
        Long id,
        String userName,
        Goals goal,
        int calories,
        LocalDate planDate
) {
    public PlanDetailedData(MealPlanModel model){
        this(
                model.getId(),
                model.getUserName(),
                model.getGoal(),
                model.getCalories(),
                model.getPlanDate()
        );
    }
}
