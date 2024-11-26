import java.util.Scanner;

public class Challenge_1 {

    private static final Scanner input = new Scanner(System.in);   
    private static String cadena; 
    private static String key[];
    private static int digits[];

    public static void main(String[] args) {
        
        String banner = """
        
            ██████╗ ██╗     ███████╗███████╗██╗    ██╗ █████╗ ██████╗ ███████╗
            ██╔══██╗██║     ██╔════╝██╔════╝██║    ██║██╔══██╗██╔══██╗██╔════╝
            ██████╔╝██║     █████╗  ███████╗██║ █╗ ██║███████║██████╔╝█████╗  
            ██╔══██╗██║     ██╔══╝  ╚════██║██║███╗██║██╔══██║██╔══██╗██╔══╝  
            ██████╔╝███████╗███████╗███████║╚███╔███╔╝██║  ██║██║  ██║███████╗
            ╚═════╝ ╚══════╝╚══════╝╚══════╝ ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝

            Desafio 1 de Codember 2024 v1.0
            """;
        
        System.out.println(banner);

        System.out.print("Digite la cadena de texto: ");
        cadena = input.nextLine();        

        System.out.println("\nLa contraseña es: " + verifyKey());
    }

    //Metodo para verificar la clave dada
    private static String verifyKey() {

        try {
                    
            String aux = cadena;

            key = cadena.split(" ");            
            fillDigits();

            if(aux.trim().contains(" ") && key.length == 2 && isInteger(key[0]) && validCodes(key[1])) {
                
                return executeComands();
            }

            return "Formato de contraseña invalido, vuelva a intentar";

        } catch (Exception e) {
            
            return "Formato de contraseña invalido, vuelva a intentar " + e;
        }        
    }

    //Metodo para ejecutar los comandos
    private static String executeComands() {

        int index = 0;

        for (int i = 0; i < key[1].length(); i++) {                       

            switch(String.valueOf(key[1].charAt(i))) {

                case "R":                                     

                    index = index == digits.length - 1 ? 0 : index + 1; 

                    break;                    

                case "L":                                                        

                    index = index == 0 ? digits.length - 1 : index - 1;

                    break;

                case "U":                    
                    
                    int x = digits[index];                                               
                    
                    x = x == 9 ? 0 : x + 1;

                    digits[index] = x;

                    String aux = "";

                    for (int j = 0; j < digits.length; j++) {
                        
                        aux += String.valueOf(digits[j]);
                    }

                    key[0] = aux;
            
                    break;

                case "D":

                    int y = digits[index];                    

                    y = y == 0 ? 9 : y - 1;

                    digits[index] = y;

                    String aux2 = "";

                    for (int j = 0; j < digits.length; j++) {
                    
                        aux2 += String.valueOf(digits[j]);
                    }

                    key[0] = aux2;
        
                    break;
            }
        }

        return key[0];
    }

    //Metodo para llenar el array de los numeros
    private static void fillDigits() {

        digits = new int [key[0].length()];

        for (int i = 0; i < key[0].length(); i++) {

            digits[i] = Integer.parseInt(String.valueOf(key[0].charAt(i)));
        }
    }

    //Metodo para validad que la secuencia de letras sea correcta
    private static boolean validCodes(String code) {

        for (int i = 0; i < code.length(); i++) {
            
            switch(String.valueOf(code.charAt(i))) {

                case "r":                    
                case "l":
                case "u":
                case "d":
                    System.out.println("Los caracteres deben ser en mayusculas");
                    return false;

                case "R":
                case "L":
                case "U":
                case "D":
                    break;
    
                default:
                    return false;
            }
        }        

        return true;
    }

    //Este metodo valida que la primera parte sean numeros validos
    private static boolean isInteger(String x) {

        if(x == null || x.isEmpty()) {

            return false;
        }

        try {

            return Long.parseLong(x) >= 0; //Usamos Long por si acaso el numero supera el limite de Integer

        } catch (Exception e) {

            return false;
        }
    }
}
