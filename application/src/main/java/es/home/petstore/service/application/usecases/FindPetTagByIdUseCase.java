package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.FindPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.domain.exceptions.PetTagNotFoundException;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UseCase
public class FindPetTagByIdUseCase implements FindPetTagByIdPort {

  private static final Logger log = LoggerFactory.getLogger(FindPetTagByIdUseCase.class);
  private final PetTagService petTagService;

  public FindPetTagByIdUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public PetTag findBy(FindPetTagByIdQuery query) {
    log.info("Finding pet tag by id: {}", query);
    if (query == null)
      throw new IllegalArgumentException("Query cannot be null");
    PetTagId petTagId = PetTagId.of(query.getId());
    return petTagService.findBy(petTagId)
      .orElseThrow(() -> new PetTagNotFoundException(petTagId));
  }
}
