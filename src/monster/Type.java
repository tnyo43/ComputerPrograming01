package monster;

public enum Type {
    FIRE, WATER, HURRICANE, HOLY, DARK;

    @Override
    public String toString() {
        switch (this) {
            case FIRE:
                return "火";
            case WATER:
                return "水";
            case HURRICANE:
                return "風";
            case HOLY:
                return "光";
            case DARK:
                return "闇";
            default:
                return "";
        }
    }
}
