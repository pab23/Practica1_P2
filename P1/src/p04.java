/**
* @author Alicia Garrido Alenda
* Se crea una serie de productos y un terreno. Se colocan algunos de estos productos.
* Se crea un mistico y se invoca regenera con este terreno y uno de los productos no
* colocados, mostrando por pantalla lo que devuelve el metodo. Finalmente se muestra 
* el contenido de los tributos del mistico y el tipo de producto que hay en cada
* parcela del terreno.
*/
import java.text.*;
import java.util.*;
public class p04{
   
   private static String mrf(double db){
     Locale lengua=new Locale("en");
     DecimalFormatSymbols chars=new DecimalFormatSymbols(lengua);
     DecimalFormat formato=new DecimalFormat("0.000",chars);

     return(formato.format(db).toString());
   }

   private static int[] creaTipos(){
     int[] tipos={1,2,3,4,5,6}; //Vegetal,Animal,Piedra,Madera,Edificado,Filosofal
     return tipos;
   }
   
   private static String[][] creaNombres(){
     String[][] names={
     {"brocoli", "berza", "espinaca"},
     {"Vaca","Cerdo","Pollo"},
     {"agata","amatista","cuarzo"},
     {"caoba","teca","mindi"},
     {"casa","casona","pazo"},
     {"rodocrosita","aventurina","actinolita"},
     };
     return names;
   }
   
   private static ArrayList<Producto> creaProductos(String[][] names,int[] tipos,double peso){
     ArrayList<Producto> creados=new ArrayList<Producto>();
     int tp=0;
     Producto creado=null;
     for(int i=0;i<names.length;i++){
       for(int j=0;j<names[i].length;j++){
         creado=new Producto(peso,tipos[tp],names[i][j]);
         creados.add(creado);
         peso+=0.2;
       }
       tp++;
       if(tp>=tipos.length) tp=0;
     }
     return creados;
   }
   
   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p04.mrf(p.getPeso())+"; colocado: "+p.getColocado());
     }
   }
   
   public static void main(String[] args){
     int[] tipos=p04.creaTipos();
     String[][] names=p04.creaNombres();
     double pinicial=1.5;
     boolean placed=false;
     int k=0,m=0;
     ArrayList<Producto> existencias=p04.creaProductos(names,tipos,pinicial);
     Terreno solar=new Terreno(3,3);
     Mistico olag=new Mistico();
     System.out.println("Productos creados -> "+existencias.size());
     for(int i=0;i<solar.getFilas()-1;i++){
      for(int j=0;j<solar.getColumnas()-1;j++){
       if(k<existencias.size()){
         Producto actual=existencias.get(k);
         placed=solar.coloca(actual,i,j);
         System.out.print("colocado ("+placed+") el producto ");
         p04.veoDatos(actual);
         k++;
       }
      }
     }
     System.out.println("SOLAR:");
     for(int i=0;i<solar.getFilas();i++){
      for(int j=0;j<solar.getColumnas();j++){
        System.out.print(solar.consultaTipo(i,j)+" ");
      }
      System.out.println();
     }
     int regenerados=olag.regenera(solar,existencias.get(k));
     System.out.println("parcelas regeneradas por el mistico: "+regenerados);
     System.out.println("SOLAR REGENERADO:");
     for(int i=0;i<solar.getFilas();i++){
      for(int j=0;j<solar.getColumnas();j++){
        System.out.print(solar.consultaTipo(i,j)+" ");
      }
      System.out.println();
     }
     ArrayList<Producto> tiru=olag.getTributos();
     System.out.println("TRIBUTOS");
     for(int i=0;i<tiru.size();i++)
       p04.veoDatos(tiru.get(i));
       
   }
}
