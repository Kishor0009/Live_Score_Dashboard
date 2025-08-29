// Ball-by-ball update code
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MatchUpdater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Connected to DB!");

            System.out.print("Enter Match ID: ");
            int matchId = sc.nextInt();

            while (true) {
                System.out.print("Over Number: ");
                int over = sc.nextInt();

                System.out.print("Ball Number (1-6): ");
                int ball = sc.nextInt();

                System.out.print("Runs scored (0,1,2,3,4,6): ");
                int runs = sc.nextInt();

                System.out.print("Wicket? (1 for Yes, 0 for No): ");
                int wicket = sc.nextInt();

                String event = "";
                if (runs == 4) event = "FOUR";
                else if (runs == 6) event = "SIX";
                else if (wicket == 1) event = "WICKET";

                String sql = "INSERT INTO balls (match_id, over_no, ball_no, runs, wicket, event) VALUES (?,?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, matchId);
                stmt.setInt(2, over);
                stmt.setInt(3, ball);
                stmt.setInt(4, runs);
                stmt.setBoolean(5, wicket == 1);
                stmt.setString(6, event);
                stmt.executeUpdate();

                System.out.println("Ball updated: Over " + over + " Ball " + ball + " Runs=" + runs + " Wicket=" + wicket + " Event=" + event);

                System.out.print("Continue? (y/n): ");
                String choice = sc.next();
                if (choice.equalsIgnoreCase("n")) break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
