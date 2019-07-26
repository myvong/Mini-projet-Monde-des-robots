package mondedesrobots;

// robot tim o dau tien co rac va di ve phia do
class RobotNettoyer_standard extends Robot {
    private int compteGrasNettoye;

    RobotNettoyer_standard(int x, int y) {
        this.posx = x;
        this.posy = y;
    }
    public void parcourir() throws Exception {
        int x = this.posx;
        int y = this.posy;
        if (x % 2 == 0) {
            // se deplace dans un case a droit
            if (y < 9)
                y++;
            else { //y == 9: se deplace dans la derniere case a la ligne dessous 
                x++;
            }        }
        else {
            if (y > 0)
            {
                y--;
            }
            else // y = 0: se deplace dans la premiere case a la ligne dessous 
            {
                x++;
            }
        }
        if (x > 9)
            throw new Exception();
        // kiem tra rac
        this.seDeplace(x, y);
        if (this.m.Mat[x][y] == true) {   
            compteGrasNettoye++;
            if (compteGrasNettoye % 2 == 1)
                this.m.prendPapierGras(x, y);
        }
    }
}