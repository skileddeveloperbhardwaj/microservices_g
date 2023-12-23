import java.util.*;

public class Solution_2 {


    /*
Task --> id, list of dependent tasks,weightage
t1 -> t2,t3,1; res = 1
t2 -> t4,t5,2; res = 3
t3 -> t6,t7,3;res = 6
t4 -> 4;res = 10
t5 -> 5;res =15
t6 -> 6; res=21
t7 -> 7;res=28
*/
    public static long totalWeightage(List<Task> tasks, String id) {
        long res = 0l;
        Map<String, Task> idTaskMap = new HashMap<>();
        for (Task t : tasks) {
            idTaskMap.put(t.taskId, t);
        }
        Task rootTask = idTaskMap.getOrDefault(id, new Task());
        if (rootTask.taskId == null || rootTask.taskId.length() == 0) return 0;

        Queue<String> q = new LinkedList<>();
        q.add(id);

        while (!q.isEmpty()) {
            String taskId = q.remove();
            Task t = idTaskMap.get(taskId);
            List<String> dependentTaskIds = t.dependentTaskIds;
            res += t.weightage;
            q.addAll(dependentTaskIds);
        }

        return res;
    }
}

/*
Task --> id, list of dependent tasks,weightage
t1 -> t2,t3,1
t2 -> t4,t5,2
t3 -> t6,t7,3
t4 -> 4
t5 -> 5
t6 -> 6
t7 -> 7

func(List<Task>, id)


 */

class Task {
    String taskId;
    List<String> dependentTaskIds;
    int weightage;
}
