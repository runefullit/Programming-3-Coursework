/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Olli
 */
public class Circle implements IShapeMetrics {
    
    private double radius;
    
    public Circle(double radius){
        this.radius = radius;
    }
    
    @Override
    public String toString(){
        return String.format("Circle with radius: %.2f", radius);
    }
    
    public String name(){
        return "circle";
    }
    
    @Override
    public double area(){
        return PI * radius * radius;
    }
    
    @Override
    public double circumference(){
        return PI * 2 * radius;
    }
    
}
