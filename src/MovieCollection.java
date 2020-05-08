import java.util.ArrayList;

public class MovieCollection {
    //must use binary tree to store
    private Node root;

    public MovieCollection() {
    }

    public Node search(int key){
        if(root ==null){
            return null;
        }
        Node current = root;
        while(current.value != key){
            if(key < current.value){
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
        if(this.search(node.value) != null){
            return false;
        }
        Node current = root;
        while(current != null){
            //try to insert to the left
            if(node.value < current.value){
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

    public boolean remove(int key){
        // if the tree is empty
        if(root == null){
            return false;
        }
        Node parent = root;
        Node target = root;
        boolean isLeft = true;
        while(target.value != key){
            if(key < target.value){
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
        if(target.left == null && target.right == null){
            if(target.value == root.value){
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
            if(target.value == root.value){
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
            if(target.value == root.value){
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
            if(target.value == root.value){
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
        if(n.value != node.right.value){
            parent.left = n.right;
        }else{
            parent.right = n.right;
        }
        return n;
    }

}
