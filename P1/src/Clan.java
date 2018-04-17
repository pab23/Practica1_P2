import java.util.ArrayList;

public class Clan {
    private String nombre;
    private ArrayList<Habitante> miembros;
    private ArrayList<Terreno> feudo;
    private Mistico deidad;

    public Clan(String n){
        ArrayList<Habitante> pobla=Habitante.getPoblacion();
        for(int i=0;i<pobla.size();i++){
            if(n.equals(pobla.get(i).getClan())){
                miembros.add(pobla.get(i));
                Habitante.getPoblacion().remove(i);
            }
        }
        nombre=n;
        feudo=new ArrayList<>();
        deidad=null;
    }
    public boolean conquista(Terreno r){
        ////////////////////////////////////////////////////////////////////////////////
    }
    public String enfrenta(Clan c){
        if(!c.nombre.equals(this.nombre)){

        }
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
}
