package mondedesrobots;

class RobotPolluerLibre extends Robot {
    private int step = 10;
    
    public RobotPolluerLibre() {
        super();
//        this.posx = this.posy = 5;
    }
    
    public RobotPolluerLibre(int i, int j){
        super(i, j);
    }          
    public void parcourir() throws Exception {
//        if (step > 0)
//        {
//            step--;
//            /*
//            creer un variable nomme direction aleatoirement
//            direction = 0 --> nord
//            direction = 1 --> nord-est
//            direction = 2 --> est
//            direction = 3 --> south-est
//            direction = 4 --> south
//            direction = 5 --> south-west
//            direction = 6 --> west 
//            direction = 7 --> nord-west
//            */
//            int direction = (int) (Math.random() * 8); 
//            switch (direction) {
//                case 0:  //nord
//                    if ((this.posx - 1) < 0)
//                        break;
//                    this.posx--;        
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 1: //nord-est
//                    if ((this.posx - 1) < 0 || (this.posy + 1) >= 10)
//                        break;
//                    this.posx--;
//                    this.posy++;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 2: //est
//                    if ((this.posx + 1) > 9)
//                        break;
//                    this.posx++;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 3: //south-est
//                    if ((this.posx + 1) > 9 || (this.posy + 1) > 9)
//                        break;
//                    this.posx++;
//                    this.posy++;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 4: //south
//                    if ((this.posy + 1) > 9)
//                        break;
//                    this.posy++;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 5: //south-west
//                    if ((this.posx + 1) > 9 || (this.posy - 1) < 0)
//                        break;
//                    this.posx++;
//                    this.posy--;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 6: //west
//                    if ((this.posy - 1) < 0)
//                        break;
//                    this.posy--;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//                case 7: //nord-west
//                    if ((this.posx - 1) < 0 || (this.posy - 1) < 0)
//                        break;
//                    this.posx--;
//                    this.posy--;
//                    this.seDeplace(posx, posy);
//                    this.m.metPapierGras(posx, posy);
//                    break;
//            }
//        }
//        else {
//            throw new Exception();
//        }
    }
}