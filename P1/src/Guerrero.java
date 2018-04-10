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

}
