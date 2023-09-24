import java.util.Iterator;

public class DynamicList<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new DynamicListIterator();
    }

    // Inner class for the iterator
    private class DynamicListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (hasNext()) {
                return (T) array[currentIndex++];
            }
            throw new java.util.NoSuchElementException();
        }
    }

    private Object[] array;
    private int length = 0;

    // DynamicList is a constructor. Equivalent of build(X)
    public DynamicList() {
        this.array = (Object[]) new Object[0];
        this.length = 0;
    }

    public DynamicList(Object[] arr) {
        this.array = arr.clone(); // clone to ensure new values in this.array
        this.length = this.array.length;
    }

    // return the number of stored items
    public int len() {
        return this.length;
    }

    // return the ith item
    public Object getAt(int i) throws IndexOutOfBoundsException {
        return this.array[i];
    }

    // replace the ith item with x
    public void setAt(int i, T x) throws IndexOutOfBoundsException {
        this.array[i] = x;
    }

    // add x as the ith item
    public void insertAt(int i, T x) throws IndexOutOfBoundsException {
        if (i > this.length) {
            throw new IndexOutOfBoundsException(i);
        }

        Object[] old = this.array.clone();

        if (this.array.length == this.length) {
            this.reallocate(); // TODO: may be it will be better to reallocate differently here
        }

        // do not reallocate here, but refill this.array

        boolean before = true;

        for (int index = 0; index < this.length + 1; index++) {
            if (index == i) {
                this.array[i] = x;
                before = false;
                continue;
            }

            if (before) {
                this.array[index] = old[index];
            } else {
                this.array[index] = old[index - 1];
            }
        }

        this.length++;
    }

    // remove and return the ith item
    public Object deleteAt(int i) throws IndexOutOfBoundsException {
        if (i >= array.length) {
            throw new IndexOutOfBoundsException(i);
        }

        Object[] old = this.array.clone();

        this.array = new Object[old.length - 1];

        boolean before = true;

        for (int index = 0; index < old.length; index++) {
            if (index == i) {
                System.out.println(i);
                before = false;
                continue;
            }

            if (before) {
                this.array[index] = old[index];
            } else {
                this.array[index - 1] = old[index];
            }
        }

        this.length--;
        
        return old[i];
    }
    
    // add x as the first item
    public void insertFirst(T x) throws IndexOutOfBoundsException {
        Object[] old = this.array.clone();

        if (this.array.length == this.length) {
            this.reallocate(); // TODO: may be it will be better to reallocate differently here
        }

        this.array[0] = x;

        for (int index = 1; index < this.length + 1; index++) {
            this.array[index] = old[index - 1];
        }

        this.length++;
    }

    // remove and return the first item
    public Object deleteFirst() throws IndexOutOfBoundsException {
        if (this.length == 0) {
            throw new IndexOutOfBoundsException(0);
        }

        Object[] old = this.array.clone();

        if (this.array.length == this.length) {
            this.reallocate(); // TODO: may be it will be better to reallocate differently here
        }

        // do not reallocate here, but refill this.array

        for (int index = 1; index < old.length; index++) {
            this.array[index - 1] = old[index];
        }

        this.length--;

        return old[0];
    }

    // add x as the last item
    public void insertLast(Object x) {
        if (this.array.length == this.length) {
            this.reallocate();
        }

        this.array[this.length] = x;
        this.length++;
    }

    // remove and return the last item
    public Object deleteLast() throws IndexOutOfBoundsException {
        if (this.length == 0) {
            throw new IndexOutOfBoundsException(0);
        }

        Object last = this.array[this.length - 1];

        this.array[this.length - 1] = null;
        this.length--;

        return last;
    }

    private void reallocate() {
        Object[] old = array;

        double k;

        if (this.array.length > 1024) {
            k = this.array.length * 1.25; // ensure more slow growing after 1024 values
        } else if (this.array.length == 0) {
            k = 1;
        } else {
            k = this.array.length * 2;
        }

        this.array = new Object[(int) k];

        for (int index = 0; index < old.length; index++) {
            this.array[index] = old[index];
        }
    }
}
