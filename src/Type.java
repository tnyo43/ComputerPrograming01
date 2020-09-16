public enum Type {
    Fire, Water, Hurricane, Holy, Dark;

    @Override
    public String toString() {
        switch (this) {
            case Fire:
                return "火";
            case Water:
                return "水";
            case Hurricane:
                return "風";
            case Holy:
                return "光";
            case Dark:
                return "闇";
        }
        return "";
    }
}
