package AsteroidsGame.game2;


// This class has been attempted but not completed. RotateNShoot is used instead.
class AimNShoot implements Controller{
    private Game game;
    private Action action = new Action();
    private static final int SHOOTING_DISTANCE = 100;
    private static final int SHOOTING_ANGLE = 100;

    AimNShoot(Game game){
        this.game = game;
    }

    @Override
    public Action action(){ //TODO: Attempted but not working
        action.turn = Controllers.aim(game.saucer, game.playerShip);
        if(game.saucer.position.dist(game.playerShip.position) > SHOOTING_DISTANCE) action.thrust = 1;
        return action;
    }
}
