package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    int[] mergeSortArray;
    int mergeSortCounter = 0;
    private static int mergeSortSwaps = 0;
    private static int mergeComparisons = 0;

    public void readArrayForMergeSort() throws IOException {
        Scanner scan = new Scanner(new File("/Users/ione/work/NULP/2year_1part/java/Sorting/src/input100.txt")); //provide file name from outside
        while (scan.hasNextInt()) {
            mergeSortCounter++;
            scan.nextInt();
        }
        Scanner scan2 = new Scanner(new File("/Users/ione/work/NULP/2year_1part/java/Sorting/src/input100.txt"));
        mergeSortArray = new int[mergeSortCounter];
        for (int i = 0; i < mergeSortCounter; i++) {
            mergeSortArray[i] = scan2.nextInt(); //fill the array with the integers
        }
    }

    public void merge_sort(int left, int right) {
        // Check if low is smaller then high, if not then the array is sorted
        if (left < right) {
            // Get the index of the element which is in the middle
            int mid = (left + right) / 2;
            // Sort the left side of the array
            merge_sort(left, mid);
            // Sort the right side of the array
            merge_sort(mid + 1, right);
            // Combine them both
            merge(left, mid, right);
        }
        mergeComparisons++;

    }

    public void merge(int left, int mid, int right) {
        int temp[] = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (mergeSortArray[i] <= mergeSortArray[j]) {
                temp[k] = mergeSortArray[i];
                k++;
                i++;
            } else {
                // array[i]>array[j]

                temp[k] = mergeSortArray[j];
                k++;
                j++;
            }
        }
        while (j <= right)
            temp[k++] = mergeSortArray[j++];
        while (i <= mid)
            temp[k++] = mergeSortArray[i++];

        for (k = 0; k < temp.length; k++)
            mergeSortArray[left + k] = temp[k];
        mergeSortSwaps++;

    }

    public void mergeSort() {

        merge_sort(0, mergeSortCounter - 1);
    }

    public void printMergeSortArray() {
        // print the elements of array
        for (int i = 0; i < mergeSortCounter; i++) {
            System.out.print(mergeSortArray[i] + ",");
        }
        System.out.println("");
    }

    public static void main(String args[]) throws IOException {
        Main mergeSorter = new Main();
        mergeSorter.readArrayForMergeSort();
        System.out.println("----------------------\n" + "MergeSort\n" +
                "----------------------\n");
        System.out.println("Array before sort :");
        mergeSorter.printMergeSortArray();
        mergeSorter.mergeSort();
        System.out.println("\n" + "Array after sort :");
        mergeSorter.printMergeSortArray();
        System.out.println();
        System.out.println("Comparations : " + mergeComparisons + "\n"
                + "Swaps : " + mergeSortSwaps);

    }
}
