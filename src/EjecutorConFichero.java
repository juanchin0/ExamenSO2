import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class EjecutorConFichero {

    public static void main(String[] args) {
        final String NOMBRE_FICHERO = "cadena.txt";

        // --- 1. Introducir la cadena y ALMACENARLA en un fichero ---
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la cadena (se guardará en un fichero, no se mostrará): ");
        String cadenaUsuario = scanner.nextLine();
        scanner.close();

        // Guardar la cadena en el fichero
        try (FileWriter writer = new FileWriter(NOMBRE_FICHERO)) {
            writer.write(cadenaUsuario);
            System.out.println("✅ Cadena guardada correctamente en " + NOMBRE_FICHERO);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero: " + e.getMessage());
            return; // Termina el programa si falla la escritura
        }

        // --- 2. Leer la cadena DESDE el fichero para ejecutar el proceso ---
        String cadenaLeida = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_FICHERO))) {
            cadenaLeida = reader.readLine();
            if (cadenaLeida == null) {
                System.err.println("Error: El fichero estaba vacío.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        // --- 3. Ejecutar VisualizadorCadena con la cadena leída ---
        String CLASSPATH = ".";

        String[] comando = {
                "java",
                "-cp",
                CLASSPATH,
                "VisualizadorCadena",
                cadenaLeida // Usa la cadena LEÍDA del fichero
        };

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            // Esto asegura que la salida del programa externo (las 5 repeticiones)
            // se muestre en la consola de este programa.
            pb.inheritIO();

            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            System.out.println("\n--- Proceso externo finalizado ---");
            System.out.println("Código de estado: " + codigoSalida);

        } catch (Exception e) {
            System.err.println("\n--- ERROR al ejecutar el proceso ---");
            e.printStackTrace();
        }
    }
}
