
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class ArrayNode extends Node implements Iterable<Node>{
    
    private final ArrayList<Node> nodes; 
    
    @Override
    public  Iterator<Node> iterator(){
        return nodes.iterator();
    }
    
    ArrayNode(){
        this.nodes = new ArrayList<>();
    }
    
    public void add(Node node){
        this.nodes.add(node);
    }
    
    public int size(){
        return nodes.size();
    }
    
}
