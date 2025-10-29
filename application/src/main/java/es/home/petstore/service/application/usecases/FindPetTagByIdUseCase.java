package es.home.petstore.service.application.usecases;

import org.springframework.transaction.annotation.Transactional;

import es.home.petstore.service.application.ports.driving.FindPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.domain.exceptions.PetTagNotFoundException;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
@Transactional(readOnly = true)
public class FindPetTagByIdUseCase implements FindPetTagByIdPort {

  private final PetTagService petTagService;

  public FindPetTagByIdUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public PetTag findBy(FindPetTagByIdQuery query) {
    if (query == null)
      throw new IllegalArgumentException("Query cannot be null");
    PetTagId petTagId = PetTagId.of(query.getId());
    return petTagService.findBy(petTagId)
      .orElseThrow(() -> new PetTagNotFoundException(petTagId));
  }

}
