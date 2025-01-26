package com.desolatetimelines.acct.catalog.ws.model;

/**
 * Container for the basic properties of an icon, which can be returned
 * by the Icons endpoint
 *
 * @param iconUUID the unique identifier of the icon across the ACCT ecosystem
 * @param iconName the human-readable name that uniquely identifies the icon in its category
 */
public record IconProperties(
    String iconUUID,
    String iconName
) {

    public static IconPropertiesBuilder builder() {
        return new IconPropertiesBuilder();
    }

    public static final class IconPropertiesBuilder {
        private String iconUUID;
        private String iconName;

        private IconPropertiesBuilder() {
        }

        public IconPropertiesBuilder withIconUUID(String iconUUID) {
            this.iconUUID = iconUUID;
            return this;
        }

        public IconPropertiesBuilder withIconName(String iconName) {
            this.iconName = iconName;
            return this;
        }

        public IconProperties build() {
            return new IconProperties(iconUUID, iconName);
        }
    }
}
