package com.kike.minheap;

public class MinHeap {
    private int[] heap; // Array que almacena los elementos del heap
    private int tamano; // Número actual de elementos en el heap (cambiará a medida que se agreguen o eliminen elementos en el heap
    private int tamanhoMax; // Tamaño máximo del heap. Es una constante que se establece durante la creación del heap y no cambiará.

    // Constructor para inicializar el heap
    public MinHeap(int maxSize) {
        this.tamanhoMax = maxSize;
        this.tamano = 0;
        heap = new int[this.tamanhoMax + 1]; // Se suma 1 para facilitar el cálculo de índices
        heap[0] = Integer.MIN_VALUE; // Se coloca un valor mínimo en la posición 0
    }

    // Método para obtener el índice del padre de un nodo
    private int obtenerIndicePadre(int pos) {
        return pos / 2;
    }

    // Método para obtener el índice del hijo izquierdo
    private int obtenerIndiceHijoIzquierdo(int pos) {
        return (2 * pos);
    }

    // Método para obtener el índice del hijo derecho
    private int obtenerIndiceHijoDerecho(int pos) {
        return (2 * pos) + 1;
    }

    // Método para intercambiar dos elementos en el heap
    private void intercambiaDosElementos(int posicionA, int posicionB) {
        int tmp;
        tmp = heap[posicionA];
        heap[posicionA] = heap[posicionB];
        heap[posicionB] = tmp;
    }

    // Método para verificar si el nodo en la posición dada es una hoja
    private boolean esHoja(int pos) {
        if (pos > (tamano / 2) && pos <= tamano) {
            return true;
        }
        return false;
    }

    // Método para "hundir" un nodo y reorganizar el heap
    private void hundirElemento(int pos) {
        // Si el nodo no es una hoja y es mayor que alguno de sus hijos
        if (!esHoja(pos)) {
            if (heap[pos] > heap[obtenerIndiceHijoIzquierdo(pos)] || heap[pos] > heap[obtenerIndiceHijoDerecho(pos)]) {
                // Intercambia con el hijo izquierdo y heapify el hijo izquierdo
                if (heap[obtenerIndiceHijoIzquierdo(pos)] < heap[obtenerIndiceHijoDerecho(pos)]) {
                    intercambiaDosElementos(pos, obtenerIndiceHijoIzquierdo(pos));
                    hundirElemento(obtenerIndiceHijoIzquierdo(pos));
                }
                // Intercambia con el hijo derecho y heapify el hijo derecho
                else {
                    intercambiaDosElementos(pos, obtenerIndiceHijoDerecho(pos));
                    hundirElemento(obtenerIndiceHijoDerecho(pos));
                }
            }
        }
    }

    // Método para insertar un elemento en el heap
    public void insertarElemento(int element) {
        if (tamano >= tamanhoMax) { // Verificar si hay espacio en el heap
            return;
        }
        heap[++tamano] = element; // Insertar el elemento en el heap
        int actual = tamano;

        // Flotar el elemento hasta su posición correcta
        while (heap[actual] < heap[obtenerIndicePadre(actual)]) {
            intercambiaDosElementos(actual, obtenerIndicePadre(actual));
            actual = obtenerIndicePadre(actual);
        }
    }

    // Método para imprimir el heap
    public void imprimirHeap() {
        for (int i = 1; i <= tamano / 2; i++) {
            System.out.print(" PADRE: " + heap[i] + " HIJO IZQUIERDO: " + heap[2 * i]
                               + " HIJO DERECHO:" + heap[2 * i + 1]);
            System.out.println();
        }
    }

    // Método para eliminar y devolver el elemento mínimo del heap
    public int eliminarYDevolverElementoMinimoHeap() {
        int popped = heap[1]; // Elemento en la raíz del heap
        heap[1] = heap[tamano--]; // Mover el último elemento a la raíz y disminuir el tamaño
        hundirElemento(1); // Reorganizar el heap
        return popped; // Devolver el elemento eliminado
    }

    public static void main(String[] arg) {
        // Prueba del min heap
        MinHeap minHeap = new MinHeap(15);
        minHeap.insertarElemento(5);
        minHeap.insertarElemento(3);
        minHeap.insertarElemento(17);
        minHeap.insertarElemento(10);
        minHeap.insertarElemento(84);
        minHeap.insertarElemento(19);
        minHeap.insertarElemento(6);
        minHeap.insertarElemento(22);
        minHeap.insertarElemento(9);

        minHeap.imprimirHeap();
        System.out.println("El elemento mínimo eliminado es: " + minHeap.eliminarYDevolverElementoMinimoHeap());
    }
}
