package es.home.petstore.service.application.ports.driving;

import es.home.petstore.service.application.ports.driving.cqs.commands.RegisterPetTagCommand;
import es.home.petstore.service.domain.model.pet.PetTag;

public interface RegisterPetTagPort {
  PetTag register(RegisterPetTagCommand command);
}
