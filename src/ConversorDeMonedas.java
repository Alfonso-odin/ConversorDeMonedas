import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class ConversorDeMonedas {
    public static final List<String> historial = new ArrayList<>();

    public static void mostrarMenu() {
        System.out.println("\n===== CONVERSOR DE MONEDAS =====");
        System.out.println("1. Realizar una conversión");
        System.out.println("2. Ver historial de conversiones");
        System.out.println("3. Convertir criptomoneda");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }



    public static boolean esConversionValida(String monedaOrigen, String monedaDestino) {
        try {
            Map<String, Double> tasas = obtenerTasas(monedaOrigen);
            return tasas.containsKey(monedaDestino);
        } catch (Exception e) {
            return false;
        }
    }

    public static double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) throws Exception {
        Map<String, Double> tasas = obtenerTasas(monedaOrigen);

        if (!tasas.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda destino no encontrada: " + monedaDestino);
        }

        double tasa = tasas.get(monedaDestino);
        return cantidad * tasa;
    }

    public static Map<String, Double> obtenerTasas(String monedaOrigen) throws Exception {
        String apiKey = Config.getApiKey();
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, monedaOrigen);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(respuesta.body());

        if (!json.getString("result").equals("success")) {
            throw new RuntimeException("API no respondió con éxito. Respuesta: " + json.toString());
        }

        JSONObject jsonTasas = json.getJSONObject("conversion_rates");

        return jsonTasas.toMap().entrySet().stream()
                .filter(e -> e.getValue() instanceof Number)
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        e -> ((Number) e.getValue()).doubleValue()
                ));
    }
}
