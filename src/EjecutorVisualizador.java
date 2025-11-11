import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class EjecutorVisualizador {

    public static void main(String[] args) {

        // 1. Introducir la cadena por teclado
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la cadena a visualizar 5 veces: ");
        String cadenaUsuario = scanner.nextLine();
        scanner.close();

        // 2. Configuración del comando y el classpath
        // NOTA: Para la ejecución correcta en línea de comandos o IntelliJ,
        // el Classpath (ruta a las clases .class) debe ser el directorio actual (".").
        String CLASSPATH = ".";

        String[] comando = {
                "java",
                "-cp",
                CLASSPATH,
                "VisualizadorCadena",
                cadenaUsuario // Pasa la cadena introducida por el usuario
        };

        Process proceso = null;
        int codigoSalida = -1;

        try {
            // 3. Ejecutar el programa VisualizadorCadena como proceso
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Esto es crucial: une la entrada/salida/error del proceso hijo al proceso padre (terminal actual)
            pb.inheritIO();

            proceso = pb.start();

            // 4. Esperar a que el proceso termine y obtener el código de salida
            codigoSalida = proceso.waitFor();

            // 5. Mostrar el resultado de la ejecución
            System.out.println("\n--- Ejecución Finalizada ---");
            System.out.println("Código de estado devuelto por el proceso externo: " + codigoSalida);

            if (codigoSalida == 0) {
                System.out.println("✅ El programa 'VisualizadorCadena' finalizó con éxito.");
            } else if (codigoSalida == 1) {
                System.out.println("❌ El programa 'VisualizadorCadena' finalizó con error (código 1).");
            } else {
                System.out.println("❓ El programa 'VisualizadorCadena' finalizó con código desconocido.");
            }

        } catch (Exception e) {
            System.err.println("\n--- ERROR al ejecutar el proceso ---");
            System.err.println("Asegúrate de que 'VisualizadorCadena.class' está compilado y accesible.");
            e.printStackTrace();
        }
    }
}
