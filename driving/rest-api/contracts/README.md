### OpenAPI specification

Definition of the API in [OpenAPI](https://spec.openapis.org/oas/v3.1.0) format. We define the API in a separate file to 
make it easier to generate the API server. Using the [openapi-generator](https://openapi-generator.tech/) tool we can 
generate the API server from the [OpenAPI specification](./apispec.yaml) with the [config.yaml](./config.yaml) 
configuration file.

### Generate API

In order to generate the API server, we use the [openapi-generator](https://openapi-generator.tech/docs/generators/spring) 
tool through a docker container.

### API generation config

[config.yaml](./config.yaml)

```yaml
interfaceOnly: true
# one operation per api
useTags: true
singleContentTypes: true
useOperationIdAsClassname: true

modelPackage: es.home.petstore.service.driving.restapi.server.model
modelNameSuffix: Resource

apiNameSuffix: API
apiPackage: es.home.petstore.service.driving.restapi.server

delegatePattern: false
# use jakarta.validation.constraints.* instead of javax.validation.constraints.*
useJakartaEe: true
# use spring boot 3.x dependencies
useSpringBoot3: true
library: spring-boot
additionalModelTypeAnnotations: "@lombok.Builder;@lombok.Data;@lombok.NoArgsConstructor;@lombok.AllArgsConstructor"

# artifactId of the library
artifactId: petstore-api-server

# groupId of the library
groupId: es.home.petstore.service
```

#### Config properties
| Config Property                | Value                                                                             | Description                                                                    |
|--------------------------------|-----------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| interfaceOnly                  | true                                                                              | Generate only the interface                                                    |
| useTags                        | true                                                                              | One operation per api                                                          |
| singleContentTypes             | true                                                                              | Single content type                                                            |
| useOperationIdAsClassname      | true                                                                              | Use operation id as classname                                                  |
| modelPackage                   | es.home.petstore.service.driving.restapi.server.model                             | Model package                                                                  |
| modelNameSuffix                | Resource                                                                          | Model name suffix                                                              |
| apiNameSuffix                  | API                                                                               | API name suffix                                                                |
| apiPackage                     | es.home.petstore.service.driving.restapi.server                                   | API package                                                                    |
| delegatePattern                | false                                                                             | Delegate pattern                                                               |
| useJakartaEe                   | true                                                                              | Use jakarta.validation.constraints.* instead of javax.validation.constraints.* |
| useSpringBoot3                 | true                                                                              | Use spring boot 3.x dependencies                                               |
| library                        | spring-boot                                                                       | Library                                                                        |
| additionalModelTypeAnnotations | @lombok.Builder;@lombok.Data;@lombok.NoArgsConstructor;@lombok.AllArgsConstructor | Additional model type annotations                                              |
| artifactId                     | petstore-api-server                                                               | Artifact id                                                                    |
| groupId                        | es.home.petstore.service                                                          | Group id                                                                       |

### Generate API Server
```shell
docker run --rm -v $PWD:/local openapitools/openapi-generator-cli generate \
-i /local/driving/rest-api/contracts/apispec.yaml \
-g spring -o /local/out \
-c /local/driving/rest-api/contracts/config.yaml
```

**One-line command**

```shell
docker run --rm -v $PWD:/local openapitools/openapi-generator-cli generate -i /local/driving/rest-api/contracts/apispec.yaml -g spring -o /local/out -c /local/driving/rest-api/contracts/config.yaml
```

### Go to the generated directory
`cd ./out`

### Build the generated jar
`mvn package`

This command will generate a jar file in the `target` directory.

### Add the generated jar to the classpath of the application

Add dependency to [./driving/rest-api/pom.xml](./../pom.xml)

```xml
<dependency>
  <groupId>es.home.petstore.service</groupId>
  <artifactId>petstore-api-server</artifactId>
  <version>1.0.0</version>
  <scope>system</scope>
  <systemPath>${project.basedir}/../../out/target/petstore-api-server-1.0.0.jar</systemPath>
</dependency>
```
