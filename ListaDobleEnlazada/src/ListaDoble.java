/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

/**
 * @author sigacdev
 */
class ListaDoble {

    Nodo cabeza;

    void insertarCabezaLista(int entrada) {
        Nodo nuevo;
        nuevo = new Nodo(entrada);
        nuevo.adelante = cabeza;
        if (cabeza != null) {
            cabeza.atras = nuevo;
        }
        cabeza = nuevo;
    }

    void insertarFinLista(int entrada) {
        Nodo nuevo = new Nodo(entrada);
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.adelante == null) {
                actual.adelante = nuevo;
                nuevo.atras = actual;
                break;
            }
            actual = actual.adelante;
        }
    }

    void insertaDespues(Nodo anterior, int entrada) {
        Nodo nuevo;
        nuevo = new Nodo(entrada);
        nuevo.adelante = anterior.adelante;
        if (anterior.adelante != null) {
            anterior.adelante.atras = nuevo;
        }
        anterior.adelante = nuevo;
        nuevo.atras = anterior;
    }

    void insertaAntes(Nodo adelante, int entrada) {
        Nodo nuevo;
        nuevo = new Nodo(entrada);
        nuevo.atras = adelante.atras;
        if (adelante.atras != null) {
            adelante.atras.adelante = nuevo;
        }
        adelante.atras = nuevo;
        nuevo.adelante = adelante;
    }

    void eliminar(int entrada) {
        Nodo actual;
        boolean encontrado = false;
        actual = cabeza;
        // Bucle de búsqueda
        while ((actual != null) && (!encontrado)) {
            /* la comparación se realiza con el método equals()...,			
            depende del tipo Elemento */
            encontrado = (actual.dato == entrada);
            if (!encontrado) {
                actual = actual.adelante;
            }
        }
        // Enlace de nodo anterior con el siguiente
        if (actual != null) {
            //distingue entre nodo cabecera o resto de la lista
            if (actual == cabeza) {
                cabeza = actual.adelante;
                if (actual.adelante != null) {
                    actual.adelante.atras = null;
                }
            } else if (actual.adelante != null) // No es el último nodo
            {
                actual.atras.adelante = actual.adelante;
                actual.adelante.atras = actual.atras;
            } else // último nodo
            {
                actual.atras.adelante = null;
            }
            actual = null;
        }
    }

    void visualizar() {
        Nodo n;
        int k = 0;
        n = cabeza;
        while (n != null) {
            System.out.print(n.dato + " ---> ");
            n = n.adelante;
            k++;
        }
        System.out.println();
    }

    @Nullable
    Nodo buscarLista(@NotNull int destino, @NotNull boolean print) {
        Nodo actual;
        boolean encontrado = false;
        actual = cabeza;
        // Bucle de búsqueda
        while ((actual != null) && (!encontrado)) {
            /* la comparación se realiza con el método equals()...,
            depende del tipo Elemento */
            encontrado = (actual.dato == destino);
            if (!encontrado) {
                actual = actual.adelante;
            }
        }
        // Enlace de nodo anterior con el siguiente
        if (actual != null) {
            int last = 0;
            int next = 0;
            try {
                last = actual.atras.dato;
            } catch (NullPointerException ignore) {
                System.out.println("El anterior es nulo, se mostrará 0 por defecto");
            }
            try {
                next = actual.adelante.dato;
            } catch (NullPointerException ignore) {
                System.out.println("El siguiente es nulo, se mostrará 0 por defecto");
            }
            if (print) {
                System.out.println("+----------+----------+-----------+");
                System.out.println("| anterior |  actual  | siguiente |");
                System.out.println("+----------+----------+-----------+");
                System.out.println(String.format("|   %4d   |   %4d   |   %4d    |", last, actual.dato, next));
                System.out.println("+----------+----------+-----------+");
            }
            return actual;
        }
        System.out.println("El " + destino + " no existe");
        return null;
    }

}
