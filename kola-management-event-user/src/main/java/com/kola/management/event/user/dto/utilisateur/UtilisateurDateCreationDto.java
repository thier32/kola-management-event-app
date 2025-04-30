package com.kola.management.event.user.dto.utilisateur;

import java.time.LocalDate;

public record UtilisateurDateCreationDto(LocalDate dateCreation) implements IUtilisateurDto {
}
