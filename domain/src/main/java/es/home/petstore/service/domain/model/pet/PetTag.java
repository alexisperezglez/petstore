package es.home.petstore.service.domain.model.pet;

import es.home.petstore.service.domain.shared.Entity;
import es.home.petstore.service.domain.shared.utils.StringUtils;

import java.util.Objects;

public class PetTag implements Entity<PetTagId> {

  private PetTagId id;
  private String value;

  public PetTag(PetTagId id, String value) {
    setId(id);
    setValue(value);
  }

  /**
   * Factory method to create new PetTag from value.
   *
   * @param value PetTag value
   * @return new PetTag instance
   *
   * @throws IllegalArgumentException if value is null or blank
   * @throws IllegalArgumentException if value is less than 3 characters long
   * @throws IllegalArgumentException if value is more than 30 characters long
   *
   * @see #setValue(String value)
   * @see #PetTag(PetTagId id, String value)
   */
  public static PetTag createFromValue(String value) {
    return new PetTag(PetTagId.generate(), value);
  }

  public PetTagId getId() {
    return id;
  }

  void setId(PetTagId id) {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  void setValue(String value) {
    if (StringUtils.isBlank(value) || value.trim().length() < 3 || value.trim().length() > 30) {
      throw new IllegalArgumentException("Name must be between 3 and 30 characters long");
    }
    this.value = StringUtils.trimAndUpperCase(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PetTag petTag = (PetTag) o;
    return id.equals(petTag.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "PetTag{" +
        "id=" + id +
        ", value='" + value + '\'' +
        '}';
  }
}
