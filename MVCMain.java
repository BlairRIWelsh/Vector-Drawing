public class MVCMain {
    public static void main(String[] args) {
        // create Model
        DrawModel model = new DrawModel();

        // Create View (GUI)
        new DrawDelegate(model);
    }
}
