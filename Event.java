abstract class Event implements Comparable<Event> {
    private static final double EPSILON = 1E-15;
    protected final double eventTime; 
    protected final Customer c;

    Event(Customer c, double eventTime) {
        this.eventTime = eventTime;
        this.c = c;
    }

    public abstract Maybe<Pair<Event,Shop>> next(Shop shop);

    public abstract Pair<Double,String> getStats();

    @Override
    public int compareTo(Event other) {
        if (Math.abs(this.eventTime - other.eventTime) > EPSILON) {
            return Double.compare(this.eventTime, other.eventTime);
        }
        return this.c.compareTo(other.c);
    }

    @Override
    public abstract String toString();
}
