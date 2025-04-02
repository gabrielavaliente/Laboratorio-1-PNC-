package hospital.Util;

public class Validaciones {
    public static boolean validarCodigoDoctor(String codigo) {
        // Formato: ZNH-XAX-MD-AX (X es número, A es letra)
        return codigo.matches("ZNH-\\d[A-Za-z]\\d-MD-[A-Za-z]\\d");
    }

}