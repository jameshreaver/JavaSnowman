public class PieceUtils {

  public static Piece charToPiece(char c) {
    switch (c) {
      case '^' : return Piece.EMITTER_NORTH;
      case '>' : return Piece.EMITTER_EAST;
      case 'v' : return Piece.EMITTER_SOUTH;
      case '<' : return Piece.EMITTER_WEST;
      case '|' : return Piece.LASER_VERTICAL;
      case '-' : return Piece.LASER_HORIZONTAL;
      case '+' : return Piece.LASER_CROSSED;
      case '/' : return Piece.MIRROR_SW_NE;
      case '\\': return Piece.MIRROR_NW_SE;
      case '#' : return Piece.WALL;
      case 'o' : return Piece.TARGET;
      case ' ' : return Piece.EMPTY;
      case '@' : return Piece.SNOWMAN;
      default  : return null;
    }
  }

  public static Piece[][] charsToPieces(char[] description,
                                        int width, int height) {
    assert description.length == width * height;
    Piece[][] result = new Piece[width][height];
    int count = 0;
    for (int h = height-1; h >= 0; h--) {
      for (int w = 0; w < width; w++) { 
        result[w][h] = charToPiece(description[count]);
        count++;
      }
    }
    return result; 
  }

  public static boolean isEmitter(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
      case EMITTER_EAST:
      case EMITTER_SOUTH:
      case EMITTER_WEST:
        return true;
    }

    return false;
  }

  public static Coordinate findEmitter(Piece[][] pieces) {
    for (int h = 0; h < pieces.length; h++) {
      for (int w = 0; w < pieces[h].length; w++) {
        if (isEmitter(pieces[w][h])) {
          return new Coordinate(w, h);
        }
      }
    }
    return null; 
  }

  public static Piece hideLaser(Piece p) {
    switch (p) {
      case LASER_VERTICAL:
      case LASER_HORIZONTAL:
      case LASER_CROSSED:
        return Piece.EMPTY;
    }
    return p;
  }

  public static Piece addLaser(Piece p, boolean isHorizontal) {
    switch (p) {
      case EMPTY :
        return (isHorizontal) ? Piece.LASER_HORIZONTAL
                              : Piece.LASER_VERTICAL;
      case LASER_VERTICAL :
        return (isHorizontal) ? Piece.LASER_CROSSED
                              : p;
      case LASER_HORIZONTAL :
        return (isHorizontal) ? p
                              : Piece.LASER_CROSSED;
    }
    return p;
  }

  public static Coordinate move(Piece p, Coordinate c, int xo, int yo) {
    assert -1 <= xo && xo <= 1 && -1 <= yo && yo <= 1;
    int x = c.getX();
    int y = c.getY();
    switch (p) {
      case EMITTER_NORTH: return new Coordinate(x, y+1);
      case EMITTER_EAST:  return new Coordinate(x+1, y);
      case EMITTER_SOUTH: return new Coordinate(x, y-1);
      case EMITTER_WEST:  return new Coordinate(x-1, y);
      case MIRROR_SW_NE: return new Coordinate(x+yo, y+xo);
      case MIRROR_NW_SE: return new Coordinate(x-yo, y-xo);
      case EMPTY:
      case LASER_VERTICAL:
      case LASER_HORIZONTAL:
      case LASER_CROSSED: return new Coordinate(x+xo, y+yo);
      default: return c;
    }
  }

  public static Piece rotate(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
        return Piece.EMITTER_EAST;
      case EMITTER_EAST:
        return Piece.EMITTER_SOUTH;
      case EMITTER_SOUTH:
        return Piece.EMITTER_WEST;
      case EMITTER_WEST:
        return Piece.EMITTER_NORTH;
      case MIRROR_SW_NE:
        return Piece.MIRROR_NW_SE;
      case MIRROR_NW_SE:
        return Piece.MIRROR_SW_NE;
    }
    return p;
  }

}
