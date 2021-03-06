//46087864 AMOROS BECERRA, PABLO

import java.util.ArrayList;

public class Terreno {
    private Producto[][] parcelas;
    private int fil, col;
    private Clan miclan;

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
        miclan=null;
        parcelas=new Producto[fil][col];
    }
    public boolean genera(int i,int j,double d,int k, String s){
        boolean devuelve=false;
        if(i<fil && j<col && k!=5 && s!=null){
            if(parcelas[i][j]==null){
                Producto p=new Producto( d, k, s);
                parcelas[i][j]=p;
                devuelve=true;
            }
        }
        return devuelve;
    }
    public Producto recoge(int i,int j){
        Producto devuelve=null;
        if(i<fil && j<col && j>=0 && i>=0) {
            if (parcelas[i][j] != null && parcelas[i][j].getTipo() != 5) {
                devuelve = parcelas[i][j];
                parcelas[i][j] = null;
                devuelve.setColocado(false);
            }
        }
        return devuelve;
    }
    public double destruye( int i, int j ){
        double devuelve=0;
        if(i<fil && j<col && j>=0 && i>=0) {
            if (parcelas[i][j] != null && parcelas[i][j].getTipo() == 5) {
                devuelve = parcelas[i][j].getPeso();
                parcelas[i][j] = null;
            }
        }
        return devuelve;
    }
    public boolean coloca( Producto p, int i, int j){
        boolean devuelve=false;
        if(i<fil && j<col && j>=0 && i>=0 && p!=null) {
            if (parcelas[i][j] == null && p.getColocado() == false) {
                parcelas[i][j] = p;
                p.setColocado(true);
                devuelve = true;
            }
        }
        return devuelve;
    }
    public int consultaTipo(int i, int j){
        int devuelve=-1;
        if(i<fil && j<col && j>=0 && i>=0){
            if (parcelas[i][j]!=null) {
                devuelve=parcelas[i][j].getTipo();
            }
        }
        return devuelve;
    }
    public double consultaPeso(int i, int j){
        double devuelve=-1;
        if(i<fil && j<col && j>=0 && i>=0){
            if (parcelas[i][j] != null) {
                devuelve = parcelas[i][j].getPeso();
            }
        }
        return devuelve;
    }
    public int existencias(int i){
        int cant=0;
        if(i<=6 && i>0) {
            for (int j = 0; j < fil; j++) {
                for (int z = 0; z < col; z++) {
                    if (parcelas[j][z].getTipo() == i) {
                        cant++;
                    }
                }
            }
        }
        return cant;
    }
    public ArrayList<Integer> calculaDemanda(){
        ArrayList<Integer> devuelve=new ArrayList<Integer>();
        double suma=0;
        ArrayList<Integer> tipos=new ArrayList<Integer>();
        for(int i=1;i<=6;i++){
            if(existencias(i)!=0){
                tipos.add(existencias(i));
            }
        }
        for(int i=0;i<tipos.size();i++){
            suma=suma+(int)Math.pow(tipos.get(i),2);/*Suma todas las exsitencias de todos los productos del terreno*/
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
    public String consultaNombre(int i, int j){
        return parcelas[i][j].getNombre();
    }
    public void setClan(Clan c){
        miclan=c;
    }
    public Clan getClan(){return miclan;}
    public int getFilas(){
        return fil;
    }
    public int getColumnas(){
        return col;
    }
}
