package lab7p1_carlosflores;

import java.time.DayOfWeek;
import java.util.Scanner;
import java.util.Random;

public class Lab7P1_CarlosFlores {

    static Scanner sc = new Scanner(System.in);
    static Random ran = new Random();
    static int score_p1 = 0;
    static int score_p2 = 0;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("1. She Shoot, she scores!\n2. Piedra papel o...\n3. Salir");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Inrese un numero de filas: ");
                    int filas = sc.nextInt();
                    System.out.println("Inrese un numero de columnas: ");
                    int columnas = sc.nextInt();

                    // Validacion de limites
                    while (filas * columnas > 88) {
                        System.out.println("Fuera de limite!");

                        System.out.println("Inrese un numero de filas: ");
                        filas = sc.nextInt();
                        System.out.println("Inrese un numero de columnas: ");
                        columnas = sc.nextInt();

                    }

                    System.out.println("Ingrese la cantidad de balas jugador 1: ");
                    int balas = sc.nextInt();

                    System.out.println("Ingrese la cantidad de balas jugador 2: ");
                    int balas2 = sc.nextInt();

                    // Validacion de balas
                    while (balas > (filas * columnas) / 2 || balas2 > (filas * columnas) / 2) {
                        System.out.println("Demasiadas balas");

                        System.out.println("Ingrese la cantidad de balas jugador 1: ");
                        balas = sc.nextInt();

                        System.out.println("Ingrese la cantidad de balas jugador 2: ");
                        balas2 = sc.nextInt();

                    }

                    boolean game_running = true;

                    int[][] tablero = generateMatrix(filas, columnas);
                    
                    
                    // loop del game
                    while (balas > 0 || balas2 > 0) {
                        print(tablero);

                        System.out.println("Ingrese un numero a disparar Jugador 1!");
                        int answer = sc.nextInt();
                        
                        while(answer == 99 || answer == 88){
                            System.out.println("No puedes disparar ahi!");
                            answer = sc.nextInt();
                        }
                        
                        tablero = newMatrix(answer, tablero);
                        print(tablero);
                        balas--;
                        System.out.println("Le quedan: " + balas + " balas");

                        System.out.println("------------");

                        System.out.println("Ingrese un numero a disparar Jugador 2!");
                        answer = sc.nextInt();
                        
                        while(answer == 99 || answer == 88){
                            System.out.println("No puedes disparar ahi!");
                            answer = sc.nextInt();
                        }
                        
                        tablero = newMatrix2(answer, tablero);
                        print(tablero);
                        balas2--;
                        System.out.println("Le quedan: " + balas2 + " balas");

                        System.out.println("------------");

                    }

                    System.out.println("Score del jugador 1: " + score_p1);
                    System.out.println("Score del juagdor 2: " + score_p2);
                    
                    if(score_p1 > score_p2){
                        System.out.println("\nGana el jugador 1!");
                    }
                    else if(score_p2 > score_p1){
                        System.out.println("\nGana el jugador 2!");
                    }
                    else if(score_p1 == score_p2){
                        System.out.println("\nAmbos quedaron empate");
                    }

                    break;

                case 2:
                    int[][] opciones = {{0, 1, 2, 1, 2}, //Tijeras 
                    {2, 0, 1, 2, 1}, //Papel
                    {1, 2, 0, 1, 2}, // Piedra
                    {2, 1, 2, 0, 1}, // Lizard
                    {1, 2, 1, 2, 0}}; // Spock

                    System.out.println("Elija lo que quiere usar: ");
                    System.out.println("1. Tijeras\n2. Papel\n3. Piedra\n4. Lizard\n5. Spock\n");
                    int user_op = sc.nextInt();
                    
                    while(user_op > 5 || user_op < 1){
                        System.out.println("Esa opcion no esta adentro de los limites");
                        user_op = sc.nextInt();
                    }

                    int comp_op = opciones[ran.nextInt(4) + 1][ran.nextInt(4) + 1];

                    // opciones de la computadora
                    if (comp_op == 0) {
                        System.out.println("Maquina eligio Tijeras");
                    } else if (comp_op == 1) {
                        System.out.println("Maquina eligio Papel");
                    } else if (comp_op == 2) {
                        System.out.println("Maquina eligio Piedra");
                    } else if (comp_op == 3) {
                        System.out.println("Maquina eligio Lizard");
                    } else if (comp_op == 4) {
                        System.out.println("Maquina eligio Spock");
                    }

                    // opciones del jugador 
                    if (user_op == 1) {
                        System.out.println("Elegiste Tijeras");
                    } else if (user_op == 2) {
                        System.out.println("Elegiste Papel");
                    } else if (user_op == 3) {
                        System.out.println("Elegiste Piedra");
                    } else if (user_op == 4) {
                        System.out.println("Elegiste Lizard");
                    } else if (user_op == 5) {
                        System.out.println("Elegiste Spock");
                    }

                    int ganar = opciones[user_op - 1][comp_op];

                    if (ganar == 1) {
                        System.out.println("\nGana jugador");
                    } else if (ganar == 2) {
                        System.out.println("\nGana la maquina");
                    } else {
                        System.out.println("\nEmpate");

                    }

                    break;

                case 3:
                    running = false;
                    break;

                default:
                    break;
            }

        }
    }

    static int[][] generateMatrix(int filas, int columnas) {
        int[][] temp = new int[filas][columnas];
        int value = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                value = ran.nextInt(80) + 1;
                temp[i][j] = value;

                while (checkInt(value, temp)) {
                    value = ran.nextInt(filas * columnas) + 1;
                }

                temp[i][j] = value;

            }

        }
        return temp;
    }

    static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("[" + matrix[i][j] + "] ");
            }
            System.out.println();
        }
    }

    static boolean checkInt(int num, int[][] matrix) {
        boolean exists = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (num == matrix[i][j]) {
                    exists = true;
                }

            }

        }

        return exists;
    }

    static int[][] newMatrix(int num, int[][] matrix) {
        int[][] temp = matrix;
        boolean acertado = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (temp[i][j] == num) {
                    System.out.println("Tiro acertado!");
                    temp[i][j] = 99;
                    acertado = true;
                    score_p1++;
                }

            }

        }

        if (!acertado) {
            System.out.println("Fallaste");
        }

        return temp;
    }

    static int[][] newMatrix2(int num, int[][] matrix) {
        int[][] temp = matrix;
        boolean acertado = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (temp[i][j] == num) {
                    System.out.println("Tiro acertado!");
                    temp[i][j] = 88;
                    acertado = true;
                    score_p2++;
                }

            }

        }

        if (!acertado) {
            System.out.println("Fallaste");
        }

        return temp;
    }

}
