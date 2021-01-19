
import static java.lang.Math.sqrt;

/* Biggest issue 
Goes to dead end

Bounced between dead end and block before
dead ends
Best potential broke it
Not even becoming dead end any more
3 5 is a dead end
*/




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Search {
   // private Node jumpPoints;
   // private Node current;
    //private Node upPath, downPath, rightPath, leftPath
    private Node currentPath;
   // private Node[] paths = {upPath, downPath, rightPath, leftPath};
    private String[][] map;
    
    //pass just x y
    private int[] start;
    private int[] goal;
    
    public Search(String[][] map, int[] start, int[] goal){
        this.map = map;
        this.start = start;
        this.goal = goal;
    }

    
    public String[][] ReturnPath(){
        //get the start of each node
        //Check if moving left would break the map
        
        
        //set locations to null when they can not be used
        //up
//        if(start[1]-1>=0){
//            //Node hold = new Node(start[0],start[1]-1);
//            Node hold = new Node(start[0],start[1]);
//            upPath = hold;
//            paths[0] = upPath;
//        }
//       
//        //down
//        if(start[1]+1<map.length){
//            //Node hold = new Node(start[0],start[1]+1);
//            Node hold = new Node(start[0],start[1]);
//            downPath = hold;
//            paths[1] = downPath;
//        }
//        //right
//        if(start[0]+1<map[0].length){
//            //Node hold = new Node(start[0]+1,start[1]);
//            Node hold = new Node(start[0],start[1]);
//            rightPath = hold;
//            paths[2] = rightPath;
//        }
//        //left
//        if(start[0]-1>=0){
//            //Node hold = new Node(start[0]-1,start[1]);
//            Node hold = new Node(start[0],start[1]);
//            leftPath = hold;
//            paths[3] = leftPath;
//        }
        // Need a new map beacause it will modify original
        String [][] newMap = new String[map.length][map[0].length];
        for(int i=0;i<map.length;i++)
            System.arraycopy(map[i], 0, newMap[i], 0, map[0].length);
        
        Node hold = new Node(start[0],start[1]);
        currentPath = hold;
        currentPath = searchPath();
        //Preserve goal
        currentPath = currentPath.getPrevious();
        int[]temp;
        while(currentPath.getPrevious() != null){
            temp = currentPath.getLocation();
            newMap[temp[0]][temp[1]] = "*";
            currentPath = currentPath.getPrevious();
        }
        newMap[start[0]][start[1]] = " ";
        start = currentPath.getNext().getLocation();
        newMap[start[0]][start[1]] = "S";
        
        
        return newMap;
    }
    
    public int[] getStart(){
        return start;
    }
    
    //private boolean searchPath(){
    private Node searchPath(){
        double bestPotential = 100;
        double currentPotential;
        int pathLoc=-1;
        int x,y,bestX,bestY;
        bestX = start[0];
        bestY = start[1];
        //int location[] = new int[2];
        do{
            //double bestPotential = 100;
//            //need to keep track of the right direction
//            for(int i = 0 ; i<paths.length ;i++){
//                if(paths[i]==null||paths[i].getLocation()==null){
//                    continue;
//                }else{
//                    location = paths[i].getLocation();
//                }
//
//    //            //going up
//    //            if(location[1]-1>=0){
//    //                //finding the best movement
//    //                x = location[0]-goal[0];
//    //                y = location[1]-1-goal[1];
//    //                currentPotential = sqrt(x*x+y*y);
//    //                if(bestPotential>currentPotential){
//    //                    //links the node to the path
//    //                    hold.setLocation(x,y);
//    //                    hold.setPrevious(path);
//    //                    currentPath = path;
//    //                    path.setNext(hold);
//    //                }
//    //            }
//
//                //going up
//                if(location[1]-1>=0){
//                    //finding the best movement
//                    x = location[0]-goal[0];
//                    y = location[1]-1-goal[1];
//                    currentPotential = sqrt(x*x+y*y);
//                    x = location[0];
//                    y = location[1]-1;
//                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
//                    if(bestPotential>currentPotential){
//                        //gets best location and path
//                        bestPotential = currentPotential;
//                        bestX = location[0];
//                        bestY = location[1]-1;
//                        pathLoc = i;
//                    }
//                }
//
//    //            //going down
//    //            if(location[1]+1<map.length){
//    //                //finding the best movement
//    //                x = location[0]-goal[0];
//    //                y = location[1]+1-goal[1];
//    //                currentPotential = sqrt(x*x+y*y);
//    //                if(bestPotential>currentPotential){
//    //                    //links the node to the path
//    //                    hold.setLocation(location[0],location[1]+1);
//    //                    hold.setPrevious(path);
//    //                    currentPath = path;
//    //                    path.setNext(hold);
//    //                }
//    //            }
//
//                //going down
//                if(location[1]+1<map.length){
//                    //finding the best movement
//                    x = location[0]-goal[0];
//                    y = location[1]+1-goal[1];
//                    currentPotential = sqrt(x*x+y*y);
//                    x = location[0];
//                    y = location[1]+1;
//                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
//                    if(bestPotential>currentPotential){
//                        //links the node to the path
//                        bestPotential = currentPotential;
//                        bestX = location[0];
//                        bestY = location[1]+1;
//                        pathLoc = i;
//
//                    }
//                }
//
//    //            //going right
//    //            if(location[0]+1<map[0].length){
//    //                //finding the best movement
//    //                x = location[0]+1-goal[0];
//    //                y = location[1]-goal[1];
//    //                currentPotential = sqrt(x*x+y*y);
//    //                if(bestPotential>currentPotential){
//    //                    //links the node to the path
//    //                    hold.setLocation(location[0]+1,location[1]);
//    //                    hold.setPrevious(path);
//    //                    currentPath = path;
//    //                    path.setNext(hold);
//    //                }
//    //            }
//
//                //going right
//                if(location[0]+1<map[0].length){
//                    //finding the best movement
//                    x = location[0]+1-goal[0];
//                    y = location[1]-goal[1];
//                    currentPotential = sqrt(x*x+y*y);
//                    x = location[0]+1;
//                    y = location[1];
//                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
//                    else if(bestPotential>currentPotential){
//                        bestPotential = currentPotential;
//                        //links the node to the path
//                        bestX = location[0]+1;
//                        bestY = location[1];
//                        pathLoc = i;
//                    }
//                }
//    //            //going left
//    //            if(location[0]-1>=0){
//    //                //finding the best movement
//    //                x = location[0]-1-goal[0];
//    //                y = location[1]-goal[1];
//    //                currentPotential = sqrt(x*x+y*y);
//    //                if(bestPotential>currentPotential){
//    //                    //links the node to the path
//    //                    hold.setLocation(location[0]-1,location[1]);
//    //                    hold.setPrevious(path);
//    //                    currentPath = path;
//    //                    path.setNext(hold);
//    //                }
//    //            }
//                 //going left
//                if(location[0]-1>=0){
//                    //finding the best movement
//                    x = location[0]-1-goal[0];
//                    y = location[1]-goal[1];
//                    currentPotential = sqrt(x*x+y*y);
//                    x = location[0]-1;
//                    y = location[1];
//                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
//                    if(bestPotential>currentPotential){
//                        bestPotential = currentPotential;
//                        //links the node to the path
//                        bestX = location[0]-1;
//                        bestY = location[1];
//                        pathLoc = i;
//                    }
//                }
//            }
//----------------------------------------------------------------
            //need to keep track of the right direction
//            for(int i = 0 ; i<paths.length ;i++){
//                if(paths[i]==null||paths[i].getLocation()==null){
//                    continue;
//                }else{
//                    location = paths[i].getLocation();
//                }
                int [] location;
                location = currentPath.getLocation();
                boolean foundPath = false;

                //going up
                if(location[0]-1>=0){
                    //finding the best movement
                    x = location[0]-1-goal[0];
                    y = location[1]-goal[1];
                    currentPotential = sqrt(x*x+y*y);
                    x = location[0]-1;
                    y = location[1];
                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
                    //if(map[y][x].equals("B")||map[y][x].equals("W")){}
                    else if(deadEnd(x,y)){}
                    else if(previousFix(x,y)){}
                    //else if(!previousFix(x,y)){}
                    ///else if(!previousFix(x,y)||deadEnd(x,y)){}
                    else if(bestPotential>currentPotential){
                        //gets best location and path
                        bestPotential = currentPotential;
                        bestX = x;
                        bestY = y;
                        foundPath = true;
                    }
                }

                //going down
                if(location[0]+1<map.length){
                    //finding the best movement
                    x = location[0]+1-goal[0];
                    y = location[1]-goal[1];
                    currentPotential = sqrt(x*x+y*y);
                    x = location[0]+1;
                    y = location[1];
                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
                    else if(deadEnd(x,y)){}
                    else if(previousFix(x,y)){}
                    //else if(!previousFix(x,y)||deadEnd(x,y)){}
                    else if(bestPotential>currentPotential){
                        //links the node to the path
                        bestPotential = currentPotential;
                        bestX = x;
                        bestY = y;
                        foundPath = true;
                    }
                }

  
                //going right
                if(location[1]+1<map[0].length){
                    //finding the best movement
                    x = location[0]-goal[0];
                    y = location[1]+1-goal[1];
                    currentPotential = sqrt(x*x+y*y);
                    x = location[0];
                    y = location[1]+1;
                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
                    else if(deadEnd(x,y)){}
                    else if(previousFix(x,y)){}
                    //else if(!previousFix(x,y)||deadEnd(x,y)){}
                    else if(bestPotential>currentPotential){
                        bestPotential = currentPotential;
                        //links the node to the path
                        bestX = x;
                        bestY = y;
                        foundPath = true;
                    }
                }
  
                 //going left
                if(location[1]-1>=0){
                    //finding the best movement
                    x = location[0]-goal[0];
                    y = location[1]-1-goal[1];
                    currentPotential = sqrt(x*x+y*y);
                    x = location[0];
                    y = location[1]-1;
                    if(map[x][y].equals("B")||map[x][y].equals("W")){}
                    else if(deadEnd(x,y)){}
                    else if(previousFix(x,y)){}
                    //else if(!previousFix(x,y)||deadEnd(x,y)){}
                    else if(bestPotential>currentPotential){
                        bestPotential = currentPotential;
                        //links the node to the path
                        bestX = x;
                        bestY = y;
                        foundPath = true;
                    }
                }
//            
//            if(pathLoc==-1){
//                return null;
//            }else{
//        
////                Node hold = new Node(bestX,bestY,null,paths[pathLoc]);
////                paths[pathLoc].setNext(hold);
////                paths[pathLoc] = hold;
////                //currentPath = hold;
//                Node hold = new Node(bestX,bestY,null,paths[pathLoc]);
//                currentPath.setNext(hold);
//                currentPath = hold;
//            }
           
        
//                Node hold = new Node(bestX,bestY,null,paths[pathLoc]);
//                paths[pathLoc].setNext(hold);
//                paths[pathLoc] = hold;
//                //currentPath = hold;
            if(foundPath){
                Node hold = new Node(bestX,bestY,null,currentPath);
                currentPath.setNext(hold);
                currentPath = hold;
                foundPath = false;
            }else{
//                //bestPotential = 100;
//                Node hold = currentPath.getPrevious();
//                //currentPath.setSearched(true);
//                hold.setDeadNode(currentPath);
//                hold.getDeadNode().setSearched(true);
                //Node hold = currentPath.getPrevious();
                //currentPath.setSearched(true);
                bestPotential = 100;
                currentPath.getPrevious().setDeadNode(currentPath);
                currentPath.setSearched(true);
//                currentPath = hold;
//                currentPath.setNext(null);
                foundPath = false;
                
            }

        }while(!map[bestX][bestY].equals("G"));
        
//        do{
//            currentPath.setNext(paths[pathLoc]);
//            paths[pathLoc] = paths[pathLoc] 
//            
//        }while(paths[pathLoc].getPrevious()!=null);
       
        return currentPath;
    }
    
    private boolean deadEnd(int x, int y){
//        Node hold = currentPath.getNext();
//        if(hold == null){
//            return false;
//        }
//        int [] temp = hold.getLocation();
//        if(hold.getSearched()&& temp[0] == x && temp[1] == y){
////            map[temp[0]][temp[1]] = "W";
//            return true;
//        }
        Node hold = currentPath.getDeadNode();
        if(hold == null){
            return false;
        }
        int [] temp = hold.getLocation();
        if(hold.getSearched()&& temp[0] == x && temp[1] == y){
//            map[temp[0]][temp[1]] = "W";
            return true;
        }
        return true;
    }
    
    private boolean previousFix(int x,int y){
        Node hold = currentPath.getPrevious();
        if(hold == null){
            return false;
        } 
        int [] temp = hold.getLocation();
        if(temp[0] == x && temp[1] == y)
            return true;
        return false;
    }
    private class Node{
        //location [x][y]
        private int[] location;
        private Node next;
        private Node previous;
        private Node deadNode = null;
        private boolean searched = false;
        
        public Node(int x, int y){
            int[] temp = new int[2];
            temp[0] = x;
            temp[1] = y;
            location = temp;
            next = null;
            previous = null;
        }
        public Node(int x, int y, Node next, Node previous){
            int[] temp = new int[2];
            temp[0] = x;
            temp[1] = y;
            location = temp;
            this.next = next;
            this.previous = previous;
        }
        public Node(int[] location , Node next, Node previous){
            this.location = location;
            this.next = next;
            this.previous = previous;
        }
        
        public Node getNext(){
            return next;
        }
        
        public void setNext(Node next){
            this.next = next;
        }
        
        public Node getPrevious(){
            return previous;
        }
        
        public void setPrevious(Node previous){
            this.previous = previous;
        }
        
        public int[] getLocation(){
            return location;
        }
        
        public void setLocation(int x, int y){
            location[0] = x;
            location[1] = y;
        }
        
        public boolean getSearched(){
            return searched;
        }
        
        public void setSearched(boolean searched){
            this.searched = searched;
        }

        public Node getDeadNode() {
            return deadNode;
        }

        public void setDeadNode(Node deadNode) {
            this.deadNode = deadNode;
        }
        
        
    }


}
