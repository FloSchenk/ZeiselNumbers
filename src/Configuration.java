public enum Configuration {
    instance;
    int maximumNumberOfThreads = Runtime.getRuntime().availableProcessors();
    long maximum = 500000000;
    long minimun = 2;
}