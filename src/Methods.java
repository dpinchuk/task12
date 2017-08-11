
public interface Methods {

    default String[] getData(String listNote) {
        return listNote.split("\\|");
    }

    void print();

}