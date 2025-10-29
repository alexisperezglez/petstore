package es.home.petstore.service.application.usecases;

import org.springframework.transaction.annotation.Transactional;

import es.home.petstore.service.application.ports.driving.ModifyPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.ModifyPetTagByIdCommand;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
@Transactional
public class ModifyPetTagByIdUseCase implements ModifyPetTagByIdPort {

  private final PetTagService petTagService;

  public ModifyPetTagByIdUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public PetTag modifyById(ModifyPetTagByIdCommand command) {
    PetTagId petTagId = PetTagId.of(command.getId());
    final PetTag petTag = new PetTag(petTagId, command.getValue());
    return petTagService.saveOrUpdate(petTag);
  }

  
}
