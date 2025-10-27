package es.home.petstore.service.application.ports.driving.cqs.commands;

import java.util.UUID;

import es.home.petstore.service.domain.shared.utils.StringUtils;

public class ModifyPetTagByIdCommand {

  private final UUID id;
  private final String value;

    public ModifyPetTagByIdCommand(UUID id, String value) {
      validateId(id);
      validateValue(value);
      this.id = id;
      this.value = value;
    }

    private void validateId(UUID id) {
      if (id == null)
        throw new IllegalArgumentException("Id cannot be null.");
    }

    private void validateValue(String value) {
      if (StringUtils.isBlank(value))
        throw new IllegalArgumentException("Value cannot be null or empty.");
    }

    public UUID getId() {
      return id;
    }

    public String getValue() {
      return value;
    }

}
