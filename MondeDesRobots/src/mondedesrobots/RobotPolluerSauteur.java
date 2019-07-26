package mondedesrobots;

class RobotPolluerSauteur extends Robot {
    private int step = 10;

    public RobotPolluerSauteur() {
        super();
    }
    
    public RobotPolluerSauteur(int i, int j) {
        super(i, j);
    }
    
    @Override
    public void parcourir() throws Exception {
        if (step > 0){
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            this.seDeplace(x, y);
            this.m.metPapierGras(this.posx, this.posy);
            step--;
        }      
        else
        {
            throw new Exception();
        }
    }
}