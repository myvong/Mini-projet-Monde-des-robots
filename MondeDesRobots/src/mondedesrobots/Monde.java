package mondedesrobots;

class Monde {
    int nbL; //nombre de lignes
    int nbC; //nombre de colonne
    boolean Mat[][]; //Matrice des case 
    public Monde(int m, int n) //constructeur de creer le monde avec m linges et n colonnes
    {
        this.nbC = n;
        this.nbL = m;
        this.Mat = new boolean[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                this.Mat[i][j] = false;
        }
    }
    public void metPapierGras(int i, int j) { //fonction pour mettre papier gras dans une case (i, j)
        try {
            if (i >= 0 && i < this.nbL && j >= 0 && j < this.nbC)
                this.Mat[i][j] = true;
            else {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur construction");
        }
    }

    public void prendPapierGras(int i, int j) { ///fonction pour prendre papier gras dans une case (i, j)
        try {
            if (i >= 0 && i < this.nbL && j >= 0 && j < this.nbC)
                this.Mat[i][j] = false;
            else {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur construction");
        }
    }
    public boolean testeGras(int i, int j) { //verifier si une case contient un papier gras ou non
        try {
            if (i >= 0 && i < this.nbL && j >= 0 && j < this.nbC)
                this.Mat[i][j] = true;
            else {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur construction");
        }
        return this.Mat[i][j];
    }
    public int compteGras() { //compter les papiers gras dans le monde
        int compteur = 0;
        for (int i = 0; i < this.nbL; i++)
            for (int j = 0; j < this.nbC; j++)
                if (this.Mat[i][j] == true)
                    compteur++;
        return compteur;
    }
    public void affiche() { //afficher le monde avec 0 et 1
        for (int i = 0; i < this.nbL; i++)
        {
            for (int j = 0; j < this.nbC; j++)
                if (Mat[i][j] == true)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            System.out.println();
        }

    }
}
