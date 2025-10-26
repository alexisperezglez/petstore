package es.home.petstore.service.application.ports.driving.cqs.commands;

import es.home.petstore.service.domain.shared.utils.StringUtils;

public class RegisterPetTagCommand {
  private final String value;

  public RegisterPetTagCommand(String value) {
    validate(value);
    this.value = value;
  }

  private void validate(String value) {
    if (StringUtils.isBlank(value) || value.trim().length() < 3 || value.trim().length() > 30)
      throw new IllegalArgumentException("Name must be between 3 and 30 characters long");
  }

  public String getValue() {
    return this.value;
  }

}
