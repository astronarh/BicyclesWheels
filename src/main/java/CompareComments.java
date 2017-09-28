import java.util.*;

public class CompareComments {
    public static List<Object> compare(Map<String, String> presentComments, Map<String, String> newComments) {
        List<Object> objectList = new ArrayList<>();
        String user = null;
        Map<String, String> selectedComments = new HashMap<>();
        Iterator iteratorNewComments = newComments.entrySet().iterator();
        while (iteratorNewComments.hasNext()) {
            Map.Entry entryNewComments = (Map.Entry) iteratorNewComments.next();
            if (!presentComments.containsKey(entryNewComments.getKey())) {
                user = (String) entryNewComments.getValue();
                selectedComments.put((String) entryNewComments.getKey(), (String) entryNewComments.getValue());
            }
        }
        objectList.add(user);
        objectList.add(selectedComments);
        return objectList;
    }
}
