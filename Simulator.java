import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final int numOfCustomers;
    private final InfList<Pair<Integer,Double>> arrivals;
    private final Supplier<Double> serviceTime;
    
    Simulator(int numOfServers, int qmax, Supplier<Double> serviceTime, int numOfCustomers,
            InfList<Pair<Integer,Double>> arrivals) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
        this.serviceTime = serviceTime;
    }

    public Maybe<Pair<String,String>> run() {
        PQ<Event> pq = this.arrivals
                .map(x -> new ArriveEvent(
                    new Customer(x.t(), x.u()),
                    x.u()
                ))
                .reduce(new PQ<Event>(), (q,c) -> q.add(c));
        return InfList.iterate(
                    new State(pq, new Shop(this.numOfServers, this.serviceTime, this.qmax)),
                    s -> s.next()
                )
                .takeWhile(state -> !state.isEmpty())
                .reduce((x,y) -> y)
                .map(state -> new Pair<>(state.toString(), state.getStats()));
    }
}