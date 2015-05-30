package by.rudko.memory;

public class Test5_References {
    public static void main(String[] args) {
        passingReferenceTest();
    }

    /*
     *   Please add to your application code which includes passing by reference.
     */
    private static void passingReferenceTest() {
        String s1 = new String("value 1");
        String s2 = new String("value 1");
        change(s2);

        assertThat(s1.equals(s2), "s2 has changed");
    }

    /*
     *   Please don't add returning the reference from method.
     *   Make creation of the new object in method where you pass value by reference (the new object is for value).
     */
    private static void change(String s2) {
        s2 = new String("value 2");
    }


    private static void assertThat(boolean b, String message){
        if(!b) throw new AssertionError(message);
    }

}
