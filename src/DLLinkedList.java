
public class DLLinkedList<T> {

    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;

        /**
         * Creates a new node with the given data
         *
         * @param d the data to put inside the node
         */
        public Node(T d) {
            data = d;
        }

        /**
         * Sets the node after this node
         *
         * @param n the node after this one
         */
        public void setNext(Node<T> n) {
            next = n;
        }

        /**
         * Sets the node before this one
         *
         * @param n the node before this one
         */
        public void setPrevious(Node<T> n) {
            previous = n;
        }

        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<T> next() {
            return next;
        }

        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node<T> previous() {
            return previous;
        }

        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public T getData() {
            return data;
        }
        
        
    }
    /**
     * Size of the list 
     */
    private int size;
    
    /**
     * Point to the head of the list
     */
    private Node<T> head;
    
    /**
     * Point to the tail of the list
     */
    private Node<T> tail;
    
    /**
     * Constructor that create new List
     */
    public DLLinkedList() {
        init();
    }
    
    /**
     * Initialize the list
     */
    private void init() {
        head = new Node<T>(null);
        tail = new Node<T>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }
    
    
     /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }
    
    /**
     * clear the list
     */
    public void clear(){
        init();
    }
    
    /**
     * Insert new element in the list
     * 
     * @param element to be added
     */
    public void add(T element) {
        add(size, element);
    }
    
    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<T> getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at " 
                    + index);
        }
        
        Node<T> current = null;
        
        int compare = size >> 1;
        
        if (index <= compare) {
            current = head.next();
            for (int i = 0; i < index; i++) {
                current = current.next();
            }
            
        }
        else {
            
            current = tail.previous();
            for (int i = size; i > (index+1); i--) {
                current = current.previous();
            }
        }
        
        
        return current;
    }
    
    /**
     * Adds the object to the position in the list
     *
     * @param index where to add the object
     *            
     * @param obj the object to add
     *            
     * @throws IndexOutOfBoundsException if index is less than zero or greater than size
     *             
     * @throws IllegalArgumentException if obj is null
     *             
     */
    public void add(int index, T obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Cannot add null "
                    + "objects to a list");
        }

        Node<T> nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        } 
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<T> element = new Node<T>(obj);
        element.setPrevious(nodeAfter.previous());
        element.setNext(nodeAfter);
        nodeAfter.previous().setNext(element);
        nodeAfter.setPrevious(element);
        size++;

    }
    
    /**
     * Removes the element at the specified index from the list
     *
     * @param index where the object is located
     *           
     * @throws IndexOutOfBoundsException if there is not an element at the index
     *             
     * @return true if successful
     */
    public boolean remove(int index) {
        Node<T> nodeToBeRemoved = getNodeAtIndex(index);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        nodeToBeRemoved = null;
        size--;
        return true;
    }

    /**
     * Removes the first object in the list that equals obj
     *
     * @param obj the object to remove
     *            
     * @return true if the object was found and removed
     */

    public boolean remove(T obj) {
        Node<T> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(obj)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    /**
     * Returns a string representation of the list
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        if (!isEmpty()) {
            Node<T> currNode = head.next();
            while (currNode != tail) {
                T element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tail) {
                    builder.append(", ");
                }  
                currNode = currNode.next();
            }
        }

        builder.append(")");
        return builder.toString();
    }

    /**
     * Gets the object at the given position
     *
     * @param index where the object is located
     *            
     * @return The object at the given position
     * @throws IndexOutOfBoundsException if there no node at the given index
     *             
     */
    public T get(int index) {
        
        return getNodeAtIndex(index).getData();
    }
    
}
