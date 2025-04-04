package hospital.service;

import hospital.Util.Validaciones;
import hospital.model.Cita;
import hospital.model.Doctor;
import hospital.model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCitas {

    public static void MostrarSaludo(){
        System.out.println("------------------------------------");
        System.out.println("  SISTEMA DE CITAS  ");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Agregar un nuevo doctor");
        System.out.println("2. Registrar a un nuevo paciente");
        System.out.println("3. Agendar cita médica");
        System.out.println("4. Ver todas las citas medicas");
        System.out.println("5. Buscar citas medicas por doctor");
        System.out.println("6. Cancelar cita");
        System.out.println("7. Mostrar todos los doctores");
        System.out.println("8. Mostrar todos los paciente");
        System.out.println("9. Paciente no llegó a la cita");
        System.out.println("10. Mundo salva vidas");
        System.out.println("0. Salir ");
        System.out.print("\nSeleccione una opción: ");
    }

    public static void AgregarDoctor(ArrayList<Doctor> listaDoctores, Scanner scanner,ArrayList<String> listaEspecialidades){
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

    }

    public static void AgregarPaciente(ArrayList<Paciente> listaPacientes, Scanner scanner){
        System.out.println("\n[Registrando nuevo paciente...]\n");
        System.out.println("\nDigite el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.println("\nDigite el apellido del paciente: ");
        String apellido = scanner.nextLine();
        String dui;
        do {
            System.out.println("\nDigite el DUI del paciente (Si es un menor solo digite 0): ");
            dui = scanner.nextLine();
        }while( !dui.equals("0") && !Validaciones.validarDui(dui)  );

        System.out.println("\nDigite el dia de nacimiento del paciente: ");
        int dia = scanner.nextInt();
        System.out.println("\nDigite el mes de nacimiento del paciente: ");
        int mes = scanner.nextInt();
        System.out.println("\nDigite el año de nacimiento del paciente: ");
        int anio = scanner.nextInt();

        LocalDate fechaNacimiento = LocalDate.of(anio,mes,dia);

        Paciente paciente = new Paciente(nombre,apellido,dui,fechaNacimiento);
        listaPacientes.add(paciente);

        System.out.println("\nAgregando...");
        System.out.println("\nPaciente agregado correctamente");
    }

    public static void AgendarCita(ArrayList<Paciente> listaPacientes, ArrayList<Doctor>  listaDoctores,ArrayList<String> listaEspecialidades,ArrayList<Cita> listaCitas, Scanner scanner){
        System.out.println("\n[Agendar cita médica]");
        System.out.println("\nRecuerde registrar al doctor y paciente antes de llenar la cita");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");

        if (listaPacientes.isEmpty() || listaDoctores.isEmpty()){
            System.out.println("No hay doctores o pacientes registrados");
            return;
        }
        System.out.println("\nSeleccione el doctor: ");
        for (int i = 0; i < listaDoctores.size(); i++) {
            System.out.println((i+1) + ". " + listaDoctores.get(i).getNombreCompleto());
        }

        int doctorSeleccionado = scanner.nextInt();
        scanner.nextLine();
        if (doctorSeleccionado < 1 || doctorSeleccionado > listaDoctores.size()) {
            System.out.println("Opción inválida. Seleccione un doctor válido.");
            return;
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
                return;
            }
            String fechaCitaFormateada = fechaDisponible.format(formato);
            System.out.println("La cita se agendara para: " + fechaCitaFormateada);

        }else{
            System.out.println("\nDigite el dia de la cita: ");
            int dia = scanner.nextInt();
            System.out.println("\nDigite el mes de la cita: ");
            int mes = scanner.nextInt();
            fechaDisponible = doctorCita.validarDisponibilidadFutura(dia,mes);

            if (fechaDisponible ==null) {
                System.out.println("No hay horas disponibles para esa fecha.");
                return;
            }else{

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
                return;
            }
            Paciente pacienteCita = listaPacientes.get(pacienteSeleccionado - 1);
            if(pacienteCita.validarDisponibilidad(fechaDisponible)){
                System.out.println("El paciente ya tiene una cita a esa hora");
                System.out.println("Vuelva a intentarlo");
                doctorCita.getFechasCitasFuturas().remove(fechaDisponible);
                doctorCita.getFechasCitasHoy().remove(fechaDisponible);
                return;
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
                return;
            }

            if(especialidadInt==-1){
                System.out.println("\nDigite la nueva especialidad: ");
                especialidadCita = scanner.nextLine();
                listaEspecialidades.add(especialidadCita);

            }else{
                especialidadCita = listaEspecialidades.get(especialidadInt - 1);
            }

            System.out.println("\nEl paciente trajo galletas? 1-true 2-false");
            boolean trajoGalletas = false;
            int galletas = scanner.nextInt();
            scanner.nextLine();
            if (galletas == 1) {
                trajoGalletas = true;
            } else if (galletas == 2) {
                trajoGalletas = false;
            } else {
                System.out.println("Opción inválida. Seleccione una opción válida.");
                return;
            }


            Cita cita = new Cita(doctorCita,pacienteCita,especialidadCita,fechaDisponible);
            cita.setTrajoGalletas(trajoGalletas);
            doctorCita.agregarCita(cita);
            pacienteCita.agregarCita(cita);
            listaCitas.add(cita);

            System.out.println("\nAgregando...");
            System.out.println("\nCita agregada correctamente");



    }

    public static void VerCitas(ArrayList<Cita> listaCitas, Scanner scanner){
        System.out.println("\n[Ver todas las citas médicas]");
        if (listaCitas.isEmpty()) {
            System.out.println("No hay citas médicas registradas.");
            return;
        }
        System.out.println("\nCitas médicas: 1-Hoy 2-Dia futuro");
        int fechaSeleccionada = 0;
        while (fechaSeleccionada != 1 && fechaSeleccionada != 2) {
            System.out.println("\nSeleccione la fecha: ");
            System.out.println("\n1-Hoy   2-Dia futuro ");
            fechaSeleccionada = scanner.nextInt();
            scanner.nextLine();
        }

        if (fechaSeleccionada==1){
            System.out.println("\nCitas médicas para hoy:");
            for (Cita citaActual : listaCitas) {
                if (citaActual.getFecha().toLocalDate().isEqual(LocalDate.now())) {
                    System.out.println(citaActual + "\n");
                }
            }
        }else{
            int dia, mes;
            System.out.println("\nDigite el dia de la cita: ");
            dia = scanner.nextInt();
            System.out.println("\nDigite el mes de la cita: ");
            mes = scanner.nextInt();
            System.out.println("\nCitas médicas para el " + dia + "/" + mes + ":");
            for (Cita citaActual : listaCitas) {
                if (citaActual.getFecha().getDayOfMonth() == dia && citaActual.getFecha().getMonthValue() == mes) {
                    System.out.println(citaActual + "\n");
                }
            }

        }


    }

    public static void CancelarCita(ArrayList<Cita> listaCitas, Scanner scanner){
        System.out.println("\n[Cancelar cita médica]");
        if (listaCitas.isEmpty()) {
            System.out.println("No hay citas médicas registradas.");
            return;
        }
        System.out.println("\nSeleccione la cita a cancelar:");
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println((i+1) + ". " + listaCitas.get(i).toString());
        }
        int citaSeleccionada = scanner.nextInt();
        scanner.nextLine();
        if (citaSeleccionada < 1 || citaSeleccionada > listaCitas.size()) {
            System.out.println("Opción inválida. Seleccione una cita válida.");
            return;
        }
        Cita citaACancelar = listaCitas.get(citaSeleccionada - 1);
        citaACancelar.getDoctor().getCitas().remove(citaACancelar);
        citaACancelar.getPaciente().getCitas().remove(citaACancelar);
        listaCitas.remove(citaACancelar);

        System.out.println("Cita cancelada correctamente.");
    }

    public static void MostrarDoctores(ArrayList<Doctor> listaDoctores){
        System.out.println("\n[Mostrar todos los doctores]");
        if (listaDoctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        System.out.println("\nLista de doctores:");
        for (Doctor doctor : listaDoctores) {
            System.out.println(doctor.toString());
        }
    }

    public static void MostrarPacientes(ArrayList<Paciente> listaPacientes){
        System.out.println("\n[Mostrar todos los pacientes]");
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("\nLista de pacientes:");
        for (Paciente paciente : listaPacientes) {
            System.out.println(paciente.toString());
        }
    }

    public static void MostrarCitasDoctor(ArrayList<Doctor> listaDoctores,Scanner scanenr){
        System.out.println("\n[Mostrar citas por doctor]");
        if (listaDoctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        int opcion;
        for(int i=0;i<listaDoctores.size();i++){
            System.out.println((i+1)+". "+listaDoctores.get(i).getNombreCompleto());
        }
        System.out.println("\nSeleccione el doctor para ver sus citas: ");
        opcion = scanenr.nextInt();
        if(opcion<1 || opcion>listaDoctores.size()){
            System.out.println("Opción inválida. Seleccione un doctor válido.");
            return;
        }
        Doctor doctorSeleccionado = listaDoctores.get(opcion-1);
        System.out.println("\nCitas del doctor " + doctorSeleccionado.getNombreCompleto() + ":");
        if (doctorSeleccionado.getCitas().isEmpty()) {
            System.out.println("No hay citas registradas para este doctor.");
            return;
        }
        for (Cita cita : doctorSeleccionado.getCitas()) {
            System.out.println(cita.toString());
        }

    }

    public static void PacienteNoLlego(ArrayList<Cita> listaCitas,Scanner scanner){
        System.out.println("\n[Paciente no llegó a la cita]");
        if (listaCitas.isEmpty()) {
            System.out.println("No hay citas médicas registradas.");
            return;
        }
        System.out.println("\nSeleccione la cita:");
        for (int i = 0; i < listaCitas.size(); i++) {
            System.out.println((i+1) + ". " + listaCitas.get(i).toString());
        }
        int citaSeleccionada = scanner.nextInt();
        scanner.nextLine();
        if (citaSeleccionada < 1 || citaSeleccionada > listaCitas.size()) {
            System.out.println("Opción inválida. Seleccione una cita válida.");
            return;
        }
        Cita citaALlegar = listaCitas.get(citaSeleccionada - 1);
        citaALlegar.setPacienteLlego(false);
        System.out.println("Paciente  ausente registrado correctamente.");
    }

    public static void MundoSalvaVidas(){

       String ascii=" _____ ______   ___  ___  ________   ________  ________              \n" +
               "|\\   _ \\  _   \\|\\  \\|\\  \\|\\   ___  \\|\\   ___ \\|\\   __  \\             \n" +
               "\\ \\  \\\\\\__\\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\|\\  \\            \n" +
               " \\ \\  \\\\|__| \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\           \n" +
               "  \\ \\  \\    \\ \\  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\          \n" +
               "   \\ \\__\\    \\ \\__\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\         \n" +
               "    \\|__|     \\|__|\\|_______|\\|__| \\|__|\\|_______|\\|_______|         \n" +
               " ________  ________  ___       ___      ___ ________                 \n" +
               "|\\   ____\\|\\   __  \\|\\  \\     |\\  \\    /  /|\\   __  \\                \n" +
               "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\    \\ \\  \\  /  / \\ \\  \\|\\  \\               \n" +
               " \\ \\_____  \\ \\   __  \\ \\  \\    \\ \\  \\/  / / \\ \\   __  \\              \n" +
               "  \\|____|\\  \\ \\  \\ \\  \\ \\  \\____\\ \\    / /   \\ \\  \\ \\  \\             \n" +
               "    ____\\_\\  \\ \\__\\ \\__\\ \\_______\\ \\__/ /     \\ \\__\\ \\__\\            \n" +
               "   |\\_________\\|__|\\|__|\\|_______|\\|__|/       \\|__|\\|__|            \n" +
               "   \\|_________|                                                      \n" +
               " ___      ___ ___  ________  ________  ________  ___  ___  ___       \n" +
               "|\\  \\    /  /|\\  \\|\\   ___ \\|\\   __  \\|\\   ____\\|\\  \\|\\  \\|\\  \\      \n" +
               "\\ \\  \\  /  / \\ \\  \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\___|\\ \\  \\ \\  \\ \\  \\     \n" +
               " \\ \\  \\/  / / \\ \\  \\ \\  \\ \\\\ \\ \\   __  \\ \\_____  \\ \\  \\ \\  \\ \\  \\    \n" +
               "  \\ \\    / /   \\ \\  \\ \\  \\_\\\\ \\ \\  \\ \\  \\|____|\\  \\ \\__\\ \\__\\ \\__\\   \n" +
               "   \\ \\__/ /     \\ \\__\\ \\_______\\ \\__\\ \\__\\____\\_\\  \\|__|\\|__|\\|__|   \n" +
               "    \\|__|/       \\|__|\\|_______|\\|__|\\|__|\\_________\\  ___  ___  ___ \n" +
               "                                         \\|_________| |\\__\\|\\__\\|\\__\\\n" +
               "                                                      \\|__|\\|__|\\|__|";

        System.out.println(ascii);
    }





}
