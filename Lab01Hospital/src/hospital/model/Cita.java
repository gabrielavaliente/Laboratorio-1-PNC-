package hospital.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Cita {
    private Doctor doctor;
    private Paciente paciente;
    private String especialidad;
    private LocalDateTime fecha;
    private boolean atendida;
    private boolean pacienteLlego;
    private boolean trajoGalletas;

    public Cita(Doctor doctor, Paciente paciente, String especialidad, LocalDateTime fecha) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.atendida = false;
        this.pacienteLlego = false;
        this.trajoGalletas = false;
    }
    // Getters y Setters
    public Doctor getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public String getEspecialidad() { return especialidad; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isAtendida() { return atendida; }
    public boolean isPacienteLlego() { return pacienteLlego; }
    public boolean isTrajoGalletas() { return trajoGalletas; }

    public void setAtendida(boolean atendida) { this.atendida = atendida; }
    public void setPacienteLlego(boolean pacienteLlego) { this.pacienteLlego = pacienteLlego; }
    public void setTrajoGalletas(boolean trajoGalletas) { this.trajoGalletas = trajoGalletas; }

    @Override
    public String toString() {
        return "Cita para " + paciente.getNombre() + " con Dr. " + doctor.getNombre() +
                " (" + especialidad + ") el " + fecha.getDayOfMonth() +" " +  fecha.getMonth() + " " + fecha.getYear() + " a las " + fecha.getHour();
    }
}