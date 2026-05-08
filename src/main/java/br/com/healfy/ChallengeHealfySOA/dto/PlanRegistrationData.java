package br.com.healfy.ChallengeHealfySOA.dto;

import br.com.healfy.ChallengeHealfySOA.enums.Goals;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

// Recebe dados POST
// O DTO deve conter apenas o que o usuário precisa enviar via JSON
public record PlanRegistrationData(
        @NotBlank ( message = "O preenchimento do nome de usuário é obrigatório")
        String userName,

        @NotNull(message = "O preenchimento do objetivo é obrigatório (EX: EMAGRECIMENTO)")
        Goals goal,

        @Positive(message = "A quantidade de calorias deve ser positiva")
        int calories
) {
}
