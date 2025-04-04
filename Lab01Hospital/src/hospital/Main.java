package hospital;

import hospital.Util.Validaciones;
import hospital.model.Cita;
import hospital.model.Doctor;
import hospital.model.Paciente;
import hospital.service.SistemaCitas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Doctor> listaDoctores = new ArrayList<>(List.of());
        ArrayList<String> listaEspecialidades = new ArrayList<>(List.of());
        ArrayList<Paciente> listaPacientes = new ArrayList<>(List.of());
        ArrayList<Cita> listaCitas = new ArrayList<>(List.of());


        int opcion;

        do {
            SistemaCitas.MostrarSaludo();

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    SistemaCitas.AgregarDoctor(listaDoctores,scanner,listaEspecialidades);
                    break;

                case 2:
                    SistemaCitas.AgregarPaciente(listaPacientes,scanner);

                    break;

                case 3:

                    SistemaCitas.AgendarCita(listaPacientes, listaDoctores,listaEspecialidades ,listaCitas, scanner);

                    break;

                case 4:

                    SistemaCitas.VerCitas(listaCitas,scanner);

                    break;

                case 5:
                    SistemaCitas.MostrarCitasDoctor(listaDoctores,scanner);
                    break;

                case 6:
                    SistemaCitas.CancelarCita(listaCitas,scanner);
                    break;

                case 7:
                    SistemaCitas.MostrarDoctores(listaDoctores);
                    break;
                case 8:
                    SistemaCitas.MostrarPacientes(listaPacientes);
                    break;
                case 9:
                    SistemaCitas.PacienteNoLlego(listaCitas,scanner);

                    break;
                case 10:
                    SistemaCitas.MundoSalvaVidas();
                    break;

                case 0:
                    System.out.println("\nSaliendo....");
                    break;

                default:
                    System.out.println("\n Elegir número entre 0-8");


            }

        } while(opcion != 0);

        scanner.close();
    }
}