//46087864 AMOROS BECERRA, PABLO

import java.util.ArrayList;

public class Bestia {
    private double fuerza;
    private Plebeyo amo;
    private String nombre;
    private Producto amuleto;
    public Bestia(double f){
        if(f>0){
            fuerza=f;
        }else{
            fuerza=10.5;
        }
        amo=null;
        nombre=null;
        amuleto=null;
    }
    public String busca(Terreno r){
        String nomamu=null;
        boolean encontrado=false;
        if(amuleto==null){
            for(int i=0;i<r.getFilas() && encontrado==false;i++){
                for(int j=0;j<r.getColumnas() && encontrado==false;j++){
                    if(r.consultaTipo(i, j)==6){
                        Producto recogi=r.recoge(i, j);
                        amuleto=recogi;
                        nomamu=amuleto.getNombre();
                        encontrado=true;
                    }
                }
            }
        }
        return nomamu;
    }
    public boolean domestica(Habitante h){
        boolean devuelve=false;
        if (amo == null && h instanceof Plebeyo) {
            Plebeyo p=(Plebeyo) h;
            if(p.getBestiola()==null) {
                amo = p;
                devuelve = true;
            }
        }
        return devuelve;
    }
    public boolean alimenta(Producto p){
        boolean devuelve=false;
        if(p.getTipo()==1){
            fuerza=fuerza+(p.getPeso()*0.1);
            devuelve=true;
        }
        return devuelve;
    }
    public ArrayList<Producto> pasturea(Terreno t, int k){
        ArrayList<Producto> devuelve= new ArrayList<Producto>();
        Producto aux;
        boolean alimentado=false;
        for(int i=0;i<t.getColumnas();i++){
           aux=t.recoge(k, i);
           if(aux!=null){
               devuelve.add(aux);
               aux=null;
           }
        }
        if(amuleto!=null){
            for(int i=0;i<devuelve.size();i++){
                amuleto.transforma(devuelve.get(i), 1);
            }
        }
        if(amo!=null){
            for(int i=0;i<devuelve.size() && !alimentado;i++){
                if(devuelve.get(i).getTipo()==1){
                    alimenta(devuelve.get(i));
                    devuelve.remove(i);
                    alimentado=true;
                }
            }
        }else{
            for(int i=0;i<devuelve.size();i++){
                if(alimenta(devuelve.get(i))){
                    devuelve.remove(i);
                }
            }
            devuelve=new ArrayList<>();
            return devuelve;
        }
        return devuelve;
    }
    public double ayuda(){
        fuerza=fuerza/2;
        return fuerza;
    }
    public Demoledor hechiza(){
        Demoledor devuelve=new Demoledor(fuerza);
        devuelve.setAmo(amo);
        devuelve.setNombre(nombre);
        devuelve.setAmuleto(amuleto);
        return devuelve;
    }
    public void setNombre(String s){
        nombre=s;
    }
    public String getNombre(){
        return nombre;
    }
    public double getFuerza(){
        return fuerza;
    }
    public Producto getAmuleto(){
        return amuleto;
    }
    public Plebeyo getAmo(){
        return amo;
    }
    public void setAmo(Plebeyo p){
        amo=p;
    }
    public void setAmuleto(Producto p){
        amuleto=p;
    }
    public void setFuerza(double f){
        fuerza=f;
    }
}
