package ohtu;

public class TennisGame {

    private int gameScoreP1 = 0;
    private int gameScoreP2 = 0;
    private final String player1Name;
    private final String player2Name;
    private final int advantageThreshold = 4;
    private final String[] scoreStrings = {"Love", "Fifteen", "Thirty", "Forty"};
    private final String[] evenScoreStrings = {"Love-All", "Fifteen-All", "Thirty-All", "Forty-All", "Deuce"};
    private final String[] advantageOrWinStrings = {"Advantage player1", "Advantage player2", "Win for player1", "Win for player2"};

    public static void main(String[] args) {
        TennisGame game = new TennisGame("player1", "player2");
        
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player2");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());

        game.wonPoint("player1");
        System.out.println(game.getScore());
    }
    
    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            gameScoreP1 += 1;
        } else {
            gameScoreP2 += 1;
        }
    }

    private String getEvenScoreAsString() {
        return evenScoreStrings[gameScoreP1];
    }
    
    private int getDifferenceInScore() {
        return gameScoreP1 - gameScoreP2;
    }
    
    private String getAdvantageOrWinAsString() {
        int differenceInScore = getDifferenceInScore();
            if (differenceInScore == 1) {
                return advantageOrWinStrings[0]; //advanatage p1
            } else if (differenceInScore == -1) {
                return advantageOrWinStrings[1]; // advantage p2
            } else if (differenceInScore >= 2) {
                return advantageOrWinStrings[2]; // win for p1
            } else {
                return advantageOrWinStrings[3]; // win for p2
            }
    }
    
    private String getLowerScoresAsString() {
        return scoreStrings[gameScoreP1] + "-" + scoreStrings[gameScoreP2];
    }
    
    public String getScore() {
        if (gameScoreP1 == gameScoreP2) {
            return getEvenScoreAsString();   
        } else if (gameScoreP1 >= advantageThreshold || gameScoreP2 >= advantageThreshold) {
            return getAdvantageOrWinAsString();
        } else {
            return getLowerScoresAsString();
        }
    }
}
