/**
* @author Alicia Garrido Alenda
* Se crea una serie de productos y un terreno. Se colocan los productos en el terreno.
* Se crean dos habitantes que recolectan cada uno unos cuantos productos de este terreno.
* Se muestra el contenido de las cestas de ambos habitantes y se invoca trueque de uno 
* de ellos con el otro, mostrando por pantalla lo que devuelve el metodo y el estado actual
* de ambas cestas.
*/
import java.text.*;
import java.util.*;
public class p05{
   
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
     {"brocoli", "berza", "espinaca","lechuga romana","comote", "zanahoria"},
     {"Vaca","Cerdo","Pollo"},
     {"agata","amatista","cuarzo"},
     {"caoba","teca","mindi"},
     {"casa","casona","pazo"},
     {"rodocrosita","aventurina","actinolita"}
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
         peso+=0.15;
       }
       tp++;
       if(tp>=tipos.length) tp=0;
     }
     return creados;
   }
   
   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p05.mrf(p.getPeso())+"; colocado: "+p.getColocado());
     }
   }
   
   private static void muestraCesta(ArrayList<Habitante> pueblo){
     Habitante change=null;
     ArrayList<Producto> cesta=null;
     for(int i=0;i<pueblo.size();i++){
       change=pueblo.get(i);
       cesta=change.getCesta();
       System.out.println("Contenido de la cesta de "+change.getNombre());
       for(int j=0;j<cesta.size();j++)
         p05.veoDatos(cesta.get(j));
     }
   }
   
   public static void main(String[] args){
     int[] tipos=p05.creaTipos();
     String[][] names=p05.creaNombres();
     double pinicial=1.5;
     boolean placed=false,get=false;
     int k=0,m=0,tipo=0;
     ArrayList<Producto> existencias=p05.creaProductos(names,tipos,pinicial);
     Terreno solar=new Terreno(5,4);
     ArrayList<Habitante> pueblo=new ArrayList<Habitante>();
     pueblo.add(new Habitante("James Mackay",'H'));
     pueblo.add(new Habitante("Muriel Macleod",'M'));
     Habitante change=pueblo.get(m);
     System.out.println("Productos creados -> "+existencias.size());
     for(int i=0;i<solar.getFilas();i++){
      for(int j=0;j<solar.getColumnas();j++){
       if(k<existencias.size()){
         Producto actual=existencias.get(k);
         placed=solar.coloca(actual,i,j);
         System.out.print("colocado ("+placed+") el producto ");
         p05.veoDatos(actual);
         k++;
       }
      }
     }
     placed=false;
     for(int i=0;i<solar.getFilas();i++){
      for(int j=0;j<solar.getColumnas();j++){
        tipo=solar.consultaTipo(i,j);
        get=change.recolecta(solar,tipo);
      }
      if(m==0)
        m=1;
      else
        m=0;
      change=pueblo.get(m);
     }
     tipo=solar.consultaTipo(solar.getFilas(),solar.getColumnas());
     get=change.recolecta(solar,tipo);
     p05.muestraCesta(pueblo);
     ArrayList<String> cambio=pueblo.get(1).trueque(pueblo.get(0));
     if(cambio!=null)
       System.out.println(cambio);
     else
       System.out.println("No hay nada que hacer");
     p05.muestraCesta(pueblo);  
   }
}
