import java.util.Scanner;

public class ConversorMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            ConversorDeMonedas.mostrarMenu();
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    // Realiza la conversión de moneda tradicional
                    break;
                case "2":
                    // Ver historial de cambios
                    break;
                case "3":
                    // realiza la conversion a Monedas digitales
                    try {
                        Scanner scanner = new Scanner(System.in);

                        System.out.print("Criptomoneda (ej: bitcoin, ethereum): ");
                        String cripto = scanner.nextLine().toLowerCase();

                        System.out.print("Moneda destino (ej: usd, eur): ");
                        String fiat = scanner.nextLine().toLowerCase();

                        double precio = MonedaDigital.obtenerPrecio(cripto, fiat);

                        System.out.printf("1 %s = %.4f %s%n", cripto.toUpperCase(), precio, fiat.toUpperCase());

                        ConversorDeMonedas.historial.add(
                                String.format("1 %s = %.4f %s", cripto.toUpperCase(), precio, fiat.toUpperCase())
                        );

                    } catch (Exception e) {
                        System.out.println("⚠️ Error al consultar precio: " + e.getMessage());
                    }
                    break;

                case "4":
                    //cierra el programa
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void realizarConversion() {
        try {
            System.out.print("Moneda de origen (ej: USD): ");
            String origen = scanner.nextLine().trim().toUpperCase();

            System.out.print("Moneda de destino (ej: EUR): ");
            String destino = scanner.nextLine().trim().toUpperCase();

            if (!ConversorDeMonedas.esConversionValida(origen, destino)) {
                System.out.println("Error: una o ambas monedas no son válidas o no están soportadas.");
                return;
            }

            System.out.print("Cantidad a convertir: ");
            double cantidad = Double.parseDouble(scanner.nextLine().trim());

            double resultado = ConversorDeMonedas.convertirMoneda(origen, destino, cantidad);

            System.out.printf("Resultado: %.2f %s = %.2f %s%n", cantidad, origen, resultado, destino);

            String registro = String.format("%.2f %s -> %.2f %s", cantidad, origen, resultado, destino);
            ConversorDeMonedas.historial.add(registro);

        } catch (NumberFormatException e) {
            System.out.println("Error: la cantidad ingresada no es válida.");
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }

    private static void mostrarHistorial() {
        if (ConversorDeMonedas.historial.isEmpty()) {
            System.out.println("No hay conversiones registradas todavía.");
        } else {
            System.out.println("\n===== HISTORIAL DE CONVERSIONES =====");
            for (String registro : ConversorDeMonedas.historial) {
                System.out.println(registro);
            }
        }
    }
}
