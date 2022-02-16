
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class NdArray<E> extends AbstractCollection<E>{
    
    private class NdArrayIterator implements Iterator<E> {
        /*
        Simple iterator for a java Array
        */

        private int i = 0;
        
        @Override
        public boolean hasNext() {
            return arrayContainer.length > i;
        }

        @Override
        public E next() {
            return arrayContainer[i++];
        }
        
    }
    
    // Class variables
    private final int[] dims;
    private final E[] arrayContainer;
    
    NdArray(Integer firstDimLen, Integer ...furtherDimLens){
        // Check thet all dimensions are non-negative
        if(firstDimLen <= 0){
            throw new NegativeArraySizeException(String.format("Illegal dimension size %d.", firstDimLen));
        } else {
            for(int dimLen: furtherDimLens){
                if (dimLen <= 0){
                    throw new NegativeArraySizeException(String.format("Illegal dimension size %d.", dimLen));
                }
            }
        }
        
        // Storing dimensions into an array
        dims = Stream.concat(Stream.of(firstDimLen), Arrays.stream(furtherDimLens))
                .mapToInt(Integer::intValue).toArray();
        
        // Initialize the array at size prod(dims)
        int prodDims = 1;
        for (int e : dims){
            prodDims *= e;
        }
        arrayContainer = (E[]) new Object[prodDims];
    }
    
    @Override
    public int size(){
        return Arrays.stream(dims).reduce(1, (a,b) -> (a*b));
    }

    @Override
    public Iterator<E> iterator() {
        return new NdArrayIterator();
    }
    
    public E get(int... indices) {
        checkIndices(indices);
        // Find the index on our row-major layout.
        return arrayContainer[getIndex(indices, dims)];
    }
    
    public void set(E item, int... indices) {
        checkIndices(indices);
        arrayContainer[getIndex(indices,dims)] = item;
    }
    
    public int[] getDimensions(){
        return dims;
    }
    
    private void checkIndices(int[] indices){
        /*
        Check that the coordinates in the indices are legal.
        */
        if (indices.length != dims.length){
            throw new IllegalArgumentException(String.format("The array has %d dimensions but %d indices were given.",
                    dims.length, indices.length));
        } else {
            for (int i = 0; i < indices.length; i++) {
                if (indices[i] > dims[i] - 1){
                    throw new IndexOutOfBoundsException(String.format("Illegal index %d for dimension %d of length %d.",
                            indices[i], i+1, dims[i]));
                }
            }
        }
    }
    
    private int getIndex(int[] indices, int[] dims){
        /*
        Given coordinates and matrix dimensions, finds row-major coordinate recursively.
        */
        if(indices.length > 1){
            int lastIndice = indices[indices.length - 1];
            int lastDim = dims[dims.length - 1];

            int[] poppedIndices = Arrays.copyOf(indices, indices.length - 1);
            int[] poppedDims = Arrays.copyOf(dims, dims.length - 1);

            return (lastIndice + lastDim * getIndex(poppedIndices, poppedDims));
        } else {
            return indices[0];
        }
    }
    
}
