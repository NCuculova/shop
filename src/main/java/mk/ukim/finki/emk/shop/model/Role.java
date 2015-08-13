package mk.ukim.finki.emk.shop.model;

/**
 * Created by Nadica-PC on 8/11/2015.
 */
public enum Role {
    ADMIN {
        @Override
        public String getSimpleName() {
            return "ADMIN";
        }
    },
    USER {
        @Override
        public String getSimpleName() {
            return "USER";
        }
    };

    public String getSimpleName() {
        return null;
    }
}
