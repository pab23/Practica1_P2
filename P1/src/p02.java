/**
* @author Alicia Garrido Alenda
* Se crea una serie de productos y un terreno. Se intenta colocar los productos en el
* terreno, de manera que unas veces se consigue y otras no. Se invoca calculaDemanda del
* terreno, mostrando por pantalla lo que devuelve.
*/
import java.text.*;
import java.util.*;
public class p02{
   
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
     for(int j=0;j<names[0].length;j++){
       for(int i=0;i<names.length;i++){
         creado=new Producto(peso,tipos[tp],names[i][j]);
         creados.add(creado);
         peso+=0.2;
         tp++;
         if(tp>=tipos.length) tp=0;
       }
     }
     return creados;
   }
   
   private static void veoDatos(Producto p){
     if(p!=null){
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p02.mrf(p.getPeso())+"; colocado: "+p.getColocado());
     }
   }
   
   public static void main(String[] args){
     int[] tipos=p02.creaTipos();
     String[][] names=p02.creaNombres();
     double pinicial=1.5;
     boolean placed=false;
     int k=0,m=0;
     ArrayList<Producto> existencias=p02.creaProductos(names,tipos,pinicial);
     Terreno solar=new Terreno(k,m);
     System.out.println("Productos creados -> "+existencias.size());
     for(int i=0;i<existencias.size();i++){
       if(k>=solar.getFilas()) k=0;
       if(m>=solar.getColumnas()){ m=0;k++;}
       Producto actual=existencias.get(i);
       placed=solar.coloca(actual,k,m);
       System.out.print("colocado ("+placed+") el producto ");
       p02.veoDatos(actual);
       m++;
     }
     ArrayList<Integer> tasas=solar.calculaDemanda();
     System.out.println("TASAS:");
     for(int i=0;i<tasas.size();i++){
         System.out.println("producto tipo "+(i+1)+" -> "+tasas.get(i));
     }
   }
}
