import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char[][] pole;
    private static final Scanner scanner = new Scanner(System.in);

    static {
        pole = createPole();
    }

    public static void main(String[] args) {
        playGame();
    }

    static void playGame() {
        paintPole();
        while (true) {

            if (!checkIsUser()) {
                return;
            }

            if (!checkIsMachine()) {
                return;
            }
        }
    }

    static boolean checkIsUser() {
        playUser();
        paintPole();
        return checkIsWin('X', "Вы выиграли!");


    }

    static boolean checkIsMachine() {
        playMachine();
        paintPole();
        return checkIsWin('O', "Вы проиграли!(");
    }

    static boolean checkIsWin(char sine, String x) {
        if (isDraw()) {
            System.out.println("Ничья!");
            return false;
        }

        if (isWin(sine)) {
            System.out.println(x);
            return false;
        }
        return true;

    }

    static char[][] createPole() {
        return new char[][]{
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'}

        };
    }

    static void paintPole() {
        for (char[] chars : pole) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void playUser() {
        int x, y;
        do {
            x = checkCoordinate(scanner, 'X');
            y = checkCoordinate(scanner, 'Y');
        } while (freeNotFree(x, y));
        pole[x][y] = 'X';
    }


    static int checkCoordinate(Scanner scanner, char cord) {
        int val;
        do {
            System.out.printf("Введите координату %s от 1 до %d %n", cord, pole.length);
            val = scanner.nextInt() - 1;
        } while (val < 0 || val > pole.length);
        return val;
    }

    static boolean freeNotFree(int x, int y) {
        return (pole[x][y] != '-');
    }


    static void playMachine() {
        int x, y;
        do {
            Random random = new Random();
            x = random.nextInt(pole.length);
            y = random.nextInt(pole.length);
        } while (freeNotFree(x, y));
        pole[x][y] = 'O';

    }

    static boolean isDraw() {
        for (char[] chars : pole) {
            for (char aChar : chars) {
                if (aChar == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isWin(char sine) {
        boolean vertical, horizontal;
        vertical = true;
        horizontal = true;
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole.length; j++) {
                vertical &= (pole[i][j] == sine);
                horizontal &= (pole[j][i] == sine);
            }
            if (vertical || horizontal) return true;
        }
        boolean diagonal, diagonal2;
        diagonal = true;
        diagonal2 = true;
        for (int i = 0; i < pole.length; i++) {
            diagonal &= (pole[i][i] == sine);
            diagonal2 &= (pole[pole.length - i - 1][i] == sine);
        }

        return diagonal || diagonal2;
    }
}

