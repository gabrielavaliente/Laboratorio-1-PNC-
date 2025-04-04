package hospital.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nombre;
    private String apellido;
    private String nombreCompleto;
    private String dui;
    private LocalDate cumpleanos;
    long edad;

    private ArrayList<Cita> citas = new ArrayList<>(List.of());


    public Paciente(String nombre, String apellido, String dui, LocalDate cumpleanos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreCompleto = nombre+" "+apellido;
        this.cumpleanos=cumpleanos;
        this.edad = ChronoUnit.YEARS.between(cumpleanos,LocalDate.now());
        if(ChronoUnit.YEARS.between(cumpleanos,LocalDate.now())<18){
            this.dui = "00000000-0";
        }else{
            this.dui = dui;
        }

    }

    public boolean validarDisponibilidad(LocalDateTime fecha){
        System.out.println("Validando disponibilidad de cita");
        for (Cita cita : citas) {
            if (cita.getFecha().getHour()==fecha.getHour() && cita.getFecha().getDayOfMonth()==fecha.getDayOfMonth() && cita.getFecha().getMonthValue()==fecha.getMonthValue()) {
                return true;
            }
        }
        return false;
    }

    public void agregarCita(Cita cita){
        this.citas.add(cita);

    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDui() { return dui; }
    public String getCumpleanos() { return cumpleanos.toString(); }
    public String getNombreCompleto(){return nombreCompleto;}
    public long getEdad(){return edad;}
    public ArrayList<Cita> getCitas() {return citas;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (DUI: " + dui + ")"+ " Edad: "+ edad;
    } 
}