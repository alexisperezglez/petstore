package es.home.petstore.service.domain.model.pet;

import es.home.petstore.service.domain.shared.pagination.CursorPagedData;

import java.util.Optional;
import java.util.UUID;

public interface PetTagRepository {

  PetTag saveOrUpdate(PetTag petTag);

  Optional<PetTag> findById(PetTagId id);

  void deleteById(PetTagId id);

  CursorPagedData<PetTag> findAllBy(String filter, Integer pageSize, UUID cursor);
}
