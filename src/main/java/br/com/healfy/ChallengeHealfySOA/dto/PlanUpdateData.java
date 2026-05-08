package br.com.healfy.ChallengeHealfySOA.dto;

import br.com.healfy.ChallengeHealfySOA.enums.Goals;

public record PlanUpdateData(

        Long id,
        String userName,
        Goals goal,
        Integer calories

) {
}
