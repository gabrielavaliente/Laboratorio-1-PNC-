package hospital.model;

public class Paciente {
    private String nombre;
    private String apellido;
    private String dui;
    private String cumpleanos;

    public Paciente(String nombre, String apellido, String dui, String cumpleanos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dui = dui;
        this.cumpleanos = cumpleanos;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleanos() { return cumpleanos; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DUI: " + dui + ")";
    } 
}