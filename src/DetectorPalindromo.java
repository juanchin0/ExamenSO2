import java.util.Scanner;

public class DetectorPalindromo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Detector de Palíndromos ---");
        System.out.print("Introduce una cadena de texto: ");

        // Leer la cadena desde la entrada estándar (teclado)
        String cadenaOriginal = scanner.nextLine();
        scanner.close();

        // 1. Verificar si la cadena está vacía
        if (cadenaOriginal.isEmpty()) {
            System.out.println("\nResultado:");
            System.out.println("⚠️ La cadena está vacía (longitud 0).");
            return; // Termina el programa
        }

        // 2. Preparar la cadena para la verificación (opcional: limpiar espacios/mayúsculas)
        // Eliminamos espacios y convertimos a minúsculas para una verificación estricta de palíndromos
        String cadenaLimpia = cadenaOriginal.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // 3. Crear la cadena invertida
        String cadenaInvertida = new StringBuilder(cadenaLimpia).reverse().toString();

        // 4. Comparar
        System.out.println("\nResultado:");
        if (cadenaLimpia.equals(cadenaInvertida)) {
            System.out.println("✅ La cadena es un **PALÍNDROMO**.");
            System.out.println("   Original: \"" + cadenaOriginal + "\"");
        } else {
            System.out.println("❌ La cadena **NO** es un palíndromo.");
            System.out.println("   Original: \"" + cadenaOriginal + "\"");
            System.out.println("   Invertida: \"" + cadenaInvertida + "\"");
        }
    }
}
