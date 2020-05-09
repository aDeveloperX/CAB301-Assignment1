import java.util.ArrayList;

public class MovieCollection {
    //must use binary tree to store
    private Node root;
    private Movie[] arr = new Movie[10];
    private int index = 0;

    public MovieCollection() { }

    public Node search(String movieName){
        if(root ==null){
            return null;
        }
        Node current = root;
        while(!current.movie.getTitle().equals(movieName) ){
            if(movieName.compareTo(current.movie.getTitle()) == -1){
                current = current.left;
            }else{
                current = current.right;
            }
            //If this node his no node attached to
            if(current == null){
                return null;
            }
        }
        return current;
    }

    public boolean insert(Node node){
        if(root == null){
            root = node;
            return true;
        }
        //make sure each node is unique
        if(this.search(node.movie.getTitle()) != null){
            return false;
        }
        Node current = root;
        while(current != null){
            //try to insert to the left
            if(node.movie.getTitle().compareTo(current.movie.getTitle()) == -1){
                if(current.left == null){
                    current.left = node;
                    return true;
                }
                //keep searching next node
                current = current.left;
            }else{
                if(current.right == null){
                    current.right = node;
                    return true;
                }
                current = current.right;
            }
        }
        //no result found
        return false;
    }

    public boolean remove(String key){
        // if the tree is empty
        if(root == null){
            return false;
        }
        Node parent = root;
        Node target = root;
        boolean isLeft = true;
        //locate the parent of the target
        while(!target.movie.getTitle().equals(key)){
            if(key.compareTo(target.movie.getTitle()) == -1){
                parent = target;
                target = target.left;
                isLeft = true;
            }else{
                parent = target;
                target = target.right;
                isLeft = false;
            }
            if(target == null){
                return false;
            }
        }
        // if this node has no child at all
        if(target.left == null && target.right == null){
            if(target.movie.getTitle() == root.movie.getTitle()){
                root = null;
                return true;
            }
            if(isLeft){
                parent.left = null;
            }else{
                parent.right = null;
            }
            // if this node has no right child
        }else if(target.left != null && target.right == null){
            if(target.movie.getTitle() == root.movie.getTitle()){
                root = root.left;
                return true;
            }
            if(isLeft){
                parent.left = target.left;
            }else{
                parent.right = target.left;
            }
            // if this node has no left child
        }else if(target.left == null && target.right != null){
            if(target.movie.getTitle() == root.movie.getTitle()){
                root = root.right;
                return true;
            }
            if(isLeft){
                parent.left = target.right;
            }else{
                parent.right = target.right;
            }
            // if this node has both left and right child
        }else{
            Node childNode = this.getChildNode(target);
            if(target.movie.getTitle() == root.movie.getTitle()){
                root = childNode;
            }else if(isLeft){
                parent.left = childNode;
            }else{
                parent.right = childNode;
            }
            childNode.left = target.left;
            childNode.right = target.right;
        }
        return true;
    }

    private Node getChildNode(Node node){
        Node parent = node;
        Node n = node.right;
        while(n.left != null){
            parent = n;
            n = n.left;
        }
        if(n.movie.getTitle() != node.right.movie.getTitle()){
            parent.left = n.right;
        }else{
            parent.right = n.right;
        }
        return n;
    }

    public void iterateOver(Node node){
        if(root != null){
            if(node.left != null){
                this.iterateOver(node.left);
            }
            System.out.println(node.movie.getTitle());
            if(node.right != null){
                this.iterateOver(node.right);
            }
        }
    }

    public Movie[] toArray(Node node){
        if(root != null){
            if(node.left != null){
                this.toArray(node.left);
            }
            if(index < 10){
                arr[index] = node.movie;
                index++;
            }

            if(node.right != null){
                this.toArray(node.right);
            }
        }
        return arr;
    }

    public int getIndex() {
        return index;
    }

    public void resetArr(){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = null;
        }
        index = 0;
    }



    public Node getRoot(){
        return this.root;
    }
}
