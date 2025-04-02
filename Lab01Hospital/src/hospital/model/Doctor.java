package hospital.model;

public class Doctor {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleanios;
    private String fechaReclutamiento;
    private String especialidad;
    private String codigoEpico;

    public Doctor(String nombre, String apellido, String dui, String cumpleanios,
                  String fechaReclutamiento, String especialidad, String codigoEpico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumpleanios = cumpleanios;
        this.fechaReclutamiento = fechaReclutamiento;
        this.especialidad = especialidad;
        this.codigoEpico = codigoEpico;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleanios() { return cumpleanios; }
    public String getFechaReclutamiento() { return fechaReclutamiento; }
    public String getEspecialidad() { return especialidad; }
    public String getCodigoEpico() { return codigoEpico; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + especialidad + ") - Código: " + codigoEpico;
    }
}