package hospital.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Doctor {
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String dui;
    private LocalDate cumpleanios;
    private LocalDate fechaReclutamiento;
    private String especialidad;
    private String codigoEpico;
    private ArrayList<LocalDateTime> fechasCitasFuturas = new ArrayList<>(List.of());
    private ArrayList<LocalDateTime> fechasCitasHoy = new ArrayList<>(List.of());
    private ArrayList<Cita> citas = new ArrayList<>(List.of());

    public Doctor(String nombre, String apellido, String dui, LocalDate cumpleanios, LocalDate fechaReclutamiento, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombre+" "+apellido;
        this.dui = dui;
        this.cumpleanios = cumpleanios;
        this.fechaReclutamiento = fechaReclutamiento;
        this.especialidad = especialidad;

    }

    public void generarCodigo (){
        Random random  = new Random();
        int digito1 = random.nextInt(10);
        char letra1 = (char) (random.nextInt(26) + 'A');
        int digito2 = random.nextInt(10);
        char letra2 = (char) (random.nextInt(26) + 'A');
        int digito3 = random.nextInt(10);

        this.codigoEpico = String.format("ZNH-%d%c%d-MD-%c%d", digito1, letra1, digito2, letra2, digito3);

    }

    public void agregarCita(Cita cita){
        this.citas.add(cita);

    }

    public LocalDateTime validarDisponibilidadHoy(LocalDateTime fechaCita){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a");
        LocalDateTime fechaCitaFormateada = LocalDateTime.parse(fechaCita.format(formato), formato);

        if(fechasCitasHoy.isEmpty()){
            fechasCitasHoy.add(fechaCitaFormateada);
            return fechaCitaFormateada;
        }else{

            LocalDateTime ultimaCita = fechasCitasHoy.getLast();
            if(ultimaCita.getHour() >= 16){
                System.out.println("No se pudo asignar cita ese dia ya que no hay horas disponibles");
                return null;
            }
            fechasCitasHoy.add(ultimaCita.plusHours(1));
            return ultimaCita.plusHours(1);

        }

    }

    public LocalDateTime validarDisponibilidadFutura(int dia, int mes) {
        LocalDateTime fechaCita = LocalDateTime.now().withDayOfMonth(dia).withMonth(mes).withHour(8).withMinute(0);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        LocalDateTime fechaCitaFormateada = LocalDateTime.parse(fechaCita.format(formato), formato);

        if (fechasCitasFuturas.isEmpty()) {
            fechasCitasFuturas.add(fechaCitaFormateada);
            return fechaCitaFormateada;
        } else {
            LocalDateTime citaMasTarde = null;

            for (LocalDateTime fecha : fechasCitasFuturas) {
                if (fecha.getDayOfMonth() == dia && fecha.getMonthValue() == mes) {
                    if (citaMasTarde == null || fecha.isAfter(citaMasTarde)) {
                        citaMasTarde = fecha;
                    }
                }
            }

            if (citaMasTarde != null) {
                LocalDateTime nuevaFechaCita = citaMasTarde.plusHours(1);
                if (nuevaFechaCita.getHour() > 16) {
                    System.out.println("No se pudo asignar cita ese dia ya que no hay horas disponibles");
                    return null;
                } else {
                    fechasCitasFuturas.add(nuevaFechaCita);
                    return nuevaFechaCita;
                }
            } else {
                fechasCitasFuturas.add(fechaCitaFormateada);
                return fechaCitaFormateada;
            }
        }
    }


    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleanios() { return cumpleanios.toString(); }
    public String getFechaReclutamiento() { return fechaReclutamiento.toString(); }
    public String getEspecialidad() { return especialidad; }
    public String getCodigoEpico() { return codigoEpico; }
    public String getNombreCompleto(){return nombreCompleto;}
    public ArrayList<Cita> getCitas() {return citas;
    }



    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + especialidad + ") - Código: " + codigoEpico;
    }
}