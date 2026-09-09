public class scoreTracker
{
    private static int score = 0;
    public scoreTracker()
    {
    }
    public static int getScore()
    {
        return score;
    }
    public static void setScore(int newScore)
    {
        score = newScore;
    }
}
