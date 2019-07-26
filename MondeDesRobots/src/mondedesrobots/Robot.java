package mondedesrobots;

abstract class Robot {
    int posx, posy;
    Monde m = new Monde(10, 10);
    public Robot(int x, int y){
        posx = x;
        posy = y;
    }
    public Robot(){
        posx = (int)(Math.random() * 10);
        posy = (int)(Math.random() * 10);
    }

    public void seDeplace(int i, int j) {
        try {
            if (i >= 0 && i < this.m.nbL && j >= 0 && j < this.m.nbC) {
                this.posx = i;
                this.posy = j;
            }
            else
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur construction");
        }
    }
    abstract public void parcourir() throws Exception;
}