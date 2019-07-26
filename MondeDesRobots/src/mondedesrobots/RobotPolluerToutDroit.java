package mondedesrobots;

class RobotPolluerToutDroit extends Robot{
    private boolean flag;
//    int numCol;
    public RobotPolluerToutDroit(int numCol) {
        this.posx = 0; 
        this.posy = numCol;
        this.flag = false;
    }

    RobotPolluerToutDroit() {
        this.posy = (int)(Math.random() * 10);
        this.flag = false;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void parcourir() throws Exception {
        this.seDeplace(posx, posy);
        this.m.metPapierGras(this.posx, this.posy);
        if (!flag) { // move right
            this.posy++;
        }
        else { // move down
            this.posx++;
        }
        flag = !flag;
        if (this.posx == this.m.nbL) {
            this.posx--;
            throw new IndexOutOfBoundsException();
        }
        
        if (this.posy == this.m.nbC) {
            this.posy--;
            throw new IndexOutOfBoundsException();
        }
    };
}