class WaitEvent extends Event {
    private final Server s;
    private final int queueNum;
    private final double arrivalTime;

    WaitEvent(Customer c, Server s, int queueNum, double arrivalTime, double eventTime) {
        super(c, eventTime);
        this.s = s;
        this.queueNum = queueNum;
        this.arrivalTime = arrivalTime;
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        return Maybe.<Pair<Event,Shop>>of(
                new Pair<Event,Shop>(
                    new WaitingEvent(this.c, this.s, this.queueNum, this.arrivalTime,
                        this.s.getTimeBusyUntil()),
                    shop
                )
        );
    }

    public Pair<Double,String> getStats() {
        return new Pair<Double,String>(
                0.0,
                ""
        );
    }

    public String toString() {
        return String.format("%.3f", eventTime) + " " + this.c + " waits at " + this.s;
    }
}