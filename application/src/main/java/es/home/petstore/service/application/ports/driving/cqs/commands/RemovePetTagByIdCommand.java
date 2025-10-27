package es.home.petstore.service.application.ports.driving.cqs.commands;

import java.util.UUID;

public class RemovePetTagByIdCommand {

  private final UUID id;

  public RemovePetTagByIdCommand(UUID id) {
    if (id == null)
      throw new IllegalArgumentException("ID must not be null");

    this.id = id;
  }

  public UUID getId() {
    return id;
  }

}
