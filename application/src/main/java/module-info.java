module application {
  requires domain;
  requires org.slf4j;
  exports es.home.petstore.service.application.usecases;
  exports es.home.petstore.service.application.ports.driving;
  exports es.home.petstore.service.application.ports.driving.cqs.commands;
  exports es.home.petstore.service.application.ports.driving.cqs.queries;
}