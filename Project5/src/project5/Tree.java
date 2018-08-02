
package project5;

/**
 *
 * @author kyle
 */
public class Tree {
    Node root;
    int nodeCount;
    int sum;
 
    // A utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
    
    int getHeight(){
        return height(root);
    }
 
    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
 
    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
 
        // Perform rotation
        x.right = y;
        y.left = T2;
 
        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
 
        // Return new root
        return x;
    }
 
    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
 
        // Perform rotation
        y.left = x;
        x.right = T2;
 
        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
 
        // Return new root
        return y;
    }
 
    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
 
        return height(N.left) - height(N.right);
    }
    
    boolean isBalanced(){
        return !(getBalance(root)>1 || getBalance(root)<1);
    }
    
    void balance(){
        
    }
 
    Node insert(Node node, int val) {
 
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(val));
 
        if (val < node.val)
            node.left = insert(node.left, val);
        else if (val > node.val)
            node.right = insert(node.right, val);
        else // Duplicate keys not allowed
            return node;
 
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                              height(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);
 
        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && val < node.left.val)
            return rightRotate(node);
 
        // Right Right Case
        if (balance < -1 && val > node.right.val)
            return leftRotate(node);
 
        // Left Right Case
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
 
        // Right Left Case
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        /* return the (unchanged) node pointer */
        return node;
    }
    
    void add(int val){
        root = insert(root, val);
    }
    
    Node deleteRecursive(Node current, int value){
        if(current == null){
            return null;
        }
        
        if(value == current.val){
            // delete node code
            
            //if no children
            if(current.left == null && current.right == null){
                return null;
            }
            
            //if exclusively left or right child
            if(current.right == null){
                return current.left;
            }
            if(current.left == null){
                return current.right;
            }
            
            if(current.right != null && current.left != null){
                //if both left and right children
                int smallestValue = findSmallestValue(current.right);
                current.val = smallestValue;
                current.right = deleteRecursive(current.right, smallestValue);
                return current;
            }
        } 
        
        if(value < current.val){
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        if(value > current.val){
            current.right = deleteRecursive(current.right, value);
            return current;
        }
        return current;
    }
    
    void delete(int value){
        root = deleteRecursive(root, value);
    }
    
    boolean containsNodeRecursive(Node current, int value){
        if(current == null){
            return false;
        }
        if(value == current.val){
            return true;
        }
        return value < current.val 
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }
    
    boolean containsNode(int value){
        return containsNodeRecursive(root, value);
    }
    
    int countNodes(Node current){
        nodeCount = 0;
        if(current != null){
            nodeCount++;
            nodeCount += countNodes(current.left);
            nodeCount += countNodes(current.right);
        }
        return nodeCount;
    }
    
    int getCount(){
        return countNodes(root);
    }
    
    int countSum(Node current){
        sum = 0;
        if(current != null){
            sum += current.val;
            sum += countSum(current.left);
            sum += countSum(current.right);
        }
        return sum;
    }
    
    int getSum(){
        return countSum(root);
    }
    
    int getAverage(){
        return countSum(root)/countNodes(root);
    }
    
    int findSmallestValue(Node root){
        return root.left == null ? root.val : findSmallestValue(root.left);
    }
    
    int getSmallest(){
        return findSmallestValue(root);
    }
    
    int findGreatestValue(Node root){
        return root.right == null ? root.val : findGreatestValue(root.right);
    }
    
    int getGreatest(){
        return findGreatestValue(root);
    }
    
    int getHighestFull(){
        int count = getCount();
        int level = 0;
        int counter = 0;
        while(counter + Math.pow(2, level) <= count){
            counter += Math.pow(2, level++);
        }
        return level;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preorder(Node node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }
    
    public void aThroughE(int[] arr){
        for(int i : arr){
            this.add(i);
        }
        System.out.println("Count is: " + this.getCount());
        System.out.println("Height is: " + this.getHeight());
        System.out.println("Max element is: " + this.getGreatest());
        System.out.println("Sum of elements is: " + this.getSum());
        System.out.println("Average of elements is: " + this.getAverage());
    }
   
}
