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
}
