package utils;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Validaciones {
	
	public static Float validateFloat(String str) {
	    if (str == null) {
	        return null;
	    }
	    try {
	        float num = Float.parseFloat(str);
	        return num;
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}
	
	public static Integer validateInt(String str) {
	    if (str == null) {
	        return null;
	    }
	    try {
	        int num = Integer.parseInt(str);
	        return num;
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}
	
	public static Integer validateDocumento(String str, String tipo) {
	    if (str == null || (tipo.equals("DNI") && str.length() != 8) || ((tipo.equals("CUIL") || tipo.equals("CUIT")) && str.length() != 11)) {
	        return null; 
	    }
	    try {
	        int num = Integer.parseInt(str);
	        return num; 
	    } catch (NumberFormatException e) {
	        return null; 
	    }
	}
	
	public static String validateNombre(String str) {
	    if (str == null || str.length() > 200 || !str.matches("^[a-zA-Z]+$")) {
	        return null; 
	    }
	    return str; 
	}
	public static String validateTipoDoc(String str) {
	    if (str.equals("DNI") || str.equals("CUIL") || str.equals("CUIT")) {
	        return str; 
	    }
	    else return null; 
	}
	
	
	public static String validateSQLDateTime(String str) {
	    if (str == null) {
	        return null;
	    }
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	    sdf.setLenient(false); // Forzar coincidencia estricta

	    try {
	        sdf.parse(str);
	        return str; // La cadena es una fecha y hora válida
	    } catch (ParseException e) {
	        return null; // La cadena no es una fecha y hora válida
	    }
	}
	
    public static int generateRandomNumber() {
        int min = 100000000;
        int max = 999999999;
        
        Random random = new Random();
        int numeroAleatorio = random.nextInt(max - min + 1) + min;
        
        return numeroAleatorio;
    }

}
