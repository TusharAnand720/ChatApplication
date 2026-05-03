package chatapp.utility.constants;

public class Constants {

    public enum Commons{
        ED("ED");

        private final String name;

        Commons(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public  enum EntityTypes {
        ROOM() ;

        private String entityType;

        EntityTypes() {
        }
    }
}
