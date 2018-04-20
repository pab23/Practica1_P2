//46087864 AMOROS BECERRA, PABLO
import java.util.ArrayList;

public class Plebeyo extends Habitante {
    private Bestia bestiola;
    private Guerrero patrono;
    private Clan tribu;

    public Plebeyo(String s, char c) {
        super(s, c);
        bestiola = null;
        patrono = null;
        tribu = null;
    }

    public ArrayList<String> trueque(Habitante h) {
        ArrayList<String> devuelve = new ArrayList<String>();
        ArrayList<Producto> cesto = this.getCesta();
        boolean encontrado = false;
        int i;
        if (h != null) {
            if (this.getClan() == null && h.getClan() == null) {
                super.trueque(h);
            } else if ((this.getClan() == null && h.getClan() != null) || (this.getClan() != null && h.getClan() == null)) {
                if (this.getCesta().get(0) != null) {
                    for (i = 1; i < cesto.size() && encontrado == false; i++) {
                        if (cesto.get(i).getTipo() != cesto.get(0).getTipo()) {
                            encontrado = true;
                        }
                    }
                    if (encontrado) {
                        Producto devuelto = h.haceTrueque(cesto.get(i).getTipo(), cesto.get(0));
                        this.setCesta(0, devuelto);
                    }
                } else {
                    if (this.getClan().equals(h.getClan()) && h instanceof Plebeyo) {
                        Plebeyo ple=(Plebeyo) h;
                        Bestia aux = this.bestiola;
                        this.bestiola = ple.bestiola;
                        ple.bestiola = aux;
                    } else {
                        encontrado = false;
                        if (cesto.get(cesto.size() - 1) != null) {
                            for (i = 0; i < cesto.size() - 1 && encontrado == false; i++) {
                                if (cesto.get(i).getTipo() != cesto.get(cesto.size()).getTipo()) {
                                    encontrado = true;
                                }
                            }
                            if (encontrado) {
                                Producto devuelto = h.haceTrueque(cesto.get(i).getTipo(), cesto.get(cesto.size() - 1));
                                this.setCesta(cesto.size() - 1, devuelto);
                            }
                        }
                    }
                }
            }
        }
        return devuelve;
    }
    public double tributa(Mistico m){
        ArrayList<Terreno> feudoaux=tribu.getFeudo();
        ArrayList<Producto> aux= new ArrayList<>();
        double antiviogr= getVigor();
        double devuelve=0;
        Terreno terraux=null;
        Producto recogido=null;
        if(m==tribu.getDeidad()){
            for(int i=0;i<feudoaux.size() && recogido==null;i++){
                terraux=feudoaux.get(i);
                if(terraux!=null){
                    for(int j=0; j<terraux.getFilas() && recogido==null;j++){
                        for(int z=0;z<terraux.getColumnas() && recogido==null;z++){
                            recogido=terraux.recoge(j, z);
                        }
                    }
                }
            }
            if(recogido!=null){
                aux.add(recogido);
                devuelve=m.culto(aux, getNombre());
            }
        }else{
            if(m instanceof Blanco){
                aux.add(getCesta().get(0));
                devuelve=m.culto(aux, getNombre());
            }else{
                aux.add(bestiola);
                devuelve=m.culto(aux, getNombre());
            }
        }
        if ((getVigor() + devuelve) < 100) {
            setVigor(getVigor()+devuelve);

        } else {
            setVigor(100);
        }
        devuelve=getVigor()-antiviogr;
        return devuelve;
    }
    public boolean plegaria(){
        boolean devuelve=false;
        double antivigor=getVigor();
        if(tribu!=null){
            if(tribu.getDeidad() instanceof Blanco){
                Blanco b=(Blanco) tribu.getDeidad();
                setVigor(getVigor()+b.ayuda(bestiola.getAmuleto()));
            }else{
                Oscuro b=(Oscuro) tribu.getDeidad();
                setVigor(getVigor()+b.ayuda(bestiola.getAmuleto()));
            }
           if(getVigor()>antivigor)devuelve=true;
        }
        return devuelve;
    }
    public boolean domestica(Bestia b, String n){
        boolean devuelve=false;
        if(b.domestica(this)){
            bestiola=b;
            bestiola.setNombre(n);
            devuelve=true;
        }
        return devuelve;
    }
    public int recolecta(int i, int j){
        int devuelve=0;
        Terreno terr;
        Producto recogido=null;
        if(getVigor()>0){
            terr=tribu.getFeudo().get(i);
            for(int k=0;k<terr.getFilas() && devuelve<j;k++){
                for(int z=0;z<terr.getColumnas()  && devuelve<j;z++){
                    recogido=terr.recoge(k, z);
                    if(recogido!=null){
                        addCesta(recogido);
                        devuelve++;
                    }
                    recogido=null;
                }
            }
        }
        setVigor(getVigor()-(getVigor()*0.05));
        if(devuelve==0)devuelve=-1;
        return devuelve;
    }
    public ArrayList<Producto> vasallaje(Terreno t) {
        Producto aux;
        ArrayList<Producto> devuelve=new ArrayList<>();
        if(getVigor()>0){
            for(int i=0;i<t.getFilas();i++){
                for(int j=0;j<t.getColumnas();j++ ){
                    aux=t.recoge(i, j);
                    if(aux!=null){
                        devuelve.add(aux);
                    }
                    aux=null;
                }
            }
        }
        setVigor(getVigor()-(getVigor()*0.1));
        return devuelve;
    }
    public int alimenta(int i){

    }
    public boolean tutela(Guerrero g){
        boolean devuelve=false;
        if(g.getSirviente()==null && patrono==null){
            g.acoge(this);
            patrono=g;
            devuelve=true;
        }
        return devuelve;
    }
    public String libera(){
        String devuelve="";
        if(patrono!=null){
            devuelve=patrono.getNombre();
            patrono = null;
        }
        return devuelve;
    }
    public void setBestia(Bestia b){
        bestiola=b;
    }
    public Bestia getBestiola(){
        return bestiola;
    }
    public Guerrero getPatrono(){
        return patrono;
    }
    public boolean esAcogido(Clan c){
        if(c.getNombre().equalsIgnoreCase(this.getClan()) && tribu==null){
            tribu=c;
            return true;
        }
        return false;
    }
    public boolean esDesterrado(){
        boolean devuelve=false;
        if(tribu!=null){
            tribu=null;
            devuelve=true;
        }
        return devuelve;
    }
    public void cambiaClan(String n){
        String[] separada=getNombre().split(" ");
        setNombre(separada[0]+n);
    }
    public Clan getTribu(){
        return tribu;
    }
}
