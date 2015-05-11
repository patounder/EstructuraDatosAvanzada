/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatosavanzada;

/**
 * @author Quiroz Vargas
 *
 * Beans que se ocupa para la construccion de cada intervalo para cada nodo del
 * arbol. Cada intervalo tiene un:
 * - comienzo
 * - fin
 * - peso
 */
public class Intervalo {

    private int comienzo;
    private int fin;
    private int peso;

    /**
     * @return the comienzo
     */
    public int getComienzo() {
        return comienzo;
    }

    /**
     * @param comienzo the comienzo to set
     */
    public void setComienzo(int comienzo) {
        this.comienzo = comienzo;
    }

    /**
     * @return the fin
     */
    public int getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(int fin) {
        this.fin = fin;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}
