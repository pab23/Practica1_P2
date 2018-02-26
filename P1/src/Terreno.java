import java.util.ArrayList;
public class Terreno {
    private Producto[][] parcelas;

    public Terreno(int i, int j){
        if(i<0){
            i=3;
        }
        if(j<0){
            j=2;
        }
        parcelas=new Producto[i][j];
    }
    public boolean genera(int i,int j,double d,int k, String s){
        boolean devuelve=false;
        if(parcelas[i][j]==null){
            Producto p=new Producto( d, k, s);
            parcelas[i][j]=p;
            devuelve=true;
        }
        return devuelve;
    }
    public Producto recoge(int i,int j){
        Producto devuelve=null;
        if(parcelas[i][j]!=null && parcelas[i][j].getTipo()!=5){
            devuelve=parcelas[i][j];
            parcelas[i][j]=null;
            devuelve.setColocado(false);
        }
        return devuelve;
    }
    public double destruye( int i, int j){
        double devuelve=0;
        if(parcelas[i][j]!=null && parcelas[i][j].getTipo()==5){
            devuelve=parcelas[i][j].getPeso();
            parcelas[i][j]=null;
        }
        return devuelve;
    }
    public boolean coloca( Producto p, int i, int j){
        boolean devuelve=false;
        if(parcelas[i][j]==null && p.getColocado()==false){
            p.setColocado(true);
            parcelas[i][j]=p;
            devuelve=true;
        }
        return devuelve;
    }
    public int consultaTipo(int i, int j){
        int devuelve=-1;
        if (parcelas[i][j]!=null) {
            devuelve=parcelas[i][j].getTipo();
        }
        return devuelve;
    }
    public double consultaPeso(int i, int j){
        double devuelve=-1;
        if (parcelas[i][j]!=null) {
            devuelve=parcelas[i][j].getPeso();
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
        ArrayList<Integer> devuelve;
    }
}
