import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        playGame();
    }

    static void playGame() {
        char[][] pole = createPole();
        while (true) {
            paintPole(pole);

            playUser(pole);
            checkIsWin(pole,'X');

            playMachine(pole);
            checkIsFail(pole,'0');
        }

    }
    static boolean checkIsWin(char[][]pole,char sine){
        if (isDraw(pole)) {
            System.out.println("Ничья!");
            return false;
        }

        if (isWin(pole, 'X')) {
            System.out.println("Вы выиграли!!!");
            return false;
        }
        return true;

    }
    static boolean checkIsFail(char[][]pole,char sine){
        if (isDraw(pole)) {
            System.out.println("Ничья!");
            return false;
        }

        if (isWin(pole, '0')) {
            System.out.println("Вы проиграли");
            return false;
        }
        return true;
    }

    static char[][] createPole() {
        return new char[][]{
                {'-', '-', '-', '-'},
                {'-', '-', '-', '-'},
                {'-', '-', '-', '-'},
                {'-', '-', '-', '-'}
        };
    }

    static void paintPole(char[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void playUser(char[][] pole) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            x = checkCoordinate(scanner, 'X', pole);
            y = checkCoordinate(scanner, 'Y', pole);
        } while (freeNotFree(pole, x, y));
        pole[x][y] = 'X';
    }


    static int checkCoordinate(Scanner scanner, char cord, char[][] pole) {
        int val;
        do {
            System.out.printf("Введите координату %s от 1 до %d %n", cord, pole.length);
            val = scanner.nextInt() - 1;
        } while (val < 0 || val > pole.length);
        return val;
    }

    static boolean freeNotFree(char[][] pole, int x, int y) {
        return (pole[x][y] != '-');

    }


    static void playMachine(char[][] pole) {
        int x, y;
        do {
            Random random = new Random();
            x = random.nextInt(pole.length);
            y = random.nextInt(pole.length);
        } while (freeNotFree(pole, x, y));
        pole[x][y] = '0';

    }

    static boolean isDraw(char[][] pole) {
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[i].length; j++) {
                if (pole[i][j] == '-') return false;
            }
        }
        return true;


    }

    static boolean isWin(char[][] pole, char sine) {
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

        if (diagonal || diagonal2) return true;

        return false;
    }
}

