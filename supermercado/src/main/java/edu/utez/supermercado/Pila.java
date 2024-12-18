package edu.utez.supermercado;

public class Pila<T> {

    int capacidad;
    int tope;
    T[] items;

    // Constructor público para poder crear la pila desde cualquier clase
    public Pila(int capacidad) {
        this.capacidad = capacidad;
        tope = -1;
        this.items = (T[]) new Object[capacidad]; // Conversión de tipo, normal en casos genéricos
    }

    // Agregar un elemento a la pila
    public void push(T item) {
        if (isFull()) {
            System.out.println("El stack ya esta lleno, no puedes agregar mas");
            return;
        }
        items[++tope] = item;
    }

    // Eliminar el elemento de la pila y devolverlo
    public T pop() {
        if (isEmpty()) {
            System.out.println("EL stack esta vacio");
            return null;
        }
        return items[tope--];
    }

    // Obtener el elemento en la cima de la pila sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            System.out.println("EL stack esta vacio");
            return null;
        }
        return items[tope];
    }

    
    public boolean isEmpty(){
        return tope==-1;
    }

    public boolean isFull(){
        return tope==capacidad-1;
    }

}