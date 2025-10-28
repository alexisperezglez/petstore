package es.home.petstore.service.jparepository.model;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
public class TagMO {
  UUID id;
  String value;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof TagMO other)) return false;
    return id.equals(other.id);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
