import java.util.List;

public class Solution {

    /*
    l1 -> 1,10
    l2 -> 2,3,4
    l3 -> 5,6,7
    l4 -> 3,11,12,13
     head --> (l1,l2) = 1,2,3,4,10
     head --> (head, l3) --> 1,2,3,4,5,6,7,10
     head --> (head,l4) --> 1,2,3,3,4,5,6,7,10,11,12,13

     head
     */
    public static LinkedNode mergeList(List<LinkedNode> headList) {
        LinkedNode head = mergeList(headList.get(0), headList.get(1));

        for (int i=2;i<headList.size();i++) {
            head = mergeList(head, headList.get(i));\
        }

        return head;
    }

    private static LinkedNode mergeList(LinkedNode l0, LinkedNode l1) {
        LinkedNode mergedNode = new LinkedNode();
        while (l0 != null || l1 != null) {
            if (l0 == null) {
                mergedNode.next = l1;
                l1 = l1.next;
            } else if (l1 == null) {
                mergedNode.next = l0;
                l0 = l0.next;
            } else if (l0.data > l1.data) {
                mergedNode.next = l1;
                l1 = l1.next;
            } else {
                mergedNode.next = l0;
                l0 = l0.next;
            }
        }

        return mergedNode.next;
    }
}

class LinkedNode {
    int data;
    LinkedNode next;
}

/*
Snake & Ladder
Game --> id, Board, List<Player>, timestamp, Map<Player, PlayerPosition>
Board --> size, List<Snake>, List<Ladder>
interface Element --> start end
Snake implem ele
Ladder imple ele
Player -> id, name, email,
Dice --> count

PlayerPosition --> player_id, currPos


Service --> logic

 */


/*
List<LinkedNode>
each list is sorted
waf
 */