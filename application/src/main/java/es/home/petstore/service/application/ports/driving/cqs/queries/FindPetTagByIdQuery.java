package es.home.petstore.service.application.ports.driving.cqs.queries;

import java.util.UUID;

public class FindPetTagByIdQuery {
  private final UUID id;

  public FindPetTagByIdQuery(UUID id) {
    if (id == null)
      throw new IllegalArgumentException("Id cannot be null");
    this.id = id;
  }

  public UUID getId() {
    return this.id;
  }
}
