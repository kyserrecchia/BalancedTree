
package project5;

/**
 *
 * @author kyle
 */
public class Project5 {
    
    public static void main(String[] args){
        
        Tree pine = new Tree();
        
//        int [] data = { 50, 30, 60, 10, 80, 55, 40, 85, 75, 65, 95, 45, 35, 25, 15, 44 };
        int [] data = { 50, 30, 600, 10, 80};
        
        pine.aThroughE(data);
        
        int someVal = 0;
        pine.containsNode(someVal);
    }
}