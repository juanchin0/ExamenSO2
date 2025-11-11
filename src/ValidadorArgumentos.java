public class ValidadorArgumentos {

    public static void main(String[] args) {

        // Regla 1: Si el número de argumentos es < 1, debe devolver 1.
        if (args.length < 1) {
            System.err.println("Error: No se proporcionó ningún argumento.");
            System.exit(1);
        }

        // El ejercicio solo pide evaluar el primer argumento si existe.
        String primerArgumento = args[0];

        try {
            // Intenta convertir el primer argumento a un entero.
            int numero = Integer.parseInt(primerArgumento);

            // Si la conversión es exitosa, es un número entero.

            // Regla 3: Si el argumento es un número entero menor que 0, debe devolver 3.
            if (numero < 0) {
                System.out.println("Argumento detectado: Número entero negativo.");
                System.exit(3);
            }

            // Si es un número entero >= 0, cae en la Regla 4: En cualquier otra situación debe devolver 0.
            System.out.println("Argumento detectado: Número entero no negativo.");
            System.exit(0);

        } catch (NumberFormatException e) {
            // Si ocurre NumberFormatException, el argumento NO es un entero válido.

            // Regla 2: Si el argumento es una cadena, debe devolver 2.
            // Nota: Cualquier argumento en args[] es técnicamente una String,
            // por lo que interpretamos "es una cadena" como "no es un número entero válido".
            System.out.println("Argumento detectado: Cadena de texto (no es un entero).");
            System.exit(2);
        }

        // Regla 4: En cualquier otra situación debe devolver 0.
        // (Este punto solo se alcanzaría si hubiese argumentos y no se cumplieran las reglas anteriores,
        // pero las reglas ya cubren todos los casos de argumentos >= 1).
        System.out.println("Argumento detectado: Caso por defecto.");
        System.exit(0);
    }
}
