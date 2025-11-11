public class VisualizadorCadena {

    public static void main(String[] args) {

        // Regla: Si no se le envía ninguna cadena, mostrar mensaje y System.exit(1).
        if (args.length == 0) {
            System.err.println("Error: No se ha proporcionado ninguna cadena como argumento.");
            System.exit(1);
        }

        // Si hay argumentos, toma el primero.
        String cadena = args[0];

        // Visualiza la cadena 5 veces.
        System.out.println("--- Visualizando 5 veces la cadena: \"" + cadena + "\" ---");
        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ": " + cadena);
        }

        // El programa finaliza con éxito (código 0) por defecto.
    }
}
