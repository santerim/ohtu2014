package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
        
        System.out.println("------------------------------------");
        System.out.println("Has at least 10 goals AND 10 assists");
        System.out.println("------------------------------------");
        
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Has fewer than 10 goals AND 10 assists");
        System.out.println("--------------------------------------");
        
        Matcher m2 = new And( new HasFewerThan(10, "goals"),
                             new HasFewerThan(10, "assists"),
                             new PlaysIn("PHI")
        );
        
        for (Player player : stats.matches(m2)) {
            System.out.println( player );
        }
        
        System.out.println("");
        System.out.println("-----------------------------------");
        System.out.println("Has at least 50 goals OR 50 assists");
        System.out.println("-----------------------------------");
        
        Matcher m3 = new Or( new HasAtLeast(50, "goals"),
                             new HasAtLeast(50, "assists")
        );
        
        for (Player player : stats.matches(m3)) {
            System.out.println( player );
        }
        
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("Has NO player with fewer than 50 goals");
        System.out.println("--------------------------------------");
        
        Matcher m4 = new Not( new HasAtLeast(50, "goals")
        );
        
        for (Player player : stats.matches(m4)) {
            System.out.println( player );
        }
        
    }
}
