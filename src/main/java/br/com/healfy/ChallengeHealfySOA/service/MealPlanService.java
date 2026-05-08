package br.com.healfy.ChallengeHealfySOA.service;

import br.com.healfy.ChallengeHealfySOA.model.MealPlanModel;
import br.com.healfy.ChallengeHealfySOA.repository.MealPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    public MealPlanModel createPlan (MealPlanModel newplan){
        return mealPlanRepository.save(newplan);
    }
    public List<MealPlanModel> readAllPlans(){
        return mealPlanRepository.findAll();
    }

    public MealPlanModel readPlanById(Long id) {
        return mealPlanRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Plano alimentar não encontrado"));
    }

    public MealPlanModel updatePlanById(Long id, MealPlanModel updatePlan){
        return mealPlanRepository.findById(id).map(existingPlan->{
                existingPlan.setUserName(updatePlan.getUserName());
                existingPlan.setGoal(updatePlan.getGoal());
                existingPlan.setCalories(updatePlan.getCalories());
            return mealPlanRepository.save(existingPlan);
        }).orElseThrow(()-> new EntityNotFoundException("Plano alimentar não encontrado"));
    }
    public void deletePlanById(Long id){
        try{
            mealPlanRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Plano alimentar não encontrado");
        }
    }

}
