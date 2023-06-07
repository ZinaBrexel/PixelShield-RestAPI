package fr.simplon.pixelshielrestapi.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Open API specifications",
        description = "Points d'entrée de l'API de vente en ligne d'assurance et gestion du personnel"),
        servers = @Server(url = "http://localhost:8080/pixelshield"))
class OpenApiConfiguration
{
}