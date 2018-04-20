//46087864 AMOROS BECERRA, PABLO

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public abstract class Habitante {
    private String nombre;
    private ArrayList<Producto> cesta;
    private double vigor;
    private char sexo;
    private static ArrayList<Habitante> poblacion;

    public  Habitante(String s, char c){
        nombre=s;
        if(c!='H' && c!='M'){
            sexo='H';
        }else{
            sexo=c;
        }
        vigor=100;
        cesta=new ArrayList<Producto>();
        if(poblacion==null){
            poblacion=new ArrayList<Habitante>();
        }
        poblacion.add(this);
    }
    public boolean recolecta(Terreno t, int i){
        boolean devuelve=false;
        if(i!=5 && t!=null) {
            for (int j = 0; j < t.getFilas() && devuelve==false; j++) {
                for (int z = 0; z < t.getColumnas() && devuelve==false; z++) {
                    if(vigor>=0.25) {
                        if (t.consultaTipo(j, z) == i) {
                            if (0.1 * (t.consultaPeso(j, z)) < vigor) {
                                vigor = vigor - (t.consultaPeso(j, z) * 0.1);
                                cesta.add(t.recoge(j, z));
                                if (cesta.get(cesta.size() - 1) != null) {
                                    cesta.get(cesta.size() - 1).setColocado(true);
                                }
                                devuelve = true;
                            } else {
                                vigor = vigor - 0.25;
                            }
                        } else {
                            vigor = vigor - 0.25;
                        }
                    }else{
                        vigor=0;
                    }
                }
            }
        }
        return devuelve;
    }
    public int estudio(){
        int[] tipos=new int[6];
        int aux=0;
        int tipo=0;
        for(int i=0;i<cesta.size();i++) {
            if (cesta.get(i) != null) {
                tipos[cesta.get(i).getTipo()-1]++;
            }
        }
        for(int i=0;i<tipos.length;i++){
            if(tipos[i]==0 && i!=4){
                return (i+1);
            }else if((aux==0 || aux>tipos[i]) && i!=4){
                aux=tipos[i];
                tipo=i+1;
            }

        }
        return tipo;
    }
    public double vigoriza(String s){
        boolean encontrado=false;
        int i=0;
        int j=0;
        double valor=0;
        double devuelve;
        if (s!=null) {/*Busca comida*/
            for (i = 0; i < cesta.size() && encontrado == false; i++) {
                if (cesta.get(i).getTipo() == 2 || cesta.get(i).getTipo() == 1) {
                    if (cesta.get(i).getNombre().equalsIgnoreCase(s)) {
                        encontrado = true;
                    }
                }
            }
            if (encontrado == true) {/*Si encuentra comida busca madera*/
                valor=cesta.get(i-1).valorProducto();
                encontrado = false;
                for (j = 0; j < cesta.size() && encontrado == false; j++) {
                    if (cesta.get(j).getTipo() == 4) {
                        encontrado = true;
                    }
                }
                if (encontrado == true) {                   /* Encuentra nadera*/
                    if ((vigor + valor) < 100) {
                        vigor = vigor + valor;
                    } else {
                        vigor = 100;
                    }
                    cesta.remove(j-1);
                } else {
                    if (vigor + (valor / 2) < 100) {
                        vigor = vigor + (valor / 2);
                    } else {
                        vigor = 100;
                    }
                }
                cesta.remove(i-1);
            }
        }
        devuelve = 100 - vigor;
        return devuelve;
    }
    public Producto edifica(String s){
        Producto p=null;
        for(int i=0;i<cesta.size();i++){       /*Busca piedra*/
            if(cesta.get(i).getTipo()==3){
                p=new Producto((cesta.get(i).getPeso()/2), 5, s);
                cesta.remove(i);
                return p;
            }
        }
        return null;
    }
    public ArrayList<String> trueque(Habitante h){
        int necesita=0;
        int aux=0;
        Producto p=null;
        ArrayList<String> devuelve= new ArrayList<String>();
        int[] suma=new int[6];
        if (h!=null) {
            for (int i = 0; i < cesta.size(); i++) {
                if (cesta.get(i) != null) {
                    suma[cesta.get(i).getTipo() - 1]++;

                }
            }
            for (int j = 0; j < suma.length; j++) {
                if (necesita == 0 || necesita < suma[j]) {
                    necesita = suma[j];
                    aux = j+1;
                }
            }
            for (int z = 0; z < cesta.size() && p == null; z++) {
                if(cesta.get(z)!=null) {
                    if (cesta.get(z).getTipo() == aux) {
                        p = cesta.get(z);
                    }
                }
            }
            necesita = this.estudio();
            Producto pdev = h.haceTrueque(necesita, p);
            if (pdev != null) {
                cesta.remove(p);
                devuelve.add(pdev.getNombre());
                devuelve.add(p.getNombre());
                cesta.add(pdev);
            }
        }
        return devuelve;
    }
    public Producto haceTrueque(int i, Producto p){
        Producto h=null;
        if(p!=null){
            if(i>0 && i<=6) {
                for (int j = 0; j < cesta.size() && h == null; j++) {
                    if (cesta.get(j).getTipo() == i) {
                        h = cesta.set(j, p);
                    }
                }
            }
        }
        return h;
    }
    public double tributa( Mistico m) {
        ArrayList<Producto> agasaja = new ArrayList<Producto>();
        Producto []aux=new Producto[6];
        double cultodev=0;
        double antiguo = vigor;
        if (m != null){
            for (int i = 0; i < cesta.size(); i++) {
                if(cesta.get(i).getTipo()>0 ) {
                    if (aux[cesta.get(i).getTipo() - 1] == null) {
                        aux[cesta.get(i).getTipo() - 1] = cesta.get(i);
                    }
                }else{
                    aux[cesta.get(i).getTipo()-1]= cesta.get(i);
                    }

                }
            }
            for(int i=0;i<aux.length;i++){
                if(aux[i]!=null){
                    agasaja.add(aux[i]);
                }
            }
            cultodev=m.culto(agasaja, nombre);
        if ((vigor + cultodev) <= 100) {
            vigor+=cultodev;
            return cultodev;
        } else {
            vigor=100;
            return 0;
        }

    }
    public int plegaria(Mistico m, Terreno t){
        int devuelve=0;
        int animal=0;
        int vegetal=0;
        ArrayList<Integer> filas= new ArrayList<Integer>();
        ArrayList<Integer> columnas= new ArrayList<Integer>();
        if( m!=null && t!=null){
            for(int i=0;i<cesta.size();i++){
                if(cesta.get(i).getTipo()==1)animal++;
                if(cesta.get(i).getTipo()==2)vegetal++;
            }
            for(int i=0;i<t.getFilas();i++){
                for(int j=0;j<t.getColumnas();j++){
                    if(t.consultaTipo(i, j)==5){
                        filas.add(i);
                        columnas.add(j);
                    }
                }
            }
            if (animal >= vegetal) {
                m.transforma(t, 2);
            } else{
                m.transforma(t, 1);
            }
            for(int i=0;i<filas.size();i++) {
                cesta.add(t.recoge(filas.get(i), columnas.get(i)));
                devuelve++;
            }
        }
        return devuelve;
    }
    public String getNombre(){
        return nombre;
    }
    public double getVigor(){
        return vigor;
    }
    public String getClan(){
        String clan=null;
        String[] separado=nombre.split(" ");
        if(separado.length>1){
            if(separado[1]!=null){
                clan=separado[1];
            }
        }
        return clan;
    }
    public boolean perteneceClan(String s){
        boolean devuelve=false;
        if(s!=null) {
            if (s.equalsIgnoreCase(this.getClan())) devuelve = true;
        }
        return devuelve;
    }
    public ArrayList<Producto> getCesta(){
        return cesta;
    }
    public static int getHombres(){
        int hombres=0;
        for(int i=0;i<poblacion.size();i++){
            if(poblacion.get(i).sexo=='H')hombres++;
        }
        return hombres;
    }
    public void setCesta(int i, Producto p){
        cesta.set(i, p);
    }
    public void addCesta(Producto p){cesta.add(p);}
    public void addPrimer(Producto p){cesta.add(0, p);}
    public void setVigor(double b){vigor=b;}
    public void setNombre(String n){nombre=n;}
    public static int getMujeres(){
        return poblacion.size()-getHombres();
    }
    public static ArrayList<Habitante> getPoblacion(){
        return poblacion;
    }
    public abstract boolean esAcogido(Clan c);
    public abstract boolean esDesterrado();
    public abstract void cambiaClan(String s);
    public abstract Clan getTribu();
}
