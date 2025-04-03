package hospital;

import hospital.Util.Validaciones;
import hospital.model.Cita;
import hospital.model.Doctor;
import hospital.model.Paciente;

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
        ArrayList<String> listaEspecialidades = new ArrayList<>(List.of("Neurologo"));
        ArrayList<Paciente> listaPacientes = new ArrayList<>(List.of());
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
                    System.out.println("\n[Agregando nuevo doctor...]\n");
                    System.out.println("\nDigite el nombre del doctor: ");
                    String nombre = scanner.nextLine();
                    System.out.println("\nDigite el apellido del doctor: ");
                    String apellido = scanner.nextLine();
                    String dui;
                    do {
                        System.out.println("\nDigite el DUI del doctor: ");
                         dui = scanner.nextLine();
                    }while(!Validaciones.validarDui(dui));


                    System.out.println("\nDigite el dia de nacimiento del doctor: ");
                    int dia = scanner.nextInt();
                    System.out.println("\nDigite el mes de nacimiento del doctor: ");
                    int mes = scanner.nextInt();
                    System.out.println("\nDigite el año de nacimiento del doctor: ");
                    int anio = scanner.nextInt();

                    LocalDate fechaNacimiento = LocalDate.of(anio,mes,dia);

                    System.out.println("\nDigite el dia de reclutamiento del doctor: ");
                    dia = scanner.nextInt();
                    System.out.println("\nDigite el mes de reclutamiento del doctor: ");
                    mes = scanner.nextInt();
                    System.out.println("\nDigite el año de reclutamiento del doctor: ");
                    anio = scanner.nextInt();

                    scanner.nextLine();
                    LocalDate fechaReclutamiento  = LocalDate.of(anio,mes,dia);

                    System.out.println("\nDigite la especialidad del doctor: ");
                    String especialidad = scanner.nextLine();

                    if (!listaEspecialidades.contains(especialidad)){
                        listaEspecialidades.add(especialidad);
                    }

                    Doctor doctor = new Doctor(nombre,apellido,dui,fechaNacimiento,fechaReclutamiento,especialidad);
                    doctor.generarCodigo();
                    listaDoctores.add(doctor);
                    System.out.println("\nAgregando...");
                    System.out.println("\nDoctor agregado correctamente");
                    break;

                case 2:
                    System.out.println("\n[Registrando nuevo paciente...]\n");
                    System.out.println("\nDigite el nombre del paciente: ");
                    nombre = scanner.nextLine();
                    System.out.println("\nDigite el apellido del paciente: ");
                    apellido = scanner.nextLine();
                    do {
                        System.out.println("\nDigite el DUI del paciente (Si es un menor solo digite 0): ");
                        dui = scanner.nextLine();
                    }while( !dui.equals("0") && !Validaciones.validarDui(dui)  );

                    System.out.println("\nDigite el dia de nacimiento del paciente: ");
                    dia = scanner.nextInt();
                    System.out.println("\nDigite el mes de nacimiento del paciente: ");
                    mes = scanner.nextInt();
                    System.out.println("\nDigite el año de nacimiento del paciente: ");
                    anio = scanner.nextInt();

                    fechaNacimiento = LocalDate.of(anio,mes,dia);

                    Paciente paciente = new Paciente(nombre,apellido,dui,fechaNacimiento);
                    listaPacientes.add(paciente);

                    System.out.println("\nAgregando...");
                    System.out.println("\nPaciente agregado correctamente");

                    break;

                case 3:
                    System.out.println("\n[Agendar cita médica]");
                    System.out.println("\nRecuerde registrar al doctor y paciente antes de llenar la cita");

                    if (listaPacientes.isEmpty() || listaDoctores.isEmpty()){
                        System.out.println("No hay doctores o pacientes registrados");
                        break;
                    }

                    System.out.println("\nSeleccione el doctor: ");
                    for (int i = 0; i < listaDoctores.size(); i++) {
                        System.out.println((i+1) + ". " + listaDoctores.get(i).getNombreCompleto());
                    }

                    int doctorSeleccionado = scanner.nextInt();
                    scanner.nextLine();
                    if (doctorSeleccionado < 1 || doctorSeleccionado > listaDoctores.size()) {
                        System.out.println("Opción inválida. Seleccione un doctor válido.");
                        break;
                    }
                    Doctor doctorCita = listaDoctores.get(doctorSeleccionado - 1);
                    System.out.println("\nSeleccione la fecha: ");
                    System.out.println("\n1-Hoy   2-Dia futuro ");
                    int fechaSeleccionada = scanner.nextInt();
                    scanner.nextLine();
                    LocalDateTime fechaCita = LocalDateTime.now();
                    LocalDateTime fechaDisponible;
                    if(fechaSeleccionada==1){
                        fechaDisponible = doctorCita.validarDisponibilidadHoy(fechaCita);
                        if (fechaDisponible == null) {
                            System.out.println("No hay horas disponibles para hoy.");
                            break;
                        }
                        System.out.println("La cita se agendara para: " + fechaDisponible);

                    }else{
                        System.out.println("\nDigite el dia de la cita: ");
                        dia = scanner.nextInt();
                        System.out.println("\nDigite el mes de la cita: ");
                        mes = scanner.nextInt();
                        fechaDisponible = doctorCita.validarDisponibilidadFutura(dia,mes);
                        if (fechaDisponible == null) {
                            System.out.println("No hay horas disponibles para esa fecha.");
                            break;
                        }else{
                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
                            String fechaCitaFormateada = fechaDisponible.format(formato);

                            System.out.println("La cita se agendara para: " + fechaCitaFormateada);
                        }
                    }
                    System.out.println("\nSeleccione el paciente: ");
                    for (int i = 0; i < listaPacientes.size(); i++) {
                        System.out.println((i+1) + ". " + listaPacientes.get(i).getNombreCompleto());
                    }
                    int pacienteSeleccionado = scanner.nextInt();
                    scanner.nextLine();
                    if (pacienteSeleccionado < 1 || pacienteSeleccionado > listaPacientes.size()) {
                        System.out.println("Opción inválida. Seleccione un paciente válido.");
                        break;
                    }
                    Paciente pacienteCita = listaPacientes.get(pacienteSeleccionado - 1);
                    if(pacienteCita.validarDisponibilidad(fechaDisponible)){
                        System.out.println("El paciente ya tiene una cita a esa hora");
                        System.out.println("Vuelva a intentarlo");
                        break;
                    }
                    System.out.println("\nSeleccione la especialidad: ");
                    System.out.println("\nSi la especialidad no se encuentra en la lista digite -1 para agregar una nueva");

                    for (int i = 0; i < listaEspecialidades.size(); i++) {
                        System.out.println((i+1) + ". " + listaEspecialidades.get(i));
                    }
                    int especialidadInt = scanner.nextInt();
                    String especialidadCita;
                    scanner.nextLine();

                    if (especialidadInt > listaEspecialidades.size()) {
                        System.out.println("Opción inválida. Seleccione una especialidad válida.");
                        break;
                    }

                    if(especialidadInt==-1){
                        System.out.println("\nDigite la nueva especialidad: ");
                        especialidadCita = scanner.nextLine();
                        listaEspecialidades.add(especialidadCita);

                    }else{
                        especialidadCita = listaEspecialidades.get(especialidadInt - 1);
                    }

                    Cita cita = new Cita(doctorCita,pacienteCita,especialidadCita,fechaDisponible);
                    doctorCita.agregarCita(cita);
                    pacienteCita.agregarCita(cita);

                    System.out.println("\nAgregando...");
                    System.out.println("\nCita agregada correctamente");


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

                case 10:
                    System.out.println("\nImprimiendo doctores");
                    listaDoctores.forEach(doc -> {
                        System.out.println(doc.toString());
                    });
                    break;
                case 11:
                    System.out.println("\nImprimiendo pacientes");
                    listaPacientes.forEach(paci->{
                        System.out.println(paci.getNombreCompleto() +" " +paci.getEdad()+ " "+paci.getDui());
                    });
                    break;

                default:
                    System.out.println("\n Elegir número entre 0-6");
            }

        } while(opcion != 0);

        scanner.close();
    }
}