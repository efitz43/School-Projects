
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class astar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Map
       // Setup
       // name x y
       // mapcup.txt
       File mapObj = new File("mapcup.txt");
       try{
           Scanner mapRead = new Scanner(mapObj);
           // get the map size
           String[] mapSize = mapRead.nextLine().split(" ");
           int x = Integer.parseInt(mapSize[1]);
           int y = Integer.parseInt(mapSize[2]);
           String[][] map = new String[x][y];
           //Fix null pointers
           for(String [] row: map)
            Arrays.fill(row, " ");
           
           String[] start = new String[3];
           String[] goal = new String[3];
           while(mapRead.hasNextLine()){
               String[] data = mapRead.nextLine().split(" ");
               //Check for end of file
               if(data[0].equals("E"))
                   break;
               // gets the start and goal
               if(data[0].equals("S")){
                   start = data;
               }
               else if(data[0].equals("G")){
                   goal = data;
               }
               //get the location
               x = Integer.parseInt(data[1]);
               y = Integer.parseInt(data[2]);
               //set the location to the correct name
               map[x][y] = data[0];
           }
           mapRead.close();
           //create barber file
           File barberObj = new File("barbercup.txt");
           Scanner barberRead = new Scanner(barberObj);
           int barberInst = 0;
           //get number of barbers
           while(barberRead.hasNext()){
               String check = barberRead.nextLine();
               if(check.equals("-1")){
                   break;
               }
               barberInst++;
           }
           barberRead.close();
           //Create Barber array
           int[][] barbers = new int[barberInst][3];
           int l = 0;
           barberRead = new Scanner(barberObj);
           while(barberRead.hasNext()){
               String[] data = barberRead.nextLine().split(" ");
               if(data[0].equals("-1")){
                   break;
               }
               barbers[l][0] = Integer.parseInt(data[0]);
               barbers[l][1] = Integer.parseInt(data[1]);
               barbers[l][2] = Integer.parseInt(data[2]);
               l++;
           }
           
           //find path
           int[] s = new int[2];
           int[] g = new int[2];
           s[0] = Integer.parseInt(start[1]);
           s[1] = Integer.parseInt(start[2]);
           g[0] = Integer.parseInt(goal[1]);
           g[1] = Integer.parseInt(goal[2]);
           //Search find = new Search(map,s,g);
           //map = find.ReturnPath();
           
           //print out map
           int time = 0;
           int k;
           for(int j = 0; j<barbers[barberInst-1][0] ; j++){
                System.out.println("TimeStep: " + time);
                //add the barbers
                for(k = 0; k<barberInst;k++){
                    if(barbers[k][0] == time){
                        map[barbers[k][1]][barbers[k][2]] = "B";
                    }
                }
                if(time==5){
                    int cool = 1;
                    cool++;
                }
                Search find = new Search(map,s,g);
                String [][] newMap = find.ReturnPath();
                for(String[] row : newMap){
                    for(String elem : row){
                        System.out.print(elem+" ");
                    }
                    System.out.println("");
                }
                //Remove barbers
                for(k = 0; k<barberInst;k++){
                    if(barbers[k][0] == time){
                        map[barbers[k][1]][barbers[k][2]] = " ";
                    }
                }
                map[s[0]][s[1]] = " ";
                s = find.getStart();
                map[s[0]][s[1]] = "S";
                time++;
           }
       }catch(FileNotFoundException e){
           System.out.println(e);
       }
       
       
       
    }
    
}
