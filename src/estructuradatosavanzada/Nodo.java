package estructuradatosavanzada;

/**
 * @author Quiroz Vargas
 *
 * Beans que se ocupa para la construccion del Arbol binario completo
 * Ademas de contener los Intervalos correspondientes, tiene informacion:
 * - altura del nodo
 * - padre del nodo
 * - hijo derecho
 * - hijo izquierdo
 */
public class Nodo {

    private double alturaNodo;
    private Nodo padre;
    private Nodo hijoDer;
    private Nodo hijoIzq;
    private Intervalo intervaloI = new Intervalo();
    private Intervalo intervaloL = new Intervalo();
    private Intervalo intervaloR = new Intervalo();
    private Intervalo intervaloM = new Intervalo();

    /**
     * Constructor de Clase Nodo setea inicialmente los atributos padre, hijoDer
     * e hijoIzq en null, y ademas la altura del nodo es cero.
     */
    public Nodo() {
        this.padre = null;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.alturaNodo = 0;
        this.intervaloI = null;
        this.intervaloL = null;
        this.intervaloR = null;
        this.intervaloM = null;
    }

    public double getAlturaNodo() {
        return alturaNodo;
    }

    public void setAlturaNodo(double alturaNodo) {
        this.alturaNodo = alturaNodo;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(Nodo hijoDer) {
        this.hijoDer = hijoDer;
    }

    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(Nodo hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    /**
     * @return the intervaloI
     */
    public Intervalo getIntervaloI() {
        return intervaloI;
    }

    /**
     * @param inter the inter to set
     */
    public void setIntervaloI(Intervalo inter) {
        this.intervaloI = inter;
    }

    /**
     * @return the intervaloL
     */
    public Intervalo getIntervaloL() {
        return intervaloL;
    }

    /**
     * @param intervaloL the intervaloL to set
     */
    public void setIntervaloL(Intervalo intervaloL) {
        this.intervaloL = intervaloL;
    }

    /**
     * @return the intervaloR
     */
    public Intervalo getIntervaloR() {
        return intervaloR;
    }

    /**
     * @param intervaloR the intervaloR to set
     */
    public void setIntervaloR(Intervalo intervaloR) {
        this.intervaloR = intervaloR;
    }

    /**
     * @return the intervaloM
     */
    public Intervalo getIntervaloM() {
        return intervaloM;
    }

    /**
     * @param intervaloM the intervaloM to set
     */
    public void setIntervaloM(Intervalo intervaloM) {
        this.intervaloM = intervaloM;
    }
}
