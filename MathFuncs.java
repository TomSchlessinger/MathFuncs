package util;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public class MathFuncs {
    @org.jetbrains.annotations.NotNull
    public static <T> T @NotNull[] flatten2d(T[] @NotNull [] arr){
        List<T> streamList = new ArrayList<>();
        for (T[] array : arr) {
            streamList.addAll(Arrays.asList(array));
        }
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(arr[0].getClass().getComponentType(), 0);
        return streamList.toArray(ret);
    }

    public static <T> T @NotNull[] join(T @NotNull [] l1, T @NotNull [] l2){
        List<T> joined = new ArrayList<>(l1.length + l2.length);
        Collections.addAll(joined, l1);
        Collections.addAll(joined, l2);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(l1.getClass().getComponentType(), 0);
        return joined.toArray(ret);
    }
    public static @NotNull String reverse(@NotNull String original){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < original.length(); i++){
            ret.append(original.charAt(original.length()-1-i));
        }
        return ret.toString();
    }
    public static char @NotNull[] reverse(char @NotNull [] original){
        char[] ret = new char[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static int @NotNull[] reverse(int @NotNull [] original){
        int[] ret = new int[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static boolean @NotNull[] reverse(boolean @NotNull [] original){
        boolean[] ret = new boolean[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static double @NotNull[] reverse(double @NotNull [] original){
        double[] ret = new double[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static float @NotNull[] reverse(float @NotNull [] original){
        float[] ret = new float[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static long @NotNull[] reverse(long @NotNull [] original){
        long[] ret = new long[original.length];
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static <T> T @NotNull[] reverse(T @NotNull [] original){
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(original[0].getClass(),original.length);
        for(int i = 0; i < original.length; i++){
            ret[i] = original[original.length-1-i];
        }
        return ret;
    }
    public static <T,R> R @NotNull[] mapFunc(@NotNull Function<T,R> f, T @NotNull [] original){
        final List<R> type = new ArrayList<>();
        type.add(f.apply(original[0]));
        @SuppressWarnings("unchecked")
        R[] ret = (R[]) Array.newInstance(type.get(0).getClass(),original.length);
        for(int i = 0; i < original.length; i++){
            ret[i] = f.apply(original[i]);
        }
        return ret;
    }
    public static <T,R extends T> @NotNull T nest(@NotNull Function<T,R> f, R expr, int n){
        assert n > 0;
        R ret = f.apply(expr);
        n--;
        while(n > 0){
            ret = f.apply(ret);
            n--;
        }
        return ret;
    }
    //{a,b,c,d} -> {b,c,d,a}
    public static <T> T @NotNull[] rotateLeft(T[] initial){
        List<T> list = new ArrayList<>(List.of(initial));
        T temp = list.get(0);
        list.remove(0);
        list.add(temp);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(initial[0].getClass(),initial.length);
        return list.toArray(ret);
    }
    public static <T> T @NotNull [] rotateLeft(T[] initial, int iter){
        return nest(MathFuncs::rotateLeft,initial,iter);
    }
    //{a,b,c,d} -> {d,a,b,c}
    public static <T> T @NotNull [] rotateRight(T[] initial){
        List<T> list = new ArrayList<>(List.of(initial));
        T temp = list.get(list.size()-1);
        list.remove(list.size()-1);
        list.add(0,temp);
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) Array.newInstance(initial[0].getClass(),initial.length);
        return list.toArray(ret);
    }
    public static <T> T @NotNull [] rotateRight(T[] initial, int iter){
        return nest(MathFuncs::rotateRight,initial,iter);
    }

    public static Integer @NotNull [] range(int i, int start){
        Integer[] ret = new Integer[i];
        for(int j = 0; j < i; j++){
            ret[j] = start + j;
        }
        return ret;
    }

    public static Integer @NotNull[] range(int i){
        return range(i,0);
    }

    public static <K> K @NotNull [] slice(K @NotNull [] init, int start, int end){
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init[0].getClass(), end - start);
        if (end - start >= 0) System.arraycopy(init, start, ret, 0, end - start);
        return ret;
    }
    public static <K> K @NotNull [] slice(K @NotNull [] init, int end){
        return slice(init,0,end);
    }

    public static <K> K @NotNull [] deleteDuplicates(K[] init) {
        List<K> list = new ArrayList<>(List.of(init));
        List<K> ret1 = new ArrayList<>(new HashSet<>(list));
        @SuppressWarnings("unchecked")
        K[] b = (K[]) Array.newInstance(init[0].getClass(), 0);
        return ret1.toArray(b);
    }
    public static @NotNull String deleteDuplicates(@NotNull String init){
        Character[] fin = new Character[init.length()];
        for(int i = 0; i < init.length();i++){
            fin[i] = init.charAt(i);
        }
        return new ArrayList<>(List.of(deleteDuplicates(fin))).toString()
                .replaceAll(", ","")
                .replaceAll("]","")
                .replace("[","");
    }
    public static int factorial(int i){
        return i==0?1:i*factorial(i-1);
    }

    public static <K> K[] echoArr(K[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static double[] echoArr(double[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static int[] echoArr(int[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static boolean[] echoArr(boolean[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static long[] echoArr(long[] ret){
        System.out.println(Arrays.toString(ret));
        return ret;
    }

    public static <K> K echo(K ret, String s){
        System.out.println(ret.toString() + s);
        return ret;
    }

    public static <K> K echo(String s, K ret){
        System.out.println(s + ret.toString());
        return ret;
    }

    public static <K> K echo(K ret){
        System.out.println(ret.toString());
        return ret;
    }
    public static <K> K[] randomSample(K[] init){
        return randomSample(init,-1,0);
    }
    public static <K> K[] randomSample(K[] init, int n){
        return randomSample(init,n,0);
    }
    public static <K> K[] randomSample(K[] init, int n, int index){
        List<K> shuffled = Arrays.asList(init);
        Collections.shuffle(shuffled);
        @SuppressWarnings("unchecked")
        K[] ret = (K[]) Array.newInstance(init[0].getClass(), 0);
        return n > index ? slice(shuffled.toArray(ret),index,n-1):shuffled.toArray(ret);
    }

    public static int binaryToInt(Boolean[] bools){
        int ret = 0;
        for(int i = 0; i < bools.length; i++){
            ret+=bools[i]?Math.pow(2,bools.length-i-1):0;
        }
        return ret;
    }

    public static String intToBinary(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.insert(0, ((n % 2) == 0 ? "0" : "1"));
            n /= 2;
        }
        return s.toString();
    }
    public static String stringToBinary(String s){
        StringBuilder bin = new StringBuilder();
        for(char i : s.toCharArray()){
            bin.append(Integer.toBinaryString(i));
        }
        return bin.toString();
    }
    public static String XOR(String a, String b){
        StringBuilder ret = new StringBuilder();
        if(a.length() > b.length())a = "0".repeat(a.length() - b.length()) + a;//THIS EXISTS???
        else if(b.length() > a.length()) b = "0".repeat(b.length() - a.length()) + b;//THIS EXISTS???
        for(int i = 0; i < a.length(); i ++) ret.append(!(a.charAt(i)==b.charAt(i)) ? "1":"0");
        return ret.toString();
    }
}
