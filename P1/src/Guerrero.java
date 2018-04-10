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

}
