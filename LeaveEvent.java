class LeaveEvent extends Event {

    LeaveEvent(Customer c, double eventTime) {
        super(c, eventTime);
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        return Maybe.<Pair<Event,Shop>>empty();
    }
  
    public Pair<Double,String> getStats() {
        return new Pair<Double,String>(
                0.0,
                "left"
        );
    }

    public String toString() {
        return String.format("%.3f", eventTime) + " " + this.c + " leaves";
    }
}
