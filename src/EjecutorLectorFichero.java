import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EjecutorLectorFichero {

    public static void main(String[] args) {
        final String FICHERO_ENTRADA = "cadenas_de_entrada.txt";

        // --- 1. Preparar el fichero de entrada con los datos ---
        // Las líneas de datos terminan con el caracter de parada '*'.
        String contenidoFichero =
                "Esta es la primera linea\n" +
                        "La segunda línea tiene datos\n" +
                        "*\n"; // <-- Este asterisco debe estar en una línea separada para la parada.

        try (FileWriter writer = new FileWriter(FICHERO_ENTRADA)) {
            writer.write(contenidoFichero);
            System.out.println("✅ Fichero de entrada creado en: " + FICHERO_ENTRADA);
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero de entrada.");
            e.printStackTrace();
            return;
        }

        // --- 2. Configuración de la ejecución y la redirección ---
        String CLASSPATH = ".";

        String[] comando = {
                "java",
                "-cp",
                CLASSPATH,
                "LectorCadenas"
        };

        Process proceso = null;
        int codigoSalida = -1;

        try {
            System.out.println("\n--- Ejecutor: Iniciando LectorCadenas desde fichero ---");

            ProcessBuilder pb = new ProcessBuilder(comando);

            // La instrucción CLAVE: Redirige la entrada estándar (System.in)
            // del proceso hijo (LectorCadenas) para que lea del archivo.
            pb.redirectInput(new File(FICHERO_ENTRADA));

            // La salida estándar (System.out) y error se imprimen en la consola
            pb.inheritIO();

            proceso = pb.start();

            // Esperar a que el proceso hijo termine
            codigoSalida = proceso.waitFor();

            System.out.println("\n--- Ejecutor: Finalizado ---");
            System.out.println("Proceso externo terminado con código: " + codigoSalida);

        } catch (IOException e) {
            System.err.println("Error de I/O al iniciar el proceso.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido.");
            e.printStackTrace();
        }
    }
}