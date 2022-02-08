
import java.util.Iterator;
import java.util.TreeMap;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class ObjectNode extends Node implements Iterable<String>{
    
    private final TreeMap<String, Node> tmap;
    
    ObjectNode(){
        this.tmap = new TreeMap<>();
    }
    
    @Override
    public  Iterator<String> iterator(){
        return tmap.keySet().iterator();
    }
    
    public Node get(String key){
        return tmap.get(key);
    }
    
    public void set(String key, Node node){
        tmap.put(key, node);
    }
    
    public int size(){
        return tmap.size();
    }
}
