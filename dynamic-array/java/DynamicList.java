public class DynamicList {
    private int[] array;
    private int length = 0;

    // see returns a smaller copy of arr to ensure that a user will not change
    // original array
    public int[] see() {
        int[] toSee = new int[this.length];

        for (int index = 0; index < toSee.length; index++) {
            toSee[index] = this.array[index];
        }

        return toSee;
    }

    // DynamicList is a constructor. Equivalent of build(X)
    public DynamicList() {
        this.array = new int[1];
        this.length = this.array.length;
    }

    public DynamicList(int[] arr) {
        this.array = arr.clone(); // clone to ensure new values in this.array
        this.length = this.array.length;
    }

    // return the number of stored items
    public int len() {
        return this.length;
    }

    // return the ith item
    public int getAt(int i) throws IndexOutOfBoundsException {
        return this.array[i];
    }

    // replace the ith item with x
    public void setAt(int i, int x) throws IndexOutOfBoundsException {
        this.array[i] = x;
    }

    // add x as the last item
    public void insertLast(int x) {
        if (this.array.length == this.length) {
            this.reallocate();
        }

        this.array[this.length] = x;
        this.length++;
    }

    // add x as the ith item
    public void insertAt(int i, int x) throws IndexOutOfBoundsException {
        if (i > this.length) {
            throw new IndexOutOfBoundsException();
        }

        int[] old = this.array.clone();

        if (this.array.length == this.length) {
            this.reallocate(); // TODO: may be it will be better to reallocate differently here
        }

        // do not reallocate here, but refill this.array

        boolean before = true;

        for (int index = 0; index < this.length+1; index++) {
            if (index == i) {
                System.out.println(x);
                this.array[i] = x;
                before = false;
                continue;
            }

            if (before) {
                this.array[index] = old[index];
            } else {
                System.out.printf("index: %d\n", index);
                this.array[index] = old[index-1];
            }
        }

        this.length++;
        
    }

    // remove and return the ith item
    // deleteAt(int i)

    // add x as the first item
    // insertFirst(int x)

    // remove and return the first item
    // deleteFirst()

    // add x as the last item
    // insertLast(int x)

    // remove and return the last item
    // deleteLast()

    private void reallocate() {
        int[] old = array;

        double k;

        if (this.array.length > 1024) {
            k = this.array.length * 1.25; // ensure more slow growing after 1024 values
        } else {
            k = this.array.length * 2;
        }

        this.array = new int[((int) k)];

        for (int index = 0; index < old.length; index++) {
            this.array[index] = old[index];
        }
    }
}
