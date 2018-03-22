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
    public ArrayList<String> trueue(Habitante h){
        if(h!=null) {
            if (this.getClan() == null && h.getClan() == null) {
                super.trueque(h);
            }else if((this.getClan()==null && h.getClan()!=null)||(this.getClan()!=null && h.getClan()==null)){

            }else if(h){
                if (this.getClan().equals(h.getClan()) && this.bestiola != null && h.bestiola != null) {

                }
            }
            }
        }
    }

}
