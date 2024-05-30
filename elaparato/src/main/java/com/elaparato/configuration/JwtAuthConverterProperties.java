package com.elaparato.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data // Utiliza Lombok para simplificar la creación de métodos getters y setters
@Validated // Habilita la validación para esta clase de configuración
@Configuration // Declara esta clase como un bean de configuración de Spring
@ConfigurationProperties(prefix = "jwt.auth.converter") // Especifica el prefijo de las propiedades de configuración que esta clase vinculará
public class JwtAuthConverterProperties {

    private String resourceId; // Define una propiedad que almacenará el identificador del recurso utilizado en JWT

    private String principalAttribute; // Define una propiedad que almacenará el atributo del JWT que representa al usuario principal

}
