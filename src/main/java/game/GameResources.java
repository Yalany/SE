package game;

import java.util.HashMap;
import java.util.Map;

public final class GameResources {

    private final Map<String, GameResource> resources;

    private GameResources(Map<String, GameResource> resources) {
        this.resources = resources;
    }

    String getDisplayName(String key) {
        return resources.get(key).getDisplayName();
    }

    public int getAmount(String key) {
        return resources.get(key).getValue();
    }

    void modifyAmount(String key, int modification) {
        resources.get(key).modifyValue(modification);
    }

    public static Builder builder() {
        return new Builder();
    }

    private static final class GameResource {
        private final String displayName;
        private int value;

        GameResource(String displayName, int value) {
            this.displayName = displayName;
            this.value = value;
        }

        String getDisplayName() {
            return displayName;
        }

        int getValue() {
            return value;
        }

        void modifyValue(int addition) {
            this.value += addition;
        }
    }

    public static final class Builder {

        private final HashMap<String, GameResource> resources;

        private Builder() {
            resources = new HashMap<>();
        }

        public Builder addResource(String key, String displayName, int startingValue) {
            resources.put(key, new GameResource(displayName, startingValue));
            return this;
        }

        public GameResources build() {
            return new GameResources(resources);
        }
    }
}
