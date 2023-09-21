public class Main {
    public static void main(String[] args) {
        int[] array = {5, 4, 3, 2, 1};
        DynamicList list = new DynamicList(array);

        for (int e : list.see()) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");

        list.set(0, 111);

        list.set(2, 111);
        list.set(4, 111);

        for (int e : list.see()) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
