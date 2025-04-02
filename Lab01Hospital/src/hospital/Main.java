package hospital;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("------------------------------------");
        System.out.println("  SISTEMA DE CITAS  ");
        System.out.println("------------------------------------");
        System.out.println();

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar un nuevo doctor");
            System.out.println("2. Registrar a un nuevo paciente");
            System.out.println("3. Agendar cita médica");
            System.out.println("4. Ver todas las citas medicas");
            System.out.println("5. Buscar citas medicas por doctor");
            System.out.println("6. Cancelar cita");
            System.out.println("0. Salir ");
            System.out.print("\nSeleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            switch(opcion) {
                case 1:
                    System.out.println("\n[Agregar nuevo doctor]");
                    break;

                case 2:
                    System.out.println("\n[Registrar nuevo paciente]");
                    break;

                case 3:
                    System.out.println("\n[Agendar cita médica]");
                    break;

                case 4:
                    System.out.println("\n[Ver todas las citas]");
                    break;

                case 5:
                    System.out.println("\n[Buscar citas por doctor]");
                    break;

                case 6:
                    System.out.println("\n[Cancelar cita]");
                    break;

                case 0:
                    System.out.println("\nSaliendo....");
                    break;

                default:
                    System.out.println("\n Elegir número entre 0-6");
            }

        } while(opcion != 0);

        scanner.close();
    }
}