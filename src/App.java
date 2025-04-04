import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        /*
         * El ejercicio nos pide que mostremos una representacion visual de los asientos del teatro, para ello lo voy a abordar con una matriz.
         * La voy a colocar por fuera para que al momento de hacer el loop del menu no se pierdan los datos de los asientos comprados.
        */
        char[][] asientos = {
        {'*', '*', '*'},
        {'*', '*', '*'},
        {'*', '*', '*'} 
        };

        /*
         * Vamos a crear una variable global para poder manejar el menu y que se repita hasta que el usuario quiera salir como pide el enunciado.
        */
        int opcion_usuario;

        do {
            System.out.println("\n--- TEATRO MORO ---");
            System.out.println("Bienvenido al menu del teatro!");
            /*
             * Como nos pedian hacer un menu pero con el ciclo for se me ocurrio colocar las opciones en un array, de forma que si queremos agregar algo como
             * "Ver Asientos" o "Ver obras" solo tenemos que colocarlo en el array y se va a mostrar por medio del for.
            */
            String[] opciones = {"Comprar entrada", "Salir"};
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ". " + opciones[i]);
            }
        
            // Vamos a pedir la opcion del usuario
            System.out.print("Seleccione una opción: ");
            opcion_usuario = scanner.nextInt();
        
            // Para evaluar la opcion voy a usar un switch, ya que es mas entendible que un IF por cada opcion.
            switch (opcion_usuario) {
                case 1:
                    comprarEntradas(asientos, scanner);
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    presionarEnterParaContinuar(scanner);
                    break;
            }
        
        } while (opcion_usuario != 2);
    }

    public static void comprarEntradas(char[][] asientos, Scanner scanner) {
        //Ahora vamos a mostrar los asientos disponibles y que el suario eliga su opcion.
        System.out.println("Estos son los asientos disponibles:");
        for (int i = 0; i < asientos.length; i++) {
            System.out.print("Fila " + (i + 1) + ": ");
            for (int j = 0; j < asientos[i].length; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println();
        }

        //Ahora le solicitamos su eleccion y dentro de un bucle para repetirla sin encesidad de volver al menu principal
        int fila = -1;
        int asiento = -1;
        
        // Bucle para validar fila
        while (true) {
            System.out.print("Ingrese la fila (1-3): ");
            fila = scanner.nextInt() - 1;
        
            if (fila >= 0 && fila < asientos.length) {
                break; // valor correcto, salimos del bucle
            } else {
                System.out.println("Fila no válida. Intente nuevamente.");
                presionarEnterParaContinuar(scanner);
            }
        }
        
        // Bucle para validar asiento
        while (true) {
            System.out.print("Ingrese el número del asiento (1-3): ");
            asiento = scanner.nextInt() - 1;
        
            if (asiento >= 0 && asiento < asientos[fila].length) {
                break; // valor correcto, salimos del bucle
            } else {
                System.out.println("Asiento no válido. Intente nuevamente.");
                presionarEnterParaContinuar(scanner);
            }
        }
        
        //Ahora vamos a modificarlo visualmente para que al volver a entrar se vea ocupado.
        if (asientos[fila][asiento] == '*') {
            asientos[fila][asiento] = 'X';
            System.out.println("Asiento reservado con éxito.");
            System.out.println("Seras redirigido al menu principal.");
            presionarEnterParaContinuar(scanner);
        } else {
            System.out.println("Ese asiento ya está ocupado.");
            presionarEnterParaContinuar(scanner);
        }

    }

    //Quise implementar este metodo para hacer mas comoda la interfaz de la terminal para que los mensajes se alcancen a leer de manera mas comoda.
    //Lo que hace es esperar a que el usuario presione enter para continuar, de esta manera no se pierde la vista de lo que se estaba haciendo.
    public static void presionarEnterParaContinuar(Scanner scanner) {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine(); // limpiar buffer
        scanner.nextLine();
    }
}
