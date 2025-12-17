import java.util.function.Supplier;

class Shop {
    private final InfList<Server> ifl;
    private final Supplier<Double> serviceTime;

    Shop(int noOfServers, Supplier<Double> serviceTime, int qmax) {
        this.ifl = InfList.iterate(1, x -> x + 1)
                .limit(noOfServers)
                .map(x -> new Server(x, serviceTime, qmax));
        this.serviceTime = serviceTime;
    }

    private Shop(InfList<Server> ifl, Supplier<Double> serviceTime) {
        this.ifl = ifl;
        this.serviceTime = serviceTime;
    }
    
    public Maybe<Server> findServer(Customer c) {
        return this.ifl
                .filter(s -> s.canServe(c))
                .findFirst();
    }

    public Maybe<Server> findServer(Server s) {
        return this.ifl
                .filter(x -> x.isSameServer(s))
                .findFirst();
    }

    public Maybe<Server> findQueueToJoin(Customer c) {
        return this.ifl
                .filter(s -> !s.isQueueFull())
                .findFirst();
    }

    public Shop update(Server s) {
        return new Shop(this.ifl
                    .map(x -> x.isSameServer(s) ? s : x),
                this.serviceTime
                );
    }

    @Override
    public String toString() {
        return this.ifl.map(s -> "<" + s + ">") 
                .reduce("Shop:", (x, y) -> x + y);
    }
}
