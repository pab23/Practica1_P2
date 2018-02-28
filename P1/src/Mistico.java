import java.util.ArrayList;
public class Mistico {
    ArrayList<Producto> tributos;
    ArrayList<String> adoradores;
    public Mistico(){
        tributos= new ArrayList<Producto>();
        adoradores= new ArrayList<String>();
    }
    public double culto(ArrayList<Producto> a, String s){
        boolean encuentra=false;
        double suma=0;
        for(int i=0;i<a.size();i++){
            if(a.get(i)!=null) {
                suma = suma + a.get(i).valorKilo();
                tributos.add(a.get(i));
            }
        }
        suma=suma/a.size();
        for(int i=0;i<adoradores.size() && encuentra==false;i++){
            if(adoradores.get(i).equals(s)) encuentra=true;
        }
        if(!encuentra) adoradores.add(s);
        return suma;
    }
}
