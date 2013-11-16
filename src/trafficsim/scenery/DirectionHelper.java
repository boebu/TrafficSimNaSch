package trafficsim.scenery;

/**
 * Created with IntelliJ IDEA.
 * User: boebu
 * Date: 11/11/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DirectionHelper {

    public static Direction calculateDirection(StreetElement previousElement, StreetElement thisElement) {
        ElementType type = previousElement.getType();
        Direction previousDirection = previousElement.getDirection();
        if(type == ElementType.CROSS) {
            if(previousElement.left() == thisElement) {
                 type = ElementType.LEFTTURN;
            } else if (previousElement.right() == thisElement) {
                 type = ElementType.RIGHTTURN;
            }  else {
                type = ElementType.STRAIGHT;
            }
        }


     Direction direction = null;
        switch (type) {
            case STRAIGHT:
                direction = previousDirection;
                break;
            case LEFTTURN:
                direction = left(previousDirection);
                break;
            case RIGHTTURN:
                direction = right(previousDirection);
                break;
            case CROSS:
                direction = previousDirection;
                break;
            case STARTPOINT:
                direction = previousDirection;
                break;
            case ENDPOINT:
                direction = previousDirection;
                break;
        }
           return direction;
    }

    private static Direction left(Direction dir) {
       Direction newdir = null;
        switch (dir) {

            case NORTH:
                newdir = Direction.WEST;
                break;
            case EAST:
                newdir = Direction.NORTH;
                break;
            case SOUTH:
                newdir = Direction.EAST;
                break;
            case WEST:
                newdir = Direction.SOUTH;
                break;
        }
        return newdir;
    }
    private static Direction right(Direction dir) {
        Direction newdir = null;
        switch (dir) {

            case NORTH:
                newdir = Direction.EAST;
                break;
            case EAST:
                newdir = Direction.SOUTH;
                break;
            case SOUTH:
                newdir = Direction.WEST;
                break;
            case WEST:
                newdir = Direction.NORTH;
                break;
        }
        return newdir;
    }
    private static Direction opposite(Direction dir) {
        return left(left(dir));
    }
}
