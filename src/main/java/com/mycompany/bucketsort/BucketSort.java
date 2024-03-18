/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bucketsort;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {

    public static void sort(Comparable[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int n = array.length;
        ArrayList<Comparable>[] buckets = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Encontrar valores maximos y minimos en el arreglo
        Comparable minValue = array[0];
        Comparable maxValue = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i].compareTo(minValue) < 0) {
                minValue = array[i];
            } else if (array[i].compareTo(maxValue) > 0) {
                maxValue = array[i];
            }
        }

        double range;

        // Calcular rango y distribuir los elementos en los casillero
        if (maxValue.getClass().getSimpleName().equals("Integer")) {
            range = (int) maxValue - (int) minValue;
            for (int i = 0; i < n; i++) {
                int bucketIndex = (int) ((((int) array[i] - (int) minValue) / range) * (n - 1));
                buckets[bucketIndex].add(array[i]);
            }
        } else {
            range = maxValue.compareTo(minValue);
            for (int i = 0; i < n; i++) {
                int bucketIndex = (int) ((array[i].compareTo(minValue) / range) * (n - 1));
                buckets[bucketIndex].add(array[i]);
                System.out.println("(((" + array[i] + "-" + minValue + ")/" + range + ")*(" + (n-1) +")");
                System.out.println("bucketIndex=" + bucketIndex);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Bucket " + i);
            System.out.print(": [");
            for (Comparable bucket : buckets[i]) {
                System.out.print(bucket + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
        
        // Ordenar cada bucket
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // Concatenar los buckets en el arreglo original
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (Comparable element : buckets[i]) {
                array[index++] = element;
            }
        }
    }

    private static void show(Comparable[] array) {
        for (Comparable comparable : array) {
            System.out.print(comparable + " ");
        }
    }

    public static void main(String[] args) {
        Integer[] array = {50, 9, 3, 2, 6, 1, 4,5,7,11,14,25,30,15};
        sort(array);

        System.out.println("array ordenado:");
        show(array);
    }
}
