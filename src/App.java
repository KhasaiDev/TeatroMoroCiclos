import java.util.Scanner;

public class App {

    /*
     * Hola profe Jorge! Esta sumativa es como una recopilacion de los trabajos anteriores, por lo que hay mucho codigo reciclado.
     * Segui la rubrica que nos dio en los anuncios y trate de cumplir con todos los requisitos.
     * El programa simula una sola instancia de venta, es decir, solo para un cliente a la vez.
     * NO puede elegir 2 reservas y solo cancelar una, va a cancelar ambas.
     */

    static final int FILAS = 3;
    static final int COLUMNAS = 3;
    static char[][] asientos = new char[FILAS][COLUMNAS];

    // Variables de instancia (mínimo 4)
    static int[] numerosEntrada = new int[10];
    static String[] tiposEntrada = new String[10];
    static String[] tiposCliente = new String[10];
    static float[] preciosFinales = new float[10];
    static int totalEntradas = 0;

    // Variables estáticas (mínimo 3)
    static int totalVendidas = 0;
    static float ingresosTotales = 0f;
    static int totalReservadas = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarAsientos();

        int opcion;
        do {
            System.out.println("\n--- TEATRO MORO ---");
            System.out.println("Gracias por usar el sistema del Teatro Moro, seleccione una opcion:");
            System.out.println("1. Ver asientos");
            System.out.println("2. Reservar asiento");
            System.out.println("3. Confirmar reservas");
            System.out.println("4. Imprimir boletas");
            System.out.println("5. Modificar reserva");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> mostrarAsientos();
                case 2 -> reservarAsiento(scanner);
                case 3 -> confirmarReservas(scanner);
                case 4 -> imprimirBoletas();
                case 5 -> modificarReserva(scanner);
                case 6 -> System.out.println("Gracias por usar el sistema del Teatro Moro.");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }


