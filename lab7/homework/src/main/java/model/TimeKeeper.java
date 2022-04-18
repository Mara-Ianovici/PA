package model;

import java.time.Instant;
import java.util.List;

public class TimeKeeper implements Runnable {
    private final Game game;
    public static long currentTime = Instant.now().getEpochSecond();

    TimeKeeper(Game game){
        this.game = game;
    }

    public void run()
    {
        if(Thread.currentThread().isDaemon())
        {
            while(true)
            {
                if(Instant.now().getEpochSecond() - currentTime > 60)
                {
                    List<Player> players = game.getPlayers();

                    System.out.println("Game over.");

                    int maxScore = players.get(0).getScore();
                    int maxScoreId = 0;

                    for(int index = 1; index < 3 ; index++)
                        if(maxScore < players.get(index).getScore())
                        {
                            maxScore = players.get(index).getScore();
                            maxScoreId = players.get(index).getId();
                        }

                    System.out.println("Maximum score is: " + maxScore + " and the winner is " + maxScoreId);
                    System.exit(1);
                }
            }
        }
    }
}
