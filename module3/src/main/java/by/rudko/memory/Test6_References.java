package by.rudko.memory;

public class Test6_References {
    public static void main(String[] args) {
        passingReferenceTest1();
        passingReferenceTest2();
        passingReferenceTest3();

        passingValueTest1();
        passingValueTest2();
    }


    private static void passingReferenceTest1() {
        String s1 = new String("value 1");
        String s2 = new String("value 1");
        changeImmutable(s2);
        assertThat(s1.equals(s2));
    }

    private static void changeImmutable(String s2) {
        s2.replace("1", "2");
    }

    private static void passingReferenceTest2() {
        StringBuilder b1 = new StringBuilder("value 1");
        StringBuilder b2 = new StringBuilder("value 1");
        changeMutable(b2);
        String s1 = b1.toString();
        String s2 = b2.toString();
        assertThat(!s1.equals(s2));
    }

    private static void changeMutable(StringBuilder b2) {
        b2.delete(b2.length()-1, b2.length()).append("2");
    }

    private static void passingReferenceTest3() {
        String s1 = new String("value 1");
        String s2 = new String("value 1");
        s2 = changeImmutableAndReturn(s2);
        assertThat(!s1.equals(s2));
    }

    private static String changeImmutableAndReturn(String s2) {
        return s2.replace("1", "2");
    }

    private static void passingValueTest1() {
        int i1 = 1;
        int i2 = 1;
        changeValue(i2);
        assertThat(i1 == i2);
    }

    private static void changeValue(int i2) {
        i2 = 2;
    }

    private static void passingValueTest2() {
        int i1 = 1;
        int i2 = 1;
        i2 = changeValueAndReturn(i2);
        assertThat(i1 != i2);
    }

    private static int changeValueAndReturn(int i2) {
        i2 = 2;
        return i2;
    }




    private static void assertThat(boolean b){
        if(!b) throw new AssertionError();
    }

}
