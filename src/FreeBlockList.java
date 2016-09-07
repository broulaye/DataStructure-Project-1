/**
 * Represent the free block list keeping
 * track of the available free blocks
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/07/2016
 *
 */
public class FreeBlockList extends DLLinkedList<Helper.Tuple> {
    /**
     * Constructor
     * @param poolsize size of free space
     */
    public FreeBlockList(int poolsize) {
        if (poolsize > 0) {
            add(new Helper.Tuple(0, poolsize - 1));
        }
    }

    /**
     * Expand list by addition
     * @param additionalLength additional length
     * @param oldLength represent the old length
     */
    public void expand(int additionalLength, int oldLength) {
        if (size() == 0) {
            add(new Helper.Tuple(oldLength, oldLength + additionalLength - 1));
            return;
        }
        Helper.Tuple lastBlock = get(size() - 1);
        if (lastBlock.getY() == oldLength - 1) {
            lastBlock.setY(lastBlock.getY() + additionalLength);
        }
        else {
            add(new Helper.Tuple(oldLength, oldLength + additionalLength - 1));
        }
    }
    
    /**
     * print content of the list 
     * @return a string representation of the list
     */
    public String printBlocks() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(get(i).toString());
            if (i < size() - 1) {
                builder.append("->");
            }
        }
        return builder.toString();
    }

    /**
     * get the next available free space 
     * of requredLength using best fit 
     * algorithm
     * @param requiredLength length to look for
     * @return -1 if no free space is found
     * otherwise return start of free block
     */
    public int getNextAvailable(int requiredLength) {
    	//TODO: optimized next available algorithm
        // check if list is empty
        if (size() == 0) {
            //expand(requiredLength, 0);
            return -1;
        }
        int min = -1;
        int at = -1;
        int currentLength =0;
        for (int i = 0; i < size(); i++) {
            // get size of current node
            currentLength = get(i).getY() - get(i).getX() + 1;
            int newMin = currentLength - requiredLength;
            if (newMin >= 0) {
            	if (min < 0) {
            		min = newMin;
            		at = i;
            	}
            	else if (newMin < min) {
            		min = newMin;
            		at = i;
            	}
            }
          
        }
        if (at < 0) {
        	return at;
        }
        currentLength = get(at).getY() - get(at).getX() + 1;
        if (currentLength == requiredLength) {
            int startOfFreeBlock = get(at).getX();
            remove(at);
            return startOfFreeBlock;
        }
        else {
            int remainingSpace = currentLength - requiredLength;
            int newStart = get(at).getX() + requiredLength;
            int startOfFreeBlock = get(at).getX();
            remove(at);
            Helper.Tuple newFreeBlock = new Helper.Tuple(newStart, newStart + remainingSpace - 1);
            add(at, newFreeBlock);
            return startOfFreeBlock;
        }
    }

    /**
     * Adds new free space block
     * @param location start of block
     * @param length length of block
     */
    public void freeUpSpace(int location, int length) {
    	Helper.Tuple node;
    	int x = location - 1;
    	int y = location + length;
    	int i = 0;
    	node = get(i);
		//check extremity 1
		if (node.getX() == y) {
			add(i, new Helper.Tuple(location, location + length - 1));
			merge(i, i + 1);
		}
		else if (node.getX() < y) {
			while (node.getX() < y) {
				i++;
				if (i == size()) {
					break;
				}
				node = get(i);
				
				
				
			}
			
			//checking second extremity
			if (i == size()) {
				if (get(i - 1).getY() == x) {
					add(i, new Helper.Tuple(location, location + length - 1));
					
	    			merge(i - 1, i);
	    			
	    			return;
				}
				else {
					add(i, new Helper.Tuple(location, location + length - 1));
					return;
				}
			}
			if (get(i - 1).getY() == x) {
				add(i, new Helper.Tuple(location, location + length - 1));
				
    			merge(i - 1, i);
    			
    			if ((get(i - 1).getY() + 1) == get(i).getX()) {
    				
    				merge(i - 1, i);
    				
    			}
			}
			else {
				add(i, new Helper.Tuple(location, location + length - 1));
				if (get(i).getY() + 1 == get(i + 1).getX()) {
    				merge(i, i + 1);
    			}
			}
			
			
			
			}
			
    }
    
    /**
     * merge to node at given location
     * @param loc1 first location
     * @param loc2 second location
     */
    private void merge(int loc1, int loc2) {
    	get(loc1).setY(get(loc2).getY());
    	remove(get(loc2));
    }
}
