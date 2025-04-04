package hospital.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cita {
    private Doctor doctor;
    private Paciente paciente;
    private String especialidad;
    private LocalDateTime fecha;

    private boolean pacienteLlego;
    private boolean trajoGalletas;

    public Cita(Doctor doctor, Paciente paciente, String especialidad, LocalDateTime fecha) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.trajoGalletas = false;
    }
    // Getters y Setters
    public Doctor getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public String getEspecialidad() { return especialidad; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isPacienteLlego() { return pacienteLlego; }
    public boolean isTrajoGalletas() { return trajoGalletas; }


    public void setPacienteLlego(boolean pacienteLlego) { this.pacienteLlego = pacienteLlego; }
    public void setTrajoGalletas(boolean trajoGalletas) { this.trajoGalletas = trajoGalletas; }

    @Override
    public String toString() {
        String fechaFormateada = fecha.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm a"));
        return "Cita para " + paciente.getNombreCompleto() + " con Dr. " + doctor.getNombreCompleto()+
                " (" + especialidad + ") el " + fechaFormateada;
    }
}