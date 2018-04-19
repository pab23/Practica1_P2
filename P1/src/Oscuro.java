import java.util.ArrayList;

public class Oscuro extends Mistico {
    private ArrayList<Demoledor> caterva;

    public Oscuro(){
        super();
        caterva= new ArrayList<>();
    }
    public double culto(Bestia c){
        double devuelve=c.getFuerza()*0.2;
        boolean encontrado=false;
        for(int i=0;i<caterva.size() && encontrado==false;i++){
            if(caterva.get(i)==c)encontrado=true;
        }
        if(encontrado==false){
            c.setFuerza(c.getFuerza()-devuelve);
            Demoledor conver= (Demoledor) c;
            caterva.add(conver);

        }
        return devuelve;
    }
    public boolean malogra(Habitante h){
        if(h instanceof Plebeyo){
            Plebeyo p=(Plebeyo) h;
            p.setBestia(p.getBestiola().hechiza());
            return true;
        }
        return false;
    }
    public int calamidades(Clan c){
        int cont=0;
        for(int i=0;i<caterva.size();i++){
            caterva.get(i).arrasa(c.getFeudo().get(i));
            cont++;
        }
        return cont;
    }
    public double ayuda(Producto p){
        addPrimer(p);
        return (-p.valorProducto());
    }

    public ArrayList<Demoledor> getCaterva() {
        return caterva;
    }
}
