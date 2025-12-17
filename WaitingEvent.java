class WaitingEvent extends Event {
    private final Server s;
    private final int queueNum;
    private final double arrivalTime;

    WaitingEvent(Customer c, Server s, int queueNum, double arrivalTime, double eventTime) {
        super(c, eventTime);
        this.s = s;
        this.queueNum = queueNum;
        this.arrivalTime = arrivalTime;
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        if (this.queueNum == 1) {
            return shop.findServer(this.s)
                    .map(s2 -> s2.serveQueue(this.c))
                    .map(st -> new Pair<Event,Shop>(
                        new ServeEvent(this.c, st.t(), this.arrivalTime, this.eventTime, st.u()),
                        shop.update(st.t())
                    ));
        }
        return shop.findServer(this.s)
                .map(s2 -> new Pair<Event,Shop>(
                    new WaitingEvent(this.c, s2, this.queueNum - 1, this.arrivalTime,
                        s2.getTimeBusyUntil()),
                    shop
                ));
    }

    public Pair<Double,String> getStats() {
        return new Pair<Double,String>(
                0.0,
                ""
        );
    }

    public String toString() {
        return "";
    }
}