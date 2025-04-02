package hospital.model;

public class Cita {
    private Doctor doctor;
    private Paciente paciente;
    private String especialidad;
    private String fecha;
    private String hora;
    private boolean atendida;
    private boolean pacienteLlego;
    private boolean trajoGalletas;

    public Cita(Doctor doctor, Paciente paciente, String especialidad,String fecha, String hora) {
        this.doctor = doctor;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.atendida = false;
        this.pacienteLlego = false;
        this.trajoGalletas = false;
    }
    // Getters y Setters
    public Doctor getDoctor() { return doctor; }
    public Paciente getPaciente() { return paciente; }
    public String getEspecialidad() { return especialidad; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public boolean isAtendida() { return atendida; }
    public boolean isPacienteLlego() { return pacienteLlego; }
    public boolean isTrajoGalletas() { return trajoGalletas; }

    public void setAtendida(boolean atendida) { this.atendida = atendida; }
    public void setPacienteLlego(boolean pacienteLlego) { this.pacienteLlego = pacienteLlego; }
    public void setTrajoGalletas(boolean trajoGalletas) { this.trajoGalletas = trajoGalletas; }

    @Override
    public String toString() {
        return "Cita para " + paciente.getNombre() + " con Dr. " + doctor.getNombre() +
                " (" + especialidad + ") el " + fecha + " a las " + hora;
    }
}