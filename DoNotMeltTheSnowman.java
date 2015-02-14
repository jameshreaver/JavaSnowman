public class DoNotMeltTheSnowman {

  int lev = 0;

  public static void main(String[] args) {
    int lev = Integer.parseInt(args[0]);
    Level level = Levels.getLevels()[lev];
    char[] d = level.getCharArray();
    int w = level.getWidth();
    int h = level.getHeight();
    Board board 
      = new Board(PieceUtils.charsToPieces(d, w, h));
    System.out.println("DO NOT MELT THE SNOWMAN");
    System.out.println("A game for the few.");
    System.out.println("=======================");
    while (true) {
      Result r = board.fireLaser();
      board.renderBoard();
      if (r == Result.HIT_TARGET) {
        System.out.println("Congratulations! You completed the level.");
        break;
      }
      if (r == Result.MELT_SNOWMAN) {
        System.out.println("You melted the snowman. Start again.");
        break;
      }
      System.out.println("Please enter the row then the column" 
                        + " of the piece you'd like to rotate.");
      int row = IOUtil.readInt();
      int col = IOUtil.readInt();
      Coordinate c = new Coordinate(col, row);
      board.rotatePiece(c);
      board.clearLasers();
    }
  }

}
