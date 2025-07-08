import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class MonedaDigital {

    public static double obtenerPrecio(String cripto, String fiat) throws Exception {
        String url = String.format(
                "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=%s",
                cripto.toLowerCase(), fiat.toLowerCase()
        );

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("No se pudo obtener el precio. Código: " + respuesta.statusCode());
        }

        JSONObject json = new JSONObject(respuesta.body());

        if (!json.has(cripto.toLowerCase())) {
            throw new IllegalArgumentException("Criptomoneda no encontrada: " + cripto);
        }

        JSONObject precioObj = json.getJSONObject(cripto.toLowerCase());

        if (!precioObj.has(fiat.toLowerCase())) {
            throw new IllegalArgumentException("Moneda destino no encontrada: " + fiat);
        }

        return precioObj.getDouble(fiat.toLowerCase());
    }
}

