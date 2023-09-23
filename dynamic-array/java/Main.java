public class Main {
    public static void main(String[] args) {
        int[] array = {5, 4, 3, 2, 1};
        DynamicList list = new DynamicList(array);

        for (int e : list.see()) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");

        list.insertLast(0);

        for (int e : list.see()) {
            System.out.print(e);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
