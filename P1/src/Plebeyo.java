//46087864 AMOROS BECERRA, PABLO
import java.util.ArrayList;

public class Plebeyo extends Habitante {
    private Bestia bestiola;
    private Guerrero patrono;
    private Clan tribu;
    public Plebeyo (String s, char c){
        super(s, c);
        bestiola=null;
        patrono=null;
        tribu=null;
    }
    public ArrayList<String> trueque(Habitante h){
        ArrayList<String> devuelve=new ArrayList<String>();
        boolean encontrado=false;
        int tipopas, i;
        if(h!=null) {
            if (this.getClan() == null && h.getClan() == null) {
                super.trueque(h);
            }else if((this.getClan()==null && h.getClan()!=null)||(this.getClan()!=null && h.getClan()==null)){
                if(this.getCesta().get(0)!=null){
                    ArrayList<Producto> cesto=this.getCesta();
                    for(i=1;i<cesto.size() && encontrado==false;i++){
                        if(cesto.get(i).getTipo()!=cesto.get(0).getTipo()){
                            tipopas=cesto.get(i).getTipo();
                            encontrado=true;
                        }
                    }
                    if(encontrado){
                        haceTrueque(cesto.get(i).getTipo(), cesto.get(0));
                    }
            }else if(h){
                if (this.getClan().equals(h.getClan()) && this.bestiola != null && h.bestiola != null) {

                }
            }
            }
        }
    }

}
