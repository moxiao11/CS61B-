package deque;

public interface Deque<T> {
    /* add the x to the front of deque */
    public void addFirst(T x) ;
    /* return the size of deque */
    public int size();
    /* add the x to the end of a deque */
    public void addLast(T x) ;
    /* deque is or isn't empty */
    default public boolean isEmpty(){
        return size() == 0 ;
    }
    /* remove first element */
    public T removeFirst();
    /* remove last element */
    public T removeLast() ;
    /* print a deque */
    default public void printDeque()
    {
        for(int i = 0 ; i < size() ; i++ )
        {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    /* get the indexth element */
    public T get(int index);
    public boolean equals(Object o);
}
