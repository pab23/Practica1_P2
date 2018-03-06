/**
* @author Alicia Garrido Alenda
* Se crea una serie de productos. Se invoca valorKilo y valorPeso para todos ellos,
* mostrando por pantalla lo que se devuelve en cada caso. Se invoca transforma de dos
* de estos productos, de manera que unas veces transforma los productos que se le pasan
* por parametro, y otras no.
*/
import java.text.*;
import java.util.*;
public class p01{
   
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
     {"rodocrosita","aventurina","actinolita"}
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
       System.out.println("Tipo: "+p.getTipo()+"; Nombre: "+p.getNombre()+"; peso: "+p01.mrf(p.getPeso())+"; colocado: "+p.getColocado());
     }
   }
   
   public static void main(String[] args){
     int[] tipos=p01.creaTipos();
     String[][] names=p01.creaNombres();
     double pinicial=1.5;
     Producto magico=null,azar=null;
     ArrayList<Producto> existencias=p01.creaProductos(names,tipos,pinicial);
     String aux=null;
     System.out.println("Productos creados -> "+existencias.size());
     for(int i=0;i<existencias.size();i++){
       Producto actual=existencias.get(i);
       p01.veoDatos(actual);
       double v=actual.valorKilo();
       aux=p01.mrf(v);
       v=actual.valorProducto();
       aux=aux+" => "+p01.mrf(v);
       System.out.println("valorK => valorP: "+aux);
       if(magico==null && actual.getTipo()==6){
         magico=actual;
       }
     }
     azar=existencias.get(existencias.size()/2);
     existencias.remove(magico);
     int conv=7;
     for(int i=0;i<existencias.size();i++){
         System.out.print("producto tipo "+magico.getTipo()+"("+magico.getNombre()+") transforma producto de tipo "+existencias.get(i).getTipo()+" ("+existencias.get(i).getNombre()+") en tipo "+conv);
         int t=magico.transforma(existencias.get(i),conv);
         if(t==1)
           System.out.println(" => hecho");
         else
           System.out.println(" => imposible");
         if(i<9){
           System.out.println("--------------------------------");
           System.out.print("producto tipo "+azar.getTipo()+"("+azar.getNombre()+") transforma producto de tipo "+existencias.get(i).getTipo()+" ("+existencias.get(i).getNombre()+") en tipo "+(conv+1));
           t=azar.transforma(existencias.get(i),(conv+1));
           if(t==1)
             System.out.println(" => hecho");
           else
             System.out.println(" => imposible");
           System.out.println("--------------------------------");
         }
         conv--;
         if(conv<=0) conv=7;
     }
   }
}
