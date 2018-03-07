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
                a.get(i).setColocado(true);
            }
        }
        suma=suma/a.size();
        for(int i=0;i<adoradores.size() && encuentra==false;i++){
            if(adoradores.get(i).equals(s)) encuentra=true;
        }
        if(!encuentra) adoradores.add(s);
        return suma;
    }
    public int regenera( Terreno t, Producto p){
        int vector=0;
        int colocado=0;
        if(!p.getColocado()){
            tributos.add(p);
            p.setColocado(true);
            for(int i=0;i<t.getFilas();i++){
                for(int j=0;j<t.getColumnas();j++){
                    if(vector>=tributos.size())vector=0;
                    if(t.genera(i, j, tributos.get(vector).getPeso(), tributos.get(vector).getTipo(),tributos.get(vector).getNombre())){
                        colocado++;
                        vector++;
                    }

                }
            }
        }
        return colocado;
    }
    public int transforma(Terreno t, int i){
        int devuelve=0;
        boolean encontrado=false;
        int j=0;
        if(i!=5){
            for(j=0;j<tributos.size();j++){
                if(tributos.get(j).getTipo()==6)encontrado=true;
            }
            for(int z=0;z<t.getFilas();z++){
                for(int k=0;k<t.getColumnas();k++){
                    if(t.consultaTipo( z, k)==5){
                        Producto p=t.recoge( z, k);
                        tributos.get(j).transforma(p, i);
                        t.coloca(p, z, k);
                        devuelve++;
                    }
                }
            }
        }
        return devuelve;
    }
    public ArrayList<Producto> getTributos(){
        return tributos;
    }
    public ArrayList<String> getAdoradores(){
        return adoradores;
    }
}
