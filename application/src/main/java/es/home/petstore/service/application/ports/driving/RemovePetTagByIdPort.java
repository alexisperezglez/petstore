package es.home.petstore.service.application.ports.driving;

import es.home.petstore.service.application.ports.driving.cqs.commands.RemovePetTagByIdCommand;

public interface RemovePetTagByIdPort {
  void removeById(RemovePetTagByIdCommand command);
}
