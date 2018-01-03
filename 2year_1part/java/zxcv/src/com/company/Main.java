package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

    public static int insComp = 0;
    public static int insExchanges = 0;

    public static int qsComp = 0;
    public static int qsExchanges = 0;

    public static void main(String[] args) {
        try {
            File file = new File("src/insqucksrt/10_input.txt");
            BufferedReader bw = new BufferedReader(new FileReader(file));

            StringBuilder sb = new StringBuilder();
            while (bw.ready()) {
                sb.append(bw.readLine());
            }

            Integer[] arr1 = new Integer[sb.toString().split(" ").length];
            Integer[] arr2 = new Integer[sb.toString().split(" ").length];
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = Integer.parseInt(sb.toString().split(" ")[i]);
                arr2[i] = Integer.parseInt(sb.toString().split(" ")[i]);
            }

            long before = System.nanoTime();
            insertion(arr1);
            long after = System.nanoTime();
            System.out.println("INSERTION: comp - " + insComp + ", exchanges - "
                    + insExchanges + ", " + (after - before + " nanosecs"));


            int low = 0;
            int high = arr2.length - 1;
            before = System.nanoTime();
            quickSort(arr2, low, high);
            after = System.nanoTime();
            System.out.println("QUICKSORT: comp - " + qsComp + ", exchanges - "
                    + qsExchanges + ", " + (after - before + " nanosecs"));


        } catch (Exception e) {

        }

    }

    public static void insertion(Integer[] arr) {
        for (int j = 1; j < arr.length; j++) {
            int element = arr[j];               //елемент, який будемо порівнювати з іншими, щоб вставити в необхідне місце
            int i = j-1;                        //і - для позначення попереднього елементу
            while ((i > -1) && (arr[i] > element)) {   //цикл, поки не знайдеться елемент менший за той, що запамятали
                insComp++;  //підрахунок кількості порівнянь
                arr[i+1] = arr[i];     //зміщуємо вправо елементи
                i--;                   //вказівник зміщуємо вліво
            }
            arr[i+1] = element;    //як тільки в циклі було знайдено потрібний елемент
            insExchanges++; //підсумовуємо кількість перестановок
            //той що ми запамятали перед ним вставляється в масив
        }
    }

    public static void quickSort(Integer[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;   //вихід з рекурсії
        }

        if (low >= high) {
            return; //вихід з рекурсії
        }

        int middle = low + (high - low) / 2; //середина заданих значень
        int pivot = arr[middle];             //визначення точки опори

        int i = low, j = high;
        while (i <= j) {                    //в циклі
            qsComp++;  //підрахунок кількості порівнянь

            while (arr[i] < pivot) {    //робимо ліву частину меншу за pivot
                i++;                //а праву - більшою
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;

                qsExchanges++; //підсумовуємо кількість перестановок
            }
        }

        if (low < j)
            quickSort(arr, low, j); //рекурсивно сортується ліва сторона

        if (high > i)
            quickSort(arr, i, high); //так само рекурсивно сортується права
    }


}
