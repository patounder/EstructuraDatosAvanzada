package estructuradatosavanzada;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

    /**
 * @author Quiroz Vargas
 *
 * Clase que enlaza un conjunto de nodos formando un arbol binario completo.
 */
    public class Arbol {

        private Nodo raiz = new Nodo();
        private Intervalo vacio = new Intervalo();
        private int posi;
        private int hojas[];

    /**
     * Constructor de clase Arbol setea inicialmente la altura del arbol en cero
     * y define el arreglo de hojas con la cantidad ingresada (debe ser potencia
     * de 2).
     */
    public Arbol(int cantHojas) {
        this.raiz.setAlturaNodo(0);
        this.posi = 0;
        this.vacio.setComienzo(-1);
        this.vacio.setFin(-1);
        this.vacio.setPeso(0);
        this.hojas = new int[cantHojas];
    }
   
    /**
     * Para calcular la altura del arbol, que corresponde a la altura de la raiz,
     * se ocupa la cantidad de hojas, tamaño del arreglo hojas[].
     */
    public void calcularAlturaArbol() {
        this.raiz.setAlturaNodo(java.lang.Math.log10(getHojas().length) / java.lang.Math.log10(2));
    }

    /**
     * Metodo recursivo que crea el arbol binario completo, en su primer llamado
     * se le pasa como parametro la raiz del arbol. El metodo se deja de llamar
     * cuando la altura del nodo que se le pasa como parametro (padre) es <= 0.
     */
    public void crearArbol(Nodo padre) {
        Nodo auxDer;
        Nodo auxIzq;

        if (padre.getAlturaNodo() > 0) {

            auxDer = new Nodo();
            auxIzq = new Nodo();

            auxDer.setAlturaNodo(padre.getAlturaNodo() - 1);
            auxIzq.setAlturaNodo(padre.getAlturaNodo() - 1);

            padre.setHijoIzq(auxIzq);
            auxIzq.setPadre(padre);
            padre.setHijoDer(auxDer);
            auxDer.setPadre(padre);

            crearArbol(padre.getHijoIzq());
            crearArbol(padre.getHijoDer());
        } else {
            return;
        }

    }

    /**
     * Una vez que se ha creado el arbol binario completo, con el metodo
     * crearArbol(param), este metodo se encarga de setear los valores de peso,
     * comienzo y fin de cada intervalo I,L,R y M para cada nodo del arbol.
     *
     * El intervalo vacio es un intervalo con :
     * - comienzo = -1
     * - fin = -1
     * - peso = 0
     *
     * Si el peso de algun intervalo L, R, ó M de algun nodo determinado es menor
     * o igual que cero (<=0), se setea el intervalo con el intervalo vacio.
     *
     * Se empieza a llenar los valores desde la hoja mas a la izquierda hasta la
     * hoja mas a la derecha del arbol. Cuando se han seteado los valores de los hijos
     * de un nodo determinado, se setea con COSTO CONSTANTE los valores de dicho nodo.
     * Para el intervalo I se unen los intervalos I del hijo izquierdo e I del hijo derecho
     * ( unirIntervalos( I hijo Izq, I hijo Der) ).
     * Para el intervalo L se calcula el intervalo con peso maximo entre: L del hijo
     * izquierdo y la union entre I del hijo izquierdo y L del derecho,
     * ( maxIntervalo(L hijo Izq, unirIntervalos(I hijo Izq, L hijo Der) ) ).
     * Para el intervalo R se calcula el intervalo con peso maximo entre: R del hijo
     * derecho y la union entre R del izquierdo e I del hijo derecho,
     * ( maxIntervalo(R hijo Der, unirIntervalos( R hijo Izq, I hijo Der) ) ).
     * Para el intervalo M se calcula el intervalo con peso maximo entre: M hijo
     * izquierdo, M hijo derecho y la union entre R hijo izquierdo y L hijo derecho,
     * ( maxIntervalo(M hijo Der, M hijo Izq, unirIntervalos( R hijo Izq, L hijo Der) ) ).
     *
     * El metodo deja de ejecutarse cuando se llega a la raiz y se setean sus
     * valores.
     */
    public void llenarValores(Nodo aux, int vector[]) {
        Intervalo interAux;
     
        if (aux != null) {

            if ((aux.getHijoDer() == null) && (aux.getHijoIzq() == null)) {
                //es una hoja, entonces setear valores

                interAux = new Intervalo();
                interAux.setComienzo(this.getPosi());
                interAux.setFin(this.getPosi());
                interAux.setPeso(vector[this.getPosi()]);

                aux.setIntervaloI(interAux);
                aux.setIntervaloL(interAux);    //Previamente setea los valores de los Intervalos
                aux.setIntervaloR(interAux);    //En el caso de ser peso negativo luego lo corrige
                aux.setIntervaloM(interAux);

                //Cuando las hojas tienen peso negativo, llenar los valores de los
                //intervalos L,R,M como intervalos vacios
                if (vector[this.getPosi()] <= 0) {

                    aux.setIntervaloL(getVacio());
                    aux.setIntervaloR(getVacio());
                    aux.setIntervaloM(getVacio());
                }

                this.setPosi(this.getPosi() + 1);
                return;
            } else {

                llenarValores(aux.getHijoIzq(), vector);
                llenarValores(aux.getHijoDer(), vector);

                aux.setIntervaloI(unirIntervalos(aux.getHijoIzq().getIntervaloI(), aux.getHijoDer().getIntervaloI()));
                aux.setIntervaloL(maxIntervalo(aux.getHijoIzq().getIntervaloL(), unirIntervalos(aux.getHijoIzq().getIntervaloI(), aux.getHijoDer().getIntervaloL())));
                aux.setIntervaloR(maxIntervalo(aux.getHijoDer().getIntervaloR(), unirIntervalos(aux.getHijoIzq().getIntervaloR(), aux.getHijoDer().getIntervaloI())));
                aux.setIntervaloM(maxIntervalo(aux.getHijoIzq().getIntervaloM(), aux.getHijoDer().getIntervaloM(), unirIntervalos(aux.getHijoIzq().getIntervaloR(), aux.getHijoDer().getIntervaloL())));

                return;
            }
        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    public void verValores(Nodo aux) {

        if (aux != null) {

            if ((aux.getHijoDer() == null) && (aux.getHijoIzq() == null)) {
                System.out.println("su altura es " + aux.getAlturaNodo() + " hoja. su peso es " + aux.getIntervaloI().getPeso());
                this.setPosi(this.getPosi() + 1);
                return;
            } else {
                System.out.println("su altura es " + aux.getAlturaNodo() + " su peso es " + aux.getIntervaloI().getPeso());
                verValores(aux.getHijoIzq());
                verValores(aux.getHijoDer());
                return;
            }

        } else {
            System.out.println("El arbol esta vacio");
        }
    }

    /**
     * Une el intervalo uno y dos y el resultado de la union, aux se retorna
     *
     * @param uno
     * @param dos
     * @return aux
     *
     * El comienzo del intervalo aux sera el comienzo de uno.
     * El fin del intervalo aux sera el fin de dos.
     * Y el peso de aux sera la suma del peso de uno y dos.
     *
     */
    public Intervalo unirIntervalos(Intervalo uno, Intervalo dos) {
        Intervalo aux = new Intervalo();

        aux.setComienzo(uno.getComienzo());
        aux.setFin(dos.getFin());
        aux.setPeso(uno.getPeso() + dos.getPeso());
        return aux;
    }

    /**
     * @param uno
     * @param dos
     *
     * Determina entre el intervalo uno y el intervalo dos cual tiene mayor peso
     * En caso de que ambos pesos sean menor o igual (<=0)que cero retorna
     * intervalo vacio
     */
    public Intervalo maxIntervalo(Intervalo uno, Intervalo dos) {
        if (uno.getPeso() <= 0 && dos.getPeso() <= 0) {
            return getVacio();
        } else {
            if (Math.max(uno.getPeso(), dos.getPeso()) == uno.getPeso()) {
                return uno;
            } else {
                return dos;
            }
        }
    }

    /**
     * @param uno
     * @param dos
     * @param tres
     *
     * Determina entre los intervalos uno, dos y tres cual es el que tiene mayor
     * peso. En caso de que ambos tres intervalos sean menor o igual que cero (<=0)
     * retorna el intervalo vacio.
     */
    public Intervalo maxIntervalo(Intervalo uno, Intervalo dos, Intervalo tres) {
        if (uno.getPeso() <= 0 && dos.getPeso() <= 0 && tres.getPeso() <= 0) {
            return getVacio();
        } else {
            if (Math.max(uno.getPeso(), dos.getPeso()) == uno.getPeso()) {
                if (Math.max(uno.getPeso(), tres.getPeso()) == uno.getPeso()) {
                    return uno;
                } else {
                    return tres;
                }
            } else {
                if (Math.max(dos.getPeso(), tres.getPeso()) == dos.getPeso()) {
                    return dos;
                } else {
                    return tres;
                }
            }
        }

    }

    /**
     * Metodo que genera el arreglo de hojas con numeros enteros aleatorios.
     */
    public void generaNumerosAleatorios() {
        Random r = new Random();
        int ordenado[] = new int[getHojas().length];
        int random[] = new int[getHojas().length];
        int valor;

        for (int i = 0; i < getHojas().length;) {
            r.setSeed(new GregorianCalendar().getTimeInMillis());

            valor = (r.nextInt(1000) - (r.nextInt(1000)));
            //aux[i]=valor;
        

            if (!(Arrays.binarySearch(ordenado, valor) >= 0) && !(valor == 0)) {
                random[i] = valor;
                ordenado[i] = valor;
                i++;
            }

        }
        setHojas(random);
    }

    /**
    *Setter's & Getter's de los atributos
    **/

    /**
     * @return the raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    /**
     * @return the vacio
     */
    public Intervalo getVacio() {
        return vacio;
    }

    /**
     * @param vacio the vacio to set
     */
    public void setVacio(Intervalo vacio) {
        this.vacio = vacio;
    }

    /**
     * @return the posi
     */
    public int getPosi() {
        return posi;
    }

    /**
     * @param posi the posi to set
     */
    public void setPosi(int posi) {
        this.posi = posi;
    }

    /**
     * @return the hojas
     */
    public int[] getHojas() {
        return hojas;
    }

    /**
     * @param hojas the hojas to set
     */
    public void setHojas(int[] hojas) {
        this.hojas = hojas;
    }
}
