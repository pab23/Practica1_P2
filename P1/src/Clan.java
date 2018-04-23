//46087864 AMOROS BECERRA, PABLO

import java.util.ArrayList;

public class Clan {
    private String nombre;
    private ArrayList<Habitante> miembros;
    private ArrayList<Terreno> feudo;
    private Mistico deidad;

    public Clan(String n){
        ArrayList<Habitante> pobla=Habitante.getPoblacion();
        miembros=new ArrayList<>();
        nombre=n;
        feudo=new ArrayList<>();
        deidad=null;
        for(int i=0;i<pobla.size();i++){
            if(n.equals(pobla.get(i).getClan())){
                pobla.get(i).esAcogido(this);
                miembros.add(pobla.get(i));
                Habitante.getPoblacion().remove(i);
            }
        }
    }
    public boolean conquista(Terreno r){
        boolean devuelve=false;
        if(r.getClan()==null){
            feudo.add(r);
            r.setClan(this);
            devuelve=true;
        }
        return devuelve;
    }
    public String enfrenta(Clan c){
        String ganador=null;
        ArrayList<Guerrero> miclan=cogeGuerrero();
        ArrayList<Guerrero> paraclan= c.cogeGuerrero();
        int yo=0, otro=0;
        if(c!=this){
            for(int i=0;i<miclan.size() && i<paraclan.size();i++){
                if(miclan.get(i).lucha(paraclan.get(i))==2)yo++;
                else if(miclan.get(i).lucha(paraclan.get(i))==1)otro++;
            }
            if(yo>otro){
                ganador=getNombre();
            }else if(yo<otro){
                ganador=c.getNombre();
            }else if(miclan.size()>paraclan.size()){
                ganador=getNombre();
            }else if(miclan.size()<paraclan.size()){
                ganador=c.getNombre();
            }
        }
        return ganador;
    }
    public int laboro(int i){
        int recolectados=0, sumador=0;
        for(int j=0;j<miembros.size() && j<feudo.size();j++){
            if(miembros.get(i) instanceof Plebeyo){
                sumador=((Plebeyo) miembros.get(i)).recolecta(j, i);
                if(sumador!=-1)recolectados++;
            }else{
                recolectados+=((Guerrero)miembros.get(i)).recolecta(j);
            }
        }
        return recolectados;
    }
    public Habitante destierra(String n){
        Habitante desterrado;
        for(int i=0;i<miembros.size();i++){
            if(miembros.get(i).getNombre().equals(n)){
                desterrado=miembros.get(i);
                miembros.remove(i);
                return desterrado;
            }
        }
        return null;
    }
    public int acoge(Habitante h){
        h.cambiaClan(nombre);
        h.limpiaClan();
        h.esAcogido(this);
        miembros.add(h);
        return miembros.size();
    }
    public boolean adoptaDeidad(Mistico m){
        boolean devuelve=false;
        if(deidad==null){
            deidad=m;
            if(m instanceof Blanco)((Blanco) m).fervor(this);
            devuelve=true;
        }
        return devuelve;
    }
    public ArrayList<Habitante> getMiembros(){
        return miembros;
    }
    public Mistico getDeidad(){
        return deidad;
    }
    public ArrayList<Terreno> getFeudo(){
        return feudo;
    }
    public String getNombre(){return nombre;}
    public ArrayList<Guerrero> cogeGuerrero(){
        ArrayList<Guerrero> devuelve= new ArrayList<>();
        for(int i=0;i<miembros.size();i++){
            if(miembros.get(i) instanceof Guerrero)devuelve.add((Guerrero)miembros.get(i));
        }
        return devuelve;
    }
}
