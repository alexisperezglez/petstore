package es.home.petstore.service.application.ports.driving;

import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagsByFilterQuery;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;

public interface FindPetTagsByFilterPort {
  CursorPagedData<PetTag> findAllBy(FindPetTagsByFilterQuery query);
}
