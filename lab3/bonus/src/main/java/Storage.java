public interface Storage {
    default int getStorage(StorageUnits unit, int storage)
    {
        if(unit == StorageUnits.BYTES)
            return storage * 1024 ^ 2;

        if(unit == StorageUnits.MEGABYTES)
            return storage * 1024;

        return storage; //default(GB)
    };

}
