package singleton;

public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

    public SerializObj getSerializObj() {
        return new SerializObj();
    }
}
