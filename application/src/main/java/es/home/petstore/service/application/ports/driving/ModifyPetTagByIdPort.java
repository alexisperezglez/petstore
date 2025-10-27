package es.home.petstore.service.application.ports.driving;

import es.home.petstore.service.application.ports.driving.cqs.commands.ModifyPetTagByIdCommand;
import es.home.petstore.service.domain.model.pet.PetTag;

public interface ModifyPetTagByIdPort {
  PetTag modifyById(ModifyPetTagByIdCommand command);
}
