package AsteroidsGame.game2;
import java.util.Random;

class RotateNShoot  implements Controller{
    private Action action = new Action();
    private Random r = new Random();
    private int timeToChange = 100;

    @Override
    public Action action(){
        if(timeToChange <= 0) {
            action.thrust = Math.abs(r.nextInt() % 2);
            action.shoot = Math.abs(r.nextInt() % 2) == 1;
            action.turn = r.nextInt(3) - 1;
            timeToChange+= 100;
        }
        else{
            timeToChange--;
        }
        return action;
    }
}
