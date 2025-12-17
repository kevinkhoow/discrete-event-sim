class State {
    private final PQ<Event> pq;
    private final Shop shop;
    private final String log;
    private final double avgWaitTime;
    private final int numServed;
    private final int numLeft;
    private final boolean emptyFlag;

    State(PQ<Event> pq, Shop shop) {
        this.pq = pq;
        this.shop = shop;
        this.log = "";
        this.avgWaitTime = 0;
        this.numServed = 0;
        this.numLeft = 0;
        this.emptyFlag = false;
    }

    private State(PQ<Event> pq, Shop shop, String log, double avgWaitTime,
            int numServed, int numLeft, boolean emptyFlag) {
        this.pq = pq;
        this.shop = shop;
        this.log = log;
        this.avgWaitTime = avgWaitTime;
        this.numServed = numServed;
        this.numLeft = numLeft;
        this.emptyFlag = emptyFlag;
    }

    public State next() {
        return this.pq.poll()
                .t()
                .flatMap(e -> e.next(shop))
                .map(pair -> new State(
                    this.pq.poll().u().add(pair.t()),
                    pair.u(),
                    this.log + this.pq.poll().t().map(e -> e.toString() + "\n").orElse(""),
                    this.updateAvgWaitTime(),
                    this.updateNumServed(),
                    this.updateNumLeft(),
                    this.pq.isEmpty()
                ))
                .orElse(new State(
                    this.pq.poll().u(),
                    this.shop,
                    this.log + this.pq.poll().t().map(e -> e.toString() + "\n").orElse(""),
                    this.updateAvgWaitTime(),
                    this.updateNumServed(),
                    this.updateNumLeft(),
                    this.pq.isEmpty()
                ));
    }

    private double updateAvgWaitTime() {
        return this.pq.poll()
                .t()
                .map(e -> e.getStats())
                .filter(ts -> ts.u().equals("served"))
                .map(ts -> ts.t())
                .map(t -> (t + this.avgWaitTime * this.numServed) / (this.numServed + 1))
                .orElse(this.avgWaitTime);
    }

    private int updateNumServed() {
        return this.pq.poll()
                .t()
                .map(e -> e.getStats().u())
                .filter(str -> str.equals("served"))
                .map(str -> this.numServed + 1)
                .orElse(this.numServed);
    }

    private int updateNumLeft() {
        return this.pq.poll()
                .t()
                .map(e -> e.getStats().u())
                .filter(str -> str.equals("left"))
                .map(str -> this.numLeft + 1)
                .orElse(this.numLeft);
    }

    public String getStats() {
        return "[" + String.format("%.3f", this.avgWaitTime) + " " + this.numServed + " "
                + this.numLeft + "]";
    }

    public boolean isEmpty() {
        return this.emptyFlag;
    }

    @Override
    public String toString() {
        return this.log;
    }
}
