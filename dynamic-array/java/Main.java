public class Main {
    public static void main(String[] args) {
        Integer[] ints = {1, 2, 3, 4, 5};
        DynamicList<Integer> list = new DynamicList<>(ints);

        for (Integer e : list) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");

        list.setAt(2, 100);

        for (Integer e : list) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
