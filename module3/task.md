## Java.MP.C4.M1.MemoryManagement Default Task2	
- Please add to your application code which includes passing by reference.
- Please don't add returning the reference from method. 
  Make creation of the new object in method where you pass value by reference (the new object is for value). 
- Please analyse the code structure, change the code by returning the value of scalar type and object type. 
- Please give 256 M for tenured area of heap, I want to have the following heap structure :3-Eden,3-S0,3-S1, give 1M to thread stack.

## Java.MP.C4.M1.MemoryManagement Default Task4	
Design and implement code that will introduce:
1. java.lang.OutOfMemoryError: Java heap space. Create big objects continuously and make them stay in memory.
2. java.lang.OutOfMemoryError: PermGen space. Load classes continuously and make them stay in memory.
3. Use options like -XX:MaxPermSize=1m -Xmx10m to reach exception quicker.

## Java.MP.C4.M1.MemoryManagement Default Task5
1. Install MAT for eclipse. Review plugin features
2. Use apps from previous tasks, investigate leak suspects.

## Java.MP.C4.M1.MemoryManagement Default Task6	
Design and implement code that will introduce:
1. java.lang.OutOfMemoryError: Java heap space. Do not use arrays or collections.
2. java.lang.StackOverflowError. Do not use recursive methods.