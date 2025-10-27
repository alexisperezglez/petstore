package es.home.petstore.service.domain.shared.pagination;

import java.util.Collection;

import es.home.petstore.service.domain.shared.Entity;

public class CursorPagedData<T extends Entity<?>> {

  private final Collection<T> data;
  private final CursorPagination pagination;

  public CursorPagedData(Collection<T> data, CursorPagination pagination) {
    this.data = data;
    this.pagination = pagination;
  }

  public Collection<T> getData() {
    return data;
  }

  public CursorPagination getPagination() {
    return pagination;
  }

}
