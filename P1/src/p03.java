/**
* @author Alicia Garrido Alenda
* Se crea un terreno y se intenta generar productos de distinto tipo en distintos sitios del terreno,
* de manera que unas veces se genera el producto y otras no, mostrando por pantalla lo que sucede en cada
* caso. Se consulta el tipo y peso del producto contenido en cada parcela del terreno y finalmente se
* invoca calculaDemanda, mostrando por pantalla lo que devuelve el metodo
*/
import java.text.*;
import java.util.*;
public class p03{
   
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
     {"brocoli", "berza", "espinaca","zanahoria"},
     {"Vaca","Cerdo","Pollo","Merluza"},
     {"agata","amatista","cuarzo","marmol"},
     {"caoba","teca","mindi","roble"},
     {"casa","casona","pazo","muralla"},
     {"rodocrosita","aventurina","actinolita","olivino"},
     };
     return names;
   }
   
   private static void generaProductos(Terreno solar,String[][] names,int[] tipos,double peso){
     int tp=0;
     boolean creado=false;
     for(int j=0;j<names[0].length;j++){
       for(int i=0;i<names.length;i++){
         creado=solar.genera(i,j,peso,tipos[tp],names[i][j]);
         System.out.println("Generado: "+creado+" -> en ("+i+","+j+"); tipo: "+tipos[tp]+"; peso: "+p03.mrf(peso));
         peso+=0.2;
         if(tipos[tp]==5) i--;
         if(creado || tipos[tp]==5)
           tp++;
         if(tp>=tipos.length) tp=0;
       }
     }
   }
   
   private static void consultaTerreno(Terreno solar){
     if(solar!=null){
       for(int i=0;i<solar.getFilas();i++)
         for(int j=0;j<solar.getColumnas();j++)
           System.out.println("Parcela ("+i+","+j+") ->  tipo: "+solar.consultaTipo(i,j)+"; peso: "+p03.mrf(solar.consultaPeso(i,j)));
     }
   }
   
   public static void main(String[] args){
     int[] tipos=p03.creaTipos();
     String[][] names=p03.creaNombres();
     double pinicial=1.5;
     boolean fet=false;
     int k=0,m=0,v=0;
     Terreno solar=new Terreno(3,4);
     p03.generaProductos(solar,names,tipos,pinicial);
     p03.consultaTerreno(solar);
     ArrayList<Integer> demanda=solar.calculaDemanda();
     System.out.println(demanda);
   }
}
