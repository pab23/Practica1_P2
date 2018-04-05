public class pruebas {
    public static void main (String args[]){
        Producto p=new Producto(0,0,null) ;
        Habitante h= new Habitante("Kitipasa", 'c');
        Terreno t= new Terreno(0,0);
        Mistico m=new Mistico();
        p.transforma(null, 0);
        t.genera(0,0,0,0,null);
        t.recoge(0,0);
        t.destruye(0,0);
        t.coloca(null, 0, 0);
        t.consultaTipo(0,0);
        t.consultaPeso(0,0);
        h.recolecta(null, 0);
        h.vigoriza(null);
        h.edifica(null);
        h.trueque(null);
        h.tributa(null);
        m.culto(null, null);
        m.regenera(null, null);
    }
}
