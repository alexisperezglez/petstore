package es.home.petstore.service.application.ports.driving.cqs.queries;

import java.util.UUID;

import es.home.petstore.service.domain.shared.utils.StringUtils;

public class FindPetTagsByFilterQuery {

  private final String value;
  private final Integer pageSize;
  private final UUID cursor;

  public FindPetTagsByFilterQuery(String value, Integer pageSize, UUID cursor) {
    if (StringUtils.isBlank(value))
      throw new IllegalArgumentException("Value filter must be provided");
    if (pageSize != null && (pageSize < 5 || pageSize > 100))
      throw new IllegalArgumentException("Page size must be between 5 and 100");
    if (pageSize == null)
      pageSize = 10;

    this.value = value;
    this.pageSize = pageSize;
    this.cursor = cursor;
  }

  public String getValue() {
    return value;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public UUID getCursor() {
    return cursor;
  }

}
