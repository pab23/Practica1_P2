import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class Habitante {
    private String nombre;
    private ArrayList<Producto> cesta;
    private double vigor;
    private char sexo;
    private static ArrayList<Habitante> poblacion;

    public Habitante(String s, char c){
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

        if(i!=5) {
            for (int j = 0; j < t.getFilas() && vigor>0 && devuelve==false; j++) {
                for (int z = 0; z < t.getColumnas() && vigor>0 && devuelve==false; z++) {
                    if(t.consultaTipo( j, z)==i){
                        if(t.consultaPeso( j, z)<vigor){
                            vigor=vigor-(t.consultaPeso( j, z)*0.1);
                            cesta.add(t.recoge( j, z));
                           devuelve=true;
                        }
                    }else{
                        vigor=vigor-0.25;
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
        for(int i=0;i<cesta.size();i++){
            tipos[cesta.get(i).getTipo()]++;
        }
        for(int i=0;i<tipos.length;i++){
            if(tipos[i]==0){
                return i;
            }else if(aux==0 && aux>tipos[i]){
                aux=tipos[i];
                tipo=i;
            }

        }
        return tipo;
    }
    public double vigoriza(String s){
        boolean encontrado=false;
        int i=0;
        int j=0;
        double devuelve;                        /*Busca comida*/
        for( i=0;i<cesta.size()&& encontrado==false;i++){
            if(cesta.get(i).getTipo()==2 || cesta.get(i).getTipo()==3){
                if(cesta.get(i).equals(s)){
                    encontrado=true;
                }
            }
        }
        if(encontrado==true){                   /*Si encuentra comida busca madera*/
            encontrado=false;
            for( j=0;j<cesta.size() && encontrado==false;j++){
                if(cesta.get(j).getTipo()==4){
                    encontrado=true;
                }
            }
        }
        if(encontrado==true){                   /* Encuentra nadera*/
            if((vigor+cesta.get(i).valorProducto())<100){
                vigor=vigor+cesta.get(i).valorProducto();
            }else{
                vigor=100;
            }
            cesta.remove(j);
        }else {
            if((vigor+(cesta.get(i).valorProducto()/2))<100){
                vigor=vigor+(cesta.get(i).valorProducto()/2);
            }else{
                vigor=100;
            }
        }
        cesta.remove(i);
        devuelve=100-vigor;
        return devuelve;
    }
    public Producto edifica(String s){
        boolean encontrado=false;
        int i;
        for(i=0;i<cesta.size() && encontrado==false;i++){       /*Busca piedra*/
            if(cesta.get(i).getTipo()==3){
                encontrado=true;
            }
        }
        if(encontrado==true){                                   /* Si encuentra piedra crea eidficio*/
            Producto p=new Producto((cesta.get(i).getPeso()/2), 5, s);
            cesta.remove(i);
            return p;
        }else return null;
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
                    aux = j;
                }
            }
            for (int z = 0; z < cesta.size() && p == null; z++) {
                if (cesta.get(z).getTipo() == aux) {
                    p = cesta.get(z);
                }
            }
            necesita = this.estudio();
            Producto pdev = h.haceTrueque(necesita, p);
            if (pdev != null) {
                cesta.add(pdev);
                devuelve.add(pdev.getNombre());
                devuelve.add(p.getNombre());
            }
        }
        return devuelve;
    }
    public Producto haceTrueque(int i, Producto p){
        Producto h=null;
        if(i!=0 && p!=null){
            for(int j=0;j<cesta.size() && p==null;j++){
                if(cesta.get(j).getTipo()== i){
                    h=cesta.remove(j);
                    cesta.add(j, p);
                }
            }
        }
        return h;
    }
    public double tributa( Mistico m){
        ArrayList<Producto> agasaja=new ArrayList<Producto>();
        double antiguo=vigor;
        for(int i=0;i<cesta.size();i++){
            if(agasaja.get(cesta.get(i).getTipo())==null){
                agasaja.add(cesta.get(i).getTipo(),cesta.get(i));
            }
        }
        if((vigor+m.culto(agasaja, nombre))<100) {
            vigor = vigor + m.culto(agasaja, nombre);
        }else{
            vigor=100;
        }
        return (vigor-antiguo);
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
        if(s.equalsIgnoreCase(this.getClan()))devuelve=true;
        return devuelve;
    }
    public static int getHombres(){
        int hombres=0;
        for(int i=0;i<poblacion.size();i++){
            if(poblacion.get(i).sexo=='H')hombres++;
        }
        return hombres;
    }
    public static int getMujeres(){
        return poblacion.size()-getHombres();
    }
    public static ArrayList<Habitante> getPoblacion(){
        return poblacion;
    }

}
