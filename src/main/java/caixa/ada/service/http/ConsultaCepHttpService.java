package caixa.ada.service.http;

import caixa.ada.model.http.AgenciaHttp;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "consulta-cep-api")
public interface ConsultaCepHttpService {

    @GET
    @Path("/{cep}/json")
    AgenciaHttp buscaCep(String cep);

}
