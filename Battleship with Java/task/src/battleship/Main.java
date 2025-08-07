package battleship;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    /*
    x[0][0], x[0][1], x[0][2], x[0][3]
    x[1][0], x[1][1], x[1][2], x[0][3]
    x[2][0], x[2][1], x[2][2], x[2][3]
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] gameArrayP1 = createArray(10, 10);
        String[][] gameArrayP2 = createArray(10, 10);
        int[] gameInfo1 = {1, 0};
        int[] gameInfo2 = {2, 0};
        System.out.println("Player 1, place your ships on the game field");
        String[][] player1 = createMapAndShips(scanner);
        System.out.println("Press Enter and pass the move to another player");
        String enter = scanner.nextLine();
        System.out.println("Player 2, place your ships to the game field");
        String[][] player2 = createMapAndShips(scanner);
        System.out.println("Press Enter and pass the move to another player");
        enter = scanner.nextLine();
        while (true){
            boolean isGameOver = startGame(player1, player2,gameArrayP1,scanner, 1);
            /*if (isGameOver){
                break;
            }*/
            System.out.println("Press Enter and pass the move to another player");
            enter = scanner.nextLine();
            isGameOver = startGame(player2, player1 ,gameArrayP2, scanner, 2);
            /*if (isGameOver){
                break;
            }*/
            System.out.println("Press Enter and pass the move to another player");
            enter = scanner.nextLine();
        }
    }
    public static String[][] createMapAndShips(Scanner scanner){
        String[][] array = createArray(10, 10);
        printArray(array);
        setShip(scanner, array);
        return array;
    }
    public static boolean shipCounter(String[][] array, int row, int cellNumber){
        boolean isShipSanked = false;
        if (row == 1) {
            if (cellNumber == 1) {
                if (!array[row][cellNumber].equals("O") && !array[row][cellNumber + 1].equals("O") &&
                    !array[row + 1][cellNumber].equals("O")){
                    isShipSanked = true;
                }
            } else if (cellNumber == 10){
                if (!array[row][cellNumber].equals("O") && !array[row + 1][cellNumber].equals("0") &&
                    !array[row][cellNumber - 1].equals("O")){
                    isShipSanked = true;
                }
            } else{
                if (!array[row][cellNumber].equals("O") && !array[row + 1][cellNumber].equals("O") &&
                    !array[row][cellNumber - 1].equals("O") && !array[row][cellNumber + 1].equals("O")){
                    isShipSanked = true;
                }
            }
        } else if (row == 10){
            if (cellNumber == 1) {
                if (!array[row][cellNumber].equals("O") && !array[row][cellNumber + 1].equals("O") &&
                    !array[row - 1][cellNumber].equals("O")){
                    isShipSanked = true;
                    }
            } else if (cellNumber == 10){
                if (!array[row][cellNumber].equals("O") && !array[row - 1][cellNumber].equals("O") &&
                    !array[row][cellNumber - 1].equals("O")){
                    isShipSanked = true;
                }
            }  else {
                if (!array[row][cellNumber].equals("O") && !array[row - 1][cellNumber].equals("O") &&
                    !array[row][cellNumber - 1].equals("O") && !array[row][cellNumber + 1].equals("O")){
                    isShipSanked = true;
                    }
            }
        } else {
            if (!array[row][cellNumber].equals("O") && !array[row - 1][cellNumber].equals("O") &&
                !array[row][cellNumber - 1].equals("O") && !array[row][cellNumber + 1].equals("O") &&
                !array[row + 1][cellNumber].equals("O")){
                    isShipSanked = true;
            }
        }
        return isShipSanked;
    }
    public static boolean isLastShip(String[][] array){
        boolean isLastShip = true;
        outerLoop:
        for(int i = 1; i < array.length; i++){
            for(int j = 1; j < array[i].length; j++){
                if (array[i][j].equals("O")){
                    isLastShip = false;
                    break outerLoop;
                }
            }
        }
        return true;
    }
    public static boolean startGame(String[][] myArray ,String[][] arrayOfEnemy,
                                    String[][] gameArray, Scanner scanner, int playerNumber) {
        boolean isGameOver = false;
        printArray(gameArray);
        System.out.println("---------------------");
        printArray(myArray);
        System.out.println("Player" + playerNumber + ", it's your turn:");
        String shot = scanner.nextLine();
        int row = shot.charAt(0) - 65 + 1;
        int cellNumber = Integer.parseInt(shot.substring(1));
        if (row > 10 || row < 1 || cellNumber < 1 || cellNumber > 10) {
            System.out.println("Error! You entered the wrong coordinates! Try again:\n");
        } else{
            if (arrayOfEnemy[row][cellNumber].equals("O")){
                gameArray[row][cellNumber] = "X";
                arrayOfEnemy[row][cellNumber] = "X";
                printArray(gameArray);
                boolean isShipSanked = shipCounter(arrayOfEnemy, row, cellNumber);
                if (isShipSanked) {
                    if(isLastShip(arrayOfEnemy)){
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        printArray(arrayOfEnemy);
                        isGameOver = true;
                    } else{
                        System.out.println("You sank a ship!\n");
                    }
                } else{
                    System.out.println("You hit a ship!");
                }
            }else if (arrayOfEnemy[row][cellNumber].equals("X")){
                printArray(gameArray);
                System.out.println("You hit a ship!");
            }
            else{
                gameArray[row][cellNumber] = "M";
                arrayOfEnemy[row][cellNumber] = "M";
                printArray(gameArray);
                System.out.println("You missed.");
            }
        }
            return isGameOver;
        }
    public static boolean[] isCellEmpty(String[][] array, String[] cords) {
        boolean[] isCellEmpty = new boolean[2];// 1 - isCellEmpty / 2 - isCellsAroundEmpty
        isCellEmpty[0] = true;
        isCellEmpty[1] = true;
        for (int i = 1; i < cords.length; i++) {
            int row = cords[i].charAt(0) - 65+1;//-65 to convert from ASCII, +1 to skip the first row
            int cellNumber = Integer.parseInt(cords[i].substring(1));
            if (array[row][cellNumber].equals("O")) {
                isCellEmpty[0] = false;
                break;
            }
            if (isCellEmpty[0]) {
                if (row == 1) {
                    if (cellNumber == 1) {
                        if (array[row][cellNumber + 1].equals("O") || array[row + 1][cellNumber].equals("O")) {
                            isCellEmpty[1] = false;
                            break;
                        }
                    } else if (cellNumber == 10) {
                        if (array[row][cellNumber - 1].equals("O") || array[row + 1][cellNumber].equals("O")) {
                            isCellEmpty[1] = false;
                            break;
                        }
                    } else{
                        if (array[row + 1][cellNumber].equals("O")){
                            isCellEmpty[1] = false;
                            break;
                        }
                    }
                }else if (row == 10) {
                    if (cellNumber == 1) {
                        if (array[row][cellNumber + 1].equals("O") || array[row - 1][cellNumber].equals("O")) {
                            isCellEmpty[1] = false;
                            break;
                        }
                    } else if (cellNumber == 10) {
                        if (array[row][cellNumber - 1].equals("O") || array[row - 1][cellNumber].equals("O")) {
                            isCellEmpty[1] = false;
                            break;
                        }
                    }else {
                        if (array[row - 1][cellNumber].equals("O")){
                            isCellEmpty[1] = false;
                            break;
                        }
                    }
                }else if (cellNumber == 1) {
                    if (array[row][cellNumber+1].equals("O")){
                        isCellEmpty[1] = false;
                        break;
                    }
                } else if (cellNumber == 10) {
                    if (array[row][cellNumber-1].equals("O")){
                        isCellEmpty[1] = false;
                    }
                } else {
                    if (array[row][cellNumber+1].equals("O") || array[row][cellNumber-1].equals("O") ||
                            array[row-1][cellNumber].equals("O") || array[row+1][cellNumber].equals("O")){
                        isCellEmpty[1] = false;
                        break;
                    }
                }
            }
        }
        return isCellEmpty;
    }
    public static void setPointsInArray(String[][] arrayMap, String[] cords){
        for (int i = 1; i < cords.length; i++) {
            int row = (int)cords[i].charAt(0) - 65 + 1;
            String test = cords[i].substring(1);
            int columNumber = Integer.parseInt(cords[i].substring(1));
            arrayMap[row][columNumber] = "O";
        }
    }
    public static void setShip(Scanner scanner, String[][] array){
        String[] ships = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
        int[] cells = {5, 4, 3, 3, 2};
        for (int i = 0; i < ships.length; i++) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", ships[i], cells[i]);
            String[] cords = takeCordinatesAndReturnArray(scanner);
            if (cords[0].equals("Error")){
                System.out.println("Error! Wrong ship location! Try again:");
                i--;
                continue;
            }else if (Integer.parseInt(cords[0]) != cells[i]){
                System.out.printf("Error! Wrong length of the %s! Try again:", ships[i]);
                i--;
                continue;
            }
            boolean[] isCellEmpty = isCellEmpty(array, cords);
            if (!isCellEmpty[0] || !isCellEmpty[1]){
                System.out.println("Error! You placed it too close to another one. Try again:");
                i--;
                continue;
            }
            setPointsInArray(array, cords);
            printArray(array);
        }
    }
    public static String[] takeCordinatesAndReturnArray(Scanner scanner) {
        //String[] arrayCords = new String[2];
        ArrayList<String> cords = new ArrayList<>();
        String cordinatesOfShip = scanner.nextLine();//read the coordinates from input
        String[] cordinatesOfShipArray = cordinatesOfShip.split(" ");//divide it an array
        char letterA = cordinatesOfShipArray[0].charAt(0);//letter of first cardinals
        char letterB = cordinatesOfShipArray[1].charAt(0);//letter of second cardinals
        int cordinatesA = Integer.parseInt(cordinatesOfShipArray[0].substring(1));
        int cordinatesB = Integer.parseInt(cordinatesOfShipArray[1].substring(1));
        if (cordinatesA > 10 || cordinatesB > 10 ||//checks if it more than 10
                letterA > 'J' || letterB > 'J' || //check if it more that letter J
                cordinatesA < 1 || cordinatesB < 1){ // check if it less than 1
            //System.out.println("Error!");// if one of logical expressions gives, true it shows an error
            cords.add("Error");
        }else if (letterA == letterB){//check if it has the same letters
            /*
            This block shows Length and parts to, us
            it words with coordinates if they have the same letter
             */
            if (cordinatesA < cordinatesB) {
                cords.add(String.valueOf(cordinatesB - cordinatesA + 1));
                for (int i = cordinatesA; i <= cordinatesB; i++) {
                    cords.add(letterA + String.valueOf(i));
                }
            } else {
                cords.add(String.valueOf(cordinatesA - cordinatesB + 1));
                for (int i = cordinatesA; i >= cordinatesB; i--) {
                    cords.add(letterB + String.valueOf(i));
                }
            }
        /*
        this block shows Length and parts to, us
            it works with coordinates if they have the same letter
         */
        }else if (cordinatesA == cordinatesB){
            if (letterA < letterB) {
                cords.add(String.valueOf((int)letterB - (int)letterA + 1));
                for (int i = letterA; i <= letterB; i++) {
                    cords.add((char)i + String.valueOf(cordinatesA));
                }
            }else if (letterA > letterB){
                cords.add(String.valueOf((int)letterA - (int)letterB + 1));
                for (int i = letterA; i >= letterB; i--){
                    cords.add((char) i + String.valueOf(cordinatesA));
                }
            }
        }else {
            cords.add("Error");
        }
        return cords.toArray(new String[cords.size()]);
    }
    public static void printArray(String[][] array) {
        for (String[] row : array) {
            for (String i : row) {
                System.out.print(i);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }
    public static String[][] createArray (int rows, int seats) {
        rows++;//for column with digits
        seats++;//for column with digits
        String[][] array = new String[rows][seats];
        //write seat numbers
        array[0][0] = " ";
        for (int i = 1; i < 11; i++) {
            array[0][i] = Integer.toString(i);
        }
        char letter = 'A';
        for (int i = 1; i < array.length; i++) {
            array[i][0] = Character.toString(letter);
            letter++;
            for (int j = 1; j < array[i].length; j++) {
                array[i][j] = "~";
            }
        }
        return array;
    }
}
