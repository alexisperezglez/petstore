package es.home.petstore.service.domain.model.pet;

import java.util.Optional;

public interface PetTagRepository {

  PetTag saveOrUpdate(PetTag petTag);

  Optional<PetTag> findById(PetTagId id);

  void deleteById(PetTagId id);
}
