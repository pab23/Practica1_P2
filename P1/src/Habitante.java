import java.util.ArrayList;
public class Habitante {
    private String nombre;
    private ArrayList<Producto> cesta;
    private double vigor;
    private char sexo;
    private static ArrayList<Habitante> poblacion;

    public Habitante(String s, char c){
        nombre=s;
        if(c!='H' && c!='M'){
            sexo='H';
        }else{
            sexo=c;
        }
        vigor=100;
        cesta=new ArrayList<Producto>();
        if(poblacion==null){
            poblacion=new ArrayList<Habitante>();
        }
        poblacion.add(this);

    }
}
