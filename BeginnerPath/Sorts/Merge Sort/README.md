Array = [16,21,11,8,12,22]

### **Step 1**
Split array by 2 until every subarray has 1 element

* [16,21,11], [8,12,22]
* [16,21], [11], [8,12], [22]
* [16], [21], [11], [8], [12], [22]

### **Step 2**
Merge every neighbor element with merging

* [16, 21], [11], [8, 12], [22]
* [11, 16, 21], [8, 12, 22]
* [8, 11, 12, 16, 21, 22]

Time Complexity = O(logn)
