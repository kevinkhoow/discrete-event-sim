import java.util.function.Supplier;

class Server {
    private static final double START_TIME = 0.0;
    private static final double EPSILON = 1E-15;
    private final int id;
    private final Supplier<Double> serviceTime;
    private final int qlength;
    private final int qmax;
    private final double timeBusyUntil;

    Server(int id, Supplier<Double> serviceTime, int qmax) {
        this.id = id;
        this.serviceTime = serviceTime;
        this.qlength = 0;
        this.qmax = qmax;
        this.timeBusyUntil = START_TIME;
    }

    private Server(int id, Supplier<Double> serviceTime, int qlength, int qmax,
            double timeBusyUntil) {
        this.id = id;
        this.serviceTime = serviceTime;
        this.qlength = qlength;
        this.qmax = qmax;
        this.timeBusyUntil = timeBusyUntil;
    }

    public Pair<Server,Double> serve(Customer c) {
        double timeServiceEnds = c.getTimeAfter(this.serviceTime.get());
        return new Pair<Server,Double>(
                new Server(this.id, this.serviceTime, this.qlength, this.qmax, timeServiceEnds),
                timeServiceEnds
        );
    }

    public Pair<Server,Double> serveQueue(Customer c) {
        double timeServiceEnds = this.timeBusyUntil + this.serviceTime.get();
        return new Pair<Server,Double>(
                new Server(this.id, this.serviceTime, this.qlength - 1, this.qmax, timeServiceEnds),
                timeServiceEnds
        );
    }

    public Pair<Server,Integer> queue(Customer c) {
        return new Pair<Server,Integer>(
                new Server(id, this.serviceTime, this.qlength + 1, this.qmax, this.timeBusyUntil),
                this.qlength + 1
        );   
    }

    public boolean canServe(Customer c) { 
        return c.canBeServed(this.timeBusyUntil); 
    }

    public double getTimeBusyUntil() {
        return this.timeBusyUntil;
    }

    public boolean isSameServer(Server s) {
        return this.id == s.id;
    }

    public boolean isQueueFull() {
        return this.qlength == this.qmax;
    }

    @Override
    public String toString() {
        return "server " + id;
    }
}