    // Metodos 
    public static void inicializarAsientos() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                asientos[i][j] = '*';
            }
        }
    }

    public static void mostrarAsientos() {
        System.out.println("\nEstado de asientos:");
        System.out.println("* = Disponible, R = Reservado, x = Vendido");
        for (int i = 0; i < FILAS; i++) {
            System.out.print("Fila " + (i + 1) + ": ");
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void reservarAsiento(Scanner scanner) {
        mostrarAsientos();
        System.out.print("Ingrese fila (1-3): ");
        int fila = scanner.nextInt() - 1;
        System.out.print("Ingrese número de asiento (1-3): ");
        int columna = scanner.nextInt() - 1;

        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            if (asientos[fila][columna] == '*') {
                asientos[fila][columna] = 'R';
                totalReservadas++;
                System.out.println("Asiento reservado correctamente.");
            } else {
                System.out.println("Asiento no disponible.");
            }
        } else {
            System.out.println("Posición inválida.");
        }
    }

    public static void confirmarReservas(Scanner scanner) {
        boolean hayReservas = false;
    
        // Verificamos si hay al menos una reserva
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (asientos[i][j] == 'R') {
                    hayReservas = true;
                    break;
                }
            }
            if (hayReservas) break;
        }
    
        if (!hayReservas) {
            System.out.println("\nNo hay reservas pendientes por confirmar.");
            return;
        }
    
        // Confirmamos cada reserva existente
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (asientos[i][j] == 'R') {
                    System.out.println("\nConfirmando reserva para asiento en Fila " + (i + 1) + ", Asiento " + (j + 1));
    
                    System.out.println("Seleccione tipo de entrada:");
                    System.out.println("1. VIP ($15000), 2. Platea ($10000), 3. General ($7000)");
                    int tipo = scanner.nextInt();
    
                    System.out.println("Tipo de cliente:");
                    System.out.println("1. Estudiante (-10%), 2. Tercera Edad (-15%), 3. Normal");
                    int cliente = scanner.nextInt();
    
                    String tipoEntrada = switch (tipo) {
                        case 1 -> "VIP";
                        case 2 -> "Platea";
                        case 3 -> "General";
                        default -> "General";
                    };
    
                    float precioBase = switch (tipo) {
                        case 1 -> 15000f;
                        case 2 -> 10000f;
                        case 3 -> 7000f;
                        default -> 7000f;
                    };
    
                    String tipoCliente = switch (cliente) {
                        case 1 -> "Estudiante";
                        case 2 -> "Tercera Edad";
                        case 3 -> "Normal";
                        default -> "Normal";
                    };
    
                    float descuento = switch (cliente) {
                        case 1 -> 0.10f;
                        case 2 -> 0.15f;
                        default -> 0f;
                    };
    
                    float precioFinal = precioBase * (1 - descuento);
    
                    // Guardamos datos
                    numerosEntrada[totalEntradas] = totalEntradas + 1;
                    tiposEntrada[totalEntradas] = tipoEntrada;
                    tiposCliente[totalEntradas] = tipoCliente;
                    preciosFinales[totalEntradas] = precioFinal;
    
                    totalEntradas++;
                    totalVendidas++;
                    ingresosTotales += precioFinal;
    
                    // Cambiamos asiento a comprado
                    asientos[i][j] = 'X';
                }
            }
        }
    
        System.out.println("Reservas confirmadas con éxito.");
    }
    
    public static void imprimirBoletas() {
        System.out.println("\n--- Boletas Generadas ---");
        for (int i = 0; i < totalEntradas; i++) {
            System.out.println("Entrada #" + numerosEntrada[i]);
            System.out.println("Tipo: " + tiposEntrada[i]);
            System.out.println("Cliente: " + tiposCliente[i]);
            System.out.println("Precio: $" + preciosFinales[i]);
            System.out.println("------------------------");
        }
    }

    public static void modificarReserva(Scanner scanner) {
        mostrarAsientos();
        System.out.println("Modificar reserva:");
        System.out.print("Ingrese fila de la reserva a modificar (1-3): ");
        int fila = scanner.nextInt() - 1;
        System.out.print("Ingrese asiento de la reserva a modificar (1-3): ");
        int columna = scanner.nextInt() - 1;
    
        if (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            if (asientos[fila][columna] == 'R') {
                System.out.println("¿Qué desea hacer?");
                System.out.println("1. Cancelar reserva");
                System.out.println("2. Cambiar asiento");
                int opcion = scanner.nextInt();
    
                switch (opcion) {
                    case 1 -> {
                        asientos[fila][columna] = '*';
                        totalReservadas--;
                        System.out.println("Reserva cancelada exitosamente.");
                    }
                    case 2 -> {
                        asientos[fila][columna] = '*'; // Liberamos el asiento original
                        totalReservadas--;
                        mostrarAsientos();
                        System.out.print("Ingrese nueva fila (1-3): ");
                        int nuevaFila = scanner.nextInt() - 1;
                        System.out.print("Ingrese nuevo asiento (1-3): ");
                        int nuevaColumna = scanner.nextInt() - 1;
    
                        if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLUMNAS) {
                            if (asientos[nuevaFila][nuevaColumna] == '*') {
                                asientos[nuevaFila][nuevaColumna] = 'R';
                                totalReservadas++;
                                System.out.println("Reserva cambiada exitosamente.");
                            } else {
                                System.out.println("El nuevo asiento no está disponible.");
                                // Revertimos el cambio si falló
                                asientos[fila][columna] = 'R';
                                totalReservadas++;
                            }
                        } else {
                            System.out.println("Nueva posición inválida.");
                            asientos[fila][columna] = 'R'; // Revertimos si hay error
                            totalReservadas++;
                        }
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("No hay reserva en ese asiento.");
            }
        } else {
            System.out.println("Posición inválida.");
        }
    
        scanner.nextLine(); // Limpia el buffer
    }
}
