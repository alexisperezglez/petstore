package es.home.petstore.service.domain.shared.pagination;

import java.util.UUID;

public class CursorPagination {

  private final Long totalElements;
  private final Integer requestedPageSize;
  private final Integer pageSize;
  private final UUID nextCursor;

  public CursorPagination(Long totalElements, Integer requestedPageSize, Integer pageSize, UUID nextCursor) {
    this.totalElements = totalElements;
    this.requestedPageSize = requestedPageSize;
    this.pageSize = pageSize;
    this.nextCursor = nextCursor;
  }
  
}
