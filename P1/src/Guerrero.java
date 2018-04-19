import java.util.ArrayList;

public class Guerrero extends Habitante {
    private Plebeyo sirviente;
    private ArrayList<Producto> armamento;
    private Clan tribu;

    public Guerrero(String s, char c){
        super(s, c);
        sirviente=null;
        armamento=new ArrayList<>();
        tribu=null;
    }
    public ArrayList<String> trueque(Habitante h){
        ArrayList<String> devuelve=null;
        if(h instanceof Guerrero){
            devuelve= new ArrayList<>();
            armamento.add(intercambio(armamento.get(0)));
            devuelve.add(armamento.get(armamento.size()-1).getNombre());
            devuelve.add(armamento.get(0).getNombre());
            armamento.remove(0);
        }
        return devuelve;
    }
    public double tributa(Mistico m){
        int posi=0;
        ArrayList<Producto> fuertes=new ArrayList<>();
        double valormax=0;
        double devuelve=0;
        double cultodev=0;
        double antivig=getVigor();
        if(m==tribu.getDeidad()){
            devuelve=super.tributa(m);
        }else{
            for(int i=0;i<armamento.size();i++){
                if(valormax==0 || valormax<armamento.get(i).valorProducto()){
                    valormax=armamento.get(i).valorProducto();
                    posi=i;
                }
            }
            fuertes.add(armamento.get(posi));
            ArrayList<Producto> armaux=armamento;
            armaux.remove(posi);
            posi=0;
            valormax=0;
            for(int i=0;i<armaux.size();i++){
                if(valormax==0 || valormax<armaux.get(i).valorProducto()){
                    valormax=armaux.get(i).valorProducto();
                    posi=i;
                }
            }
            fuertes.add(armaux.get(posi));
            cultodev=m.culto(fuertes, this.getNombre());
            if ((getVigor() + cultodev) <= 100) {
                setVigor(getVigor()+cultodev);

            } else {
                setVigor(100);
            }
            devuelve=getVigor()-antivig;
        }
        return devuelve;
    }
    public boolean plegaria(){
        ArrayList<Producto> cestaux=getCesta();
        Producto mistic=null;
        double antivigor=getVigor();
        boolean encontrado=false;
        if(tribu!=null){
            if(tribu.getDeidad() instanceof Blanco){
                for(int i=0;i<cestaux.size() && encontrado==false;i++){
                    if(cestaux.get(i).getTipo()==6){
                        encontrado=true;
                        mistic=cestaux.get(i);
                    }
                }
                encontrado=false;
                setVigor(getVigor()+tribu.getDeidad().ayuda(mistic));

            }
        }
        if(getVigor()>antivigor)return true;
        else return false;
    }
    public Producto intercambio(Producto p){
        Producto devuelve=null;
        if(p.getTipo()==3){
            devuelve=cogeProduc(4);
            addCesta(p);
        }else if(p.getTipo()==4){
            devuelve=cogeProduc(3);
            addCesta(p);
        }
        return devuelve;
    }
    public boolean acoge(Habitante h){
        boolean devuelve=false;
        if(h instanceof Plebeyo){
            Plebeyo aux=(Plebeyo) h;
            if(aux.getPatrono()==null){
                sirviente=aux;
                aux.tutela(this);
                devuelve=true;
            }
        }
        return devuelve;
    }
    public int recolecta(int i){

    }



    public Plebeyo getSirviente(){
        return sirviente;
    }
    public boolean esAcogido(Clan c){
        if(c.getNombre().equalsIgnoreCase(this.getClan()) && tribu==null){
            tribu=c;
            return true;
        }
        return false;
    }

    public Producto cogeProduc(int tipo) {//Este metodo coge el primer producto del tipo pedido
        Producto aux = null;
        for (int i = 0; i < getCesta().size() && aux == null; i++) {
            if (getCesta().get(i).getTipo() == tipo) {
                aux = getCesta().get(i);
                getCesta().remove(i);

            }
        }
        return aux;
    }
    public boolean esDesterrado(){
        boolean devuelve=false;
        if(tribu!=null){
            tribu=null;
            devuelve=true;
        }
        return devuelve;
    }

    public Clan getTribu(){
        return tribu;
    }
}
