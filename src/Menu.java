import java.util.Scanner;

public class Menu {
    private final ConversorMoneda conversor = new ConversorMoneda();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("*********************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("*********************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");
            opcion = scanner.nextInt();
            manejarOpcion(opcion);
        } while (opcion != 7);
    }

    private void manejarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> conversor.convertir("USD", "ARS");
            case 2 -> conversor.convertir("ARS", "USD");
            case 3 -> conversor.convertir("USD", "BRL");
            case 4 -> conversor.convertir("BRL", "USD");
            case 5 -> conversor.convertir("USD", "COP");
            case 6 -> conversor.convertir("COP", "USD");
            case 7 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción inválida.");
        }
    }
}