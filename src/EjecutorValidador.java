import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EjecutorValidador {

    // NOTA: Para que este programa funcione correctamente,
    // ValidadorArgumentos.class (el archivo compilado) debe estar
    // accesible en el directorio de trabajo actual o en el classpath.
    public static void main(String[] args) {
        // --- DEFINICIÓN DEL CLASSPATH ---
        // Si ejecutas desde la terminal en C:\Java\ExamenSO2\src:
        String CLASSPATH = ".";

        // Si la solución anterior no funciona en IntelliJ o terminal, usa la ruta completa:
        // String CLASSPATH = "C:\\Java\\ExamenSO2\\out\\production\\ExamenSO2";

        String ARGUMENTO_PRUEBA = "holaMundo"; // Define el argumento de prueba aquí

        // --- CONSTRUCCIÓN DEL COMANDO (MODIFICADO) ---
        String[] comando = {
                "java",
                "-cp",
                CLASSPATH, // Especifica el directorio donde está ValidadorArgumentos.class
                "ValidadorArgumentos",
                ARGUMENTO_PRUEBA // Pasa el argumento
        };

        Process proceso = null;
        int codigoSalida = -1;

        try {
            // 1. Ejecutar el programa ValidadorArgumentos
            ProcessBuilder pb = new ProcessBuilder(comando);
            proceso = pb.start();

            // 2. Esperar a que el proceso termine y obtener el código de salida
            codigoSalida = proceso.waitFor();

            // Opcional: Mostrar la salida estándar del programa ejecutado
            // MostrarSalida(proceso);

            // 3. Mostrar en pantalla lo que pasa dependiendo del valor devuelto
            System.out.println("\n--- Resultado de la Ejecución ---");
            System.out.println("Comando ejecutado: " + String.join(" ", comando));
            System.out.println("Código de estado devuelto: " + codigoSalida);

            String mensajeResultado = "";
            switch (codigoSalida) {
                case 0:
                    mensajeResultado = "✅ Éxito: El argumento es un **número entero no negativo** (o caso por defecto).";
                    break;
                case 1:
                    mensajeResultado = "❌ Fallo (Código 1): **No se proporcionó ningún argumento**.";
                    break;
                case 2:
                    mensajeResultado = "⚠️ Fallo (Código 2): El argumento es una **cadena de texto** (no un entero).";
                    break;
                case 3:
                    mensajeResultado = "⚠️ Fallo (Código 3): El argumento es un **número entero negativo**.";
                    break;
                default:
                    mensajeResultado = "❓ Código Desconocido: Se recibió un código de salida inesperado: " + codigoSalida;
                    break;
            }
            System.out.println("Interpretación: " + mensajeResultado);

        } catch (Exception e) {
            System.err.println("\n--- ERROR al ejecutar el proceso ---");
            System.err.println("Asegúrate de que 'ValidadorArgumentos.class' esté en el mismo directorio.");
            System.err.println("Detalles del error: " + e.getMessage());
        }
    }

    // Método auxiliar para imprimir la salida estándar del proceso ejecutado (Opcional)
    private static void MostrarSalida(Process p) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        System.out.println("\n--- Salida del programa ValidadorArgumentos ---");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
