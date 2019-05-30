import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;

public class ex1Labyrinth {
    private enum cellType{
        empty,
        wall,
        path
    };
    private static final int fieldDimensions = 2;
    private static cellType [][] field;
    private static cellType [][] findPath(int Ax, int Ay, int Bx, int By){
        //breadth first search. Assume Ax,Ay,Bx,By < field.length
        int n = field.length;
        cellType [][] res = new cellType[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) res[i][j] = field[i][j];
        }
        if(Ax == Ay && Bx == By){
            res[Ax][Ay] = cellType.path;
            return res;
        }
        boolean [][] visited = new boolean[n][n];
        for (int i=0; i< n; i++) {
            Arrays.fill(visited[i], false);
            for (int j=0; j<n; j++){
                if(field[i][j] == cellType.wall)
                    visited[i][j] = true; //we don't want to visit walls
            }
        }
        ArrayDeque<Integer[]> q = new ArrayDeque<Integer[]>();
        Integer [] point = {Ax, Ay};
        q.add(point);
        visited[Ax][Ay] = true;
        int x, y;
        Integer [][][] previous = new Integer [n][n][fieldDimensions];
        ArrayList<Integer []> next = new ArrayList<Integer[]>();
        bfs:while(!q.isEmpty()){
            point = q.poll();
            x = point[0];
            y = point[1];
            /*
            *            (x, y-1)
            *   (x-1, y) (x,y) (x+1, y)
            *           (x, y+1)
            * */
            if(x + 1 < n && !visited[x+1][y]){
                Integer[] nextPoint = {x + 1, y};
                next.add(nextPoint);
            }
            if(x - 1 >= 0 && !visited[x-1][y]){
                Integer[] nextPoint = {x - 1, y};
                next.add(nextPoint);
            }
            if(y + 1 < n && !visited[x][y + 1]){
                Integer[] nextPoint = {x, y + 1};
                next.add(nextPoint);
            }
            if(y - 1 >= 0 && !visited[x][y - 1]){
                Integer[] nextPoint = {x, y - 1};
                next.add(nextPoint);
            }
            for(Integer[] nextPoint:next){
                int xNext = nextPoint[0];
                int yNext = nextPoint[1];
                previous[xNext][yNext][0] = point[0];
                previous[xNext][yNext][1] = point[1];
                visited[xNext][yNext] = true;
                if(xNext == Bx && yNext == By) break bfs;
                q.add(nextPoint);
            }
            next.clear();
        }
        point[0] = Bx;
        point[1] = By;
        if(!visited[Bx][By]) return null;
        int pathLength = 0;
        while(!(point[0].equals(Ax) && point[1].equals(Ay))){
            x = point[0];
            y = point[1];
            res[point[0]][point[1]] = cellType.path;
            point[0] = previous[x][y][0];
            point[1] = previous[x][y][1];
            pathLength++;
        }
        System.out.println("Path found! The journey takes " + pathLength + " cells");
        res[Ax][Ay] = cellType.path;
        return res;
    }
    private static String printPath(cellType [][] path){
        StringBuilder sb = new StringBuilder();
        for(cellType [] row:path){
            for(cellType cell : row){
                switch(cell){
                    case empty:
                        sb.append(" ");
                        break;
                    case wall:
                        sb.append("#");
                        break;
                    case path:
                        sb.append("*");
                }
            }
            sb.append("|\n");
        }
        String res = sb.toString();
        return res;
    }
    /*
    Calculates the path from A(Ax, Ay) to B(Bx, By): min(Ax,Ay,Bx,By) >= 0
    Ax,Ay,Bx,By are to be typed in console
    field is red from "input.txt":
    first line - int n > 0, n >= max(Ax, Ay, Bx, By)
    next n lines of n chars - labyrinth: 0 - empty cell, 1 - wall
    result is printed to console
     */
    public static void launch() {
        String filename = "input.txt";
        int n;
        cellType [][] Field;
        System.out.println("reading field from 'input.txt'...");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            n = Integer.parseInt(line);
            Field = new cellType[n][n];
            for(int i=0; i<n; i++){
                line = br.readLine();
                if(line.length() < n){
                    System.err.println("input field too small");
                    return;
                }
                for(int j=0; j<n; j++){
                    if(line.charAt(j) == '0') Field[i][j] = cellType.empty;
                    else Field[i][j] = cellType.wall;
                }
            }
            br.close();
        }catch (IOException e){
            System.err.println("Input error: "+e);
            return;
        }
        field = Field;
        int Ax, Ay, Bx, By;
        String line;
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bis = new BufferedReader(is);
        System.out.println("field " + n + " * " + n + " initialized.");
        System.out.println(printPath(field));
        System.out.println(" Now enter the starting point A and finish B (type in  like:\n" +
                "Ax\nAy\nBx\nBy\n" +
                " separated with newlines)" );
        try{
            line = bis.readLine();
            Ax = Integer.parseInt(line);
            line = bis.readLine();
            Ay = Integer.parseInt(line);
            line = bis.readLine();
            Bx = Integer.parseInt(line);
            line = bis.readLine();
            By = Integer.parseInt(line);
        } catch (IOException e){
            System.err.println("Error processing your input: " + e);
            return;
        } catch (NumberFormatException e){
            System.err.println("Error - wrong number format: " + e);
            return;
        }
        if(Ax > n || Ay > n || Bx > n || By > n || Ax < 0 || Bx < 0 || Ay < 0 || By < 0){
            System.out.println("Error: One or more points outside the field");
            return;
        }
        if(field[Ax][Ay] == cellType.wall || field[Bx][By] == cellType.wall){
            System.out.println("error: one of points is inside the wall :(");
            return;
        }
        cellType [][] res = findPath(Ax, Ay, Bx,  By);
        if(res == null){
            System.out.println("no way");
            return;
        }
        System.out.println(printPath(res));
    }
}
