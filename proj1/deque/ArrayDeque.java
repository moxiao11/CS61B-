package deque;

import net.sf.saxon.functions.Minimax;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> , Iterable<T> {
    private T[] items;
    private int size;
    private int Max_space = 8;
    private int nextFirst, nextLast;
    private class Myiterator implements Iterator<T>
    {
        private int index ;
        Myiterator()
        {
            index = 0;
        }
        @Override
        public boolean hasNext()
        {
            return index < size ;
        }
        public T next()
        {
            return get(index++);
        }
    }
    ArrayDeque() {
        items = (T[]) new Object[Max_space];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    ArrayDeque(T x) {
        items = (T[]) new Object[Max_space];
        size = 1;
        nextFirst = 2;
        items[nextFirst + 1] = x ;
        nextLast = 4;
    }
    @Override
    public void addFirst(T x) {

        if(size >= Max_space)
        {
            resize( Max_space * 2 ) ;
        }

        items[nextFirst] =x ;
        nextFirst = (nextFirst - 1 + Max_space) % Max_space;
        size ++ ;
    }
    @Override
    public void addLast(T x) {
        if(size >= Max_space)
        {
            resize(Max_space * 2 );
        }
        items[nextLast] = x ;
        nextLast = (nextLast + 1 ) % Max_space;
        size ++ ;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public T get(int index)
    {
        if(index < 0 || index >= size ) return null ;
        int pos = (nextFirst + index + 1) % Max_space;
        return items[pos];
    }
    public void resize(int s)
    {
        T[] newItems = (T[])new Object[s] ;
        /* find the mid of the newItems */
        int start = (s - size ) / 2 ;
        for(int i = 0 ; i < size ; i++ )
        {
            int oldIndex = (nextFirst + 1 +i ) % Max_space;
            newItems[start + i ] = items[oldIndex] ;
        }
        nextFirst = start - 1 ;
        nextLast = start + size ;
        items = newItems ;
        Max_space = s ;
    }
    @Override
    public T removeFirst()
    {
        if(size == 0) return null ;
        int firstindex = (nextFirst + 1) % Max_space ;
        T removeItem = items[firstindex] ;
        items[firstindex] = null ;
        nextFirst = firstindex ;
        size -- ;
        shrink();
        return removeItem;
    }
    @Override
    public  T removeLast()
    {
        if(size == 0) return null ;
        int LastIndex = (nextLast - 1 + Max_space) % Max_space ;
        T removeItem = items[LastIndex];
        items[LastIndex] = null ;
        nextLast = LastIndex;
        size -- ;
        shrink();
        return removeItem;
    }
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof ArrayDeque)) return false ;
        ArrayDeque<T> temp = (ArrayDeque<T>)o ;
        if(temp.size() != this.size())return false ;
        for(int i = 0 ; i< size ; i ++ )
        {
            if(temp.get(i) != this.get(i)) return false ;
        }
        return true ;
    }
    public void shrink()
    {
        if(Max_space >= 8 && 4 * size <= Max_space)
        {
            resize(Max_space / 2) ;
        }
    }
    public Iterator<T> iterator()
    {
        return new Myiterator() ;
    }
}
