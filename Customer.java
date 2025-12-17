class Customer implements Comparable<Customer> {
    private static final double EPSILON = 1E-15;
    private final int id;
    private final double arrivalTime;

    Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime; 
    }

    public double getTimeAfter(double time) {
        return this.arrivalTime + time;
    }

    public boolean canBeServed(double time) {
        return this.arrivalTime + EPSILON >= time;
    }

    @Override
    public int compareTo(Customer other) {
        return (Math.abs(this.arrivalTime - other.arrivalTime) < EPSILON)
                ? 0
                : (this.arrivalTime < other.arrivalTime) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "customer " + id;
    }
}