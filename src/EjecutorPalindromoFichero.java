import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class EjecutorPalindromoFichero {

    public static void main(String[] args) {
        final String FICHERO_ENTRADA = "cadena_entrada.txt";
        final String FICHERO_ERROR = "error_palindromo.txt";

        // --- 1. Preparar la cadena de prueba en el archivo de entrada ---
        // Prueba el caso "cadena vacía" para forzar un error, o "reconocer" para éxito.
        String cadenaDePrueba = ""; // <-- Intenta probar una cadena vacía para generar error
        // String cadenaDePrueba = "reconocer"; // <-- Intenta probar un palíndromo

        try (FileWriter writer = new FileWriter(FICHERO_ENTRADA)) {
            writer.write(cadenaDePrueba);
            System.out.println("✅ Cadena de prueba guardada en: " + FICHERO_ENTRADA);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero de entrada.");
            return;
        }

        // --- 2. Leer la cadena DESDE el fichero para usarla como argumento ---
        String argumentoAEnviar = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHERO_ENTRADA))) {
            argumentoAEnviar = reader.readLine();
            // Si el archivo estaba vacío, la línea será null, pero nuestro programa acepta String.isEmpty()
            if (argumentoAEnviar == null) argumentoAEnviar = "";
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de entrada.");
            return;
        }

        // --- 3. Configuración de la ejecución y la redirección ---
        String CLASSPATH = ".";

        String[] comando = {
                "java",
                "-cp",
                CLASSPATH,
                "DetectorPalindromo",
                argumentoAEnviar // La cadena leída se pasa como argumento
        };

        try {
            // Crear el ProcessBuilder y configurar la redirección
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Redirección del ERROR ESTÁNDAR (System.err) al fichero
            pb.redirectError(new File(FICHERO_ERROR));

            // La SALIDA ESTÁNDAR (System.out) se hereda (va a la consola)
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            Process proceso = pb.start();
            int codigoSalida = proceso.waitFor();

            // 4. Reporte Final
            System.out.println("\n--- Ejecución del Detector de Palíndromos ---");
            System.out.println("Código de estado devuelto: " + codigoSalida);

            if (codigoSalida != 0) {
                System.out.println("❌ Fallo detectado. Revisa el archivo '" + FICHERO_ERROR + "'.");
            } else {
                System.out.println("✅ Éxito. El resultado está arriba.");
            }

        } catch (Exception e) {
            System.err.println("\n--- ERROR al ejecutar el proceso ---");
            e.printStackTrace();
        }
    }
}