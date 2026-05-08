package br.com.healfy.ChallengeHealfySOA.repository;

import br.com.healfy.ChallengeHealfySOA.model.MealPlanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlanModel, Long> {
    Page<MealPlanModel> findAllByActiveTrue(Pageable paginacao);
}
