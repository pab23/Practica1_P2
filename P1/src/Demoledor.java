//46087864 AMOROS BECERRA, PABLO

import java.util.ArrayList;

public class Demoledor extends Bestia{
    public Demoledor(double f){
        super(f);
    }
    public boolean domestica(Habitante h){
        boolean devuelve=false;
        int produc=0;
        double fuer=getFuerza();
        if(getAmo()!=null){
            this.setFuerza(fuer+(double)getAmo().getCesta().size());
            produc=getAmo().getCesta().size();
            for(int i=0;i<produc;i++){
                getAmo().setCesta(i, null);
            }
            if(getFuerza()>fuer){ devuelve=true;}
            setAmo(null);
        }else{
            this.setFuerza(fuer+(double)h.getCesta().size());
            produc=h.getCesta().size();
            for(int i=0;i<produc;i++){
                h.setCesta(i, null);
            }
            if(getFuerza()>fuer){ devuelve=true;}
        }
        return devuelve;
    }
    public boolean alimenta(Producto p){
        boolean devuelve=false;
        if(p.getTipo()==2){
            setFuerza(getFuerza()+(p.getPeso()*0.15));
            devuelve=true;
        }
        return devuelve;
    }
    public ArrayList<Producto> pasturea(Terreno r, int k){
        ArrayList<Producto> devuelve=new ArrayList<>();

        for(int i=0;i<r.getColumnas();i++){
           if(r.consultaTipo(k, i)==5){
               r.destruye(k, i);
           }else{
               devuelve.add(r.recoge(k, i));
           }
        }
        if(getAmuleto()!=null){
            for(int i=0;i<devuelve.size();i++){
                getAmuleto().transforma(devuelve.get(i), 2);
            }
        }
        for(int i=0;i<devuelve.size();i++){
            alimenta(devuelve.get(i));
            devuelve.remove(i);
        }
        return devuelve;
    }
    public double ayuda(){
        return (-(getFuerza()*0.5));
    }
    public double arrasa(Terreno r){
        double peso=0;
        for (int i=0;i<r.getFilas();i++){
            for(int j=0;j<r.getColumnas();j++){
                peso+=r.destruye(i,j);
            }
        }
        return peso;
    }
    public Bestia exorciza(){
        Bestia devuelve=new Bestia(getFuerza());
        devuelve.setNombre(getNombre());
        devuelve.setAmo(getAmo());
        devuelve.setAmuleto(getAmuleto());
        return devuelve;
    }
}
