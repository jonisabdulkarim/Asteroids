package AsteroidsGame.game2;

class Controllers {
    public static GameObject findTarget(GameObject ship, Iterable<GameObject> gameObjects){
        for (GameObject obj :
                gameObjects) {
            if (obj.equals(ship)){
                return obj;
            }
        }
        return null;
    }

    static int aim(GameObject ship, GameObject target){
        if(ship.direction.angle(target.position) > ship.direction.angle()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
