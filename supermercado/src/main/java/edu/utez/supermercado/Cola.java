package edu.utez.supermercado;

public class Cola<T> {
    int head;
    int tail;
    int intemsNumber;
    int arrLength;
    T[] items;

    @SuppressWarnings("unchecked")
    public Cola(int arrLength) {
        this.head = 0;
        this.tail = -1;
        this.arrLength = arrLength;
        this.intemsNumber = 0;
        this.items = (T[]) new Object[arrLength];
    }

    public void offer(T item) {
        if (isFull()) {
            System.out.println("La Queue esta llena, no puedes agregar mas elementos");
            return;
        }
        tail = (tail + 1) % arrLength; //indicador de tail en forma circular
        items[tail] = item;
        intemsNumber++;
    }

    public T poll() {
        if (isEmpty()) {
            System.out.println("La Queue esta vacia");
            return null;
        }
        T item = items[head]; // seleccionar el item en la cabeza de la queue
        head = (head + 1) % arrLength; // actualizar el indicador head de forma circular
        intemsNumber--; // actualizar el numero de items actuales en la queue
        return item; // retorna el primer elemento en la queue
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("La Queue esta vacia");
            return null;
        }
        return items[head]; //selecciona y returna el elemento head
    }

    public boolean isEmpty() {
        return (intemsNumber == 0);
    }

    public boolean isFull() {
        return (intemsNumber == arrLength);
    }
}
