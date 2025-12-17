class DoneEvent extends Event {
    private final Server s;

    DoneEvent(Customer c, Server s, double eventTime) {
        super(c, eventTime);
        this.s = s;
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        return Maybe.<Pair<Event,Shop>>empty();
    }

    public Pair<Double,String> getStats() {
        return new Pair<Double,String>(
                0.0,
                ""
        );
    }
    
    public String toString() {
        return String.format("%.3f", eventTime) + " " + this.c + " done";
    }
}