import java.util.Random;
import java.util.Scanner;

public class TickTackToe {
    private static char[][] map;//матрица
    private static int SIZE = 3;//размерность поля

    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static final boolean SILLY_MODE = false;

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            if (isEndGame(DOT_X)) {
                break;
            }
            computerTurn();
            if (isEndGame(DOT_O)) {
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    /**
     * Метод подготовки игрового поля
     */
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Ход человека
     */
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ячейки, через пробел: ");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    /**
     * Ход компьютера
     */
    private static void computerTurn() {
        int x = -1;
        int y = -1;
        if (SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер выбрал ячейку: " + (y + 1) + " " + (x + 1));
            map[y][x] = DOT_O;
        } else {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    // Проверяем клкетки по направлениям
                }
            }
        }
    }
    /** Метод валидации запрашивает ячейки на корректность
     * @param  x -координата по горизонтали
     * @param  x -координата по горизонтали
     * @return boolean - признак валидности
     */
    private static boolean isCellValid(int x, int y){
        boolean result = true;
        //проверка координаты
        if (x < 0 || x > SIZE || y < 0 || y > SIZE){
            result = false;
        }
        // проверка заполнения ячейки
        if (map[y][x] != DOT_EMPTY) {
            result = false;
        }
        return result;
    }
    /**
     * Метод проверки игры на завершение
     * @param playerSymbol - символ которым играет текущий игрок
     * @return boolean - признак завершения игры
     */
    private static boolean isEndGame(char playerSymbol){
        boolean result = false;
        printMap();
        // проверяем необходимость следующего хода
        if (checkWin(playerSymbol)) {
            System.out.println("Победили: " + playerSymbol);
            result = true;
        }
        return result;
    }
    /**
     * Проверка на 100% заполненость поля
     * @return boolean - признак оптимальности
     */
    private static boolean isMapFull(){
        boolean result = true;
        for ( int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }
        return result;
    }
    private static boolean checkWin(char playerSymbol){
        boolean result = false;
        if (
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                        (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                        (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                        (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)){
            result = true;
        }
        return result;
    }
}