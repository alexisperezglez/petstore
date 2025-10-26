package es.home.petstore.service.application.ports.driving;

import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.domain.model.pet.PetTag;

public interface FindPetTagByIdPort {
  PetTag findBy(FindPetTagByIdQuery query);
}
