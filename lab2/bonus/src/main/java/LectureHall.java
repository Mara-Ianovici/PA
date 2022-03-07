public class LectureHall extends Room {
    private boolean hasProjector;

    public LectureHall(String name, int capacity, boolean hasProjector) {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    public boolean isHaveProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

}
