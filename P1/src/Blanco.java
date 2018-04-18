import java.util.ArrayList;

public class Blanco extends Mistico{
    private ArrayList<Clan> acolitos;
    private String nombre;

    public Blanco(String n){
        super();
        nombre=n;
        acolitos= new ArrayList<>();
    }
    public boolean explora(String c){
        ArrayList<Terreno> terraux;
        Terreno activo;
        boolean recogido=false;
        for(int i=0;i<acolitos.size() && recogido==false;i++){
            terraux=acolitos.get(i).getFeudo();
            for(int j=0;j<terraux.size() && recogido==false;j++){
                activo=terraux.get(j);
                for(int z=0;z<activo.getFilas() && recogido==false;j++){
                    for(int k=0;k<activo.getColumnas() && recogido==false;k++){
                        if(activo.consultaTipo(z, k)==6 && activo.consultaNombre(z,k).equals(c)){
                            recogido=true;
                            this.addPrimer(activo.recoge(z, k));
                        }
                    }
                }
            }
        }
        return recogido;
    }
    public int exorcismo(int i){
        ArrayList<Habitante> poblacion=acolitos.get(i).getMiembros();
        for(int j=0;j<poblacion.size();j++){
            if(poblacion.get(j) instanceof Plebeyo){
               Plebeyo p=(Plebeyo) poblacion.get(j);
               if(p.getBestiola() instanceof Demoledor) {


                }
            }
        }
    }
    public double ayuda(Producto p){

    }
    public void fervor(Clan c){

    }
    public String getNombre(){
        return nombre;
    }

    public ArrayList<Clan> getAcolitos() {
        return acolitos;
    }
}
