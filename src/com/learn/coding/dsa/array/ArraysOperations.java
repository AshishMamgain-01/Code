package com.learn.coding.dsa.array;

public class ArraysOperations {

    public static void main(String[] args) {
        // Main method boilerplate code
        System.out.println("ArraysOperations main method started.");
        
        int[] array = {1, 2, 3, 4, 5};
        int item = 10;

        // Call insertAtFirst method
        int[] newArrayFirst = insertAtFirst(array, item);
        System.out.println("Array after inserting at first: " + java.util.Arrays.toString(newArrayFirst));

        // Call insertAtLast method
        int[] newArrayLast = insertAtLast(array, item);
        System.out.println("Array after inserting at last: " + java.util.Arrays.toString(newArrayLast));

        // Call insertAtMiddle method
        int[] newArrayMiddle = insertAtMiddle(array, item);
        System.out.println("Array after inserting at middle: " + java.util.Arrays.toString(newArrayMiddle));

        // Call insertAtPosition method
        int[] newArrayAtPosition = insertAtPosition(array, item, 2);
        System.out.println("Array after inserting at position 2: " + java.util.Arrays.toString(newArrayAtPosition));

        // Call deleteFromFirst method
        int[] arrayAfterDeleteFirst = deleteFromFirst(array);
        System.out.println("Array after deleting from first: " + java.util.Arrays.toString(arrayAfterDeleteFirst));

        // Call deleteFromLast method
        int[] arrayAfterDeleteLast = deleteFromLast(array);
        System.out.println("Array after deleting from last: " + java.util.Arrays.toString(arrayAfterDeleteLast));

        // Call deleteFromMiddle method
        int[] arrayAfterDeleteMiddle = deleteFromMiddle(array);
        System.out.println("Array after deleting from middle: " + java.util.Arrays.toString(arrayAfterDeleteMiddle));

        // Call deleteFromPosition method
        int[] arrayAfterDeleteAtPosition = deleteFromPosition(array, 2);
        System.out.println("Array after deleting from position 2: " + java.util.Arrays.toString(arrayAfterDeleteAtPosition));

    }

    // Method to insert an item at the first position of the array
    public static int[] insertAtFirst(int[] array, int item) {
        int[] newArray = new int[array.length + 1];
        newArray[0] = item;
        for (int i = 0; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }
        return newArray;
    }

    // Method to insert an item at the last position of the array
    public static int[] insertAtLast(int[] array, int item) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = item;
        return newArray;
    }

    // Method to insert an item at the middle position of the array
    public static int[] insertAtMiddle(int[] array, int item) {
        int[] newArray = new int[array.length + 1];
        int middle = array.length / 2;
        for (int i = 0; i < middle; i++) {
            newArray[i] = array[i];
        }
        newArray[middle] = item;
        for (int i = middle; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }
        return newArray;
    }

    // Method to insert an item at a specified position of the array
    public static int[] insertAtPosition(int[] array, int item, int position) {
        if (position < 0 || position > array.length) throw new IndexOutOfBoundsException("Invalid position");
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }
        newArray[position] = item;
        for (int i = position; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }
        return newArray;
    }

    // Method to delete an item from the first position of the array
    public static int[] deleteFromFirst(int[] array) {
        if (array.length == 0) return array;
        int[] newArray = new int[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        return newArray;
    }

    // Method to delete an item from the last position of the array
    public static int[] deleteFromLast(int[] array) {
        if (array.length == 0) return array;
        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    // Method to delete an item from the middle position of the array
    public static int[] deleteFromMiddle(int[] array) {
        if (array.length == 0) return array;
        int[] newArray = new int[array.length - 1];
        int middle = array.length / 2;
        for (int i = 0; i < middle; i++) {
            newArray[i] = array[i];
        }
        for (int i = middle + 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        return newArray;
    }

    // Method to delete an item from a specified position of the array
    public static int[] deleteFromPosition(int[] array, int position) {
        if (position < 0 || position >= array.length) throw new IndexOutOfBoundsException("Invalid position");
        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }
        for (int i = position + 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        return newArray;
    }
}
