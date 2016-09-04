
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
	
	
	public DLLinkedList() {
		head = new Node<T>(null);
	}
	
}
