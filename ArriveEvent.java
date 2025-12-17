class ArriveEvent extends Event {
    
    ArriveEvent(Customer c, double eventTime) {
        super(c, eventTime);
    }

    public Maybe<Pair<Event,Shop>> next(Shop shop) {
        return Maybe.<Pair<Event,Shop>>of(
                shop.findServer(this.c)
                    .map(s -> s.serve(this.c))
                    .map(st -> new Pair<Event,Shop>(
                        new ServeEvent(this.c, st.t(), this.eventTime, this.eventTime, st.u()),
                        shop.update(st.t())
                    ))
                    .orElse(shop.findQueueToJoin(this.c)
                        .map(s -> s.queue(this.c))
                        .map(sn -> new Pair<Event,Shop>(
                            new WaitEvent(this.c, sn.t(), sn.u(), this.eventTime, this.eventTime),
                            shop.update(sn.t())
                        ))
                        .orElse(new Pair<Event,Shop>(
                            new LeaveEvent(this.c, eventTime),
                            shop
                        ))
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
        return String.format("%.3f", this.eventTime) + " " + this.c + " arrives";
    }
}
