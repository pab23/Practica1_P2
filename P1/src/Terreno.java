import java.util.ArrayList;
public class Terreno {
    private Producto[][] parcelas;
    private int fil, col;

    public Terreno(int i, int j){
        if(i<=0){
            fil=3;
        }else{
            fil=i;
        }
        if(j<=0){
            col=2;
        }else{
            col=j;
        }
        parcelas=new Producto[fil][col];
    }
    public boolean genera(int i,int j,double d,int k, String s){
        boolean devuelve=false;
        if(parcelas[i][j]==null && i<parcelas.length && j<parcelas[0].length){
            Producto p=new Producto( d, k, s);
            parcelas[i][j]=p;
            devuelve=true;
        }
        return devuelve;
    }
    public Producto recoge(int i,int j){
        Producto devuelve=null;
        if(i<parcelas.length && j<parcelas[0].length) {
            if (parcelas[i][j] != null && parcelas[i][j].getTipo() != 5) {
                devuelve = parcelas[i][j];
                parcelas[i][j] = null;
                devuelve.setColocado(false);
            }
        }
        return devuelve;
    }
    public double destruye( int i, int j){
        double devuelve=0;
        if(i<parcelas.length && j<parcelas[0].length) {
            if (parcelas[i][j] != null && parcelas[i][j].getTipo() == 5) {
                devuelve = parcelas[i][j].getPeso();
                parcelas[i][j] = null;
            }
        }
        return devuelve;
    }
    public boolean coloca( Producto p, int i, int j){
        boolean devuelve=false;
        if(i<fil && j<col) {
            if (parcelas[i][j] == null && p.getColocado() == false) {
                p.setColocado(true);
                parcelas[i][j] = p;
                devuelve = true;
            }
        }
        return devuelve;
    }
    public int consultaTipo(int i, int j){
        int devuelve=-1;
        if(i<parcelas.length && j<parcelas[0].length){
            if (parcelas[i][j]!=null) {
                devuelve=parcelas[i][j].getTipo();
            }
        }
        return devuelve;
    }
    public double consultaPeso(int i, int j){
        double devuelve=-1;
        if(i<parcelas.length && j<parcelas[0].length){
            if (parcelas[i][j] != null) {
                devuelve = parcelas[i][j].getPeso();
            }
        }
        return devuelve;
    }
    public int existencias(int i){
        int cant=0;
        for( int j=0;j<parcelas.length;j++){
            for(int z=0;z<parcelas[0].length;z++) {
                if (parcelas[j][z].getTipo() == i) {
                    cant++;
                }
            }
        }
        return cant;
    }
    public ArrayList<Integer> calculaDemanda(){
        ArrayList<Integer> devuelve=new ArrayList<Integer>();
        double suma=0;
        ArrayList<Integer> tipos=new ArrayList<Integer>();
        for(int i=0;i<fil;i++){                 /*Coge los diferentes tipos de la matriz*/
            for(int j=0;j<col;j++){
                for(int z=0;z<6;z++){
                    if(tipos.get(z)!=null){
                        if(parcelas[i][j].getTipo()!=tipos.get(z) || tipos.size()==0){
                            tipos.add(parcelas[i][j].getTipo());
                        }
                    }
                }
            }
        }
        for(int i=0;i<tipos.size();i++){
            suma=suma+this.existencias(tipos.get(i));           /*Suma todas las exsitencias de todos los productos del terreno*/
        }
        suma=Math.sqrt(suma);
        if(tipos.size()==1){
            devuelve.add(100);
        }else{
            for(int i=0;i<tipos.size();i++){
                devuelve.add((int)((tipos.get(i)/suma)*100));
            }
        }
        return devuelve;
    }
    public int getFilas(){
        return parcelas.length;
    }
    public int getColumnas(){
        return col;
    }
}
