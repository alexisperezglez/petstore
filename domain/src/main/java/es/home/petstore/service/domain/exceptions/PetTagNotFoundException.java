package es.home.petstore.service.domain.exceptions;

import es.home.petstore.service.domain.model.pet.PetTagId;

public class PetTagNotFoundException extends PetStoreException {
  public PetTagNotFoundException(PetTagId id) {
    super("TAG001", "Pet tag not found with id: %s", id);
  }
}
