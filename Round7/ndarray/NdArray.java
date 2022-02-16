
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
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
    
    private final int nDims;
    private final int[] dims;
    private final ArrayList<E> arrayContainer;
    
    NdArray(Integer firstDimLen, Integer ...furtherDimLens) throws NegativeArraySizeException{
        // Check thet all dimensions are non-negative
        if(firstDimLen <= 0){
            throw new NegativeArraySizeException(String.format("Illegal dimension size %d", firstDimLen));
        } else {
            for(int dimLen: furtherDimLens){
                if (dimLen <= 0){
                    throw new NegativeArraySizeException(String.format("Illegal dimension size %d", dimLen));
                }
            }
        }
        
        // Store number of dimensions into nDims
        nDims = 1 + furtherDimLens.length;
        
        // Storing dimensions into an array
        dims = Stream.concat(Stream.of(firstDimLen), Arrays.stream(furtherDimLens))
                .mapToInt(Integer::intValue).toArray();
        
        // Initialize the array at size prod(dims)
        int prodDims = 1;
        for (int e : dims){
            prodDims *= e;
        }
        arrayContainer = new ArrayList<>(prodDims);
    }
    
    @Override
    public int size(){
        return Arrays.stream(dims).reduce(1, (a,b) -> (a*b));
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public E get(int... indices){
        int index = Arrays.stream(indices).limit(indices.length - 1)
                .reduce(1, (a,b) -> (a*b)) + indices[indices.length - 1];
        return arrayContainer.get(index);
    }
    
    
}
