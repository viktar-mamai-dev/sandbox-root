package part1.week3.iq;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Shuffling a linked list. Given a singly-linked list containing n items, rearrange the items
 * uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra
 * memory and run in time proportional to n * log(n) in the worst case
 */
public class Task3 {

  private void randomShuffle(List<Integer> list, List<Integer> aux, int low, int mid, int high) {
    int i = low;
    int j = mid + 1;
    for (int k = low; k <= high; k++) {
      if (i > mid) {
        aux.set(k, list.get(j++));
      } else if (j > high) {
        aux.set(k, list.get(i++));
      } else if (new Random().nextBoolean()) { // choose either from left subarray or from the right
        aux.set(k, list.get(j++));
      } else {
        aux.set(k, list.get(i++));
      }
    }
  }

  private void shuffleLinkedList(List<Integer> list, List<Integer> aux, int low, int high) {
    if (low >= high) {
      return;
    }

    int mid = (low + high) / 2;
    shuffleLinkedList(aux, list, low, mid);
    shuffleLinkedList(aux, list, mid + 1, high);
    randomShuffle(list, aux, low, mid, high);
  }

  public List<Integer> shuffleLinkedList(List<Integer> list) {
    List<Integer> shufflesList = new LinkedList<>(list);
    shuffleLinkedList(list, new LinkedList<>(list), 0, list.size() - 1);
    return shufflesList;
  }
}
