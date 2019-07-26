package mondedesrobots;

// robot tim o dau tien co rac va di ve phia do
class RobotNettoyer_intelligent extends Robot {
    public void parcourir() throws Exception {
        
        for (int i = 0; i < this.m.nbL; i++)
        {
            for (int j = 0; j < this.m.nbC; j++)
            {
                if (this.m.Mat[i][j] == true)
                {
                    if (j > this.posy)
                    {
                        this.seDeplace(this.posx, this.posy + 1);
                    }
                    else if (j < this.posy)
                    {
                        this.seDeplace(this.posx, this.posy - 1);
                    }
                    // x
                    if (i > this.posx)
                    {
                        this.seDeplace(this.posx + 1, this.posy);
                    }
                    else if (i < this.posx)
                    {
                        this.seDeplace(this.posx - 1, this.posy);
                    }
                    this.m.prendPapierGras(this.posx, this.posy);
                    return;
                }
            }
        }
        throw new Exception();
    }
}