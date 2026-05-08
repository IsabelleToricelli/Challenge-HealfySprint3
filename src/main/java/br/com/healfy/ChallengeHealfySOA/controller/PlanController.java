package br.com.healfy.ChallengeHealfySOA.controller;

import br.com.healfy.ChallengeHealfySOA.dto.PlanDetailedData;
import br.com.healfy.ChallengeHealfySOA.dto.PlanRegistrationData;
import br.com.healfy.ChallengeHealfySOA.dto.PlanUpdateData;
import br.com.healfy.ChallengeHealfySOA.model.MealPlanModel;
import br.com.healfy.ChallengeHealfySOA.repository.MealPlanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mealplans")
public class PlanController {

    @Autowired
    private MealPlanRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PlanDetailedData> createPlan (@Valid @RequestBody PlanRegistrationData data, UriComponentsBuilder uriBuilder){
       MealPlanModel plan = new MealPlanModel(data);
       repository.save(plan);
       URI uri = uriBuilder.path("/mealplans/{id}").buildAndExpand(plan.getId()).toUri();
       return ResponseEntity.created(uri).body(new PlanDetailedData(plan));
    }
//Mostra todos os elementos
    @GetMapping
    public ResponseEntity<Page<PlanDetailedData>> readPlans(
            @PageableDefault(size = 10, sort = {"userName"}) Pageable paginacao)
    {
        Page page = repository.findAll(paginacao).map(PlanDetailedData::new);

        return ResponseEntity.ok(page);
    }
//Mostra um elemento especifico buscado por seu id
    @GetMapping("/{id}")
   public ResponseEntity<PlanDetailedData> getPlan(@PathVariable Long id){
            MealPlanModel planModel = repository.getReferenceById(id);
            return ResponseEntity.ok(new PlanDetailedData(planModel));
   }

   @PutMapping
   @Transactional
    public ResponseEntity<PlanDetailedData> updatePlan
           (@RequestBody @Valid PlanUpdateData date) {
        MealPlanModel plan = repository.getReferenceById(date.id());
        plan.updateInformation(date);
        return ResponseEntity.ok(new PlanDetailedData(plan));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePlan(@PathVariable Long id){
        MealPlanModel plan = repository.getReferenceById(id);
        plan.delete();
        return ResponseEntity.noContent().build();

    }
}
