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
                        Bestia aux = this.bestiola;
                        this.bestiola = h.bestiola;
                        h.bestiola = aux;
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

    }
}
