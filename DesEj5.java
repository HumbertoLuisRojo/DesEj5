import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class DesEj5 {
    public static void main(String[] args) {
        //BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
        //String letrita;
        //System.out.println("Ingrese una letra");
        //letrita = bufEntrada.readLine();
        System.out.println(" ");
        creaElemento();
        String letrita = "a".toUpperCase();
        System.out.println("Personas cuyo apellido comienza con " + letrita);
        Persona.empiezaLetra(letrita);
        System.out.println(" ");
        System.out.println("La persona de Mayor edad es: ");
        Persona.May();
        System.out.println(" ");
        System.out.println("La persona de menor edad es: ");
        Persona.men();
        System.out.println(" ");
        System.out.println("El Mayor sueldo lo tiene: ");
        Persona.menSueldo();
        System.out.println(" ");
        System.out.println("El menor sueldo es: ");
        Persona.MaySueldo();
        System.out.println(" ");
        System.out.println("Ordenado:");
        Persona.ordenarSort();
        System.out.println(" ");
    }
    
    // Lee el archivo Ejercicio5.txt 
    static void creaElemento() {
    
        String archivoTxt = "Ejercicio5.txt";
        try (Stream<String> stream = Files.lines(Paths.get(archivoTxt))){
            stream.forEach((elem) -> {
                String[] lista = elem.split(",");
                String nombre = lista[0];
                String apellido = lista[1];
                String fechaNacimiento = lista[2];
                String sueldo = lista[3];
                Persona persona = new Persona(nombre, apellido, fechaNacimiento, sueldo);
             });
         } catch (Exception elem) {
            System.out.println("ERROR de archivo" + elem.getMessage());
        }
    }

    static class Persona {
        static List<Persona> listaPersonas = new ArrayList<>();
        String nombre;
        String apellido;
        LocalDate fechaNacimiento;
        BigDecimal sueldo;

        public Persona(String nombre, String apellido, String fechaNacimiento, String sueldo) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.fechaNacimiento = toFecha(fechaNacimiento);
            this.sueldo = toSueldo(sueldo);
            listar();
        }

        static LocalDate toFecha(String fechaNacimiento) {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(fechaNacimiento, formato);
            return fecha;
        }

        static BigDecimal toSueldo(String sueldo) {
            BigDecimal monto = new BigDecimal(sueldo);
            return monto;
        }

        // Agrega un elemento a la lista
        private void listar() {
                    listaPersonas.add(this);
                }
        
                // Muestra las personas cuyo apellido comience con "letra"
                public static void empiezaLetra(String letra) {
                    listaPersonas.forEach((elem) -> {
                         if (elem.apellido.startsWith(letra)) {
                             System.out.println(elem);
                        }
                    });
                }
        
                private int edad() {
                    int edad = LocalDate.now().getYear() - this.fechaNacimiento.getYear();
                    return edad;
        }

        // Muestra la persona con mayor edad
        public static void May() {
            if (listaPersonas.size() != 0) {
                int comp = 0;
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (comp < listaPersonas.get(i).edad()) {
                        comp = listaPersonas.get(i).edad();
                    }
                }
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (listaPersonas.get(i).edad() == comp) {
                        System.out.println(listaPersonas.get(i));
                    }
                }
            }
            else {
                System.out.println("Sin elementos en el archivo");
            }
        }

        // Muestra la persona con menor edad
        public static void men() {
            if (listaPersonas.size() != 0) {
                int comp = listaPersonas.get(0).edad();
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (comp > listaPersonas.get(i).edad()) {
                        comp = listaPersonas.get(i).edad();
                    }
                }
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (listaPersonas.get(i).edad() == comp) {
                        System.out.println(listaPersonas.get(i));
                    }
                }
            }
            else {
                System.out.println("Sin elementos en el archivo");
            }
        }

        // Muestra la persona que gana más
        public static void MaySueldo() {
            if (listaPersonas.size() != 0) {
                BigDecimal comp = listaPersonas.get(0).sueldo;
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (comp.compareTo(listaPersonas.get(i).sueldo) == -1 ) {
                        comp = listaPersonas.get(i).sueldo;
                    }
                }
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (listaPersonas.get(i).sueldo == comp) {
                        System.out.println(listaPersonas.get(i));
                    }
                }
            }
            else {
                System.out.println("Sin elementos en el archivo");
            }
        }
        
        // Muestra la persona que gana menos
        public static void menSueldo() {
            if (listaPersonas.size() != 0) {
                BigDecimal comp = listaPersonas.get(0).sueldo;
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (comp.compareTo(listaPersonas.get(i).sueldo) == 1 ) {
                        comp = listaPersonas.get(i).sueldo;
                    }
                }
                for (int i = 0; i < listaPersonas.size(); i++) {
                    if (listaPersonas.get(i).sueldo == comp) {
                        System.out.println(listaPersonas.get(i));
                    }
                }
            }
            else {
                System.out.println("Sin elementos en el archivo");
            }
        }

        // Ordena la lista de personas
         public static void ordenarSort() {
            listaPersonas.sort((o1, o2) -> o1.nombre.compareTo(o2.nombre));
            System.out.println(listaPersonas);
            System.out.println(" ");
        }

        @Override
        public String toString() {
            return this.nombre + " " + this.apellido + ": $" + this.sueldo;
        }
    }
}