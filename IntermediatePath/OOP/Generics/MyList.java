public class MyList<T> {
    private T[] array;
    private int size;
    private int capacity;

    // Default Constructor
    public MyList() {
        this.capacity = 10;
        this.array = (T[]) new Object[this.capacity];
    }

    // Constructor with initial capacity
    public MyList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[this.capacity];
    }

    public void add(T data) {
        if (size == capacity) doubleCapacity();
        // Increase the size and add the data to the (size)th index. If size = 0, array[0] = data and size = 1
        this.array[size++] = data;
    }

    public void doubleCapacity() {
        // Create a new Object array with doubled capacity
        Object[] temp = new Object[capacity * 2];
        // Copy the array to the new array
        System.arraycopy(this.array, 0, temp, 0, this.capacity);
        this.capacity *= 2;
        // Set the array to the new array (with casting to T[])
        this.array = (T[]) temp;
    }

    public T get(int index) {
        if (invalidIndex(index)) return null;
        return this.array[index];
    }

    public T remove(int index) {
        if (invalidIndex(index)) return null;

        // Hold the removed data to return
        T removed = this.array[index];
        // Set removed index to null
        this.array[index] = null;

        // Shift the remaining elements to left
        shiftLeft(index);

        // Decrease the size and return removed
        this.size--;
        return removed;
    }

    public void shiftLeft(int start) {
        // Shifting every element to left from start to size - 1 (size - 1 is the index of last element)
        for (int i = start; i < size - 1; i++) {
            this.array[start] = this.array[start + 1];
        }
    }

    public T set(int index, T data) {
        if (invalidIndex(index)) return null;

        this.array[index] = data;

        return data;
    }

    public boolean invalidIndex(int index) {
        return index > this.size;
    }

    public int indexOf(T data) {
        for (int i = 0; i < size; i++) {
            if (equals(this.array[i], data)) return i;
        }
        return -1;
    }

    public int lastIndexOf(T data) {
        // Only difference from IndexOf is starts with size - 1 to 0
        for (int i = size - 1; i >= 0; i--) {
            if (equals(this.array[i], data)) return i;
        }

        return -1;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T[] toArray() {
        return this.array;
    }

    public void clear() {
        while (size != 0) {
            this.array[size - 1] = null;
            this.size--;
        }
    }

    // To create a sublist, we need to create a new object and add every element from start to finish to the new object
    public MyList<T> subList(int start, int finish) {
        MyList<T> res = new MyList<>();

        // If we write while(start != finish) we won't add the last element, so start <= finish works best. Could be also while(start != finish + 1)
        while (start <= finish) {
            res.add(this.array[start]);
            start++;
        }

        return res;
    }

    public boolean contains(T data) {
        for (T value : this.array) {
            if (isNull(value)) break;
            if (equals(value, data)) return true;
        }
        return false;
    }

    public String toString() {
        if(isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(T value : this.array) {
            if(isNull(value)) break;
            sb.append(value).append(", ");
        }

        // After the loop, sb will be something like this -->  "[10, 20, 30, ", we need to replace the ", " with "]"
        sb.replace(sb.length() - 2, sb.length() - 1, "]");

        return sb.toString();

        // We could also use this.array.toString() but I want to create my own func.
    }


    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isNull(T value) {
        return value == null;
    }

    public boolean equals(T data1, T data2) {
        return data1 == data2;
    }
}
