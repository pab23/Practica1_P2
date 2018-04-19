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
        int cont=0;
        //Preguntar si hay que eliminar los PRoductos Filosofal
        ArrayList<Producto> recogido=new ArrayList<>();
        ArrayList<Habitante> poblacion=acolitos.get(i).getMiembros();
        for(int j=0;j<poblacion.size();j++){
            if(poblacion.get(j) instanceof Plebeyo){
               Plebeyo p=(Plebeyo) poblacion.get(j);
               if(p.getBestiola() instanceof Demoledor) {
                    for(int z=0;z<getTributos().size() && recogido.size()<2;z++){
                        if(getTributos().get(z).getTipo()==6)recogido.add(getTributos().get(z));
                    }
                    if(recogido.size()==2) {
                        Bestia b = p.getBestiola();
                        p.setBestia(b);
                        cont++;
                    }
                }
            }
        }
        return cont;
    }
    public double ayuda(Producto p){
        if(p.getTipo()==6){
            this.addTributo(p);
        }
        return p.getPeso();
    }
    public void fervor(Clan c){
        boolean encontrado=false;
        if(c.getDeidad()==null){
            for(int i=0;i<acolitos.size() && encontrado==false;i++){
                if(acolitos.get(i)==c)encontrado=true;
            }
            if(encontrado==false){
                acolitos.add(c);
            }
        }
    }
    public String getNombre(){
        return nombre;
    }

    public ArrayList<Clan> getAcolitos() {
        return acolitos;
    }
}
