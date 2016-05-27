import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class TravelDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.travel.daily.traveldaily.dao");
        schema.enableKeepSectionsByDefault();
        createScenery(schema);
        createHotel(schema);
        createDelicacy(schema);
        createBill(schema);
        createAccount(schema);
        createImage(schema);
        createBillList(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void createScenery(Schema schema) {
        Entity entity = schema.addEntity("SceneryBean");
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("imgUrl");
        entity.addStringProperty("title");
        entity.addStringProperty("detail");
        entity.addFloatProperty("price");
    }

    private static void createHotel(Schema schema) {
        Entity entity = schema.addEntity("HotelBean");
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("imgUrl");
        entity.addStringProperty("title");
        entity.addStringProperty("detail");
        entity.addFloatProperty("price");
    }

    private static void createDelicacy(Schema schema) {
        Entity entity = schema.addEntity("DelicacyBean");
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("imgUrl");
        entity.addStringProperty("title");
        entity.addStringProperty("detail");
        entity.addFloatProperty("price");
    }

    private static void createBill(Schema schema) {
        Entity entity = schema.addEntity("BillBean");
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("imgUrl");
        entity.addStringProperty("detail");
        entity.addFloatProperty("price");
        entity.addLongProperty("time");
        entity.addStringProperty("name");
        entity.addStringProperty("subBill");
    }

    private static void createBillList(Schema schema) {
        Entity entity = schema.addEntity("BillList");
        entity.addStringProperty("key").primaryKey();
        entity.addStringProperty("billList");
    }

    private static void createAccount(Schema schema) {
        Entity entity = schema.addEntity("AccountBean");
        entity.addLongProperty("id").primaryKey().autoincrement();
        entity.addStringProperty("imgUrl");
        entity.addStringProperty("name");
        entity.addStringProperty("detail");
    }

    private static void createImage(Schema schema) {
        Entity entity = schema.addEntity("ImageBean");
        entity.addStringProperty("imgUrl").primaryKey();
        entity.addByteArrayProperty("pic");
    }
}
