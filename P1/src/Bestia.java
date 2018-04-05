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
        if (amo == null && h instanceof Plebeyo && h.getBestiola==null) {
            amo=(Plebeyo)h;
            devuelve=true;
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
            alimenta(devuelve.get(0));
            devuelve.remove(0);
        }else{
            for(int i=0;i<devuelve.size();i++){
                if(alimenta(devuelve.get(i)){
                    devuelve.remove(i);
                }
            }
            return null;
        }
        return devuelve;
    }
    public double ayuda(){
        fuerza=fuerza/2;
        return fuerza;
    }
    public Demoledor hechiza(){
        Demoledor devuelve=new Demoledor(fuerza);
        devuelve.amo=amo;
        devuelve.nombre=nombre;
        devuelve.amuleto=amuleto;
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
}
