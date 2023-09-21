public class DynamicList {
    private int[] array;
    private int len = 0;

    // see returns a copy of arr to ensure that a user will not change original one
    public int[] see() {        
        return array.clone();
    }

    public DynamicList() {
        this.array = new int[1];
    }

    public DynamicList(int[] arr) {
        this.array = arr.clone(); // clone to ensure new values in this.array
    }

    // add adds a new element 
    public void add(int e) {
        int cap = this.array.length;

        if (cap == len) {
            double k = 2;
            if (cap > 1024) {
                k = 1.25; // ensure more slow growing after 1024 values
            }
            this.reallocate(k);

            cap = this.array.length;
        }

        this.array[len] = e;
        this.len++;
    }

    // get returns element of i or throws IndexOutOfBoundsException
    public int get(int i) throws IndexOutOfBoundsException {
        return this.array[i];
    }

    // set edits DynamicList[i] to v or throws IndexOutOfBoundsException
    public void set(int i, int v) throws IndexOutOfBoundsException {
        this.array[i] = v;
    }

    private void reallocate(double k) {
        int cap = array.length;

        int[] old = array;

        double result = cap * k;

        this.array = new int[((int)result)];

        for (int e : old) {
            this.array[e] = e;
        }
    }
}
