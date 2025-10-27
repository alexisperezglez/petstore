package es.home.petstore.service.restapi.mappers;

import org.mapstruct.Mapper;

import es.home.petstore.service.domain.shared.pagination.CursorPagination;
import es.home.petstore.service.driving.restapi.server.model.PaginationResource;

@Mapper(componentModel = "spring")
public interface PaginationMapper {

  PaginationResource toPaginationResource(CursorPagination source);

}
