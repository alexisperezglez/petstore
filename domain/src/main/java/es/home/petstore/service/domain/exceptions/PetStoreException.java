package es.home.petstore.service.domain.exceptions;

public class PetStoreException extends RuntimeException {

  private final String code;
  private final String message;
  private final Object[] args;

  public PetStoreException(String code, String message, Object... args) {
    super(message);
    this.code = code;
    this.message = message;
    this.args = args;
  }
}
