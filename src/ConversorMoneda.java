import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class ConversorMoneda {
    private final String API_KEY = "5cfaca0579826dd28954b7c9";
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private final Scanner scanner = new Scanner(System.in);

    public void convertir(String monedaOrigen, String monedaDestino) {
        System.out.print("Ingrese la cantidad de " + monedaOrigen + ": ");
        double cantidad = scanner.nextDouble();
        double tasaCambio = obtenerTasaCambio(monedaOrigen, monedaDestino);
        if (tasaCambio != -1) {
            double resultado = cantidad * tasaCambio;
            System.out.printf("%.2f %s equivalen a %.2f %s\n", cantidad, monedaOrigen, resultado, monedaDestino);
        } else {
            System.out.println("Error al obtener la tasa de cambio.");
        }
    }

    private double obtenerTasaCambio(String monedaOrigen, String monedaDestino) {
        try {
            String url = URL_BASE + monedaOrigen;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            if (json.get("result").getAsString().equals("success")) {
                return json.getAsJsonObject("conversion_rates").get(monedaDestino).getAsDouble();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }
}
