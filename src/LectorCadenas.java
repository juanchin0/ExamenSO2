import java.util.Scanner;

public class LectorCadenas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String linea;
        int contador = 0;

        System.out.println("--- Lector de Cadenas Iniciado ---");
        System.out.println("Escriba '*' o ' * ' para finalizar la lectura.");

        while (true) {
            System.out.print("> ");

            linea = scanner.nextLine();

            // CORRECCIÓN CLAVE: Usar trim() para quitar espacios
            // y luego verificar si la cadena resultante es "*"
            if (linea.trim().equals("*")) {
                break;
            }

            System.out.println("Cadena leída: " + linea);
            contador++;
        }

        System.out.println("\n--- Proceso Finalizado ---");
        System.out.println("Total de cadenas leídas: " + contador);
    }
}