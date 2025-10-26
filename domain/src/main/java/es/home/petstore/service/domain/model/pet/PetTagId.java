package es.home.petstore.service.domain.model.pet;

import es.home.petstore.service.domain.shared.Identity;

import java.util.UUID;

public record PetTagId(UUID id) implements Identity<PetTagId> {
  public PetTagId {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
  }

  public static PetTagId of(UUID id) {
    return new PetTagId(id);
  }

  public static PetTagId generate() {
    return new PetTagId(UUID.randomUUID());
  }

}
