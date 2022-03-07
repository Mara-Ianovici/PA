public interface Identifiable {
    default String getIP() {
        return "no IP";
    }
}
