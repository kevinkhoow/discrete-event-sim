class ServeEvent extends Event {
    private final Server s;
    private final double arrivalTime;
    private final double endTime;

    ServeEvent(Customer c, Server s, double arrivalTime, double eventTime, double endTime) {
        super(c, eventTime);
        this.s = s;
        this.arrivalTime = arrivalTime;
        this.endTime = endTime;
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        return Maybe.<Pair<Event,Shop>>of(
                new Pair<Event,Shop>(
                    new DoneEvent(this.c, this.s, this.endTime),
                    shop
                )
        );
    }

    public Pair<Double,String> getStats() {
        return new Pair<Double,String>(
                this.eventTime - this.arrivalTime,
                "served"
        );
    }

    public String toString() {
        return String.format("%.3f", this.eventTime) + " " 
                + this.c + " serves by " + this.s;  
    }
}