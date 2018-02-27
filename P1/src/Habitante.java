import java.util.ArrayList;
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
}
