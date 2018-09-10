
public class Labirint {
    public int apeluri = 0;
    private final static int MAX_VALUE = 101;

    int best_solution = MAX_VALUE;


    public char[][] maze =
            {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                    {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
                    {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
                    {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
                    {'#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', '#'},
                    {'#', '#', '#', '#', '#', '#', '#', '#', ' ', '#'},
                    {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '#'},
                    {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#', '#'},
                    {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#'},
                    {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},};

    public char[][] output_maze = null;


    private void copiazaLab() {
        output_maze = new char[10][10];
        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                output_maze[x][y] = maze[x][y];
            }
        }
    }


    public void rezolva(int x, int y) {
        best_solution = MAX_VALUE;

        if (step(x,y, 0) != MAX_VALUE) {
            output_maze[x][y] = 'S';
        }
    }


    public int step (int x, int y, int count) {

        apeluri++;

        // Daca este true, am gasit rezolvarea si iesim din recursivitate.
        if (maze[x][y] == 'X') {
            best_solution = count;
            this.copiazaLab();
            return count;
        }

        // Daca ne lovim de perete sau de drumul nostru nu putem continua.
        if (maze[x][y] == '#' || maze[x][y] == '*') {
            return MAX_VALUE;
        }

        // Daca avem deja o solutie mai buna, nu mai continuam.
        if (count == best_solution) {
            return MAX_VALUE;
        }


        maze[x][y] = '*';            // Drumul il vom marca cu *
        int rezultat = MAX_VALUE;	     // Initializam variabila cu 101
        int rezultatNou = MAX_VALUE;  // Initializam variabila cu 101.

        // Incearca sa se duca la dreapta
        rezultatNou = step(x, y+1, count+1);
        if (rezultatNou < rezultat) {
            rezultat = rezultatNou;
        }

        // Incearca sa se duca in sus
        rezultatNou = step(x-1, y, count+1);
        if (rezultatNou < rezultat) {
            rezultat = rezultatNou;
        }

        // Incearca sa se duca in stanga
        rezultatNou = step(x, y-1, count+1);
        if (rezultatNou < rezultat) {
            rezultat = rezultatNou;
        }

        // Incearca sa se duca in jos
        rezultatNou = step(x+1, y, count+1);
        if (rezultatNou < rezultat) {
            rezultat = rezultatNou;
        }

        // Sterge marcarea drumului cu * pentru ca ori nu reprezinta o solutie.
        maze[x][y] = ' ';

        if (rezultat < MAX_VALUE) {
            return rezultat;
        }

        // PUNCT MORT, de aici incepem sa facem pasi inapoi.

        // Acest return ne duce cu un pas in spate
        return MAX_VALUE;
    }



    public String toStringSolution() {
        if (output_maze == null) {
            return "Labirintul nu are solutie!";
        }
        String output = "";
        for (int x=0; x<10; x++) {
            for (int y=0; y<10; y++) {
                output += output_maze[x][y] + " ";
            }
            output += "\n";
        }
        return output;
    }

    public static void main(String[] args) {

        Labirint lab = new Labirint();

        lab.maze[1][1] = 'X'; // Seteaza iesirea

        lab.rezolva(8, 1); // Seteaza punctul de inceput

        System.out.println(lab.toStringSolution());
        System.out.println("Functia a fost apelata de " + lab.apeluri + " ori.");
    }
}
