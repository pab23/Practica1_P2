public class Producto {
    private int tipo;
    private double peso;
    private boolean colocado;
    private String nombre;

    public Producto(double p,int i, String s){
        if(p>0){
            peso=p;
        }else{
            peso=1;
        }
        if(i<=6 && i>0){
            tipo=i;
        }else{
            tipo=6;
        }
        if(s!=null){
            nombre=s;
        }else{
            nombre="Ordinario";
        }
        colocado=false;
    }
    public double valorKilo(){
        int suma=0;
        double res=0;
        char[] aux=nombre.toCharArray();
        for(int i=0;i<aux.length;i++){
            suma=suma+(aux[i]-97);
        }
        res=suma/(double)nombre.length();
        return res;
    }
    public double valorProducto(){
        return (this.valorKilo()*peso);
    }
    public int transforma(Producto p,int i){
        int devuelve=-1;
        if (p != null) {
            if (tipo == 6 && i <= 6 && i > 0 && p.tipo != i) {
                p.tipo = i;
                devuelve = 1;
            }
        }
        return devuelve;
    }
    public int getTipo(){
        return tipo;
    }
    public boolean getColocado(){
        return colocado;
    }
    public void setColocado(boolean b){
        colocado=b;
    }
    public double getPeso(){
        return peso;
    }
    public String getNombre(){
        return nombre;
    }
}
