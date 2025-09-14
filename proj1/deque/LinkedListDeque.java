package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    
    private class Node
    {
        private T item;
        private Node next ;
        private Node prev ;

        Node(T item , Node next ,Node prev)
        {
            this.item = item ;
            this.next = next ;
            this.prev = prev ;
        }
    }
    private Node last ;
    private Node sentinel ;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null , null , null) ;
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x)
    {
        sentinel = new Node(null , null, null ) ;
        sentinel.next = new Node(x , null, sentinel) ;
        last = sentinel.next ;
        size = 1 ;
    }
    @Override
    public void addFirst(T x)
    {
        Node newNode = new Node(x ,sentinel.next , sentinel);
        if(sentinel.next !=null)
        {
            sentinel.next.prev = newNode ;
        }
        sentinel.next = newNode ;
        size ++ ;
        if(size == 1)
        {
            last = newNode ;
        }
    }
    @Override
    public void printDeque()
    {
        Node p = sentinel.next;
        while(p != null )
        {
            System.out.print(p.item + " ");
            p = p.next ;
        }
        System.out.println();
    }
    @Override
    public int size() {return size ; }
    @Override
    public void addLast(T x)
    {
        Node newNode = new Node (x , null , last );
        last.next = newNode ;
        last = newNode ;
        size ++ ;
    }
    @Override
    public T removeFirst()
    {
        if(sentinel.next == null) return null;
        Node first = sentinel.next ;
        /* get the first element */
        T tmp = first.item ;
        sentinel.next = first.next ;

        if(sentinel.next != null)
        {
            sentinel.next.prev = sentinel ;
        }
        else
        {
            last = sentinel;
        }
        size -- ;
        return tmp ;
    }
    @Override
    public T removeLast()
    {
        if (sentinel == last) return null;
        T tmp = last.item ;
        last = last.prev ;
        last.next = null ;
        size -- ;
        return tmp ;
    }
    @Override
    public T get(int index)
    {
        if(index < 0 || index >= size) return null ;
        Node temp = sentinel.next ;
        while( index > 0 )
        {
            temp = temp.next ;
            index -- ;
        }
        return temp.item;
    }
    private class myIterator implements Iterator<T> {
        private Node current ;

        public myIterator()
        {
            current = sentinel.next ;
        }
        @Override
        public boolean hasNext(){
            return current != null ;
        }
        @Override
        public T next()
        {
            T x = current.item ;
            current = current.next ;
            return x ;
        }
    }
    @Override
    public Iterator<T> iterator()
    {
        return new myIterator();
    }
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof LinkedListDeque )) return false ;
        LinkedListDeque<T> temp = (LinkedListDeque<T>)o ;
        if(temp.size != this.size)  return false ;
        for(int i =0 ; i < this.size ; i ++ )
        {
            if(temp.get(i) != this.get(i)) return false ;
        }
        return true ;
    }
    public T getRecurisive(int index)
    {
        return getRecurisiveHelper(index , sentinel.next);
    }
    public T getRecurisiveHelper(int index ,Node head)
    {
        if(index == 0) return head.item;
        else return getRecurisiveHelper(index - 1 , head.next);
    }


}
